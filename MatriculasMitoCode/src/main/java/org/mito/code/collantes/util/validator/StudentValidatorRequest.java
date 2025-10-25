package org.mito.code.collantes.util.validator;

import org.mito.code.collantes.model.CourseRequest;
import org.mito.code.collantes.model.StudentRequest;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentValidatorRequest {

    private static final String PREFIX = "MITOCODE-ERROR";

    public static Map<String, String> validateCursoRequest(CourseRequest request) {
        return Optional.ofNullable(request).map(req ->
                Stream.of(
                                validate(req.nombre(), s -> s == null || s.isBlank(), " - 201", "nombre es obligatorio"),
                                validate(req.nombre(), s -> s.length() > 100, " - 202", "nombre excede 100 caracteres"),
                                validate(req.siglas(), s -> s == null || s.isBlank(), " - 203", "siglas es obligatorio"),
                                validate(req.siglas(), s -> s.length() > 20, " - 204", "siglas excede 20 caracteres"),
                                validateBoolean(req.estado(), b -> b == null, " - 205", "estado no puede ser null")
                        ).flatMap(Optional::stream)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        ).orElse(Map.of(PREFIX + " - 100", "CourseRequest es null"));
    }

    public static Map<String, String> validateEstudianteRequest(StudentRequest request) {
        return Optional.ofNullable(request).map(req ->
                Stream.of(
                                validate(req.nombre(), s -> s == null || s.isBlank(), " - 301", "nombre es obligatorio"),
                                validate(req.nombre(), s -> s.length() > 100, " - 302", "nombre excede 100 caracteres"),
                                validate(req.apellidos(), s -> s == null || s.isBlank(), " - 303", "apellidos es obligatorio"),
                                validate(req.apellidos(), s -> s.length() > 100, " - 304", "apellidos excede 100 caracteres"),
                                validate(req.dni(), s -> s == null || s.isBlank(), " - 305", "dni es obligatorio"),
                                validate(req.dni(), s -> !s.matches("\\d{8}"), " - 306", "dni debe tener 8 dígitos numéricos"),
                                validateInt(req.edad(), i -> i == null || i < 0 || i > 120, " - 307", "edad inválida o fuera de rango")
                        ).flatMap(Optional::stream)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        ).orElse(Map.of(PREFIX + " - 101", "StudentRequest es null"));
    }

    private static Optional<Map.Entry<String, String>> validateBoolean(
            Boolean value,
            Predicate<Boolean> condition,
            String code,
            String message
    ) {
        return condition.test(value) ? Optional.of(new AbstractMap.SimpleEntry<>(PREFIX + code, message)) : Optional.empty();
    }

    private static Optional<Map.Entry<String, String>> validateInt(
            Integer value,
            Predicate<Integer> condition,
            String code,
            String message
    ) {
        return condition.test(value) ? Optional.of(new AbstractMap.SimpleEntry<>(PREFIX + code, message)) : Optional.empty();
    }

    private static Optional<Map.Entry<String, String>> validate(
            String value,
            Predicate<String> condition,
            String code,
            String message
    ) {
        return condition.test(value) ? Optional.of(new AbstractMap.SimpleEntry<>(PREFIX + code, message)) : Optional.empty();
    }

}