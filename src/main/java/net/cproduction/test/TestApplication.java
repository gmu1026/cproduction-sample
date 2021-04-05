package net.cproduction.test;

import net.cproduction.test.domain.Diary;
import net.cproduction.test.domain.DiaryRepository;
import net.cproduction.test.domain.Tag;
import net.cproduction.test.domain.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public CommandLineRunner setSampleData(DiaryRepository diaryRepository, TagRepository tagRepository) {
        return (args) -> {
            for (int i = 0; i < 3; i++) {
                Tag tag = Tag.builder()
                        .name("태그" + i)
                        .build();

                tagRepository.save(tag);
            }

            Random random = new Random();
            List<Tag> tags = tagRepository.findAll();
            for (int i = 0; i < 100; i++) {
                Diary diary = Diary.builder()
                        .title("title" + i)
                        .note("<p>note" + i + "</p>")
                        .tag(tags.get(random.nextInt(3)))
                        .build();

                diaryRepository.save(diary);
            }
        };
    }
}
