package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record StudentListResponse(

        @JsonProperty("data")
        List<StudentDTO> data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static StudentListResponse buildExample() {
        return StudentListResponse.builder()
                .data(List.of())
                .notifications(List.of())
                .build();
    }
}