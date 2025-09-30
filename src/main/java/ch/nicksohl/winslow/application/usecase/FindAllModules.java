package ch.nicksohl.winslow.application.usecase;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;
import ch.nicksohl.winslow.application.mapper.ModuleMapper;

import java.util.List;

public class FindAllModules {
	ModuleRepositoryInterface moduleRepository;

	public FindAllModules(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	public List<ModuleDto> findAllModules() {
		return moduleRepository.findAllModules().stream().map(ModuleMapper::toDto).toList();
	}
}
