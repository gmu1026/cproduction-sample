package net.cproduction.test.service;

import net.cproduction.test.domain.Diary;
import net.cproduction.test.dto.DiaryEditRequestDto;
import net.cproduction.test.dto.DiarySaveRequestDto;

import java.util.List;

public interface DiaryService {
    void saveDiary(DiarySaveRequestDto requestDto);
    List<Diary> getDiaryList();
    Diary getDiary(long no);
    void editDiary(long no, DiaryEditRequestDto requestDto);
    void deleteDiary(long no);
}
