package net.cproduction.test.service;

import net.cproduction.test.domain.Diary;
import net.cproduction.test.domain.DiaryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DiaryServiceTest {
    @Autowired
    private DiaryRepository diaryRepository;

    private static final String TITLE = "TEST_TITLE";

    private static final String NOTE = "TEST_NOTE";

    private static final String NOT_FOUND_DIARY_MESSAGE = "해당 ID를 가진 다이어리가 존재하지 않습니다.";

    @Transactional
    @DisplayName("다이어리_저장_테스트")
    @Test
    void saveDiary() throws Exception {
        Diary diary = Diary.builder()
                .title(TITLE)
                .note(NOTE)
                .build();

        Long id = diaryRepository.save(diary).getNo();

        List<Diary> diaries = diaryRepository.findAll();

        assertThat(diaries.get(0).getTitle()).isEqualTo(TITLE);
        assertThat(diaries.get(0).getNote()).isEqualTo(NOTE);
    }

    @Transactional
    @DisplayName("다이어리목록_가져오기_테스트")
    @Test
    void getDiaryList() throws Exception {
        for (int i = 0; i < 3; i++) {
            Diary diary = Diary.builder()
                    .title(TITLE + i)
                    .note(NOTE + i)
                    .build();

            diaryRepository.save(diary);
        }
        List<Diary> diaries = diaryRepository.findAll();
        assertThat(diaries.size()).isEqualTo(3);
        assertThat(diaries.get(0).getTitle()).isEqualTo(TITLE + 0);
        assertThat(diaries.get(1).getTitle()).isEqualTo(TITLE + 1);
        assertThat(diaries.get(2).getTitle()).isEqualTo(TITLE + 2);
    }

    @Transactional
    @DisplayName("다이어리_가져오기_테스트")
    @Test
    void getDiary() throws Exception {
        Diary diary = Diary.builder()
                .title(TITLE)
                .note(NOTE)
                .build();

        Long id = diaryRepository.save(diary).getNo();

        Diary target = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        NOT_FOUND_DIARY_MESSAGE));

        assertThat(target.getTitle()).isEqualTo(TITLE);
        assertThat(target.getNote()).isEqualTo(NOTE);
    }

    @Transactional
    @DisplayName("다이어리_수정_테스트")
    @Test
    void editDiary() throws Exception {
        Diary diary = Diary.builder()
                .title(TITLE)
                .note(NOTE)
                .build();

        Long id = diaryRepository.save(diary).getNo();

        Diary target = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        NOT_FOUND_DIARY_MESSAGE));

        target.update(TITLE + 0, NOTE + 0);

        List<Diary> diaries = diaryRepository.findAll();

        assertThat(diaries.get(0).getTitle()).isEqualTo(TITLE + 0);
        assertThat(diaries.get(0).getNote()).isEqualTo(NOTE + 0);
    }

    @Transactional
    @DisplayName("다이어리_삭제_테스트")
    @Test
    void deleteDiary() throws Exception {
        Diary diary = Diary.builder()
                .title(TITLE)
                .note(NOTE)
                .build();

        Long id = diaryRepository.save(diary).getNo();
        assertThat(diaryRepository.findAll().size()).isEqualTo(1);

        diaryRepository.deleteById(id);
        List<Diary> diaries = diaryRepository.findAll();

        assertThat(diaries.size()).isEqualTo(0);
    }
}