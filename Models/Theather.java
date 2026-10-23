package com.example.Models;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Theather {
  int theatherId;
  String theatherName;
  List<Screen> screens;
  City city;
  private final AtomicInteger counter = new AtomicInteger(0);
  public Theather(City city, String theatherName, List<Screen> screens) {
    this.theatherId = counter.incrementAndGet();
    this.theatherName = theatherName;
    this.screens = screens;
    this.city=city;
  }

  public int getTheatherId() {
    return theatherId;
  }

  public String getTheatherName() {
    return theatherName;
  }

  public List<Screen> getScreens() {
    return screens;
  }

  public void addScreen(Screen screen) {
    screens.add(screen);
  }

  public void removeScreen(Screen screen){
    screens.remove(screen);
  }

  public City getCity() {
    return city;
  }


}
