package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CourseDTO(

        @JsonProperty("id")
        Integer id,

        @JsonProperty("nombre")
        String nombre,

        @JsonProperty("siglas")
        String siglas,

        @JsonProperty("estado")
        Boolean estado

) {}