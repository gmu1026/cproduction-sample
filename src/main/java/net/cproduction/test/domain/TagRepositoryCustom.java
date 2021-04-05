package net.cproduction.test.domain;

import net.cproduction.test.dto.TagListResponseDto;

import java.util.List;

public interface TagRepositoryCustom {
    List<TagListResponseDto> findAllToDto();
}
