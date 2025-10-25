package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CourseSingleResponse(

        @JsonProperty("data")
        CourseDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CourseSingleResponse buildExample() {
        return CourseSingleResponse.builder()
                .data(CourseDTO.builder()
                        .id(101)
                        .nombre("Matemáticas")
                        .siglas("MAT101")
                        .estado(true)
                        .build())
                .notifications(List.of())
                .build();
    }
}