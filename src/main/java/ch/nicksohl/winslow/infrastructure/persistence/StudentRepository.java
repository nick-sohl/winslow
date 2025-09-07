package ch.nicksohl.winslow.infrastructure.persistence;

import ch.nicksohl.winslow.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
