package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private HashMap<String , Movie> moviemap;

    private HashMap<String,Director> directormap;

    private HashMap<String, List<String>> directormoviemap;


    public MovieRepository() {
        this.moviemap = new HashMap<String , Movie>();
        this.directormap = new HashMap<String,Director>();
        this.directormoviemap = new HashMap<String, List<String>>();
    }

    public void addMovie(Movie movie) {
               moviemap.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
            directormap.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director) {
        if(moviemap.containsKey(movie) && directormap.containsKey(director)){
            List<String> currentmovies = new ArrayList<>();
            if(directormoviemap.containsKey(director)) currentmovies = directormoviemap.get(director);
            currentmovies.add(movie);
            directormoviemap.put(director,currentmovies);
        }
    }

    public Movie getMovieByName(String name) {
        return moviemap.get(name);
    }

    public Director getDirectorByName(String name) {
        return directormap.get(name);

    }

    public List<String> getDirectorByDirectorName(String name) {
        List<String> movielist = new ArrayList<>();
        if(directormoviemap.containsKey(name)) movielist = directormoviemap.get(name);
        return movielist;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(moviemap.keySet());
    }

    public void deleteDirectorByName(String director) {
        List<String> movies = new ArrayList<>();
        if(directormoviemap.containsKey(director)){
            movies = directormoviemap.get(director);
        }
        for(String movie : movies ){
            if(moviemap.containsKey(movie)) moviemap.remove(movie);
        }
        directormoviemap.remove(director);
        if(directormap.containsKey(director)) directormap.remove(director);
    }

    public void deleteAllDirectors() {
        Set<String> movielist = new HashSet<>();

        for (String director : directormoviemap.keySet()){
            for (String movie : directormoviemap.get(director)){
                movielist.add(movie);
            }
        }

        for(String movie : movielist){
            if(moviemap.containsKey(movie)) moviemap.remove(movie);
        }
        directormap.clear();
        directormoviemap.clear();
    }
}
