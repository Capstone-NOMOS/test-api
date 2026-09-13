package com.nomos.testapi.study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsDefaultPageSortedByCreatedAtDescending() throws Exception {
        mockMvc.perform(get("/api/studies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.limit").value(20))
                .andExpect(jsonPath("$.total").value(5))
                .andExpect(jsonPath("$.studies.length()").value(5))
                .andExpect(jsonPath("$.studies[0].title").value("면접 스터디"))
                .andExpect(jsonPath("$.studies[0].leaderName").value("정면접"))
                .andExpect(jsonPath("$.studies[0].memberCount").value(2))
                .andExpect(jsonPath("$.studies[0].maxMembers").value(6))
                .andExpect(jsonPath("$.studies[0].status").value("recruiting"))
                .andExpect(jsonPath("$.studies[4].title").value("토이 프로젝트 스터디"));
    }

    @Test
    void appliesPagination() throws Exception {
        mockMvc.perform(get("/api/studies").param("page", "2").param("limit", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(2))
                .andExpect(jsonPath("$.limit").value(2))
                .andExpect(jsonPath("$.total").value(5))
                .andExpect(jsonPath("$.studies.length()").value(2));
    }

    @Test
    void returnsEmptyListWhenPageBeyondTotal() throws Exception {
        mockMvc.perform(get("/api/studies").param("page", "10").param("limit", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(5))
                .andExpect(jsonPath("$.studies.length()").value(0));
    }

    @Test
    void rejectsZeroPage() throws Exception {
        mockMvc.perform(get("/api/studies").param("page", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void rejectsNonIntegerPage() throws Exception {
        mockMvc.perform(get("/api/studies").param("page", "abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void rejectsLimitAboveMax() throws Exception {
        mockMvc.perform(get("/api/studies").param("limit", "101"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void rejectsNonPositiveLimit() throws Exception {
        mockMvc.perform(get("/api/studies").param("limit", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
