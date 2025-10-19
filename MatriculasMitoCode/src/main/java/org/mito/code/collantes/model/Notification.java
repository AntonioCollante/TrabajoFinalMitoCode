package org.mito.code.collantes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notification {

    @JsonProperty("category")
    private String category;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("class_loader_name")
    private String classLoaderName;

    @JsonProperty("module_name")
    private String moduleName;

    @JsonProperty("module_version")
    private String moduleVersion;

    @JsonProperty("declaring_class")
    private String declaringClass;

    @JsonProperty("method_name")
    private String methodName;

    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("line_number")
    private int lineNumber;

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp;

    public static Notification buildExample() {
        Notification obj = new Notification();
        obj.setCategory("ERROR");
        obj.setCode("CODE");
        obj.setMessage("MESSAGE ERROR");
        obj.setDescription("DESCRIPTION ERROR");
        obj.setTimestamp(OffsetDateTime.now());
        return obj;
    }

}