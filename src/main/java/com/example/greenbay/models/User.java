package com.example.greenbay.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String username;

  String password;

  Double balance;


  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "seller")
  List<Item> itemsToSell;


  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "bidder")
  List<Bid> bids;


  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "buyer")
  List<Item> itemsBought;

  public void addBid(Bid bid) {
    this.bids.add(bid);
  }

  public void addItemToSell(Item itemToSell) {
    this.itemsToSell.add(itemToSell);
  }

  public void addItemBought(Item itemBought) {
    this.itemsBought.add(itemBought);
  }

  public User() {
    itemsToSell = new ArrayList<>();
    bids = new ArrayList<>();
    itemsBought = new ArrayList<>();
  }

  public User(String username, String password, Double balance) {
    this();
    this.username = username;
    this.password = password;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String userName) {
    this.username = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public List<Item> getItemsToSell() {
    return itemsToSell;
  }

  public void setItemsToSell(List<Item> itemsToSell) {
    this.itemsToSell = itemsToSell;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }

  public List<Item> getItemsBought() {
    return itemsBought;
  }

  public void setItemsBought(List<Item> itemsBought) {
    this.itemsBought = itemsBought;
  }
}
