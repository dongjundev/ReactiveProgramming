package com.example.webflux.domain;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyMessage implements Serializable {

    private String message;
}
