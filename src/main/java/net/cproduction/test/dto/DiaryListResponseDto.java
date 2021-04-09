package net.cproduction.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DiaryListResponseDto {
    private Long no;
    private String title;
    private String tagName;
    private LocalDateTime createdDate;
}
