package com.example.projetobd.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleBr;
    private String titleOriginal;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(length = 1000)
    private String mainCast;
    private String director;
    private Integer length;

    @Column(length = 1000)
    private String synopsis;
    private LocalDate releaseDate;
    private Integer requiredAge;
    private String producer;
    private boolean national;
    private boolean released;
    @OneToMany
    @ToString.Exclude
    private List<Session> sessions;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Poster poster;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
