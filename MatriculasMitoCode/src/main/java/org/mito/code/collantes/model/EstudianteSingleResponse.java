package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record EstudianteSingleResponse(

        @JsonProperty("data")
        EstudianteDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static EstudianteSingleResponse buildExample() {
        return EstudianteSingleResponse.builder()
                .data(EstudianteDTO.builder()
                        .id(1)
                        .nombre("Juan")
                        .apellidos("Pérez")
                        .dni("12345678")
                        .edad(20)
                        .build())
                .notifications(List.of())
                .build();
    }
}
