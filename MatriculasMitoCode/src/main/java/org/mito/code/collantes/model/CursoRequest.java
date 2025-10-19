package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CursoRequest(

        @JsonProperty("id")
        Integer id,

        @JsonProperty("nombre")
        String nombre,

        @JsonProperty("siglas")
        String siglas,

        @JsonProperty("estado")
        Boolean estado

) {
    public static CursoRequest buildExample() {
        return CursoRequest.builder()
                .id(101)
                .nombre("Matemáticas")
                .siglas("MAT101")
                .estado(true)
                .build();
    }
}