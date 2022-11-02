package com.example.projetobd.controller.api;

import com.example.projetobd.entity.Movie;
import com.example.projetobd.request.MoviePosterRequest;
import com.example.projetobd.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public Iterable<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{movieId}")
    public String getMovieById(@PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @PostMapping
    public String createMovie(@RequestBody MoviePosterRequest moviePosterRequest) {
        return movieService.createMovie(moviePosterRequest);
    }

    @PutMapping("/{movieId}")
    public String updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
        return movieService.updateMovie(movieId, movie);
    }

    @DeleteMapping("/{movieId}")
    public String deleteMovie(@PathVariable Long movieId) {
        return movieService.deleteMovie(movieId);
    }

    @PutMapping("/{movieId}/make-movie-released")
    public String makeMovieReleased(@PathVariable Long movieId) {
        return movieService.makeMovieReleased(movieId);
    }
}
