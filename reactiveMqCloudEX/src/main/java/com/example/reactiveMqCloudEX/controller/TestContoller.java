package com.example.reactiveMqCloudEX.controller;

import com.example.reactiveMqCloudEX.config.TestConfig;
import com.example.reactiveMqCloudEX.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestContoller {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/test/member")
    public String objectPublish() {

        Member member = new Member("박동준", 28, "대구시 동구", "010-1234-5678");

        rabbitTemplate.convertAndSend(TestConfig.EXCHANGE_NAME, TestConfig.ROUTING_KEY, member);
        return "object sending";
    }
}
