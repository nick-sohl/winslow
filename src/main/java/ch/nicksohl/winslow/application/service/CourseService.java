package ch.nicksohl.winslow.application.service;

import ch.nicksohl.winslow.application.cqrs.command.CourseCommand;
import ch.nicksohl.winslow.application.cqrs.dto.CourseDto;
import ch.nicksohl.winslow.application.cqrs.dto.DepartmentDto;
import ch.nicksohl.winslow.application.port.CourseRepositoryInterface;
import ch.nicksohl.winslow.domain.Course;
import ch.nicksohl.winslow.domain.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseService {
	CourseRepositoryInterface courseRepository;
	DepartmentService departmentService;

	public CourseService(CourseRepositoryInterface courseRepository, DepartmentService departmentService) {
		this.courseRepository = courseRepository;
		this.departmentService = departmentService;
	}

	public List<CourseDto> findAllCourses() {
		List<Course> courses = courseRepository.findAllCourses();
		List<CourseDto> listOfCoursesDto = new ArrayList<>();
		// Map each course in courses list to DTO
		for (Course course : courses) {
			// Use helper method
			CourseDto courseDto = mapToDto(course);
			// Add Dto to new ArrayList
			listOfCoursesDto.add(courseDto);
		}
		return listOfCoursesDto;
	}

	// Return a DTO -> GET request
	public CourseDto findCourseById(int course_id) {
		Optional<Course> optionalCourse = courseRepository.findCourseById(course_id);
		// Convert Optional to Object with helper-method
		Course course = getCourseOrEmpty(optionalCourse);
		// Map Object to DTO
		return mapToDto(course);
	}

	// Use a Command to define data in the DB
	public CourseDto saveCourse(CourseCommand courseCommand) {
		int departmentId = courseCommand.departmentId();
		// Use the Department Service to retrieve the Department with an ID from the DB.
		DepartmentDto departmentDto = departmentService.findDepartmentById(departmentId);
		Department department = Department.fromDto(departmentDto);

		Course course = new Course();
		course.setTitle(courseCommand.title());
		course.setDescription(courseCommand.description());
		course.setGoals(courseCommand.goals());
		// ? To set the Department we need functionality to get the Department with its ID from the DB.
		// ? For that we first must use a Service, to retrieve the Department with a Repository from the DB.
		course.setDepartment(department);

		// Save course in the DB
		course = courseRepository.saveCourse(course);
		// Map course to dto
		return mapToDto(course);
	}

	public void deleteCourseById(int courseId) {
		courseRepository.deleteCourseById(courseId);
	}

	public CourseDto updateCourse(int courseId, CourseDto courseDto) {
		Optional<Course> optionalCourse = courseRepository.findCourseById(courseId);
		// Convert Optional to Object with helper-method
		Course course = getCourseOrEmpty(optionalCourse);

		// ? Update course -> Method inside Course Class
		// The Method inside the Controller provides the Request Body which we put in as an argument, as we use this method in the Controller Class
		course.updateFromDto(courseDto);

		Course updatedCourse = courseRepository.saveCourse(course);

		// Map updated course to DTO
		return mapToDto(updatedCourse);
	}

	public CourseDto mapToDto(Course course) {
		// Get the Department Object from the course
		Department department = course.getDepartment();
		// Map the Object to a DTO
		DepartmentDto departmentDto = department != null ?
				new DepartmentDto(department.getId(), department.getName()) : null;

		return new CourseDto (
				course.getCourseId(),
				course.getTitle(),
				course.getDescription(),
				course.getGoals(),
				departmentDto // Add DepartmentDto to CourseDto
		);
	}

	private Course getCourseOrEmpty(Optional<Course> courseOptional) {
		return courseOptional.orElse(new Course());
	}
}
