package com.example.monitoringservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDataDto {
    private Long id;
    private String userEmail;
    private String className;
    private String methodName;
    private int executionTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("localDateTime")
    private LocalDateTime localDateTime;

}
