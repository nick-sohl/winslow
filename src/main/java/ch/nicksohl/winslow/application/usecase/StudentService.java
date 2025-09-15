package ch.nicksohl.winslow.application.usecase;

// Application
import ch.nicksohl.winslow.application.cqrs.CreateStudentCommand;
import ch.nicksohl.winslow.application.cqrs.StudentDto;
import ch.nicksohl.winslow.application.port.StudentRepositoryInterface;
import ch.nicksohl.winslow.domain.Department;
import ch.nicksohl.winslow.domain.student.Password;
import ch.nicksohl.winslow.domain.student.Student;
import jakarta.persistence.EntityManager;

// Java SDK
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepositoryInterface studentRepository;
    private final EntityManager entityManager;

    /*
    The application-layer is independent from the "outside". It doesn't no anything about the infrastructure or the presentation.
    To get access to data, we need to use the Adapter, which is implementing the JpaRepository.
    To use its methods to communicate with the JpaRepo and further with the DB, we need to use DI (dependency injection),
    to inject the Adapter from the "outside" infrastructure, into the usecase (service) inside the application-layer.
    For that we use a Config-File inside the infrastructure to create the Service as a "Bean" in the Spring IOC-Container.
    Inside the Service-Class, we use the Constructor (Constructor Injection) to define the StudentRepositoryInterface as an Parameter.
    In the Config-File, we use this Parameter, to inject the Adapter as an Argument.
    We use the Liskov-Substituion-Priciple -> Each Class that implements an Interface (Adapter implements StudentRepoInterface)
    can be implemented, where the Interface is used. In that case, we substitute the interface with the class implementing it.
    */

    public StudentService(StudentRepositoryInterface studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    // Return List with Objects of type StudentDto
    public List<StudentDto> getStudents() {
        List<Student> listOfStudents = studentRepository.students(); // Use method of adapter to return students

        // Transform all Students in List<Student> into a StudentDto and put them into a new List<StudentDto>
        List<StudentDto> listOfStudentDtos = new ArrayList<>();
        for (Student student : listOfStudents) {
            // Use getters from Model to get its properties
            StudentDto studentDto = new StudentDto(
                    student.getId(),
                    student.getFirstname(),
                    student.getLastname(),
                    student.getEmail(),
                    student.getDepartment() != null ? student.getDepartment().getName() : null
            );
            // Add Dto to the new List
            listOfStudentDtos.add(studentDto);
        }
        return listOfStudentDtos;
    }

    // Get Student by ID -> Transform student object into DTO -> Return DTO
    public StudentDto getStudent(int studentId) {
        // Optional to prevent NullPointerException if Student object is Null
        Optional<Student> student = studentRepository.getStudent(studentId);

        // Do not include Password
        return student.map(s -> new StudentDto(
                s.getId(),
                s.getFirstname(),
                s.getLastname(),
                s.getEmail(),
                s.getDepartment() != null ? s.getDepartment().getName() : null
        )).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // Delete Student object
    public void deleteStudent(int studentId) {
        studentRepository.deleteStudent(studentId);
    }

    // Create new Student
    public StudentDto addStudent(CreateStudentCommand request) {
        // Create new Student object
        Student student = new Student();
        // Map values from request-body with command methods to student object with its methods
        student.setFirstname(request.firstname());
        student.setLastname(request.lastname());
        student.setEmail(request.email());
        // Map plain string to embedded Password value object.
        // If your Password type has a factory method, use it here instead.
        student.setPassword(new Password(request.password()));
        if (request.departmentId() != null) { // departmentId needs to be type of Integer to be null -> int can't be null and would cause an error
            Department ref = entityManager.getReference(Department.class, request.departmentId());
            student.setDepartment(ref);
        } else {
            student.setDepartment(null);
        }

        // Save the student in the DB via JpaRepository
        Student saved = studentRepository.addStudent(student);

        // Check if department name is set -> if yes get its name
        String departmentName = saved.getDepartment() != null ? saved.getDepartment().getName() : null;
        // Map to DTO to avoid exposing entities/proxies
        return new StudentDto(
                saved.getId(),
                saved.getFirstname(),
                saved.getLastname(),
                saved.getEmail(),
                departmentName
        );
    }
}
