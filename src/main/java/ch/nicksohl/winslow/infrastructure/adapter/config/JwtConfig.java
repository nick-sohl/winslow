package ch.nicksohl.winslow.infrastructure.adapter.config;

import ch.nicksohl.winslow.application.service.JwtBuilder;
import ch.nicksohl.winslow.application.service.JwtService;
import ch.nicksohl.winslow.application.usecase.jwt.GetLoggedInUserDetails;
import ch.nicksohl.winslow.application.usecase.jwt.ValidateToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    JwtBuilder.Builder jwtBuilder() {
        return new JwtBuilder.Builder();
    }

    @Bean
    JwtService jwtService(GetLoggedInUserDetails getLoggedInUserDetails, ValidateToken validateToken) {
        return new JwtService(getLoggedInUserDetails, validateToken);
    }

    @Bean
    GetLoggedInUserDetails getLoggedInUserDetails() {
        return new GetLoggedInUserDetails();
    }

    @Bean
    ValidateToken validateToken(
            // Reads jwt.secret from application properties; falls back to env var JWT_SECRET if not set
            @Value("${jwt.secret}") String secret
    ) {
        return new ValidateToken(secret);
    }
}
