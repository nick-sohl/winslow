package ch.nicksohl.winslow.application.cqrs;

public record CreateStudentCommand(String firstname, String lastname, String email, String password, Integer departmentId) {
}
