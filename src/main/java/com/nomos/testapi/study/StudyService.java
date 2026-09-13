package com.nomos.testapi.study;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
public class StudyService {

    private final List<Study> studies = List.of(
            new Study("11111111-1111-4111-8111-111111111111", "알고리즘 스터디",
                    "매주 코딩테스트 문제를 함께 풀어요", "김리더", 4, 6,
                    "recruiting", Instant.parse("2026-09-12T09:00:00Z")),
            new Study("22222222-2222-4222-8222-222222222222", "CS 지식 스터디",
                    "운영체제, 네트워크 기초를 다집니다", "이스터디", 3, 5,
                    "recruiting", Instant.parse("2026-09-10T09:00:00Z")),
            new Study("33333333-3333-4333-8333-333333333333", "모각코",
                    "각자 코드를 짜고 서로 리뷰합니다", "박모각", 8, 8,
                    "in_progress", Instant.parse("2026-09-08T09:00:00Z")),
            new Study("44444444-4444-4444-8444-444444444444", "토이 프로젝트 스터디",
                    "작은 웹 서비스를 함께 만들어봅니다", "최토이", 5, 5,
                    "closed", Instant.parse("2026-09-05T09:00:00Z")),
            new Study("55555555-5555-4555-8555-555555555555", "면접 스터디",
                    "기술 면접 질문을 함께 준비해요", "정면접", 2, 6,
                    "recruiting", Instant.parse("2026-09-13T09:00:00Z"))
    );

    public StudyListResponse getStudies(int page, int limit) {
        List<Study> sorted = studies.stream()
                .sorted(Comparator.comparing(Study::createdAt).reversed())
                .toList();

        int total = sorted.size();
        long offset = (long) (page - 1) * limit;
        int fromIndex = (int) Math.min(offset, total);
        int toIndex = (int) Math.min(offset + limit, total);

        List<StudyResponse> pageItems = sorted.subList(fromIndex, toIndex).stream()
                .map(StudyResponse::from)
                .toList();

        return new StudyListResponse(pageItems, page, limit, total);
    }
}
