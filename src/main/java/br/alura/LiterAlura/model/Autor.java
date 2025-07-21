package br.alura.LiterAlura.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private Integer anoNascimento;
  private Integer anoFalecimento;

  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
  private List<Livro> livros;

  public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
    this.nome = nome;
    this.anoNascimento = anoNascimento;
    this.anoFalecimento = anoFalecimento;
  }

  public void setLivros(List<Livro> livros) {
    livros.forEach(l -> l.setAutor(this));
    this.livros = livros;
  }

}
