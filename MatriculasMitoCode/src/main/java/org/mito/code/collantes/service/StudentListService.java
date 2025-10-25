package org.mito.code.collantes.service;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.dao.StudentDAO;
import org.mito.code.collantes.model.StudentDTO;
import org.mito.code.collantes.model.StudentListResponse;
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
public class StudentListService {

    private final StudentDAO dao;
    private final ObjectConverter objectConverter;

    public ResponseEntity<StudentListResponse> listar() {
        return obtenerListaEstudiantes()
                .map(lista -> {

                    List<StudentDTO> ordenados = lista.stream()
                            .sorted(Comparator.comparingInt(StudentDTO::edad).reversed())
                            .toList();

                    return ResponseEntity.status(HttpStatus.OK)
                            .body(StudentListResponse.builder().data(ordenados).notifications(List.of()).build());

                }).orElseGet(() -> ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(
                                StudentListResponse.builder().data(null)
                                .notifications(objectConverter.notificationConverterListar("Error al Listar Estudiantes"))
                                .build()
                        )
                );
    }

    private Optional<List<StudentDTO>> obtenerListaEstudiantes() {
        try {
            return Optional.of(dao.listStudents());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}