package net.cproduction.test.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TagEditRequestDto {
    private String name;

    @Builder
    public TagEditRequestDto(String name) {
        this.name = name;
    }
}
