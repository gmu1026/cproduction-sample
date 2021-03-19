package net.cproduction.test.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.cproduction.test.dto.DiaryEditRequestDto;
import net.cproduction.test.dto.DiarySaveRequestDto;
import net.cproduction.test.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@Slf4j
@Controller
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping(value = "/")
    @ResponseBody
    public String index() {
        return "main";
    }

    @GetMapping(value = "/diaries")
    public ModelAndView showDiaryList() {
        ModelAndView modelAndView = new ModelAndView("diary/diaryList");

        modelAndView.addObject("diaries", diaryService.getDiaryList());

        return modelAndView;
    }

    @GetMapping(value = "/diary")
    public String registerDiaryForm(@ModelAttribute DiarySaveRequestDto requestDto, Model model) {
        model.addAttribute("requestDto", requestDto);

        return "diary/diaryRegisterForm";
    }

    @PostMapping(value = "/diary")
    public String saveDiary(DiarySaveRequestDto requestDto) {
        diaryService.saveDiary(requestDto);

        return "redirect:/diaries";
    }

    @GetMapping(value = "/diary/{no}")
    public ModelAndView getDiary(@PathVariable("no") long no) {
        ModelAndView modelAndView = new ModelAndView("diary/diaryDetails");
        modelAndView.addObject("diary", diaryService.getDiary(no));

        return modelAndView;
    }

    @GetMapping(value = "/diary/edit/{no}")
    public ModelAndView editDiaryForm(@PathVariable("no") long no, DiaryEditRequestDto requestDto) {
        ModelAndView modelAndView = new ModelAndView("diary/diaryEditForm");
        modelAndView.addObject("diary", diaryService.getDiary(no));
        modelAndView.addObject("requestDto", requestDto);

        return modelAndView;
    }

    @PutMapping(value = "/diary/{no}")
    public ModelAndView editDiary(@PathVariable("no") long no, DiaryEditRequestDto requestDto) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/diary/" + no));

        diaryService.editDiary(no, requestDto);

        return modelAndView;
    }

    @DeleteMapping(value = "/diary/{no}")
    public ModelAndView deleteDiary(@PathVariable("no") long no) {
        diaryService.deleteDiary(no);

        return new ModelAndView(new RedirectView("/diaries"));
    }
}
