package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record EstudianteOperacionResponse(

        @JsonProperty("data")
        OperacionStatusDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static EstudianteOperacionResponse ok(String mensaje) {
        return EstudianteOperacionResponse.builder()
                .data(OperacionStatusDTO.ok(mensaje))
                .notifications(List.of())
                .build();
    }
}