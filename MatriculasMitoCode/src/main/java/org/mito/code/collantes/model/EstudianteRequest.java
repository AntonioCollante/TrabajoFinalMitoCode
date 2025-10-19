package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EstudianteRequest(

        @JsonProperty("id")
        Integer id,

        @JsonProperty("nombre")
        String nombre,

        @JsonProperty("apellidos")
        String apellidos,

        @JsonProperty("dni")
        String dni,

        @JsonProperty("edad")
        Integer edad

) {
    public static EstudianteRequest buildExample() {
        return EstudianteRequest.builder()
                .id(1)
                .nombre("Juan")
                .apellidos("Pérez")
                .dni("12345678")
                .edad(20)
                .build();
    }
}