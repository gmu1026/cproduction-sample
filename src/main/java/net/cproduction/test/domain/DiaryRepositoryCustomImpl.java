package net.cproduction.test.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.cproduction.test.dto.DiaryFindRequestDto;
import net.cproduction.test.dto.DiaryListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static net.cproduction.test.domain.QDiary.diary;

@RequiredArgsConstructor
public class DiaryRepositoryCustomImpl implements DiaryRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 0;

    @Override
    public Page<Diary> findByTitle(DiaryFindRequestDto requestDto, Pageable pageable) {
        QueryResults<Diary> results = queryFactory
                .selectFrom(diary)
                .where(diary.title.contains(requestDto.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<Diary> findByNote(DiaryFindRequestDto requestDto, Pageable pageable) {
        QueryResults<Diary> diaries = queryFactory
                .selectFrom(diary)
                .where(diary.note.contains(requestDto.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(diaries.getResults(), pageable, diaries.getTotal());
    }

    @Override
    public Page<DiaryListResponseDto> findAllDiary(Pageable pageable) {
        QueryResults<DiaryListResponseDto> diaries = queryFactory
                .select(Projections.constructor(DiaryListResponseDto.class,
                        diary.no, diary.title, diary.tag.name, diary.createdDate))
                .from(diary)
                .orderBy(diary.no.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(diaries.getResults(), pageable, diaries.getTotal());
    }

    public List<DiaryListResponseDto> paginationCoveringIndex(DiaryFindRequestDto requestDto, Pageable pageable) {
        List<Long> indexes = queryFactory
                .select(diary.no)
                .from(diary)
                .where(diary.title.like(requestDto.getKeyword() + "%"))
                .orderBy(diary.no.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(indexes)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(DiaryListResponseDto.class,
                        diary.no,
                        diary.title,
                        diary.tag.name,
                        diary.createdDate))
                .from(diary)
                .where(diary.no.in(indexes))
                .orderBy(diary.no.desc())
                .fetch();
    }
}
