package ch.nicksohl.winslow.infrastructure.persistence;

import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
