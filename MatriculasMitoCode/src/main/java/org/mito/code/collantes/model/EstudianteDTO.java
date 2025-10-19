package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EstudianteDTO(

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

) {}