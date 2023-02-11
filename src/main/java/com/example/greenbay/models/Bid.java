package com.example.greenbay.models;

import javax.persistence.*;

@Entity
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  Double amount;

  @ManyToOne
  User bidder;

  @ManyToOne
  Item item;

  public Bid() {
  }

  public Bid(Double amount, User bidder, Item item) {
    this.amount = amount;
    this.bidder = bidder;
    this.item = item;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public User getBidder() {
    return bidder;
  }

  public void setBidder(User bidder) {
    this.bidder = bidder;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }
}
