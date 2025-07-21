package br.alura.LiterAlura.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDados {
  private ObjectMapper mapper = new ObjectMapper();

  public <T> T converterJson(String json, Class<T> classe) {
    if (json == null || json.trim().isEmpty()) {
      throw new RuntimeException("JSON de entrada está vazio ou nulo.");
    }

    try {
      return mapper.readValue(json, classe);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Erro ao converter JSON: " + e.getMessage(), e);
    }
  }

  public <T> List<T> converterListaJson(String json, Class<T> classe) {
    if (json == null || json.trim().isEmpty()) {
      throw new RuntimeException("JSON de entrada está vazio ou nulo.");
    }

    CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);

    try {
      return mapper.readValue(json, lista);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Erro ao converter JSON: " + e.getMessage(), e);
    }
  }
}
