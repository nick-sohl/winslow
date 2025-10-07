package ch.nicksohl.winslow.application.cqrs.dto;

public record UserDto(Long userId, String firstname, String lastname, String username, String email, String role, Integer departmentId) {
}
