package com.example.projetobd.controller.api.frontend;

import com.example.projetobd.entity.Movie;
import com.example.projetobd.entity.Poster;
import com.example.projetobd.request.MoviePosterRequest;
import com.example.projetobd.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieControllerFront {

    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public MovieControllerFront(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
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

    @GetMapping("/{movieId}/edit")
    public String editMovieForm(@PathVariable Long movieId, Model model) {
        model.addAttribute("movie", movieService.getMovieById(movieId));
        return "edit-movie";
    }

    @PutMapping("/{movieId}/edit")
    public String editMovie(@PathVariable Long movieId, MoviePosterRequest moviePosterRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit-movie";
        }
        Movie movie = modelMapper.map(moviePosterRequest, Movie.class);
        if(moviePosterRequest.getUrl() != null){
            Poster poster = new Poster(moviePosterRequest.getUrl(), movie);
            movie.setPoster(poster);
        }
        movieService.updateMovie(movieId, movie);
        return "redirect:/movies";
    }

    @GetMapping("/{movieId}/delete")
    public String deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return "redirect:/movies";
    }

}
