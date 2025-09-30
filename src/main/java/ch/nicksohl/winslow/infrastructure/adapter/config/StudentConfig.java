package ch.nicksohl.winslow.infrastructure.adapter.config;

// Application Use Cases
import ch.nicksohl.winslow.application.service.StudentService;

// Framework
import ch.nicksohl.winslow.infrastructure.adapter.StudentRepositoryAdapter;
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
