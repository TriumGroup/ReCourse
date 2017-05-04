package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.document.impl.StudentProfilePdfGenerator;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.MarkRepository;
import by.triumgroup.recourse.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfiguration {

    @Bean
    public StudentProfilePdfGenerator studentProfilePdfGenerator(
            UserRepository userRepository,
            LessonRepository lessonRepository,
            MarkRepository markRepository) {
        return new StudentProfilePdfGenerator(userRepository, lessonRepository, markRepository);
    }

}
