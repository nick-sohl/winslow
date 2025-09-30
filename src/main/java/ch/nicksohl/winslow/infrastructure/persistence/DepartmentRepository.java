package ch.nicksohl.winslow.infrastructure.persistence;

import ch.nicksohl.winslow.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
