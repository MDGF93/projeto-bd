package com.example.projetobd.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Poster poster = (Poster) o;
        return id != null && Objects.equals(id, poster.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
