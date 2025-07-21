package br.alura.LiterAlura.exception;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException() {
    super("Livro não encontrado.");
  }

  public BookNotFoundException(String message) {
    super(message);
  }
}
