package ch.nicksohl.winslow.presentation.api;

import ch.nicksohl.winslow.application.cqrs.command.user.LoginUserCommand;
import ch.nicksohl.winslow.application.cqrs.command.user.RegisterUser;
import ch.nicksohl.winslow.application.cqrs.dto.UserDto;
import ch.nicksohl.winslow.application.mapper.UserMapper;
import ch.nicksohl.winslow.application.service.UserService;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = {"/register"}, consumes = "application/json", produces = "application/json")
    @Transactional
    public Result<UserDto> createUser(@RequestBody RegisterUser registerUser) {
        Result<User> result = userService.createUser(registerUser);

        if (result.isFailure()) {
            return Result.failure(result.errorCode(), result.errorMessage());
        }
        User user = result.data();
        UserDto userDto = UserMapper.toDto(user);
        return Result.success(userDto);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public Result<String> loginUser(@RequestBody LoginUserCommand loginUserCommand) {
        return userService.authenticateUser(loginUserCommand);
    }

    @GetMapping("api/users")
    public Result<List<UserDto>> findAllUsers() {
        Result<List<User>> result = userService.findAllUsers();
        List<User> data = result.data();
        List<UserDto> listOfUserDtos = new ArrayList<>();
        for (User user : data) {
            listOfUserDtos.add(UserMapper.toDto(user));
        }
        return Result.success(listOfUserDtos);
    }
}
