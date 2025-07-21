package br.alura.LiterAlura.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.alura.LiterAlura.model.Autor;
import br.alura.LiterAlura.model.Idiomas;
import br.alura.LiterAlura.model.Livro;
import br.alura.LiterAlura.service.BibliotecaService;

@Component
public class MenuPrincipal {
  private Scanner sc = new Scanner(System.in);

  @Autowired
  private BibliotecaService bibliotecaServices;

  public void exibirMenu() {
    int opcao = -1;

    while (opcao != 0) {
      System.out.println("\n==================================================");
      System.out.println("            BEM-VINDO AO LITERALURA CLI");
      System.out.println("==================================================");
      System.out.println();
      System.out.println("Escolha uma opção:");
      System.out.println("1 - Buscar livro pelo título");
      System.out.println("2 - Listar livros registrados");
      System.out.println("3 - Listar autores registrados");
      System.out.println("4 - Listar autores vivos em um determinado ano");
      System.out.println("5 - Listar livros em um determinado idioma");
      System.out.println("0 - Sair");
      System.out.println();
      System.out.print("Digite sua opção: ");

      opcao = Integer.parseInt(sc.nextLine());

      switch (opcao) {
        case 1:
          buscarLivrosPorTitulos();
          break;
        case 2:
          listarLivrosRegistrados();
          break;
        case 3:
          listarAutoresRegistrados();
          break;
        case 4:
          listarAutoresVivosPorAno();
          break;
        case 5:
          listarLivrosPorIdioma();
          break;
        case 0:
          System.out.println("\nObrigado por usar o LiterAlura CLI!");
          break;
        default:
          System.out.println("\nOpção inválida! Tente novamente.");
      }
    }
  }

  private void listarAutoresRegistrados() {
    var autores = bibliotecaServices.listarAutoresRegistrados();

    if (autores.isEmpty()) {
      System.out.println("\nNenhum autor registrado.");
      return;
    }

    System.out.println("\nAutores registrados:");
    autores.stream().forEach(a -> printAutor(a));
  }

  private void listarLivrosPorIdioma() {
    System.out.print("Digite o idioma (en, pt, fr, es): ");
    String idiomaInput = sc.nextLine().trim().toLowerCase();

    if (idiomaInput.isEmpty()) {
      System.out.println("Idioma não pode ser vazio!");
      return;
    }

    try {
      var idioma = Idiomas.fromString(idiomaInput);
      var livros = bibliotecaServices.listarLivrosPorIdioma(idioma);

      if (livros.isEmpty()) {
        System.out.println("\nNenhum livro encontrado no idioma " + idioma.getIdioma() + ".");
        return;
      }

      System.out.println("\nLivros no idioma " + idioma.getIdioma() + ":");
      livros.stream().forEach(e -> printLivro(e));
    } catch (IllegalArgumentException e) {
      System.out.println("Idioma inválido! Por favor, use um dos seguintes: en, pt, fr, es.");
    }
  }

  private void listarAutoresVivosPorAno() {
    System.out.print("Digite o ano: ");
    int ano = Integer.parseInt(sc.nextLine());

    if (ano <= 0) {
      System.out.println("Ano inválido!");
      return;
    }

    var autores = bibliotecaServices.listarAutoresVivosPorAno(ano);

    if (autores.isEmpty()) {
      System.out.println("\nNenhum autor vivo encontrado no ano " + ano + ".");
      return;
    }

    System.out.println("\nAutores vivos em " + ano + ":\n");
    autores.stream().forEach(a -> printAutor(a));
  }

  private void listarLivrosRegistrados() {
    var livros = bibliotecaServices.listarLivrosRegistrados();

    if (livros.isEmpty()) {
      System.out.println("\nNenhum livro registrado.");
      return;
    }

    System.out.println("\nLivros registrados:");
    livros.stream().forEach(e -> printLivro(e));
  }

  private void buscarLivrosPorTitulos() {
    System.out.print("Digite o título do livro: ");
    String titulo = sc.nextLine().trim();

    if (titulo.isEmpty()) {
      System.out.println("Título não pode ser vazio!");
      return;
    }

    CompletableFuture<Optional<Livro>> fetchApiRequest = CompletableFuture.supplyAsync(() -> {
      return bibliotecaServices.buscarLivroPorTitulo(titulo);
    });

    System.out.print("Carregando.");
    while (!fetchApiRequest.isDone()) {
      try {
        Thread.sleep(1000);
        System.out.print(".");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    var livroOptional = fetchApiRequest.join();
    System.out.println();

    if (livroOptional.isPresent()) {
      var livro = livroOptional.get();
      System.out.println("\nLivro encontrado:");
      printLivro(livro);
    } else {
      System.out.println("\nLivro não encontrado.");
    }
  }

  private void printLivro(Livro livro) {
    System.out.println(addIndent("Título: " + livro.getTitulo(), 2));
    System.out.println(addIndent("Autor: " + livro.getAutor().getNome(), 2));
    System.out.println(addIndent("Idioma: " + livro.getIdioma().getIdioma().toLowerCase(), 2));
    System.out.println(addIndent("Número de downloads: " + livro.getNumeroDownloads(), 2));
    System.out.println();
  }

  private void printAutor(Autor autor) {
    System.out.println(addIndent("Nome: " + autor.getNome(), 2));
    System.out.println(addIndent("Ano de nascimento: " + autor.getAnoNascimento(), 2));
    if (autor.getAnoFalecimento() != null) {
      System.out.println(addIndent("Ano de falecimento: " + autor.getAnoFalecimento(), 2));
    } else {
      System.out.println(addIndent("Autor ainda está vivo", 2));
    }

    List<String> titulos = autor.getLivros().stream()
        .map(Livro::getTitulo)
        .toList();
    System.out.println(addIndent("Livros: " + titulos, 2));
    System.out.println();
  }

  private String addIndent(String string, int numSpaces) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numSpaces; i++) {
      sb.append(" ");
    }
    sb.append(string);
    return sb.toString();
  }
}
