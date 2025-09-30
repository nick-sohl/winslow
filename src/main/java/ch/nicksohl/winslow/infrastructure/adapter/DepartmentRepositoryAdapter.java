package ch.nicksohl.winslow.infrastructure.adapter;

import ch.nicksohl.winslow.application.port.DepartmentRepositoryInterface;
import ch.nicksohl.winslow.domain.Department;
import ch.nicksohl.winslow.infrastructure.persistence.DepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentRepositoryAdapter implements DepartmentRepositoryInterface {
	private final DepartmentRepository departmentRepository;
	public DepartmentRepositoryAdapter(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}


	@Override
	public List<Department> findAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> findDepartmentById(int departmentId) {
		return departmentRepository.findById(departmentId);
	}

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public void deleteDepartmentById(int departmentId) {
		departmentRepository.deleteById(departmentId);
	}
}
