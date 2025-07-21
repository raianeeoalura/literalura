package br.alura.LiterAlura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.LiterAlura.model.Idiomas;
import br.alura.LiterAlura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

  Optional<Livro> findFirstByTituloContainingIgnoreCase(String titulo);

  List<Livro> findByIdioma(Idiomas idioma);

}
