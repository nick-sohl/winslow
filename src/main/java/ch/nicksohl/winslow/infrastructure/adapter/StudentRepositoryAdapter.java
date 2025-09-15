package ch.nicksohl.winslow.infrastructure.adapter;

// Domain
import ch.nicksohl.winslow.domain.student.Student;

// Application
import ch.nicksohl.winslow.application.cqrs.StudentDto;
import ch.nicksohl.winslow.application.port.StudentRepositoryInterface;

// Infrastructure
import ch.nicksohl.winslow.infrastructure.persistence.StudentRepository;

// Framework
import org.springframework.stereotype.Service;

// Java SDK
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRepositoryAdapter implements StudentRepositoryInterface {
    StudentRepository studentRepository;

    public StudentRepositoryAdapter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> students() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudent(int studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        studentRepository.deleteById(studentId);
    }
}
