package ch.nicksohl.winslow.infrastructure.adapter;

import ch.nicksohl.winslow.application.port.CourseRepositoryInterface;
import ch.nicksohl.winslow.domain.Course;
import ch.nicksohl.winslow.infrastructure.persistence.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRepositoryAdapter implements CourseRepositoryInterface {
	CourseRepository courseRepository;

	public CourseRepositoryAdapter(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> findCourseById(int course_id) {
		return courseRepository.findById(course_id);
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourseById(int course_id) {
		courseRepository.deleteById(course_id);
	}
}
