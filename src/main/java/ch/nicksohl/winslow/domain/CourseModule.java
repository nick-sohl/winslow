package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.domain.value_object.OrderIndex;

import jakarta.persistence.*;

@Entity
@Table(name = "modules")
public class CourseModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "module_id")
	private Long moduleId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Embedded
	private OrderIndex orderIndex;

	// ? Many entity instances (Modules) can be associated with one instance of another entity (Course)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	Course course;

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderIndex getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(OrderIndex orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
