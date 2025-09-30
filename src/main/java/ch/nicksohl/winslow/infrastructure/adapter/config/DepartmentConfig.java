package ch.nicksohl.winslow.infrastructure.adapter.config;

import ch.nicksohl.winslow.application.service.DepartmentService;
import ch.nicksohl.winslow.infrastructure.adapter.DepartmentRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfig {

	@Bean
	DepartmentService departmentService(DepartmentRepositoryAdapter departmentRepositoryAdapter) {
		return new DepartmentService(departmentRepositoryAdapter);
	}

}
