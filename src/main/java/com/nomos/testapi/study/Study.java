package com.nomos.testapi.study;

import java.time.Instant;

public record Study(
        String id,
        String title,
        String description,
        String leaderName,
        int memberCount,
        int maxMembers,
        String status,
        Instant createdAt
) {
}
