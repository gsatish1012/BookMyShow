package com.example.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.Models.City;
import com.example.Models.Movie;
import com.example.Models.Screen;
import com.example.Models.Show;
import com.example.Models.Theather;

public class TheatherService {
  Map<City, List<Theather>> theatherList;

  public TheatherService() {
    this.theatherList = new HashMap<>();
  }

  public void addTheather(Theather theather) {
    theatherList.computeIfAbsent(theather.getCity(), k -> new ArrayList<>()).add(theather);
  }

  public List<Theather> getTheatres(City city, Movie movie, LocalDate date) {
    List<Theather> theatres = theatherList.get(city);
    if (theatres == null) {
      return new ArrayList<>();
    }

    List<Theather> matchingTheatres = new ArrayList<>();

    for (Theather theather : theatres) {
      for (Screen screen : theather.getScreens()) {
        List<Show> shows = screen.getShows().get(date);
        if (shows == null) {
          continue;
        }

        boolean hasMovie = shows.stream()
            .anyMatch(show -> show.getMovie() == movie);

        if (hasMovie) {
          matchingTheatres.add(theather);
          break; // This theater only needs to be added once.
        }
      }
    }

    return matchingTheatres;
  }

  public Set<Movie> getMovies(City city, LocalDate date) {
    List<Theather> theathers = theatherList.get(city);
    Set<Movie> matchingMovies = new HashSet<>();

    if (theathers == null)
      return matchingMovies;

    for (Theather theather : theathers) {
      for (Screen screen : theather.getScreens()) {
        List<Show> shows = screen.getShows().get(date);
        if (shows == null) {
          continue;
        }
        for (Show show : shows) {
          matchingMovies.add(show.getMovie());
        }
      }
    }
    return matchingMovies;
  }

  public List<Show> getShows(Movie movie, LocalDate date, Theather theather){
    List<Show> requriedShows = new ArrayList<>();
    for (Screen screen : theather.getScreens()) {
        List<Show> shows = screen.getShows().get(date);
        if (shows == null) {
          continue;
        }

        boolean hasMovie = shows.stream()
            .anyMatch(show -> show.getMovie() == movie);

        if (hasMovie) {
          requriedShows.addAll(shows);
          break; // This theater only needs to be added once.
        }
      }
    return requriedShows;
  }
}
