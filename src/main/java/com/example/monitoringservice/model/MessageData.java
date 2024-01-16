package com.example.monitoringservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "logs")
public class MessageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    private String className;
    private String methodName;
    private int executionTime;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;
}
