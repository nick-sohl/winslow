package ch.nicksohl.winslow.application.usecase.module;

import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;

public class CreateModule {
	ModuleRepositoryInterface moduleRepository;

	public CreateModule(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}
}
