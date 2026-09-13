package com.nomos.testapi.study;

import java.time.Instant;

public record StudyResponse(
        String id,
        String title,
        String description,
        String leaderName,
        int memberCount,
        int maxMembers,
        String status,
        Instant createdAt
) {

    public static StudyResponse from(Study study) {
        return new StudyResponse(
                study.id(),
                study.title(),
                study.description(),
                study.leaderName(),
                study.memberCount(),
                study.maxMembers(),
                study.status(),
                study.createdAt()
        );
    }
}
