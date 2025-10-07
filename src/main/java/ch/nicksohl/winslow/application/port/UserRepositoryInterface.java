package ch.nicksohl.winslow.application.port;

import ch.nicksohl.winslow.domain.User;

import java.util.List;

public interface UserRepositoryInterface {
    User getUserByUsername(String username);
    List<User> findAllUsers();
    User createUser(User user);
}
