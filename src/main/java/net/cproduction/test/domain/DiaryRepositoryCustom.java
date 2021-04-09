package net.cproduction.test.domain;

import net.cproduction.test.dto.DiaryFindRequestDto;
import net.cproduction.test.dto.DiaryListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryRepositoryCustom {
    Page<Diary> findByTitle(DiaryFindRequestDto requestDto, Pageable pageable);
    Page<Diary> findByNote(DiaryFindRequestDto requestDto, Pageable pageable);
    Page<DiaryListResponseDto> findAllDiary(Pageable pageable);
}
