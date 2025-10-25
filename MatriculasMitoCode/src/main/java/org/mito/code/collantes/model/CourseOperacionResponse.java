package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CourseOperacionResponse(

        @JsonProperty("data")
        OperacionStatusDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CourseOperacionResponse ok(String mensaje) {
        return CourseOperacionResponse.builder()
                .data(OperacionStatusDTO.ok(mensaje))
                .notifications(List.of())
                .build();
    }
}