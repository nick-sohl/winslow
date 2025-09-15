package ch.nicksohl.winslow.infrastructure.adapter;

// Application
import ch.nicksohl.winslow.application.usecase.StudentService;

// Framework
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class StudentConfig {
    @Bean
    StudentService studentService(StudentRepositoryAdapter studentRepositoryAdapter, EntityManager entityManager) {
        return new StudentService(studentRepositoryAdapter, entityManager);
    }
}
