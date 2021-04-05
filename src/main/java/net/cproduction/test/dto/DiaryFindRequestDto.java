package net.cproduction.test.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DiaryFindRequestDto {
    private String condition;
    private String keyword;

    @Builder
    public DiaryFindRequestDto(String condition, String keyword) {
        this.condition = condition;
        this.keyword = keyword;
    }
}
