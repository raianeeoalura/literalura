package br.alura.LiterAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.alura.LiterAlura.view.MenuPrincipal;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
  @Autowired
  private MenuPrincipal menu;

  public static void main(String[] args) {
    SpringApplication.run(LiterAluraApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    menu.exibirMenu();
  }

}
