package ch.nicksohl.winslow.infrastructure.adapter.config;

import ch.nicksohl.winslow.application.service.ModuleService;
import ch.nicksohl.winslow.application.usecase.module.*;
import ch.nicksohl.winslow.infrastructure.adapter.ModuleRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {
	@Bean
	ModuleService moduleService(FindAllModules findAllModules, FindModule findModule, FindAllModulesByCourse findAllModulesByCourse, CreateModule createModule, UpdateModule updateModule, DeleteModule deleteModule) {
		return new ModuleService(findAllModules, findAllModulesByCourse, findModule, createModule, updateModule, deleteModule);
	}

	@Bean
	FindAllModules findAllModules(ModuleRepositoryAdapter moduleRepositoryAdapter) {
		return new FindAllModules(moduleRepositoryAdapter);
	}
	@Bean
	FindAllModulesByCourse findAllModulesById(ModuleRepositoryAdapter moduleRepositoryAdapter) {
		return new FindAllModulesByCourse(moduleRepositoryAdapter);
	}
	@Bean
	FindModule findModule(ModuleRepositoryAdapter moduleRepositoryAdapter) {
		return new FindModule(moduleRepositoryAdapter);
	}
	@Bean
	CreateModule createModule(ModuleRepositoryAdapter moduleRepositoryAdapter) {
		return new CreateModule(moduleRepositoryAdapter);
	}
	@Bean
	UpdateModule updateModule(ModuleRepositoryAdapter moduleRepositoryAdapter) {
		return new UpdateModule(moduleRepositoryAdapter);
	}
	@Bean
	DeleteModule deleteModule(ModuleRepositoryAdapter moduleRepositoryAdapter) {
		return new DeleteModule(moduleRepositoryAdapter);
	}
}
