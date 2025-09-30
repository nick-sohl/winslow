package ch.nicksohl.winslow.application.port;

import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.CourseModule;

import java.util.List;
import java.util.Optional;

public interface ModuleRepositoryInterface {
	List<CourseModule> findAllModules();
	Optional<CourseModule> findModuleById(Long moduleId);
	CourseModule saveModule(CourseModule courseModule);
	void deleteModuleById(Long moduleId);
	boolean existsById(Long moduleId);
}
