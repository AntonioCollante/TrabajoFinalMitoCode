package org.mito.code.collantes.controller;

import org.mito.code.collantes.model.CursoListResponse;
import org.mito.code.collantes.model.CursoOperacionResponse;
import org.mito.code.collantes.model.CursoRequest;
import org.mito.code.collantes.model.CursoSingleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CursoController {

    @PostMapping("/cursos")
    public ResponseEntity<CursoOperacionResponse> crearCurso(@RequestBody CursoRequest request) {
        cursos.put(request.id(), request);
        return ResponseEntity.ok(
                CursoOperacionResponse.ok("Curso registrado correctamente")
        );
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<CursoSingleResponse> obtenerCurso(@PathVariable Integer id) {
        CursoRequest curso = cursos.get(id);
        if (curso == null) return ResponseEntity.notFound().build();

        CursoDTO dto = CursoDTO.builder()
                .id(curso.id())
                .nombre(curso.nombre())
                .siglas(curso.siglas())
                .estado(curso.estado())
                .build();

        return ResponseEntity.ok(
                CursoSingleResponse.builder()
                        .data(dto)
                        .notifications(List.of())
                        .build()
        );
    }

    @GetMapping("/cursos")
    public ResponseEntity<CursoListResponse> listarCursos() {
        List<CursoDTO> lista = cursos.values().stream()
                .map(curso -> CursoDTO.builder()
                        .id(curso.id())
                        .nombre(curso.nombre())
                        .siglas(curso.siglas())
                        .estado(curso.estado())
                        .build())
                .toList();

        return ResponseEntity.ok(
                CursoListResponse.builder()
                        .data(lista)
                        .notifications(List.of())
                        .build()
        );
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<CursoOperacionResponse> actualizarCurso(@PathVariable Integer id, @RequestBody CursoRequest request) {
        if (!cursos.containsKey(id)) return ResponseEntity.notFound().build();
        cursos.put(id, request);
        return ResponseEntity.ok(
                CursoOperacionResponse.ok("Curso actualizado correctamente")
        );
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<CursoOperacionResponse> eliminarCurso(@PathVariable Integer id) {
        if (cursos.remove(id) != null) {
            return ResponseEntity.ok(
                    CursoOperacionResponse.ok("Curso eliminado correctamente")
            );
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}