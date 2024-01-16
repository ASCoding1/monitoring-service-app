package com.example.monitoringservice.messagereceiver;

import com.example.monitoringservice.MessageReceiver;
import com.example.monitoringservice.model.MessageDataDto;
import com.example.monitoringservice.repository.MonitoringRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageReceiverTest {

    @Mock
    private MonitoringRepository monitoringRepository;

    @InjectMocks
    private MessageReceiver messageReceiver;

    @Test
    public void testSaveLog() {
        MessageDataDto messageDataDto = MessageDataDto.builder()
                .id(1L)
                .userEmail("test@example.com")
                .className("TestClass")
                .methodName("testMethod")
                .executionTime(100)
                .localDateTime(LocalDateTime.now())
                .build();

        messageReceiver.saveLog(messageDataDto);

        verify(monitoringRepository, times(1)).save(any());
    }
}

