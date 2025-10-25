package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CourseOperacionResponse;
import org.mito.code.collantes.model.CourseRequest;
import org.mito.code.collantes.service.CourseSaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseSaveOperation {

    private final CourseSaveService courseSaveService;

    public ResponseEntity<CourseOperacionResponse> saveCurso(CourseRequest request){
        return courseSaveService.createCourse(request);
    }

    public ResponseEntity<CourseOperacionResponse> updateCurso(int id, CourseRequest request){
        return courseSaveService.updateCourse(id, request);
    }

}