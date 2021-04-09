package net.cproduction.test.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.cproduction.test.domain.Diary;
import net.cproduction.test.dto.DiaryEditRequestDto;
import net.cproduction.test.dto.DiaryFindRequestDto;
import net.cproduction.test.dto.DiaryListResponseDto;
import net.cproduction.test.dto.DiarySaveRequestDto;
import net.cproduction.test.service.DiaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Controller
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping(value = "/")
    @ResponseBody
    public Page<DiaryListResponseDto> getDiaryList(Pageable pageable) {
        return diaryService.getDiaries(pageable);
    }

    @GetMapping(value = "/diaries")
    public ModelAndView showDiaryList(@PageableDefault(sort = { "no" }, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("diary/diaryList");

        Page<Diary> pages = diaryService.getDiaryList(pageable);
        List<Diary> diaries = pages.stream().collect(Collectors.toList());

        modelAndView.addObject("diaries", diaries);
        modelAndView.addObject("offset", pages.getTotalPages());
        modelAndView.addObject("currentPage", pages.getPageable().getPageNumber());
        modelAndView.addObject("type", "main");

        return modelAndView;
    }

    @GetMapping(value = "/diary")
    public String registerDiaryForm(@ModelAttribute DiarySaveRequestDto requestDto, Model model) {
        model.addAttribute("requestDto", requestDto);
//        model.addAttribute("tags", tagRepository.findAll());

        return "diary/diaryRegisterForm";
    }

    @PostMapping(value = "/diary")
    public String saveDiary(DiarySaveRequestDto requestDto) {
        diaryService.saveDiary(requestDto);

        return "redirect:/";
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

        return new ModelAndView(new RedirectView("/"));
    }

    @GetMapping(value = "/diaries/search")
    public ModelAndView findDiary(@ModelAttribute DiaryFindRequestDto requestDto,
                                  @PageableDefault(sort = { "no" }, direction = Sort.Direction.DESC) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("diary/diaryList");

        Page<Diary> diaries = diaryService.findDiary(requestDto, pageable);
        modelAndView.addObject("diaries", diaries.stream().collect(Collectors.toList()));
        modelAndView.addObject("offset",diaries.getTotalPages());
        modelAndView.addObject("type", "search");
        modelAndView.addObject("condition", requestDto.getCondition());
        modelAndView.addObject("keyword", requestDto.getKeyword());

        return modelAndView;
    }
}
