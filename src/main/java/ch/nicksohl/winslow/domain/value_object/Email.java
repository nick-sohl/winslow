package ch.nicksohl.winslow.domain.value_object;

import ch.nicksohl.winslow.application.shared.Result;
import ch.nicksohl.winslow.domain.enumeration.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Locale;
import java.util.Objects;

@Embeddable
public final class Email {
    @Column(name = "email", nullable = false, unique = true)
    private String value;

    // JPA requires a no-arg constructor
    protected Email() {
        // for JPA
    }

    // private constructor: only the factory method can create valid instances
    private Email(String value) {
        this.value = value;
    }

    /**
     * Factory method: validates, normalizes and returns either a successful Result<Email>
     * or a failure Result with a validation error.
     */
    public static Result<Email> of(String email) {
        if (email == null || email.isBlank()) {
            return Result.failure(ErrorCode.VALIDATION_ERROR, "The email cannot be empty");
        }

        String trimmed = email.trim();

        if (!isValidEmail(trimmed)) {
            return Result.failure(ErrorCode.VALIDATION_ERROR, "The email is not valid");
        }

        // Optional normalization: lowercase the domain for consistent uniqueness checks,
        // while preserving local part casing (local part can be technically case-sensitive).
        String normalized = normalizeDomain(trimmed);

        return Result.success(new Email(normalized));
    }

    /**
     * Normalization & uniqueness: DB uniqueness is case-sensitive or insensitive depending on DB/collation.
     * By lowercasing the domain we reduce sources of duplicate records (e.g., user@EXAMPLE.com vs. user@example.com).
     */
    private static String normalizeDomain(String email) {
        int at = email.lastIndexOf('@');
        if (at < 0) return email;
        String local = email.substring(0, at);
        String domain = email.substring(at + 1).toLowerCase(Locale.ROOT);
        return local + "@" + domain;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        // Practical regex: covers common addresses (allows +, %, _, ., - etc.)
        String emailRegex = "^[A-Za-z0-9._%+-]+(\\.[A-Za-z0-9._%+-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailRegex);
    }

    // keep original getter name for compatibility
    public String getEmail() {
        return value;
    }

    // No setter — the object is effectively immutable after creation (value object semantics)
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email other = (Email) o;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
