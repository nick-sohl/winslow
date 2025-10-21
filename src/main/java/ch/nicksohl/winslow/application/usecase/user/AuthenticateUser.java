package ch.nicksohl.winslow.application.usecase.user;

import ch.nicksohl.winslow.application.cqrs.command.user.LoginUserCommand;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.application.service.JwtBuilder;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AuthenticateUser {
    AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    String secret;

    public AuthenticateUser(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Result<String> authenticateUser(LoginUserCommand loginUserCommand) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserCommand.username(), loginUserCommand.password()));

        if (!authentication.isAuthenticated()) {
            return Result.failure(ErrorCode.VALIDATION_ERROR, "The user could not be authenticated.");
        }

        try {
            // Secret String -> Bytes -> HmacSHA512 -> SecretKey object
            // Decode String into array of bites
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            // Convert array of bites into SecretKey object, to use it for the JwtBuilder
            SecretKey secretKey = new SecretKeySpec(decodedKey, "HmacSHA512");

            System.out.println(Base64.getUrlEncoder().withoutPadding().encodeToString(secretKey.getEncoded()));
            return Result.success(
                    new JwtBuilder.Builder()
                            .subject(loginUserCommand.username())
                            .claims("role", loginUserCommand.role())
                            .issuedAt()
                            .expiresInSeconds(3200) // 1 hour
                            .signWith(secretKey)
                            .build()
            );
        } catch (Exception e) {
            return Result.failure(ErrorCode.CONFLICT, "No such algorithm or invalid key.");
        }
    }
}
