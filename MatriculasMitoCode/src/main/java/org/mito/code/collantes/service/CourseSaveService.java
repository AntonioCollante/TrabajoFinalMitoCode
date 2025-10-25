package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.CourseDAO;
import org.mito.code.collantes.model.CourseOperacionResponse;
import org.mito.code.collantes.model.CourseRequest;
import org.mito.code.collantes.model.OperacionStatusDTO;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSaveService {

    private final CourseDAO dao;
    private final ObjectConverter objectConverter;

    public ResponseEntity<CourseOperacionResponse> createCourse(CourseRequest request) {
        return ejecutar(() -> dao.create(request), "ESTUDIANTE REGISTRADO EXITOSAMENTE");
    }

    public ResponseEntity<CourseOperacionResponse> updateCourse(int id, CourseRequest request) {
        return ejecutar(() -> dao.update(id, request), "ESTUDIANTE MODIFICADO EXITOSAMENTE");
    }

    private ResponseEntity<CourseOperacionResponse> ejecutar(Runnable accion, String mensajeExito) {
        try {
            accion.run();
            OperacionStatusDTO status = OperacionStatusDTO.builder().status("Ok").message(mensajeExito).build();
            return ResponseEntity.status(HttpStatus.OK).body(CourseOperacionResponse.builder().data(status).notifications(List.of()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CourseOperacionResponse.builder().data(null).notifications(objectConverter.notificationConverter(e)).build());
        }
    }

}