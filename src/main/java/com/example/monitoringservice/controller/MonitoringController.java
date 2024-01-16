package com.example.monitoringservice.controller;

import com.example.monitoringservice.model.MessageData;
import com.example.monitoringservice.model.MessageDataDto;
import com.example.monitoringservice.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.example.monitoringservice.mapper.MessageMapper.MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logs")
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<MessageDataDto> findAllLogsByQuery(
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String methodName,
            @RequestParam(required = false) String userEmail,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<MessageData> logsPage;

        if (className != null) {
            logsPage = monitoringService.findAllByClassName(className, pageable);
        } else if (methodName != null) {
            logsPage = monitoringService.findAllByMethodName(methodName, pageable);
        } else if (userEmail != null) {
            logsPage = monitoringService.findAllByUserEmail(userEmail, pageable);
        } else {
            logsPage = monitoringService.findAllLogs(pageable);
        }

        return logsPage.map(MAPPER::mapToDto);
    }
}
