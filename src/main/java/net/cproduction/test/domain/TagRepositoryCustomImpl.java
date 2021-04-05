package net.cproduction.test.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.cproduction.test.dto.TagListResponseDto;

import java.util.List;

import static net.cproduction.test.domain.QTag.tag;

@RequiredArgsConstructor
public class TagRepositoryCustomImpl implements TagRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagListResponseDto> findAllToDto() {
        return queryFactory
                .select(Projections
                        .constructor(TagListResponseDto.class, tag.no, tag.name))
                .from(tag)
                .orderBy(tag.name.asc())
                .fetch();
    }
}
