package com.example.greenbay.dtos;

public class BidDTO {

  Double amount;

  String bidderName;

  public BidDTO() {
  }

  public BidDTO(Double amount, String bidderName) {
    this.amount = amount;
    this.bidderName = bidderName;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getBidderName() {
    return bidderName;
  }

  public void setBidderName(String bidderName) {
    this.bidderName = bidderName;
  }
}
