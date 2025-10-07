package ch.nicksohl.winslow.application.service;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.application.usecase.module.*;

import java.util.List;

public class ModuleService {
	// use cases
	FindAllModules findAllModules;
	FindAllModulesByCourse findAllModulesByCourse;
	FindModule findModule;
	CreateModule createModule;
	UpdateModule updateModule;
	DeleteModule deleteModule;

	public ModuleService(FindAllModules findAllModules, FindAllModulesByCourse findAllModulesByCourse, FindModule findModule, CreateModule createModule, UpdateModule updateModule, DeleteModule deleteModule) {
		this.findAllModules = findAllModules;
		this.findAllModulesByCourse = findAllModulesByCourse;
		this.findModule = findModule;
		this.createModule = createModule;
		this.updateModule = updateModule;
		this.deleteModule = deleteModule;
	}

	public List<ModuleDto> findAllModules() {
		return findAllModules.findAllModules();
	}

	public List<ModuleDto> findAllModulesByCourse(int courseId) {
		return findAllModulesByCourse.findAllModulesByCourse(courseId);
	}

	public Result<ModuleDto> findModuleById(Long moduleId) {
		return findModule.findModuleById(moduleId);
	}

	Result<Void> deleteModuleById(Long moduleId) {
		return deleteModule.deleteModuleById(moduleId);
	}
}
