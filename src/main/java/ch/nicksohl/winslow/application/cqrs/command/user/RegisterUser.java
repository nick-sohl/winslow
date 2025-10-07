package ch.nicksohl.winslow.application.cqrs.command.user;

import ch.nicksohl.winslow.domain.enumeration.Role;

public record RegisterUser(String firstname, String lastname, String email, String password, Role role, Integer departmentId) {
}
