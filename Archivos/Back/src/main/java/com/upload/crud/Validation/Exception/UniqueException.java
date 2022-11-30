package com.upload.crud.Validation.Exception;


import com.upload.crud.Validation.Entity.Error;;

public class UniqueException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final transient Error error;

  public UniqueException(Error error) {
    super();
    this.error = error;
  }

  public UniqueException(String message, Error error) {
    super(message);
    this.error = error;
  }

  public Error getError() {
    return error;
  }
}


