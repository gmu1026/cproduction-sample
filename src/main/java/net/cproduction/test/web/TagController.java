package net.cproduction.test.web;

import lombok.RequiredArgsConstructor;
import net.cproduction.test.dto.TagEditRequestDto;
import net.cproduction.test.dto.TagSaveRequestDto;
import net.cproduction.test.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TagController {
    private final TagService tagService;

    @GetMapping(value = "/tags")
    public ResponseEntity getTagList() {
        return ResponseEntity.ok(tagService.getTagList());
    }

    @PostMapping(value = "/tag")
    public ResponseEntity saveTag(@RequestBody TagSaveRequestDto requestDto) {
        return ResponseEntity.ok(tagService.savaTag(requestDto));
    }

    @PutMapping(value = "/tag/{no}")
    public ResponseEntity editTag(@PathVariable long no, @RequestBody TagEditRequestDto requestDto) {
        return ResponseEntity.ok(tagService.editTag(no, requestDto));
    }

    @DeleteMapping(value = "/tag/{no}")
    public ResponseEntity removeTag(@PathVariable long no) {
        return ResponseEntity.ok(tagService.removeTag(no));
    }
}
