package com.example.projetobd.request;

import com.example.projetobd.entity.Genre;
import lombok.Data;

import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MoviePosterRequest {

    @NotNull(message = "O título em português é obrigatório")
    private String titleBr;
    private String titleOriginal;
    private Genre genre;
    @NotNull(message = "O elenco principal é obrigatório")
    private String mainCast;
    @NotNull(message = "O diretor é obrigatório")
    private String director;
    @Min(value = 1, message = "O comprimento do filme deve ser maior que 0")
    @Max(value = 500, message = "O comprimento do filme deve ser menor que 500")
    private Integer length;
    @NotNull(message = "A sinopse é obrigatória")
    private String synopsis;
    private LocalDate releaseDate;
    private Integer requiredAge;
    private String producer;
    private boolean national;
    private boolean released;
    private String url;

}
