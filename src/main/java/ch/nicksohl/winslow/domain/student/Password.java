package ch.nicksohl.winslow.domain.student;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Password {
    @Column(name = "password", nullable = false, length = 200)
    private String value;

    protected Password() {}
    public Password(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("The password can not be empty.");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Password{" +
                "value='" + value + '\'' +
                '}';
    }
}
