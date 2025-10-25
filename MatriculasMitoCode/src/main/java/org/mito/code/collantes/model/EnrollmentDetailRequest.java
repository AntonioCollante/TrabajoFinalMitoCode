package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EnrollmentDetailRequest(

        @JsonProperty("curso")
        CourseRequest curso,

        @JsonProperty("aula")
        String aula

) {
    public static EnrollmentDetailRequest buildExample() {
        return EnrollmentDetailRequest.builder()
                .curso(CourseRequest.buildExample())
                .aula("Aula 202")
                .build();
    }
}