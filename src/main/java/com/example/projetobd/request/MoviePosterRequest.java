package com.example.projetobd.request;

import com.example.projetobd.entity.Genre;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MoviePosterRequest {

    private String titleBr;
    private String titleOriginal;
    private Genre genre;
    private String mainCast;
    private String director;
    private Integer length;
    private String synopsis;
    private LocalDate releaseDate;
    private Integer requiredAge;
    private String producer;
    private boolean national;
    private boolean released;
    private String url;

}
