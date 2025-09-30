package ch.nicksohl.winslow.infrastructure.persistence;

import ch.nicksohl.winslow.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
