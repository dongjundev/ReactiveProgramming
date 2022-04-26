package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import reactor.blockhound.BlockHound;

@SpringBootApplication
//@EnableBinding(ProducerChannel.class)
public class WebfluxApplication {

    public static void main(String[] args) {
        BlockHound.install();

        SpringApplication.run(WebfluxApplication.class, args);
    }

}
