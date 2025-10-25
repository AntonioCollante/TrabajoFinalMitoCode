package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnrollmentStatus {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    VALIDATION_FAILED("VALIDATION_FAILED");

    private final String value;

    EnrollmentStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
