package ch.nicksohl.winslow.presentation.auth;

import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;
import ch.nicksohl.winslow.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WinslowUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Result<User> result = isValidUser(user);
        return new UserPrinciple(result.data());
    }

    private Result<User> isValidUser(User user) {
        if (user == null) {
            return Result.failure(ErrorCode.NOT_FOUND, "The user object is empty!");
        }
        return Result.success(user);
    }
}
