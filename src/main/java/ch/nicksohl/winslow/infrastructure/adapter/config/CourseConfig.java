package ch.nicksohl.winslow.infrastructure.adapter.config;

import ch.nicksohl.winslow.application.service.CourseService;
import ch.nicksohl.winslow.application.service.DepartmentService;
import ch.nicksohl.winslow.infrastructure.adapter.CourseRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CourseConfig {

	@Bean
	CourseService courseService(CourseRepositoryAdapter courseRepositoryAdapter, DepartmentService departmentService) {
		return new CourseService(courseRepositoryAdapter, departmentService);
	}

}
