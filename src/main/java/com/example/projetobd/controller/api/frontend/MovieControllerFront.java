package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.request.MoviePosterRequest;
import com.example.projetobd.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MovieControllerFront {

    private final MovieService movieService;

    public MovieControllerFront(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getMovies());
        return "movies";
    }


    @GetMapping("/new")
    public String addMovieForm(Model model) {
        model.addAttribute("moviePosterRequest", new MoviePosterRequest());
        return "add-movie";
    }

    @PostMapping
    public String addMovie(MoviePosterRequest moviePosterRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add-movie";
        }
        movieService.createMovie(moviePosterRequest);
        return "redirect:/movies";
    }

    @GetMapping("/{movieId}/delete")
    public String deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return "redirect:/movies";
    }

}
