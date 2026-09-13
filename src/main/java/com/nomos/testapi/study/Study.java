package com.nomos.testapi.study;

import java.time.LocalDate;

public record Study(
        Long id,
        String title,
        int currentParticipants,
        int maxParticipants,
        LocalDate startDate
) {
}
