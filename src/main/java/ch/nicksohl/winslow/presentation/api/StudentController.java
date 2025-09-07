package ch.nicksohl.winslow.presentation.api;

// Application
import ch.nicksohl.winslow.application.cqrs.StudentDto;
import ch.nicksohl.winslow.application.port.StudentRepositoryInterface;
import ch.nicksohl.winslow.domain.student.Student;

// Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

// Java SDK
import java.util.ArrayList;
import java.util.List;

// JPA
import jakarta.persistence.EntityManager;

// Domain
import ch.nicksohl.winslow.domain.Department;
import ch.nicksohl.winslow.domain.student.Password;

@RestController // Framework creates Object in IOC
@RequestMapping("/api/students")
class StudentController {
    StudentRepositoryInterface studentRepository;
    private final EntityManager entityManager;

    // Framework Scans for Component - in our case the Adapter - which implements the Interface
    // Framework Injects Adapter through Dependency Injection
    public StudentController(StudentRepositoryInterface studentRepository, EntityManager entityManager) {
        this.studentRepository = studentRepository;
        this.entityManager = entityManager;
    }

    @GetMapping({"", "/"})
    public List<StudentDto> getStudents() {
        // Use method of adapter to return students
        List<Student> listOfStudents = studentRepository.students();
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

//    @GetMapping(path = "/{studentId}", produces = "application/json")
//    @ResponseBody
//    public Student getStudent(@PathVariable Long studentId) {
//
//    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Transactional
    public StudentDto addStudent(@RequestBody CreateStudentRequest request) {
        Student student = new Student();
        student.setFirstname(request.getFirstname());
        student.setLastname(request.getLastname());
        student.setEmail(request.getEmail());
        // Map plain string to embedded Password value object.
        // If your Password type has a factory method, use it here instead.
        student.setPassword(new Password(request.getPassword()));
        if (request.getDepartmentId() != null) {
            Department ref = entityManager.getReference(Department.class, request.getDepartmentId());
            student.setDepartment(ref);
        } else {
            student.setDepartment(null);
        }

        Student saved = studentRepository.addStudent(student);

        // Map to DTO to avoid exposing entities/proxies
        String departmentName = saved.getDepartment() != null ? saved.getDepartment().getName() : null;
        return new StudentDto(
                saved.getId(),
                saved.getFirstname(),
                saved.getLastname(),
                saved.getEmail(),
                departmentName
        );
    }

    // Request DTO to decouple API from entities and handle the embedded Password
    public static class CreateStudentRequest {
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private Integer departmentId;

        public String getFirstname() { return firstname; }
        public void setFirstname(String firstname) { this.firstname = firstname; }
        public String getLastname() { return lastname; }
        public void setLastname(String lastname) { this.lastname = lastname; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Integer getDepartmentId() { return departmentId; }
        public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }
    }
}
