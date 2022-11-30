package com.example.projetobd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    private Integer availableSeats;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;


    //create endTime based on startTime and movie duration
    private LocalTime endTime;

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
        //should print in the following format: [DD/MM] [HH:MM] - [HH:MM] [Movie Title]
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        String dateFormatted = date.format(formatter);
        String startTimeFormatted = startTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String endTimeFormatted = endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return String.format("[%s] [%s] - [%s] [%s]", dateFormatted, startTimeFormatted, endTimeFormatted, sessionMovieTitle);
    }

    public String getDateAndTime(){
        return this.getDate().getDayOfMonth() + "/" + this.getDate().getMonthValue() + " " + this.getStartTime();
    }

    public LocalDateTime getLocalDateTime(){
        return LocalDateTime.of(this.getDate(), this.getStartTime());
    }

    public Integer getAvailableSeats(){
        return this.availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats){
        this.availableSeats = availableSeats;
    }

}
