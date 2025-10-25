package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EnrollmentResponse (

        @JsonProperty("status")
        EnrollmentStatus status,

        @JsonProperty("message")
        String message

) {


}