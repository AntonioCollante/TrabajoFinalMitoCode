package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CourseListResponse;
import org.mito.code.collantes.service.CourseListService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseListOperation {

    private final CourseListService courseListService;

    public ResponseEntity<CourseListResponse> listCourses(){
        return courseListService.getListCourses();
    }

}