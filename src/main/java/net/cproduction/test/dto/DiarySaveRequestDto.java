package net.cproduction.test.dto;

import lombok.Builder;
import lombok.Getter;
import net.cproduction.test.domain.Diary;
import net.cproduction.test.domain.Tag;

@Getter
public class DiarySaveRequestDto {
    private String title;
    private String note;
    private Long tagNo;
    private Tag tag;

    @Builder
    public DiarySaveRequestDto(String title, String note, Long tagNo) {
        this.title = title;
        this.note = note;
        this.tagNo = tagNo;
    }

    public Diary toEntity() {
        return Diary.builder()
                .title(this.title)
                .note(this.note)
                .tag(this.tag)
                .build();
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
