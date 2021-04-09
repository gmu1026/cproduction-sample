package net.cproduction.test.service;

import lombok.RequiredArgsConstructor;
import net.cproduction.test.domain.Diary;
import net.cproduction.test.domain.DiaryRepository;
import net.cproduction.test.domain.Tag;
import net.cproduction.test.domain.TagRepository;
import net.cproduction.test.dto.DiaryEditRequestDto;
import net.cproduction.test.dto.DiaryFindRequestDto;
import net.cproduction.test.dto.DiaryListResponseDto;
import net.cproduction.test.dto.DiarySaveRequestDto;
import net.cproduction.test.util.LogExecutionTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final TagRepository tagRepository;

    private static final String NOT_FOUND_DIARY = "해당 ID를 가진 다이어리가 없습니다.";
    private static final String NOT_FOUND_TAG = "해당 ID를 가진 태그가 없습니다.";

    @Transactional
    @Override
    public void saveDiary(DiarySaveRequestDto requestDto) {
        Tag tag = tagRepository.findById(requestDto.getTagNo())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_TAG));

        requestDto.setTag(tag);

        diaryRepository.save(requestDto.toEntity());
    }

    @LogExecutionTime(name = "Get Diary List")
    @Transactional(readOnly = true)
    @Override
    public Page<Diary> getDiaryList(Pageable pageable) {
        return diaryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Diary getDiary(long no) {
        return diaryRepository.findById(no).orElseThrow(
                () -> new IllegalArgumentException(NOT_FOUND_DIARY));
    }

    @Transactional
    @Override
    public void editDiary(long no, DiaryEditRequestDto requestDto) {
        Diary diary = diaryRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_DIARY));

        diary.update(requestDto.getTitle(), requestDto.getNote());
    }

    @Transactional
    @Override
    public void deleteDiary(long no) {
        diaryRepository.deleteById(no);
    }

    @LogExecutionTime(name = "Find Diary")
    @Transactional(readOnly = true)
    @Override
    public Page<Diary> findDiary(DiaryFindRequestDto requestDto, Pageable pageable) {
        if (requestDto.getCondition().equals("title")) {
            return diaryRepository.findByTitle(requestDto, pageable);
        } else if (requestDto.getCondition().equals("contents")) {
            return diaryRepository.findByNote(requestDto, pageable);
        } else {
            return null;
        }
    }

    @LogExecutionTime(name = "Paging_diaries")
    @Transactional(readOnly = true)
    @Override
    public Page<DiaryListResponseDto> getDiaries(Pageable pageable) {
        return diaryRepository.findAllDiary(pageable);
    }
}
