package ch.nicksohl.winslow.infrastructure.adapter;

import ch.nicksohl.winslow.domain.CourseModule;
import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;
import ch.nicksohl.winslow.infrastructure.persistence.ModuleRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleRepositoryAdapter implements ModuleRepositoryInterface {
	ModuleRepository moduleRepository;

	public ModuleRepositoryAdapter(ModuleRepository moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	@Override
	public List<CourseModule> findAllModules() {
		return moduleRepository.findAll();
	}

	@Override
	public Optional<CourseModule> findModuleById(Long moduleId) {
		return moduleRepository.findById(moduleId);
	}

	@Override
	public CourseModule saveModule(CourseModule course) {
		return null;
	}

	@Override
	public void deleteModuleById(Long moduleId) {
		moduleRepository.deleteById(moduleId);
	}

	@Override
	public boolean existsById(Long moduleId) {
		return moduleRepository.existsById(moduleId);
	}
}
