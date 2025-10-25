package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record CourseOperationResponse(

        @JsonProperty("data")
        OperacionStatusDTO data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static CourseOperationResponse ok(String mensaje) {
        return CourseOperationResponse.builder()
                .data(OperacionStatusDTO.ok(mensaje))
                .notifications(List.of())
                .build();
    }
}