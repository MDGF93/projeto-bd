package com.example.projetobd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
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

    private String sessionMovieTitle;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    //create endTime based on startTime and movie duration
    private LocalTime endTime;

    public Session(Movie movie, Room room, LocalTime startTime) {
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Session session = (Session) o;
        return id != null && Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        //should print in the following format: 12:00 - 13:30 - Room 1 - Movie Title
        return this.getStartTime() + " - " + this.getEndTime() + " - " + this.getRoom().getId() + " - " + this.getSessionMovieTitle();
    }
}
