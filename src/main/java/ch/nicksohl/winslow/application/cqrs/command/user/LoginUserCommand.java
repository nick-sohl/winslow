package ch.nicksohl.winslow.application.cqrs.command.user;

import ch.nicksohl.winslow.domain.enumeration.Role;

public record LoginUserCommand(String username, String password, Role role) {
}
