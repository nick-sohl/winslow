package ch.nicksohl.winslow.presentation.api;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.application.service.ModuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ModuleController {

	private final ModuleService moduleService;

	public ModuleController(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@GetMapping("/modules")
	public List<ModuleDto> findAll() {
		return moduleService.findAllModules();
	}


	// ? Explicitly bind the name of the variable {courseId} to @Pathvariable
	@GetMapping("/courses/{courseId}/modules")
	public List<ModuleDto> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		return moduleService.findAllModulesByCourse(courseId);
	}

	@GetMapping("/courses/{courseId}/modules/{moduleId}")
	public ModuleDto findModuleById(@PathVariable("moduleId") Long moduleId) {
		return moduleService.findModuleById(moduleId);
	}
}

