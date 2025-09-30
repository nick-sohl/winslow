package ch.nicksohl.winslow.domain.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Grade {
	@Column(name = "grade", nullable = false)
	private Integer value; // ? Immutable Value that can only exist ones

	public Grade() {} // no args constructor

	public Grade(Integer value) {
		validation(value);
	}

	private void validation(Integer value) {
		if (value < 1 || value > 6) {
			System.out.println("Invalid Value. Provide a Value between 1 and 6");
			throw new IllegalArgumentException();
		}
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Grade grade = (Grade) o;
		return Objects.equals(value, grade.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}
}
