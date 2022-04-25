package com.example.reactiveMqCloudEX;

import com.example.reactiveMqCloudEX.domain.ProducerChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ProducerChannel.class)
@SpringBootApplication
public class ReactiveMqCloudExApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMqCloudExApplication.class, args);
    }
}
