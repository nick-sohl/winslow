package ch.nicksohl.winslow.application.usecase.user;

import ch.nicksohl.winslow.application.port.UserRepositoryInterface;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;

import java.util.List;

public class FindAllUsers {
    UserRepositoryInterface userRepoAdapter;

    public FindAllUsers(UserRepositoryInterface userRepoAdapter) {
        this.userRepoAdapter = userRepoAdapter;
    }

    public Result<List<User>> findAllUsers() {
        return Result.success(userRepoAdapter.findAllUsers());
    }
}
