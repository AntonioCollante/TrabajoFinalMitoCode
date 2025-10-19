package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CursoSingleResponse(

        @JsonProperty("data")
        CursoDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CursoSingleResponse buildExample() {
        return CursoSingleResponse.builder()
                .data(CursoDTO.builder()
                        .id(101)
                        .nombre("Matemáticas")
                        .siglas("MAT101")
                        .estado(true)
                        .build())
                .notifications(List.of())
                .build();
    }
}