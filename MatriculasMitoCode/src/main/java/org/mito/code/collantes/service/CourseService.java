package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.CourseDAO;
import org.mito.code.collantes.model.CourseDTO;
import org.mito.code.collantes.model.CourseSingleResponse;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseDAO dao;
    private final ObjectConverter objectConverter;

    public ResponseEntity<CourseSingleResponse> getCourse(int id) {
        try {
            CourseDTO student = dao.getCourseForId(id);
            return ResponseEntity.status(HttpStatus.OK).body(CourseSingleResponse.builder().data(student).notifications(List.of()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CourseSingleResponse.builder().data(null).notifications(objectConverter.notificationConverter(e)).build());
        }
    }

}