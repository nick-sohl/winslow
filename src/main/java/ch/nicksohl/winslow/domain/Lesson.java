package ch.nicksohl.winslow.domain;

import ch.nicksohl.winslow.domain.value_object.OrderIndex;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lesson_id")
	private Integer lessonId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Embedded
	private OrderIndex orderIndex;

	// ? Many entity instances (Lessons) can be associated with one instance of another entity (Modules)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
	private CourseModule courseModule;

	public Integer getLessonId() {
		return lessonId;
	}

	private void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public CourseModule getModule() {
		return courseModule;
	}

	private void setModule(CourseModule courseModule) {
		this.courseModule = courseModule;
	}

}
