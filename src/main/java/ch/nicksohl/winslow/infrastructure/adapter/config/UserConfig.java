package ch.nicksohl.winslow.infrastructure.adapter.config;

import ch.nicksohl.winslow.application.service.DepartmentService;
import ch.nicksohl.winslow.application.service.UserService;
import ch.nicksohl.winslow.application.usecase.user.AuthenticateUser;
import ch.nicksohl.winslow.application.usecase.user.CreateUser;
import ch.nicksohl.winslow.application.usecase.user.FindAllUsers;
import ch.nicksohl.winslow.application.usecase.user.FindUserByUsername;
import ch.nicksohl.winslow.infrastructure.adapter.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UserConfig {

    @Bean
    UserService userService(
            UserRepositoryAdapter userRepositoryAdapter,
            CreateUser createUser,
            FindUserByUsername findUserByUsername,
            FindAllUsers findAllUsers,
            AuthenticateUser authenticateUser
    ) {
        return new UserService(userRepositoryAdapter, createUser, findUserByUsername, findAllUsers, authenticateUser);
    }

    @Bean
    CreateUser createUser(UserRepositoryAdapter userRepositoryAdapter, DepartmentService departmentService) {
        return new CreateUser(userRepositoryAdapter, departmentService);
    }

    @Bean
    FindUserByUsername findUserByUsername(UserRepositoryAdapter userRepositoryAdapter) {
        return new FindUserByUsername(userRepositoryAdapter);
    }

    @Bean
    AuthenticateUser authenticateUser(AuthenticationManager authenticationManager) {
        return new AuthenticateUser(authenticationManager);
    }

    @Bean
    FindAllUsers findAllUsers(UserRepositoryAdapter userRepositoryAdapter) {
        return new FindAllUsers(userRepositoryAdapter);
    }

}
