package com.nomos.testapi.study;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudyService {

    private final List<Study> studies = List.of(
            new Study(1L, "알고리즘 스터디", 4, 6, LocalDate.of(2026, 9, 20)),
            new Study(2L, "CS 지식 스터디", 3, 5, LocalDate.of(2026, 9, 22)),
            new Study(3L, "모각코", 6, 8, LocalDate.of(2026, 10, 1))
    );

    public List<Study> findAll() {
        return studies;
    }
}
