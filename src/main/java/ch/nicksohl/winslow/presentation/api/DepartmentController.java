package ch.nicksohl.winslow.presentation.api;

import ch.nicksohl.winslow.application.cqrs.dto.DepartmentDto;
import ch.nicksohl.winslow.application.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping({"", "/"})
	List<DepartmentDto> departments() {
		return departmentService.findAllDepartments();
	}

	@GetMapping(value = "/{departmentId}", produces = "application/json")
	@ResponseBody
	DepartmentDto findDepartmentById(@PathVariable("departmentId") int departmentId) {
		return departmentService.findDepartmentById(departmentId);
	}

//	@PostMapping(value = {"", "/"}, consumes = "application/json", produces = "application/json")
//	@Transactional
//	DepartmentDto addDepartment(@RequestBody DepartmentCommand departmentCommand) {
//		return departmentService.saveDepartment(departmentCommand);
//	}

//	void deleteDepartment(int departmentId) {
//		departmentService.deleteDepartmentById(departmentId);
//	}

//	@PutMapping("/{departmentId}")
//	public DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable("departmentId") int departmentId ) {
//		return departmentService.updateDepartment(departmentId, departmentDto);
//	}
}
