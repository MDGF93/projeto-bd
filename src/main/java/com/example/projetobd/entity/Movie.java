package com.example.projetobd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
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
    private List<Session> sessions;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Poster poster;
}
