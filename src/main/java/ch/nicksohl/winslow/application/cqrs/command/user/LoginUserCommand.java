package ch.nicksohl.winslow.application.cqrs.command.user;

public record LoginUserCommand(String username, String password) {
}
