package com.example.projetobd.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class SessionCreateRequest {
    private Long movieId;
    private Long roomId;
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    public SessionCreateRequest(Long movieId, LocalDate date, LocalTime startTime) {
        this.movieId = movieId;
        this.date = date;
        this.startTime = startTime;
    }

    public SessionCreateRequest(Long roomId){
        this.roomId = roomId;
    }
}
