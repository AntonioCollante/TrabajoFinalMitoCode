package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CourseListResponse(

        @JsonProperty("data")
        List<CourseDTO> data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CourseListResponse buildExample() {
        return CourseListResponse.builder()
                .data(List.of())
                .notifications(List.of())
                .build();
    }
}