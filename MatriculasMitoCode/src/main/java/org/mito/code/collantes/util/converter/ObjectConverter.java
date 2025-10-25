package org.mito.code.collantes.util.converter;

import org.mito.code.collantes.model.Notification;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ObjectConverter {

    public List<Notification> notificationConverter(Exception e) {
        return Optional.ofNullable(e)
                .map(ex -> {
                    String mensaje = Optional.ofNullable(ex.getMessage()).orElse("Sin mensaje");
                    String descripcion = Optional.ofNullable(ex.getLocalizedMessage()).orElse("Sin descripción");

                    String mensajeFinal = extractPostgresError(mensaje);
                    String descripcionFinal = extractPostgresError(descripcion);

                    return List.of(Notification.builder()
                            .category("Error")
                            .code(String.valueOf(ex.hashCode()))
                            .message(mensajeFinal)
                            .description(descripcionFinal)
                            .timestamp(OffsetDateTime.now())
                            .build());
                })
                .orElse(List.of(Notification.builder()
                        .category("Error")
                        .code("000")
                        .message("Excepción nula")
                        .description("No se recibió excepción")
                        .timestamp(OffsetDateTime.now())
                        .build()));
    }

    public List<Notification> notificationConverterMap(Map<String, String> errores) {
        List<Notification> listNotifications = new ArrayList<>();
        errores.forEach((key, value) -> listNotifications.add(Notification.builder()
                .category("Error")
                .code(String.valueOf(key.hashCode()))
                .message(String.valueOf(key))
                .description(String.valueOf(value))
                .timestamp(OffsetDateTime.now())
                .build()));
        return listNotifications;
    }

    public List<Notification> notificationConverterListar(String mensaje) {
        return List.of(Notification.builder()
                .category("Error")
                .code("001")
                .message("Error al Listar")
                .description(mensaje)
                .timestamp(OffsetDateTime.now())
                .build());
    }

    private String extractPostgresError(String raw) {
        if (raw == null) return "Sin mensaje";
        int start = raw.indexOf("ERROR:");
        if (start == -1) return raw.trim();
        int end = raw.indexOf("\n", start);
        if (end == -1) end = raw.length();
        return raw.substring(start + 6, end).trim();
    }

}