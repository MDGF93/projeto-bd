package com.example.projetobd.repository;

import com.example.projetobd.entity.Poster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterRepository extends JpaRepository<Poster, Long> {
    Poster findByMovieId(Long movieId);
}
