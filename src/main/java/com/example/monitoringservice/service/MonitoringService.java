package com.example.monitoringservice.service;

import com.example.monitoringservice.model.MessageData;
import com.example.monitoringservice.repository.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitoringService {
    private final MonitoringRepository monitoringRepository;

    public Page<MessageData> findAllLogs(Pageable pageable) {
        return monitoringRepository.findAll(pageable);
    }

    public Page<MessageData> findAllByClassName(String className, Pageable pageable) {
        return monitoringRepository.findAllByClassName(className, pageable);
    }

    public Page<MessageData> findAllByMethodName(String methodName, Pageable pageable) {
        return monitoringRepository.findAllByMethodName(methodName, pageable);
    }

    public Page<MessageData> findAllByUserEmail(String userEmail, Pageable pageable) {
        return monitoringRepository.findAllByUserEmail(userEmail, pageable);
    }
}
