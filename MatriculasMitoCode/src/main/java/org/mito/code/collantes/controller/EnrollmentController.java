package org.mito.code.collantes.controller;

import org.mito.code.collantes.delegate.EnrollmentDelegate;
import org.mito.code.collantes.model.EnrollmentOperationResponse;
import org.mito.code.collantes.model.EnrollmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EnrollmentController {

    @Autowired
    EnrollmentDelegate enrollmentDelegate;

    @PostMapping(value= "/createEnrollment", produces = {"application/json"})
    public ResponseEntity<EnrollmentOperationResponse> crearEnrollment(
            @RequestBody EnrollmentRequest request
    ) {
        return enrollmentDelegate.createEnrollment(request);
    }

}