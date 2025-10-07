package ch.nicksohl.winslow.application.usecase.user;

import ch.nicksohl.winslow.application.cqrs.command.user.LoginUserCommand;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.application.service.JwtService;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticateUser {
    AuthenticationManager authenticationManager;
    JwtService jwtService;

    public AuthenticateUser(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Result<String> authenticateUser(LoginUserCommand loginUserCommand) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserCommand.username(), loginUserCommand.password()));

        if (!authentication.isAuthenticated()) {
            return Result.failure(ErrorCode.VALIDATION_ERROR, "The user could not be authenticated.");
        }

        // TODO : Return Token

        return Result.success("User " + loginUserCommand.username()  + " successfully authenticated!");
    }
}
