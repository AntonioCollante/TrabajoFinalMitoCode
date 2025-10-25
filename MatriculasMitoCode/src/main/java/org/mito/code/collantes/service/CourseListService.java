package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.CourseDAO;
import org.mito.code.collantes.model.CourseDTO;
import org.mito.code.collantes.model.CourseListResponse;
import org.mito.code.collantes.model.Notification;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CourseListService {

    private final CourseDAO dao;
    private final ObjectConverter objectConverter;

    public ResponseEntity<CourseListResponse> getListCourses() {
        return obtenerListaCursos()
                .map(lista -> {

                    List<CourseDTO> ordenados = lista.stream()
                            .sorted(Comparator.comparing(CourseDTO::nombre))
                            .toList();

                    return ResponseEntity.status(HttpStatus.OK)
                            .body(CourseListResponse.builder().data(ordenados).notifications(List.of()).build());

                }).orElseGet(() -> ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(
                                CourseListResponse.builder().data(null)
                                .notifications(objectConverter.notificationConverterListar("Error al obtener la lista de Cursos"))
                                .build()
                        )
                );
    }

    private Optional<List<CourseDTO>> obtenerListaCursos() {
        try {
            return Optional.of(dao.listCourses());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}