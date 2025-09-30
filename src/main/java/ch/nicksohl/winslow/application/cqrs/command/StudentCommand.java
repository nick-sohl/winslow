package ch.nicksohl.winslow.application.cqrs.command;

public record StudentCommand(String firstname, String lastname, String email, String password, Integer departmentId) {
}
