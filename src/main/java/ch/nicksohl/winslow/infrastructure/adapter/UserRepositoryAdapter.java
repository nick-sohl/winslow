package ch.nicksohl.winslow.infrastructure.adapter;

import ch.nicksohl.winslow.application.port.UserRepositoryInterface;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;
import ch.nicksohl.winslow.infrastructure.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryAdapter implements UserRepositoryInterface {
    UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
