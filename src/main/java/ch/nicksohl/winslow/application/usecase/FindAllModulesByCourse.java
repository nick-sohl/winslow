package ch.nicksohl.winslow.application.usecase;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.application.mapper.ModuleMapper;
import ch.nicksohl.winslow.application.port.ModuleRepositoryInterface;
import ch.nicksohl.winslow.domain.CourseModule;

import java.util.ArrayList;
import java.util.List;

public class FindAllModulesByCourse {
	ModuleRepositoryInterface moduleRepository;

	public FindAllModulesByCourse(ModuleRepositoryInterface moduleRepository) {
		this.moduleRepository = moduleRepository;
	}

	// ? Filter Modules by Course-ID
	public List<ModuleDto> findAllModulesByCourse(int courseId) {
		List<CourseModule> courseModules = moduleRepository.findAllModules();
		List<ModuleDto> moduleDtos = new ArrayList<>();
		for (CourseModule module : courseModules) {
			if (module.getCourse().getCourseId() == courseId) {
				moduleDtos.add(ModuleMapper.toDto(module));
			}
		}
		return moduleDtos;
	}
}
