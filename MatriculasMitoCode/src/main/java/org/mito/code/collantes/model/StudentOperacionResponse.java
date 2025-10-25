package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record StudentOperacionResponse(

        @JsonProperty("data")
        OperacionStatusDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static StudentOperacionResponse ok(String mensaje) {
        return StudentOperacionResponse.builder()
                .data(OperacionStatusDTO.ok(mensaje))
                .notifications(List.of())
                .build();
    }
}