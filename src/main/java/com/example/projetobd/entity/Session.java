package com.example.projetobd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Movie movie;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Room room;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    private String sessionMovieTitle;
    private LocalTime startTime;

    //create endTime based on startTime and movie duration
    private LocalTime endTime;

    public Session(Movie movie, Room room, List<Ticket> tickets, LocalTime startTime) {
        this.movie = movie;
        this.room = room;
        this.tickets = tickets;
        this.startTime = startTime;
    }

}
