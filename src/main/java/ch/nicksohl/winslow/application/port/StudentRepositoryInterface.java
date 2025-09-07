package ch.nicksohl.winslow.application.port;

// Java SDK
import java.util.List;
import java.util.Optional;

// Application
import ch.nicksohl.winslow.application.cqrs.StudentDto;
import ch.nicksohl.winslow.domain.student.Student;

public interface StudentRepositoryInterface {
    List<Student> students();
    Optional<Student> getStudent(Integer studentId);
    Student addStudent(Student student); // add or update -> GET or UPDATE
    void deleteStudent(Long studentId);
}
