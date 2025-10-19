package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record EstudianteListResponse(

        @JsonProperty("data")
        List<EstudianteDTO> data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static EstudianteListResponse buildExample() {
        return EstudianteListResponse.builder()
                .data(List.of())
                .notifications(List.of())
                .build();
    }
}