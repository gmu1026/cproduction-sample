package net.cproduction.test.dto;

import lombok.Builder;
import lombok.Getter;

/*
    NoArgsConstructor가 존재 시 서버로 넘어온 value를 DTO에 저장하지 못함? 왜 인지 찾아볼것
    1) 모든 속성을 가지는 생성자를 만들었을때 받아짐
    2) 서버로 넘어온 데이터를 DTO에 대입하는 과정에 대해서 조사필요
    3) TEXTAREA 태그 내 밸류넣기
    4) th:field 없는 것이 영향을 주었는가 조사
 */
@Getter
public class DiaryEditRequestDto {
    private String title;
    private String note;

    @Builder
    public DiaryEditRequestDto(String title, String note) {
        this.title = title;
        this.note = note;
    }
}
