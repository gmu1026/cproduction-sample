package net.cproduction.test.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import net.cproduction.test.dto.DiaryFindRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static net.cproduction.test.domain.QDiary.diary;

@RequiredArgsConstructor
public class DiaryRepositoryCustomImpl implements DiaryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

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
        QueryResults<Diary> results = queryFactory
                .selectFrom(diary)
                .where(diary.note.contains(requestDto.getKeyword()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageNumber())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
