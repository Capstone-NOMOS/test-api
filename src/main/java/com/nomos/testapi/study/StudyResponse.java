package com.nomos.testapi.study;

import java.time.LocalDate;

public record StudyResponse(
        Long id,
        String title,
        ParticipantInfo participants,
        LocalDate startDate
) {

    public record ParticipantInfo(int current, int max) {
    }

    public static StudyResponse from(Study study) {
        return new StudyResponse(
                study.id(),
                study.title(),
                new ParticipantInfo(study.currentParticipants(), study.maxParticipants()),
                study.startDate()
        );
    }
}
