package com.example.greenbay.dtos;

public class SellableItemShortDTO {
  String name;

  String photoURL;

  Double lastBid;

  public SellableItemShortDTO() {
  }

  public SellableItemShortDTO(String name, String photoURL, Double lastBid) {
    this.name = name;
    this.photoURL = photoURL;
    this.lastBid = lastBid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  public Double getLastBid() {
    return lastBid;
  }

  public void setLastBid(Double lastBid) {
    this.lastBid = lastBid;
  }
}
