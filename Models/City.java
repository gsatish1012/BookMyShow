package com.example.Models;

public class City {
  String cityName;
  int cityId;
  public City(String cityName, int cityId) {
    this.cityName = cityName;
    this.cityId = cityId;
  }
  public String getCityName() {
    return cityName;
  }
  public int getCityId() {
    return cityId;
  }
  
}
