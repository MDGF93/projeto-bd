package com.example.projetobd.service;

import com.example.projetobd.entity.Movie;
import com.example.projetobd.entity.Poster;
import com.example.projetobd.repository.MovieRepository;
import com.example.projetobd.repository.PosterRepository;
import com.example.projetobd.request.MoviePosterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final PosterRepository posterRepository;

    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper, PosterRepository posterRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.posterRepository = posterRepository;
    }

    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public String getMovieById(Long movieId) {
        return movieRepository.findById(movieId).toString();
    }

    public String createMovie(MoviePosterRequest request) {
        Movie movieToSave = modelMapper.map(request, Movie.class);
        movieToSave.setReleased(false);
        movieRepository.save(movieToSave);
        if (request.getUrl() != null) {
            Poster posterToSave = new Poster(request.getUrl(), movieToSave);
            posterRepository.save(posterToSave);
            movieToSave.setPoster(posterToSave);
        }
        return "Movie created";
    }

    public String updateMovie(Long movieId, Movie movie) {
//        Movie movieToUpdate = movieRepository.findById(movieId).get();
//        movieToUpdate.setTitleBr(movie.getTitleBr());
//        movieToUpdate.setTitleOriginal(movie.getTitleOriginal());
//        movieToUpdate.setGenre(movie.getGenre());
//        movieToUpdate.setMainCast(movie.getMainCast());
//        movieToUpdate.setDirector(movie.getDirector());
//        movieToUpdate.setLength(movie.getLength());
//        movieToUpdate.setSynopsis(movie.getSynopsis());
//        movieToUpdate.setRequiredAge(movie.getRequiredAge());
//        movieToUpdate.setProducer(movie.getProducer());
//        movieToUpdate.setNational(movie.isNational());
//        movieToUpdate.setReleased(movie.isReleased());
//        movieToUpdate.setSessions(movie.getSessions());
//        movieRepository.save(movieToUpdate);
        Movie movieToUpdate = movieRepository.findById(movieId).get();
        modelMapper.map(movie, movieToUpdate);
        return "Movie updated";
    }


    public String deleteMovie(Long movieId) {
        //Check if movie has poster
        if (movieRepository.findById(movieId).get().getPoster() != null) {
            //Delete poster
            Poster posterToDelete = posterRepository.findByMovieId(movieId);
            System.out.println(posterToDelete);
            posterToDelete.setMovie(null);
            posterRepository.delete(posterToDelete);
            posterRepository.flush();
        }
        Movie movieToDelete = movieRepository.findById(movieId).get();
        movieToDelete.setPoster(null);
        movieRepository.deleteById(movieId);
        return "Movie deleted";
    }

    public String makeMovieReleased(Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        movie.setReleased(true);
        movieRepository.save(movie);
        return "Movie released";
    }
}
