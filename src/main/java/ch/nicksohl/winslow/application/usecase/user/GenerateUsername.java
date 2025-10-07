package ch.nicksohl.winslow.application.usecase.user;

public class GenerateUsername {
    // Generate a unique username from the first- and lastname
    // We use it within Spring Security to authenticate the user by its unique name
    static String generateUsername(String firstname, String lastname) {
        return firstname.toLowerCase() + "-" + lastname.toLowerCase();
    }
}
