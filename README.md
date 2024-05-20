# Gerenciamento de Cadastros e Login - Challenge FIAP ft. SalesForce - APIRestFul Java Jersey-JAX

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

# Sobre o projeto

O Sistema de Cadastro e gerenciamento de Login da SalesForce foi desenvolvido de acordo com as necessidades para o Projeto de criação de um novo portal para a SalesForce, com acessibilidade e inclusão, visando impulsionar vendas e alcançar um público mais abrangente.
Nosso projeto foi criado para ser um sistema intuitivo e simplista, mas com código robusto e organizado, contemplando as boas práticas do Java.

# Funcionalidades

- As operações CRUD (criação, leitura, atualização e deleção) de cadastros de empresa/usuários.
- O sistema estabele uma conexão entre usuários e suas atividades dentro do sistema, realizando o cadastro dos usuários no sistema.
- Sistema de login, feito com e-mail do usuário.

# Procedimentos para Rodar a Aplicação
- Iniciar o git com **git init**
- Adicionar **git clone** https://github.com/julianamo93/SalesForce-FIAP-challenge.git
- No IntelliJ: configurar o JDBC em Project Structure > Modules > JARs > adicionar o JAR do JDBC mais atual (disponível para download no portal Oracle)
OU
- Colar no arquivo POM.xml a Dependência do link abaixo:
```bash
https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11/23.3.0.23.09
```
- **Certifique-se de que o projeto foi criado em Maven Project no IntelliJ.**
- No Eclipse: configurar o JDBC com botão direito no projeto > Properties > Java Build Path > clique em Modulepath > Add External JARs… > Escolha o driver que você fez download anteriormente > Apply and Close;
Em ambas IDEs, o run do projeto é pelo Application.


# Tecnologias utilizadas

## Backend

- Java
- Jersey JAX
- Oracle SQL Developer

# Técnicas
- Arquitetura DAO

# Modelo Conceitual
![diagramJavaSprint]()

## ByteBloomTech - Integrantes
- Juliana Moreira - RM: 554113 - Desenvolvedora Back-end Java
- Kevin Christian Nobre - RM: 552590 - Desenvolvedor Back-end Python
- Sabrina Couto Xavier - RM: 552728 - Desenvolvedora Front-end
