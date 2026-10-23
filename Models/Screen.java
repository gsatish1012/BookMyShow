package com.example.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Enums.SeatCategory;

public class Screen {
  int screenId;
  List<Seat> seats;
  Map<LocalDate,List<Show>> shows;
  public Screen(int screenId,int seatcount) {
    this.screenId = screenId;
    this.shows=new HashMap<>();
    this.seats= new ArrayList<>();
    initializeSeats(seatcount);
  }

  private void initializeSeats(int seatcount){
    for(int i=1;i<=seatcount;i++){
      Seat seat= new Seat(i, SeatCategory.SILVER);
      seats.add(seat);
    }
  }

  public void addShow(LocalDate date,Show show){
    shows.computeIfAbsent(date, k-> new ArrayList<>()).add(show);
  }

  public void removeShow(LocalDate date,Show show){
    List<Show> showList= shows.get(date);
    if(showList!=null){
      showList.remove(show);
    } 
  }

  public List<Seat> getSeatsList(){
    return seats;
  }

  public int getScreenId() {
    return screenId;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public Map<LocalDate, List<Show>> getShows() {
    return shows;
  }
  
  
}
