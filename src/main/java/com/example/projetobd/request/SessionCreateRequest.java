package com.example.projetobd.request;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SessionCreateRequest {
    private Long movieId;
    private Long roomId;
    private LocalTime startTime;
}
