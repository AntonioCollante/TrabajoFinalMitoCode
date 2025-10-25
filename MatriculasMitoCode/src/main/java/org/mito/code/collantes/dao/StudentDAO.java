package org.mito.code.collantes.dao;

import org.mito.code.collantes.model.StudentDTO;
import org.mito.code.collantes.model.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class StudentDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public List<StudentDTO> listStudents() {
        return jdbc.query("SELECT * FROM fn_listar_estudiantes()", (rs, rowNum) ->
                StudentDTO.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .dni(rs.getString("dni"))
                        .edad(rs.getInt("edad"))
                        .build()
        );
    }

    public void createStudent(StudentRequest request) {
        jdbc.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("CALL public.sp_guardar_estudiante(?, ?, ?, ?)");
            cs.setString(1, request.nombre());
            cs.setString(2, request.apellidos());
            cs.setString(3, request.dni());
            cs.setInt(4, request.edad());
            cs.execute();
            cs.close();
            return null;
        });
    }

    public void updateStudent(int id, StudentRequest request) {
        jdbc.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("CALL public.sp_modificar_estudiante(?, ?, ?, ?, ?)");
            cs.setInt(1, id);
            cs.setString(2, request.nombre());
            cs.setString(3, request.apellidos());
            cs.setString(4, request.dni());
            cs.setInt(5, request.edad());
            cs.execute();
            cs.close();
            return null;
        });
    }

    public StudentDTO getStudentForId(int id) {
        String sql = "SELECT * FROM public.fn_obtener_estudiante(?)";
        return jdbc.query((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        }, (ResultSet rs) -> {
            if (rs.next()) {
                return StudentDTO.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .dni(rs.getString("dni"))
                        .edad(rs.getInt("edad"))
                        .build();
            }
            return null;
        });
    }

}