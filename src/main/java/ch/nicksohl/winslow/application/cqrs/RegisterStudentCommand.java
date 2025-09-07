package ch.nicksohl.winslow.application.cqrs;

public record RegisterStudentCommand(String firstname, String lastname, String password) {
}
