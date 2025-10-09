package ch.nicksohl.winslow.application.service;

import ch.nicksohl.winslow.application.cqrs.command.user.LoginUserCommand;
import ch.nicksohl.winslow.application.cqrs.command.user.RegisterUser;
import ch.nicksohl.winslow.application.port.UserRepositoryInterface;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.application.usecase.jwt.GetLoggedInUserDetails;
import ch.nicksohl.winslow.application.usecase.user.*;
import ch.nicksohl.winslow.domain.User;

import java.util.List;

public class UserService {
    UserRepositoryInterface userRepoAdapter;

    // Use cases
    CreateUser createUser;
    FindUserByUsername findUserByUsername;
    AuthenticateUser authenticateUser;
    FindAllUsers findAllUsers;
    GetLoggedInUserDetails getLoggedInUserDetails;

    // Constructor
    public UserService(
            UserRepositoryInterface userRepoAdapter,
            CreateUser createUser,
            FindUserByUsername findUserByUsername,
            FindAllUsers findAllUsers,
            AuthenticateUser authenticateUser,
            GetLoggedInUserDetails getLoggedInUserDetails
    ) {
        this.userRepoAdapter = userRepoAdapter;
        this.createUser = createUser;
        this.findUserByUsername = findUserByUsername;
        this.findAllUsers = findAllUsers;
        this.authenticateUser = authenticateUser;
        this.getLoggedInUserDetails = getLoggedInUserDetails;
    }

    public Result<List<User>> findAllUsers() {
        return findAllUsers.findAllUsers();
    }

    // Use cases
    public Result<User> createUser(RegisterUser registerUser) {
        return createUser.createUser(registerUser);
    }

    public Result<User> findUserByUsername(String username) {
        return findUserByUsername.findUserByUsername(username);
    }

    public Result<String> authenticateUser(LoginUserCommand loginUserCommand) {
        return authenticateUser.authenticateUser(loginUserCommand);
    }
}
