package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EnrollmentRequest(

        @JsonProperty("fechaMatricula")
        LocalDateTime fechaMatricula,

        @JsonProperty("estudiante")
        StudentRequest estudiante,

        @JsonProperty("detalles")
        List<EnrollmentDetailRequest> detalles,

        @JsonProperty("estado")
        Boolean estado

) {
    public static EnrollmentRequest buildExample() {
        return EnrollmentRequest.builder()
                .fechaMatricula(LocalDateTime.now())
                .estudiante(StudentRequest.buildExample())
                .detalles(List.of(EnrollmentDetailRequest.buildExample()))
                .estado(true)
                .build();
    }
}