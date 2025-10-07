package ch.nicksohl.winslow.application.usecase.user;

import ch.nicksohl.winslow.application.cqrs.command.user.RegisterUser;
import ch.nicksohl.winslow.application.cqrs.dto.DepartmentDto;
import ch.nicksohl.winslow.application.port.UserRepositoryInterface;
import ch.nicksohl.winslow.application.service.DepartmentService;
import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.Department;
import ch.nicksohl.winslow.domain.User;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;
import ch.nicksohl.winslow.domain.value_object.Password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

public class CreateUser {
    UserRepositoryInterface userRepoAdapter;
    // ? Inject DepartmentService via UserConfig in Infrastructure-Layer
    DepartmentService departmentService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public CreateUser(UserRepositoryInterface userRepoAdapter, DepartmentService departmentService) {
        this.userRepoAdapter = userRepoAdapter;
        this.departmentService = departmentService;
    }

    public Result<User> createUser(RegisterUser registerUser) {
        User user = new User();

        // ? Get the Department from the DB via DepartmentService -> Insert DepartmentId via Command (ResponseBody defined in Controller = JSON from User)
        DepartmentDto departmentDto = departmentService.findDepartmentById(registerUser.departmentId());
        // ? Convert DTO back to normal Object with Method defined inside Department Domain Model
        Department department = Department.fromDto(departmentDto);

        user.setFirstname(registerUser.firstname());
        user.setLastname(registerUser.lastname());
        user.setUsername(GenerateUsername.generateUsername(user.getFirstname(), user.getLastname())); // use case to generate unique username
        user.setEmail(registerUser.email());
        user.setPassword(new Password(bCryptPasswordEncoder.encode(registerUser.password())));
        user.setRole(registerUser.role());
        user.setDepartment(department);

        // Check if user already exists (null-safe)
        User existing = userRepoAdapter.getUserByUsername(user.getUsername());
        if (existing != null && Objects.equals(existing.getUsername(), user.getUsername())) {
            return Result.failure(ErrorCode.CONFLICT, "The user already exists.");
        } else {
            return Result.success(userRepoAdapter.createUser(user));
        }
    }
}
