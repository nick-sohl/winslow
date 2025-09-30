package ch.nicksohl.winslow.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "schools")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "school_id", nullable = false)
	private int school_id;

	@Column(name = "name", nullable = false, length = 200)
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JoinColumn(name = "address_id")
	Address address;

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
