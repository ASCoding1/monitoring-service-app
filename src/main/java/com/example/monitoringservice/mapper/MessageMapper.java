package com.example.monitoringservice.mapper;

import com.example.monitoringservice.model.MessageData;
import com.example.monitoringservice.model.MessageDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper MAPPER = Mappers.getMapper(MessageMapper.class);

    MessageData mapFromDto(MessageDataDto messageDataDto);

    MessageDataDto mapToDto(MessageData messageData);
}
