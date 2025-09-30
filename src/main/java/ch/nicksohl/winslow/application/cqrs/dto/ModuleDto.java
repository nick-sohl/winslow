package ch.nicksohl.winslow.application.cqrs.dto;

public record ModuleDto(Long moduleId, String name, String description, Integer orderIndex, Integer courseId) {
}
