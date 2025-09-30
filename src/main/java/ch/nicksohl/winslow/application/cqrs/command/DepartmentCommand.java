package ch.nicksohl.winslow.application.cqrs.command;

public record DepartmentCommand(Integer departmentId, String name) {
}
