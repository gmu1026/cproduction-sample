package net.cproduction.test.service;

import net.cproduction.test.dto.TagEditRequestDto;
import net.cproduction.test.dto.TagListResponseDto;
import net.cproduction.test.dto.TagSaveRequestDto;

import java.util.List;

public interface TagService {
    boolean savaTag(TagSaveRequestDto requestDto);
    List<TagListResponseDto> getTagList();
    boolean editTag(long no, TagEditRequestDto requestDto);
    boolean removeTag(long no);
}
