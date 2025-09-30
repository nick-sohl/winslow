package ch.nicksohl.winslow.infrastructure.persistence;

import ch.nicksohl.winslow.domain.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {
}
