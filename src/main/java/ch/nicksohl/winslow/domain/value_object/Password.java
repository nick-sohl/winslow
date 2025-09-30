package ch.nicksohl.winslow.domain.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Password {
    @Column(name = "password", nullable = false, length = 200)
    private long value;

    protected Password() {}
    public Password(String value) {
        if (value == null || value.isBlank()) {
            // TODO : Use result pattern
            throw new IllegalArgumentException("The password can not be empty.");
        }
        // TODO : Have a look at spring security
        this.value = value.hashCode();
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
