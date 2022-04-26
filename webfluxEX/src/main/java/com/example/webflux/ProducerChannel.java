package com.example.webflux;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {
    //레거시 방식
    String DIRECT = "direct";

    String BROADCAST = "broadcast";

    @Output(ProducerChannel.DIRECT)
    MessageChannel directMessage();

    @Output(ProducerChannel.BROADCAST)
    MessageChannel broadcastMessage();
}

