package ch.nicksohl.winslow.application.mapper;

import ch.nicksohl.winslow.application.cqrs.dto.UserDto;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null when mapping to DTO");
        }

        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getDepartment().getId()
        );
    }
}
