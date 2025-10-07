package ch.nicksohl.winslow.application.usecase.module;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.CourseModule;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;

import java.util.Optional;

public class FindModule {
	ModuleRepositoryInterface moduleRepository;
	public FindModule(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	public Result<ModuleDto> findModuleById(Long moduleId) {
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
        else {
            Result.failure(ErrorCode.NOT_FOUND, "The module could not be found.");
        }

        return Result.success(moduleDto);
	}
}
