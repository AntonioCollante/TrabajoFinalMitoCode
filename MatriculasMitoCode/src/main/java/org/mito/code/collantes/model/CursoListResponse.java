package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CursoListResponse(

        @JsonProperty("data")
        List<CursoDTO> data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CursoListResponse buildExample() {
        return CursoListResponse.builder()
                .data(List.of())
                .notifications(List.of())
                .build();
    }
}