package ch.nicksohl.winslow.presentation.api;

// Application
import ch.nicksohl.winslow.application.cqrs.command.StudentCommand;
import ch.nicksohl.winslow.application.cqrs.dto.StudentDto;
import ch.nicksohl.winslow.application.service.StudentService;

// Framework
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

// Java SDK
import java.util.List;

@RestController
@RequestMapping("/api/students")
class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"", "/"})
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "/{studentId}", produces = "application/json")
    @ResponseBody
    public StudentDto getStudent(@PathVariable("studentId") int studentId) {
        return studentService.getStudent(studentId);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
    }

    @PostMapping(value = {"", "/"}, consumes = "application/json", produces = "application/json")
    @Transactional
    // The request body gets read and deserialized into an Object through an HttpMessageReader.
    public StudentDto addStudent(@RequestBody StudentCommand request) {
        return studentService.addStudent(request);
    }
}
