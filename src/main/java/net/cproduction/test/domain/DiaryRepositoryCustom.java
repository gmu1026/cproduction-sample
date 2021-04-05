package net.cproduction.test.domain;

import net.cproduction.test.dto.DiaryFindRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryRepositoryCustom {
    Page<Diary> findByTitle(DiaryFindRequestDto requestDto, Pageable pageable);
    Page<Diary> findByNote(DiaryFindRequestDto requestDto, Pageable pageable);
}
