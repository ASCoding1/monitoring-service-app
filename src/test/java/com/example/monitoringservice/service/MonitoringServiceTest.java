package com.example.monitoringservice.service;

import com.example.monitoringservice.model.MessageData;
import com.example.monitoringservice.repository.MonitoringRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MonitoringServiceTest {

    @Mock
    private MonitoringRepository monitoringRepository;

    private MonitoringService monitoringService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        monitoringService = new MonitoringService(monitoringRepository);
    }

    @Test
    public void testFindAllLogs() {
        MessageData messageData = new MessageData();
        messageData.setClassName("TestClass");
        messageData.setId(1L);
        messageData.setUserEmail("user@example.com");
        messageData.setMethodName("testMethod");
        List<MessageData> messages = List.of(messageData);

        Pageable pageable = Pageable.ofSize(10);
        Page<MessageData> page = new PageImpl<>(messages, pageable, messages.size());

        when(monitoringRepository.findAll(pageable)).thenReturn(page);

        Page<MessageData> result = monitoringService.findAllLogs(pageable);

        assertEquals(page.getTotalElements(), result.getTotalElements());
        assertEquals(messages.size(), result.getContent().size());
        assertEquals(messageData.getClassName(), result.getContent().get(0).getClassName());
        assertEquals(messageData.getId(), result.getContent().get(0).getId());
        assertEquals(messageData.getUserEmail(), result.getContent().get(0).getUserEmail());
        assertEquals(messageData.getMethodName(), result.getContent().get(0).getMethodName());

        verify(monitoringRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testFindAllByClassName() {
        MessageData messageData = new MessageData();
        messageData.setClassName("TestClass");
        messageData.setId(1L);
        messageData.setUserEmail("user@example.com");
        messageData.setMethodName("testMethod");
        List<MessageData> messages = List.of(messageData);

        Pageable pageable = Pageable.ofSize(10);
        Page<MessageData> page = new PageImpl<>(messages, pageable, messages.size());

        String className = "TestClass";

        when(monitoringRepository.findAllByClassName(className, pageable)).thenReturn(page);
        Page<MessageData> result = monitoringService.findAllByClassName(className, pageable);

        assertEquals(page.getTotalElements(), result.getTotalElements());
        assertEquals(messages.size(), result.getContent().size());
        assertEquals(className, result.getContent().get(0).getClassName());
        assertEquals(messageData.getId(), result.getContent().get(0).getId());
        assertEquals(messageData.getUserEmail(), result.getContent().get(0).getUserEmail());
        assertEquals(messageData.getMethodName(), result.getContent().get(0).getMethodName());

        verify(monitoringRepository, times(1)).findAllByClassName(className, pageable);
    }

    @Test
    public void testFindAllByMethodName() {
        MessageData messageData = new MessageData();
        messageData.setClassName("TestClass");
        messageData.setId(1L);
        messageData.setUserEmail("user@example.com");
        messageData.setMethodName("testMethod");
        List<MessageData> messages = List.of(messageData);

        Pageable pageable = Pageable.ofSize(10);
        Page<MessageData> page = new PageImpl<>(messages, pageable, messages.size());

        String methodName = "testMethod";

        when(monitoringRepository.findAllByMethodName(methodName, pageable)).thenReturn(page);
        Page<MessageData> result = monitoringService.findAllByMethodName(methodName, pageable);

        assertEquals(page.getTotalElements(), result.getTotalElements());
        assertEquals(messages.size(), result.getContent().size());
        assertEquals(messageData.getClassName(), result.getContent().get(0).getClassName());
        assertEquals(messageData.getId(), result.getContent().get(0).getId());
        assertEquals(messageData.getUserEmail(), result.getContent().get(0).getUserEmail());
        assertEquals(methodName, result.getContent().get(0).getMethodName());

        verify(monitoringRepository, times(1)).findAllByMethodName(methodName, pageable);
    }

    @Test
    public void testFindAllByUserEmail() {
        String userEmail = "test@example.com";

        MessageData messageData = new MessageData();
        messageData.setClassName("TestClass");
        messageData.setId(1L);
        messageData.setUserEmail("test@example.com");
        messageData.setMethodName("testMethod");
        List<MessageData> messages = List.of(messageData);

        Pageable pageable = Pageable.ofSize(10);
        Page<MessageData> page = new PageImpl<>(messages, pageable, messages.size());

        when(monitoringRepository.findAllByUserEmail(userEmail, pageable)).thenReturn(page);
        Page<MessageData> result = monitoringService.findAllByUserEmail(userEmail, pageable);

        assertEquals(page.getTotalElements(), result.getTotalElements());
        assertEquals(messages.size(), result.getContent().size());
        assertEquals(messageData.getClassName(), result.getContent().get(0).getClassName());
        assertEquals(messageData.getId(), result.getContent().get(0).getId());
        assertEquals(userEmail, result.getContent().get(0).getUserEmail());
        assertEquals(messageData.getMethodName(), result.getContent().get(0).getMethodName());

        verify(monitoringRepository, times(1)).findAllByUserEmail(userEmail, pageable);
    }
}