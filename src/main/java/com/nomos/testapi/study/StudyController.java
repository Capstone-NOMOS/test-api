package com.nomos.testapi.study;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studies")
public class StudyController {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_LIMIT = 20;
    private static final int MAX_LIMIT = 100;

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping
    public ResponseEntity<?> getStudies(
            @RequestParam(name = "page", defaultValue = "" + DEFAULT_PAGE) String pageParam,
            @RequestParam(name = "limit", defaultValue = "" + DEFAULT_LIMIT) String limitParam) {

        Integer page = parsePositiveInt(pageParam);
        if (page == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("page must be a positive integer"));
        }

        Integer limit = parsePositiveInt(limitParam);
        if (limit == null || limit > MAX_LIMIT) {
            return ResponseEntity.badRequest().body(new ErrorResponse("limit must be a positive integer up to " + MAX_LIMIT));
        }

        return ResponseEntity.ok(studyService.getStudies(page, limit));
    }

    private Integer parsePositiveInt(String value) {
        try {
            int parsed = Integer.parseInt(value.trim());
            return parsed >= 1 ? parsed : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
