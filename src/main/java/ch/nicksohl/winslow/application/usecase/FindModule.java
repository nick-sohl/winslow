package ch.nicksohl.winslow.application.usecase;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;
import ch.nicksohl.winslow.domain.CourseModule;
import ch.nicksohl.winslow.infrastructure.adapter.ModuleRepositoryAdapter;

import java.util.Optional;

public class FindModule {
	ModuleRepositoryInterface moduleRepository;
	public FindModule(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	public ModuleDto findModuleById(Long moduleId) {
		Optional<CourseModule> courseModule = moduleRepository.findModuleById(moduleId);
		ModuleDto moduleDto = null;
		if (courseModule.isPresent()) {
			moduleDto = new ModuleDto(
					moduleId,
					courseModule.get().getName(),
					courseModule.get().getDescription(),
					courseModule.get().getOrderIndex().getValue(),
					courseModule.get().getCourse().getCourseId()
			);
		}
		return moduleDto;
	}
}
