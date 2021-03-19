package net.cproduction.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.cproduction.test.domain.Diary;

@Getter
public class DiarySaveRequestDto {
    private String title;
    private String note;

    @Builder
    public DiarySaveRequestDto(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public Diary toEntity() {
        return Diary.builder()
                .title(this.title)
                .note(this.note)
                .build();
    }
}
