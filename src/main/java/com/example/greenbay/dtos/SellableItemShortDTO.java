package com.example.greenbay.dtos;

public class SellableItemShortDTO {
  String name;

  String photoURL;

  Double lastBidAmount;

  public SellableItemShortDTO() {
  }

  public SellableItemShortDTO(String name, String photoURL, Double lastBidAmount) {
    this.name = name;
    this.photoURL = photoURL;
    this.lastBidAmount = lastBidAmount;
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

  public Double getLastBidAmount() {
    return lastBidAmount;
  }

  public void setLastBid(Double lastBidAmount) {
    this.lastBidAmount = lastBidAmount;
  }
}
