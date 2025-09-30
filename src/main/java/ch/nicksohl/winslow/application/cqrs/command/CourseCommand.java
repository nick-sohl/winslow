package ch.nicksohl.winslow.application.cqrs.command;

public record CourseCommand(int courseId, String title, String description, String goals, Integer departmentId) {
}
