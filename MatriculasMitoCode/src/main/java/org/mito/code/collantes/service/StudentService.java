package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.StudentDAO;
import org.mito.code.collantes.model.StudentDTO;
import org.mito.code.collantes.model.StudentSingleResponse;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDAO dao;
    private final ObjectConverter objectConverter;

    public ResponseEntity<StudentSingleResponse> getEstudiante(int id) {
        try {
            StudentDTO student = dao.getStudentForId(id);
            return ResponseEntity.status(HttpStatus.OK).body(StudentSingleResponse.builder().data(student).notifications(List.of()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(StudentSingleResponse.builder().data(null).notifications(objectConverter.notificationConverter(e)).build());
        }
    }

}