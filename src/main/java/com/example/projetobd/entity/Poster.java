package com.example.projetobd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Poster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @OneToOne
    private Movie movie;

    public Poster(String url, Movie movie) {
        this.url = url;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return this.getUrl();
    }
}
