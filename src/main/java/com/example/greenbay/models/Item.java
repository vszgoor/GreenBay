package com.example.greenbay.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  String description;

  String photoURL;

  Double startingPrice;

  Double purchasePrice;

  @ManyToOne(cascade = CascadeType.PERSIST)
  User seller;

  Boolean sellable;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.PERSIST)
  List<Bid> bids;

  @ManyToOne(cascade = CascadeType.PERSIST)
  User buyer;

  public Item() {
    this.sellable = true;
    bids = new ArrayList<>();
  }

  public Item(String name, String description, String photoURL, Double startingPrice,
              Double purchasePrice, User seller) {
    this();
    this.name = name;
    this.description = description;
    this.photoURL = photoURL;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.seller = seller;
  }

//    public Optional<Bid> getLastBid(){
//        if(bids.isEmpty()){
//            return Optional.empty();
//        }
//        return Optional.of((bids.get(bids.size() - 1)));
//    }

  public void addBid(Bid bid) {
    bids.add(bid);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoURL() {
    return photoURL;
  }

  public void setPhotoURL(String photoURL) {
    this.photoURL = photoURL;
  }

  public Double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.startingPrice = startingPrice;
  }

  public Double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public User getSeller() {
    return seller;
  }

  public void setSeller(User seller) {
    this.seller = seller;
  }

  public Boolean getSellable() {
    return sellable;
  }

  public void setSellable(Boolean sellable) {
    this.sellable = sellable;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }

  public User getBuyer() {
    return buyer;
  }

  public void setBuyer(User buyer) {
    this.buyer = buyer;
  }
}
