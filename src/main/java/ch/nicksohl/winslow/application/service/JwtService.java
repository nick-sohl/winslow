package ch.nicksohl.winslow.application.service;

import ch.nicksohl.winslow.application.usecase.jwt.GetLoggedInUserDetails;
import ch.nicksohl.winslow.application.usecase.jwt.ValidateToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    GetLoggedInUserDetails getLoggedInUserDetails;
    ValidateToken validateToken;

    public JwtService(GetLoggedInUserDetails getLoggedInUserDetails, ValidateToken validateToken) {
        this.getLoggedInUserDetails = getLoggedInUserDetails;
        this.validateToken = validateToken;
    }

    public String extractUsername(String token) {
        return validateToken.extractUsername(token);
    }

    // Get Logged-in username
    public UserDetails getLoggedInUserDetails() {
        return getLoggedInUserDetails.getLoggedInUserDetails();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return validateToken.validate(token, userDetails);
    }

}
