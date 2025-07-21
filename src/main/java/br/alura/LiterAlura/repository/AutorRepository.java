package br.alura.LiterAlura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.alura.LiterAlura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
  Optional<Autor> findByNome(String nome);

  @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.livros")
  List<Autor> findAllWithLivros();

  @Query("SELECT a FROM Autor a JOIN FETCH a.livros WHERE a.anoNascimento <= :ano AND a.anoFalecimento > :ano")
  List<Autor> findByAnoVivo(int ano);
}
