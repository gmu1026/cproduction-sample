package net.cproduction.test.web;

import net.cproduction.test.domain.Diary;
import net.cproduction.test.domain.DiaryRepository;
import net.cproduction.test.service.DiaryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DiaryController.class)
class DiaryControllerTest {
    private static final long TEST_DIARY_NO = 0;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiaryService diaryService;

    @DisplayName("메인페이지_검증")
    @Test
    void index() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @DisplayName("다이어리_목록페이지_검증")
    @Test
    void showDiaryList() throws Exception {
        mockMvc.perform(get("/diaries"))
                .andExpect(status().isOk())
                .andExpect(view().name("diary/diaryList"));
        // TODO 다이어리 더미 데이터 넣고 내용에 대한 검증 부분도 구현 필요
    }

    @DisplayName("다이어리_등록페이지_검증")
    @Test
    void registerDiaryForm() throws Exception {
        mockMvc.perform(get("/diary"))
                .andExpect(status().isOk())
                .andExpect(view().name("diary/diaryRegisterForm"));
    }

    @DisplayName("다이어리_저장기능_검증")
    @Test
    void saveDiary() throws Exception {
        mockMvc.perform(post("/diary")
                .param("title", "테스트")
                .param("note", "테스트 내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/diaries"));
    }

    @DisplayName("다이어리_조회기능_검증")
    @Test
    void getDiary() throws Exception {
        Diary diary = Diary.builder()
                .title("test")
                .note("notes")
                .build();

        given(diaryService.getDiary(TEST_DIARY_NO)).willReturn(diary);

        mockMvc.perform(get("/diary/{no}", TEST_DIARY_NO))
                .andExpect(status().isOk())
                .andExpect(view().name("diary/diaryDetails"))
                .andExpect(model().attributeExists("diary"));
    }
}