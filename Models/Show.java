package com.example.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import com.example.Enums.SeatStatus;

public class Show {
  private final Movie movie;
  private final LocalTime startTime;
  // LocalTime endTime;
  private final LocalDate date;
  private final Map<Integer, SeatStatus> seatStatus = new HashMap<>();
  private final Map<Integer, ReentrantLock> seatLocks = new HashMap<>();

  public Show(Movie movie, LocalDate date, LocalTime startTime, Screen screen) {
    this.movie = movie;
    this.startTime = startTime;
    this.date = date;
    // this.endTime=startTime+movie.getLength();
    for (Seat seat : screen.getSeatsList()) {
      seatStatus.put(seat.getSeatId(), SeatStatus.AVAILABLE);
      seatLocks.put(seat.getSeatId(), new ReentrantLock());
    }
  }
  
  public Movie getMovie() {
    return movie;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalDate getDate() {
    return date;
  }

  public boolean lockSeats(List<Integer> seatIds) {
    List<Integer> sortedSeatIds = new ArrayList<>(seatIds);
    List<ReentrantLock> acquriedLocks = new ArrayList<>();
    try {
      for (int id : sortedSeatIds) {
        ReentrantLock lock = seatLocks.get(id);
        lock.lock();
        acquriedLocks.add(lock);
      }

      for (int id : sortedSeatIds) {
        if (seatStatus.get(id) != SeatStatus.AVAILABLE) {
          return false;
        }
      }

      for (int id : sortedSeatIds) {
        seatStatus.put(id, SeatStatus.LOCKED);
      }
      return true;

    } finally {
      for (ReentrantLock lock : acquriedLocks) {
        lock.unlock();
      }
    }

  }

  public void confirmSeats(List<Integer> seatIds) {
    for (int seatId : seatIds) {
      seatStatus.put(seatId, SeatStatus.BOOKED);
    }
  }

  public void releaseSeats(List<Integer> seatIds) {
    for (int seatId : seatIds) {
      seatStatus.put(seatId, SeatStatus.AVAILABLE);
    }
  }
}
