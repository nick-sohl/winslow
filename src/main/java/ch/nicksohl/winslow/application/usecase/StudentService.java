package ch.nicksohl.winslow.application.usecase;

// Application
import ch.nicksohl.winslow.application.cqrs.StudentDto;
import ch.nicksohl.winslow.application.port.StudentRepositoryInterface;
import ch.nicksohl.winslow.domain.student.Student;

// Java SDK
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private StudentRepositoryInterface studentRepository;

    // Liskov-Substitution
    // Substitute the Interface with the Adapter which itself is implementing the Interface
    public StudentService(StudentRepositoryInterface studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> students() {
        // Get all students from the adapter, which the adapter got from the JpaRepo
        return studentRepository.students();
    }
}
