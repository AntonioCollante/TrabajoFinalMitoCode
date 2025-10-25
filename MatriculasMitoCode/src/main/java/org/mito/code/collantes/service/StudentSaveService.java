package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.StudentDAO;
import org.mito.code.collantes.model.StudentOperacionResponse;
import org.mito.code.collantes.model.StudentRequest;
import org.mito.code.collantes.model.Notification;
import org.mito.code.collantes.model.OperacionStatusDTO;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSaveService {

    private final StudentDAO dao;
    private final ObjectConverter objectConverter;

    public ResponseEntity<StudentOperacionResponse> createStudent(StudentRequest request) {
        return ejecutar(() -> dao.createStudent(request), "ESTUDIANTE REGISTRADO EXITOSAMENTE");
    }

    public ResponseEntity<StudentOperacionResponse> updateStudent(int id, StudentRequest request) {
        return ejecutar(() -> dao.updateStudent(id, request), "ESTUDIANTE MODIFICADO EXITOSAMENTE");
    }

    private ResponseEntity<StudentOperacionResponse> ejecutar(Runnable accion, String mensajeExito) {
        try {
            accion.run();
            OperacionStatusDTO status = OperacionStatusDTO.builder().status("Ok").message(mensajeExito).build();
            return ResponseEntity.status(HttpStatus.OK).body(StudentOperacionResponse.builder().data(status).notifications(List.of()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StudentOperacionResponse.builder().data(null).notifications(objectConverter.notificationConverter(e)).build());
        }
    }

}