package org.mito.code.collantes.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class EnrollmentDAO {

    @Autowired
    private JdbcTemplate jdbc;

    public void registerEnrollment(
            LocalDateTime enrollmentDate,
            Integer studentId,
            Boolean active,
            String registeredBy,
            List<Integer> courseIds,
            List<String> classrooms
    ) throws Exception {

        Connection connection = jdbc.getDataSource().getConnection();

        Array courseArray = connection.createArrayOf("INTEGER", courseIds.toArray());
        Array classroomArray = connection.createArrayOf("TEXT", classrooms.toArray());

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbc)
                .withFunctionName("fn_registrar_matricula")
                .declareParameters(
                        new SqlParameter("p_fecha_matricula", Types.TIMESTAMP),
                        new SqlParameter("p_estudiante_id", Types.INTEGER),
                        new SqlParameter("p_estado", Types.BOOLEAN),
                        new SqlParameter("p_usuario_registro", Types.VARCHAR),
                        new SqlParameter("p_cursos", Types.ARRAY),
                        new SqlParameter("p_aulas", Types.ARRAY)
                );

        Map<String, Object> params = Map.of(
                "p_fecha_matricula", enrollmentDate,
                "p_estudiante_id", studentId,
                "p_estado", active,
                "p_usuario_registro", registeredBy,
                "p_cursos", courseArray,
                "p_aulas", classroomArray
        );

        jdbcCall.execute(params);
    }

}
