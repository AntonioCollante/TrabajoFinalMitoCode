package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CursoOperacionResponse(

        @JsonProperty("data")
        OperacionStatusDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CursoOperacionResponse ok(String mensaje) {
        return CursoOperacionResponse.builder()
                .data(OperacionStatusDTO.ok(mensaje))
                .notifications(List.of())
                .build();
    }
}