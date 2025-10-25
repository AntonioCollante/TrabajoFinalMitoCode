package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.EnrollmentDAO;
import org.mito.code.collantes.model.EnrollmentDetailRequest;
import org.mito.code.collantes.model.EnrollmentOperationResponse;
import org.mito.code.collantes.model.EnrollmentRequest;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final ObjectConverter objectConverter;
    private final EnrollmentDAO enrollmentDAO;

    public ResponseEntity<EnrollmentOperationResponse> createEnrollment(EnrollmentRequest request) {

        List<Integer> cursosId = request.detalles().stream()
                .map(detail -> detail.curso().id())
                .toList();

        List<String> aulas = request.detalles().stream()
                .map(EnrollmentDetailRequest::aula)
                .toList();

        try {
            enrollmentDAO.registerEnrollment(request.fechaMatricula(), request.estudiante().id(), request.estado(), "WebService", cursosId, aulas);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(EnrollmentOperationResponse.ok("SE REGISTRO MATRICULA EXITOSAMENTE"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(EnrollmentOperationResponse.builder().data(null).notifications(objectConverter.notificationConverter(e)).build());
        }

    }

}