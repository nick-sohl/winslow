package ch.nicksohl.winslow.application.cqrs;

public record StudentDto(int student_id, String firstname, String lastname, String email, String department) {
}
