package ch.nicksohl.winslow.application.port;

import ch.nicksohl.winslow.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepositoryInterface {
	List<Course> findAllCourses();
	Optional<Course> findCourseById(int course_id);
	Course saveCourse(Course course);
	void deleteCourseById(int course_id);
}
