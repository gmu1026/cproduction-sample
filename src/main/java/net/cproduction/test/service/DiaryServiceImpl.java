package net.cproduction.test.service;

import lombok.RequiredArgsConstructor;
import net.cproduction.test.domain.Diary;
import net.cproduction.test.domain.DiaryRepository;
import net.cproduction.test.dto.DiaryEditRequestDto;
import net.cproduction.test.dto.DiarySaveRequestDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    private static final String NOT_FOUND_MESSAGE = "해당 ID를 가진 다이어리가 없습니다.";

    @Transactional
    @Override
    public void saveDiary(DiarySaveRequestDto requestDto) {
        diaryRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Diary> getDiaryList() {
        return diaryRepository.findAll(Sort.by("no").descending());
    }

    @Transactional(readOnly = true)
    @Override
    public Diary getDiary(long no) {
        return diaryRepository.findById(no).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_MESSAGE));
    }

    @Transactional
    @Override
    public void editDiary(long no, DiaryEditRequestDto requestDto) {
        Diary diary = diaryRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_MESSAGE));

        diary.update(requestDto.getTitle(), requestDto.getNote());
    }

    @Transactional
    @Override
    public void deleteDiary(long no) {
        diaryRepository.deleteById(no);
    }
}
