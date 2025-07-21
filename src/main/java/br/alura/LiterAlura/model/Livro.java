package br.alura.LiterAlura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livros")
@NoArgsConstructor
@Data
public class Livro {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  @Enumerated(EnumType.STRING)
  private Idiomas idioma;

  private Long numeroDownloads;

  @ManyToOne
  private Autor autor;

  public Livro(String titulo, Idiomas idioma, Long numeroDownloads, Autor autor) {
    this.titulo = titulo;
    this.idioma = idioma;
    this.numeroDownloads = numeroDownloads;
    this.autor = autor;
  }
}
