package org.mito.code.collantes.delegate;

import org.mito.code.collantes.model.*;
import org.mito.code.collantes.operation.EnrollmentOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentDelegate {

    @Autowired
    @Qualifier("enrollmentOperation")
    EnrollmentOperation enrollmentOperation;

    public ResponseEntity<EnrollmentOperationResponse> createEnrollment(EnrollmentRequest request) {
        return enrollmentOperation.saveEnrollment(request);
    }

}