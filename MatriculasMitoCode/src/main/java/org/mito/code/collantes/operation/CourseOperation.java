package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CourseSingleResponse;
import org.mito.code.collantes.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseOperation {

    private final CourseService courseService;

    public ResponseEntity<CourseSingleResponse> getCurso(Integer idCurso) {
        return courseService.getCourse(idCurso);
    }

}