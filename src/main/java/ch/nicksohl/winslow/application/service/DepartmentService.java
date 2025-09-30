package ch.nicksohl.winslow.application.service;

import ch.nicksohl.winslow.application.cqrs.dto.DepartmentDto;
import ch.nicksohl.winslow.application.port.DepartmentRepositoryInterface;
import ch.nicksohl.winslow.domain.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentService {
	DepartmentRepositoryInterface departmentRepository;

	public DepartmentService(DepartmentRepositoryInterface departmentRepositoryInterface) {
		this.departmentRepository = departmentRepositoryInterface;
	}

	public List<DepartmentDto> findAllDepartments() {
		List<Department> departments = departmentRepository.findAllDepartments();
		List<DepartmentDto> listOfCoursesDto = new ArrayList<>();
		for (Department department : departments) {
			DepartmentDto departmentDto = mapToDto(department);
			listOfCoursesDto.add(departmentDto);
		}
		return listOfCoursesDto;
	}

	public DepartmentDto findDepartmentById(int departmentId) {
		Optional<Department> optionalDepartment = departmentRepository.findDepartmentById(departmentId);
		// Convert Optional to Object with helper-method
		Department department = getDepartmentOrEmpty(optionalDepartment);
		// Map Object to DTO
		return mapToDto(department);
	}

	public DepartmentDto mapToDto(Department department) {
		// Map the Object to an DTO
		if (department != null) {
			return new DepartmentDto(department.getId(), department.getName());
		}
		return null; // If department is null return null
	}

	private Department getDepartmentOrEmpty(Optional<Department> departmentOptional) {
		return departmentOptional.orElse(new Department());
	}

}
