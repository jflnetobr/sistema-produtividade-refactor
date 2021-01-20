package src.util;

public class IntercurrenceException extends Exception {
  private String message;

  public IntercurrenceException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}