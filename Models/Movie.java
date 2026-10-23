package com.example.Models;

public class Movie {
  String movieName;
  int length;
  public Movie(String movieName, int length) {
    this.movieName = movieName;
    this.length = length;
  }
  public String getMovieName() {
    return movieName;
  }
  public int getLength() {
    return length;
  }
  
}
