package net.cproduction.test.dto;

import lombok.Builder;
import lombok.Getter;
import net.cproduction.test.domain.Tag;

@Getter
public class TagSaveRequestDto {
    private String name;

    @Builder
    public TagSaveRequestDto(String name) {
        this.name = name;
    }

    public Tag toEntity() {
        return Tag.builder()
                .name(this.name)
                .build();
    }
}
