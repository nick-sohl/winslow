package ch.nicksohl.winslow.application.usecase.user;

import ch.nicksohl.winslow.application.cqrs.command.user.LoginUserCommand;
import ch.nicksohl.winslow.application.port.UserRepositoryInterface;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.User;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;

public class FindUserByUsername {
    UserRepositoryInterface userRepoAdapter;

    public FindUserByUsername(UserRepositoryInterface userRepoAdapter) {
        this.userRepoAdapter = userRepoAdapter;
    }

    public Result<User> findUserByUsername(String username) {
        if (userRepoAdapter.getUserByUsername(username) == null) {
            return Result.failure(ErrorCode.NOT_FOUND, "User not found.");
        }

        return Result.success(userRepoAdapter.getUserByUsername(username));
    }
}
