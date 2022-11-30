package com.upload.crud.Validation.Exception;


import com.upload.crud.Validation.Entity.Error;;

public class NoAuthorizeException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final transient Error error;

  public NoAuthorizeException(Error error) {
    super();
    this.error = error;
  }

  public NoAuthorizeException(String message, Error error) {
    super(message);
    this.error = error;
  }

  public Error getError() {
    return error;
  }
}


