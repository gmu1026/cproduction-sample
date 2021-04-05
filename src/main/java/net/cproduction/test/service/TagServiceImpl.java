package net.cproduction.test.service;

import lombok.RequiredArgsConstructor;
import net.cproduction.test.domain.Tag;
import net.cproduction.test.domain.TagRepository;
import net.cproduction.test.dto.TagEditRequestDto;
import net.cproduction.test.dto.TagListResponseDto;
import net.cproduction.test.dto.TagSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    private static final String NOT_FOUND_TAG = "해당 ID를 가진 태그가 없습니다.";

    @Transactional
    @Override
    public boolean savaTag(TagSaveRequestDto requestDto) {
        tagRepository.save(requestDto.toEntity());

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TagListResponseDto> getTagList() {
        return tagRepository.findAllToDto();
    }

    @Transactional
    @Override
    public boolean editTag(long no, TagEditRequestDto requestDto) {
        Tag tag = tagRepository.findById(no)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_TAG));
        tag.update(requestDto.getName());

        return true;
    }

    @Transactional
    @Override
    public boolean removeTag(long no) {
        tagRepository.deleteById(no);

        return true;
    }
}
