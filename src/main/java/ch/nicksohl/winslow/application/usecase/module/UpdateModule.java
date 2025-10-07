package ch.nicksohl.winslow.application.usecase.module;

import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;

public class UpdateModule {
	ModuleRepositoryInterface moduleRepository;

	public UpdateModule(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}
}
