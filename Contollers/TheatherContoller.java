package com.example.Contollers;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.example.Models.City;
import com.example.Models.Movie;
import com.example.Models.Show;
import com.example.Models.Theather;
import com.example.Services.TheatherService;

public class TheatherContoller {
  private final TheatherService theatherService;

  public TheatherContoller() {
    this.theatherService = new TheatherService();
  }

  public void addTheather(Theather theather) {
    theatherService.addTheather(theather);
  }

  public Set<Movie> getMovies(City city, LocalDate date) {
    return theatherService.getMovies(city, date);
  }

  public List<Theather> getTheatres(City city, Movie movie, LocalDate date) {
    return theatherService.getTheatres(city, movie, date);
  }

  public List<Show> getShows(Movie movie, LocalDate date, Theather theather) {
    return theatherService.getShows(movie, date, theather);
  }
}
