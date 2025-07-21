# LiterAlura

## Sobre o projeto

LiterAlura é uma aplicação Java que permite consultar e explorar livros de domínio público, utilizando dados obtidos de uma API externa. O sistema oferece funcionalidades para pesquisar livros, visualizar detalhes de autores e realizar operações relacionadas à biblioteca.

## Tecnologias utilizadas

- Java 17+
- Maven 
- Consumo de API REST (HTTP)
- Padrão DTO

## API utilizada

Os dados dos livros são obtidos da [Gutendex API](https://gutendex.com/), um serviço aberto que fornece informações sobre obras do Projeto Gutenberg.

## Como rodar o projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/LiterAlura.git
   ```
2. **Acesse a pasta do projeto:**
   ```bash
   cd LiterAlura
   ```
3. **Compile o projeto (Maven):**
   ```bash
   mvn clean install
   ```
   ```
4. **Execute a aplicação:**
   ```bash
   mvn exec:java -Dexec.mainClass="br.alura.LiterAlura.LiterAluraApplication"
   ```
   ```

## Estrutura do projeto

- **src/main/java/br/alura/LiterAlura/model/** — Entidades principais (Livro, Autor, Idiomas)
- **src/main/java/br/alura/LiterAlura/dto/** — Objetos de transferência de dados (DTOs)
- **src/main/java/br/alura/LiterAlura/service/** — Lógica de negócio e serviços
- **src/main/java/br/alura/LiterAlura/util/** — Utilitários, como consumo de API
- **src/main/java/br/alura/LiterAlura/view/** — Menu e interação com usuário

## Observações

- Certifique-se de ter o Java 17 ou superior instalado.
- A aplicação utiliza o padrão DTO para facilitar a integração com APIs externas.
- O projeto pode ser adaptado para outros tipos de interface (web, por exemplo) conforme necessidade.

---

Desenvolvido para fins educativos.

