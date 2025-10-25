package org.mito.code.collantes.dao;

import org.mito.code.collantes.model.CourseDTO;
import org.mito.code.collantes.model.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class CourseDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void create(CourseRequest request) {
        jdbc.update("CALL sp_guardar_curso(?, ?, ?)",
                request.nombre(),
                request.siglas(),
                request.estado()
        );
    }

    public void update(int id, CourseRequest request) {
        jdbc.update("CALL sp_modificar_curso(?, ?, ?, ?)",
                id,
                request.nombre(),
                request.siglas(),
                request.estado()
        );
    }

    public List<CourseDTO> listCourses() {
        return jdbc.query("SELECT * FROM fn_listar_cursos()", (rs, rowNum) ->
                CourseDTO.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .siglas(rs.getString("siglas"))
                        .estado(rs.getBoolean("estado"))
                        .build()
        );
    }

    public CourseDTO getCourseForId(int id) {
        String sql = "SELECT * FROM public.fn_obtener_curso(?)";

        return jdbc.query((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        }, (ResultSet rs) -> {
            if (rs.next()) {
                return CourseDTO.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .siglas(rs.getString("siglas"))
                        .estado(rs.getBoolean("estado"))
                        .build();
            }
            return null;
        });
    }

}