package com.example.greenbay.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public class SellableItemDetailedDTO {

  String name;

  String description;

  String photoURL;

  Double startingPrice;

  Double purchasePrice;

  String sellerName;

  Boolean sellable;

  List<BidDTO> bids;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  String buyerName;

  public SellableItemDetailedDTO() {
  }

  public SellableItemDetailedDTO(String name, String description, String photoURL,
                                 Double startingPrice, Double purchasePrice, String sellerName,
                                 Boolean sellable, List<BidDTO> bids, String buyerName) {
    this.name = name;
    this.description = description;
    this.photoURL = photoURL;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.sellerName = sellerName;
    this.sellable = sellable;
    this.bids = bids;
    this.buyerName = buyerName;
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

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }

  public Boolean getSellable() {
    return sellable;
  }

  public void setSellable(Boolean sellable) {
    this.sellable = sellable;
  }

  public List<BidDTO> getBids() {
    return bids;
  }

  public void setBids(List<BidDTO> bids) {
    this.bids = bids;
  }

  public String getBuyerName() {
    return buyerName;
  }

  public void setBuyerName(String buyerName) {
    this.buyerName = buyerName;
  }
}
