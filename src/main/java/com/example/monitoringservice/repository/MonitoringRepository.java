package com.example.monitoringservice.repository;

import com.example.monitoringservice.model.MessageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringRepository extends JpaRepository<MessageData, Long> {
    Page<MessageData> findAllByClassName(String className, Pageable pageable);
    Page<MessageData> findAllByMethodName(String methodName, Pageable pageable);
    Page<MessageData> findAllByUserEmail(String userEmail, Pageable pageable);
}
