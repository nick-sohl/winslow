package ch.nicksohl.winslow.domain.value_object;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// ? Avoid "magic ints" -> Value Object
@Embeddable
public class OrderIndex {
	@Column(name = "order_index", nullable = false)
	private Integer value;

	protected OrderIndex() {} // JPA

	public OrderIndex(Integer value) {
		if (value == null || value < 1) {
			throw new IllegalArgumentException("Order index must be >= 1");
		}
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
