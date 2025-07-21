package br.alura.LiterAlura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.alura.LiterAlura.dto.LivroApiResponseDTO;
import br.alura.LiterAlura.dto.LivroDTO;
import br.alura.LiterAlura.model.Autor;
import br.alura.LiterAlura.model.Idiomas;
import br.alura.LiterAlura.model.Livro;
import br.alura.LiterAlura.repository.AutorRepository;
import br.alura.LiterAlura.repository.LivroRepository;
import br.alura.LiterAlura.util.ConsumirAPI;
import br.alura.LiterAlura.util.ConverteDados;

@Service
public class BibliotecaService {
  private ConverteDados converteDados = new ConverteDados();

  @Autowired
  private ConsumirAPI consomeAPI;

  @Autowired
  private LivroRepository livroRepository;

  @Autowired
  private AutorRepository autorRepository;

  public Optional<Livro> buscarLivroPorTitulo(String titulo) {
    Optional<Livro> livroExistente = livroRepository.findFirstByTituloContainingIgnoreCase(titulo.trim());
    if (livroExistente.isPresent()) {
      return Optional.of(livroExistente.get());
    }

    String json = consomeAPI.fetch("/books?search=" + titulo.trim().replaceAll(" ", "+"));

    var dadosLivros = converteDados.converterJson(json, LivroApiResponseDTO.class);
    Optional<LivroDTO> primeiroLivro = dadosLivros.livros().stream().findFirst();

    if (primeiroLivro.isEmpty()) {
      return Optional.empty();
    }

    var dto = primeiroLivro.get();
    var idioma = dto.idiomas().stream().findFirst().orElse(null);
    var autorDto = dto.autores().stream().findFirst().orElse(null);

    Optional<Autor> autorOptional = autorRepository.findByNome(autorDto.nome());
    Autor autor;

    if (autorOptional.isPresent()) {
      autor = autorOptional.get();
    } else {
      autor = new Autor(autorDto.nome(), autorDto.anoNascimento(), autorDto.anoFalecimento());
      autorRepository.save(autor);
    }
    Livro livro = new Livro(dto.titulo(), Idiomas.fromString(idioma), dto.numeroDownloads(), autor);
    livroRepository.save(livro);

    return Optional.of(livro);
  }

  public List<Livro> listarLivrosRegistrados() {
    return livroRepository.findAll();
  }

  public List<Autor> listarAutoresRegistrados() {
    return autorRepository.findAllWithLivros();
  }

  public List<Autor> listarAutoresVivosPorAno(int ano) {
    return autorRepository.findByAnoVivo(ano);
  }

  public List<Livro> listarLivrosPorIdioma(Idiomas idioma) {
    return livroRepository.findByIdioma(idioma);
  }
}
