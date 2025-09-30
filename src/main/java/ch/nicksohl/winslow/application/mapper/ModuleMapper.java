package ch.nicksohl.winslow.application.mapper;

import ch.nicksohl.winslow.application.cqrs.dto.ModuleDto;
import ch.nicksohl.winslow.domain.CourseModule;

public class ModuleMapper {

	private ModuleMapper() {
		// utility class
	}

	public static ModuleDto toDto(CourseModule module) {
		return new ModuleDto(
				module.getModuleId(),
				module.getName(),
				module.getDescription(),
				module.getOrderIndex().getValue(),
				module.getCourse().getCourseId()
		);
	}
}
