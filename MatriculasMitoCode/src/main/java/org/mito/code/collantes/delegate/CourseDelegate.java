package org.mito.code.collantes.delegate;

import org.mito.code.collantes.model.*;
import org.mito.code.collantes.operation.CourseListOperation;
import org.mito.code.collantes.operation.CourseOperation;
import org.mito.code.collantes.operation.CourseSaveOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseDelegate {

    @Autowired
    @Qualifier("courseListOperation")
    CourseListOperation cursoListOperation;

    @Autowired
    @Qualifier("courseOperation")
    CourseOperation cursoOperation;

    @Autowired
    @Qualifier("courseSaveOperation")
    CourseSaveOperation cursoSaveOperation;

    public ResponseEntity<CourseSingleResponse> getCourses(int id){
        return cursoOperation.getCurso(id);
    }

    public ResponseEntity<CourseOperationResponse> createCourses(CourseRequest request) {
        return cursoSaveOperation.saveCurso(request);
    }

    public ResponseEntity<CourseOperationResponse> updateCourses (int id, CourseRequest request) {
        return cursoSaveOperation.updateCurso(id, request);
    }

    public ResponseEntity<CourseListResponse> listCourses () {
        return cursoListOperation.listCourses();
    }

}