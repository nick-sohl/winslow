package ch.nicksohl.winslow.infrastructure.adapter;

// Application
import ch.nicksohl.winslow.application.usecase.StudentService;

// Framework
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class StudentConfig {
    @Bean
    StudentService studentService(StudentRepositoryAdapter studentRepositoryAdapter) {
        return new StudentService(studentRepositoryAdapter);
    }
}
