package com.example.Models;

import com.example.Enums.SeatCategory;

public class Seat {
  int seatId;
  SeatCategory SeatCategory;
  public Seat(int seatId, SeatCategory seatCategory) {
    this.seatId = seatId;
    SeatCategory = seatCategory;
  }
  public int getSeatId() {
    return seatId;
  }
  public SeatCategory getSeatCategory() {
    return SeatCategory;
  }
  
}
