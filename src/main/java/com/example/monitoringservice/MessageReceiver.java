package com.example.monitoringservice;

import com.example.monitoringservice.model.MessageDataDto;
import com.example.monitoringservice.repository.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.monitoringservice.mapper.MessageMapper.MAPPER;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageReceiver {
    private final MonitoringRepository monitoringRepository;

    @RabbitListener(queues = "logging-queue")
    public void saveLog(MessageDataDto messageDataDto) {
        monitoringRepository.save(MAPPER.mapFromDto(messageDataDto));
    }
}
