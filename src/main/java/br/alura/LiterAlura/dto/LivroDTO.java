package br.alura.LiterAlura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
    @JsonAlias("title") String titulo,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("authors") List<AutorDTO> autores,
    @JsonAlias("download_count") Long numeroDownloads) {
}
