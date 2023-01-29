package com.example.greenbay.dtos;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateItemDTO {

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotBlank
    @URL
    String photoURL;

    @NotNull
    @Positive
    Double startingPrice;

    @NotNull
    @Positive
    Double purchasePrice;

    public CreateItemDTO() {
    }

    public CreateItemDTO(String name, String description, String photoURL, Double startingPrice, Double purchasePrice) {
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
        this.startingPrice = startingPrice;
        this.purchasePrice = purchasePrice;
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
}
