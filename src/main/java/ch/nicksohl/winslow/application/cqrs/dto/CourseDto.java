package ch.nicksohl.winslow.application.cqrs.dto;

public record CourseDto(int courseId, String title, String description, String goals, DepartmentDto department) {

	// ! Problem: Record fields are final and immutable. To update them, we can use a Wither method.
	// ? URL: https://stackoverflow.com/questions/65253856/set-value-to-one-of-the-property-in-java-15-record
	public CourseDto withId(int course_id) {
		return new CourseDto(course_id, title(), description(), goals(), department());
	}
}
