package ch.nicksohl.winslow.application.usecase;

import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;

public class DeleteModule {
	private final ModuleRepositoryInterface moduleRepository;

	public DeleteModule(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	public Result<Void> deleteModuleById(Long moduleId) {
		if (!moduleRepository.existsById(moduleId)) {
			return Result.failure(
					ErrorCode.NOT_FOUND,
					"Module with id " + moduleId + " does not exist"
			);
		}

		try {
			moduleRepository.deleteModuleById(moduleId);
			return Result.success(null);
		} catch (Exception e) {
			return Result.failure(
					ErrorCode.SERVER_ERROR,
					"Unexpected error: " + e.getMessage()
			);
		}
	}
}
