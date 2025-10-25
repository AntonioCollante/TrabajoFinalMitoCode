package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record EnrollmentOperationResponse(

        @JsonProperty("data")
        EnrollmentResponse data,

        @JsonProperty("notifications")
        List<Notification> notifications

) {
    public static EnrollmentOperationResponse ok(String mensaje) {
        return EnrollmentOperationResponse.builder()
                .data(EnrollmentResponse.builder().status(EnrollmentStatus.SUCCESS).message(mensaje).build())
                .notifications(List.of())
                .build();
    }
}