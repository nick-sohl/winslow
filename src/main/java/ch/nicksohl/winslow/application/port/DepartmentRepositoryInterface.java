package ch.nicksohl.winslow.application.port;

import ch.nicksohl.winslow.domain.Department;

import java.util.List;
import java.util.Optional;

// TODO : Refactor naming of Methods
public interface DepartmentRepositoryInterface {
	List<Department> findAllDepartments();
	Optional<Department> findDepartmentById(int departmentId);
	Department saveDepartment(Department department);
	void deleteDepartmentById(int departmentId);
}
