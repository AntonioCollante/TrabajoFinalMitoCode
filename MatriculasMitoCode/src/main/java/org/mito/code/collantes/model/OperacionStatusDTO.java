package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record OperacionStatusDTO(

        @JsonProperty("status")
        String status,

        @JsonProperty("message")
        String message

) {
    public static OperacionStatusDTO ok(String mensaje) {
        return OperacionStatusDTO.builder()
                .status("ok")
                .message(mensaje)
                .build();
    }
}