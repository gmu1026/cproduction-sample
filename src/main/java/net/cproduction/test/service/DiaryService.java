package net.cproduction.test.service;

import net.cproduction.test.domain.Diary;
import net.cproduction.test.dto.DiaryEditRequestDto;
import net.cproduction.test.dto.DiaryFindRequestDto;
import net.cproduction.test.dto.DiaryListResponseDto;
import net.cproduction.test.dto.DiarySaveRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryService {
    void saveDiary(DiarySaveRequestDto requestDto);
    Page<Diary> getDiaryList(Pageable pageable);
    Diary getDiary(long no);
    void editDiary(long no, DiaryEditRequestDto requestDto);
    void deleteDiary(long no);
    Page<Diary> findDiary(DiaryFindRequestDto requestDto, Pageable pageable);
    Page<DiaryListResponseDto> getDiaries(Pageable pageable);
}
