package com.nomos.testapi.study;

import java.util.List;

public record StudyListResponse(
        List<StudyResponse> studies,
        int page,
        int limit,
        int total
) {
}
