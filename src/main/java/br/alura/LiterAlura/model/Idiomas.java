package br.alura.LiterAlura.model;

public enum Idiomas {
  EN("en"),
  PT("pt"),
  FR("fr"),
  ES("es");

  private String idiomaAbrev;

  private Idiomas(String idiomaAbrev) {
    this.idiomaAbrev = idiomaAbrev;
  }

  public String getIdioma() {
    return idiomaAbrev;
  }

  public static Idiomas fromString(String idioma) {
    for (Idiomas i : Idiomas.values()) {
      if (i.idiomaAbrev.equalsIgnoreCase(idioma)) {
        return i;
      }
    }

    throw new IllegalArgumentException("Idioma não suportado: " + idioma);
  }
}
