package ch.nicksohl.winslow.presentation.api;

import ch.nicksohl.winslow.application.cqrs.command.CourseCommand;
import ch.nicksohl.winslow.application.cqrs.dto.CourseDto;
import ch.nicksohl.winslow.application.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping({"", "/"})
	List<CourseDto> courses() {
		return courseService.findAllCourses();
	}

	@GetMapping(value = "/{courseId}", produces = "application/json")
	@ResponseStatus
	CourseDto getCourse(@PathVariable("courseId") int course_id) {
		return courseService.findCourseById(course_id);
	}

	@PostMapping(value = {"", "/"}, consumes = "application/json", produces = "application/json")
	@Transactional
	CourseDto addCourse(@RequestBody CourseCommand courseCommand) {
		return courseService.saveCourse(courseCommand);
	}

	@DeleteMapping("/{courseId}")
	ResponseEntity<Void> deleteCourseById(@PathVariable("courseId") int courseId) {
		courseService.deleteCourseById(courseId);
		return ResponseEntity.noContent().build(); // ? returns 204 no content status code
	}

	@PutMapping("/{courseId}")
	public CourseDto updateCourse(@RequestBody CourseDto courseDto, @PathVariable("courseId") int courseId ) {
		return courseService.updateCourse(courseId, courseDto);
	}
}
