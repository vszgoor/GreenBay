package com.example.greenbay.exceptions;

public class SimpleErrorDTO {

  String message;

  public SimpleErrorDTO() {
  }

  public SimpleErrorDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
