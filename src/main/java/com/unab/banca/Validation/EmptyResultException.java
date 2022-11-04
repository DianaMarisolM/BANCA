package com.unab.banca.Validation;

public class EmptyResultException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public EmptyResultException(String message) {

    super(message);
    System.out.println(message);
  }

}
