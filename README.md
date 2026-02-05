# java-financial-management-system

# Projeto: Sistema Financeiro em Java

# Ambiente utilizado

    Linguagem: Java
    Banco de Dados: PostgreSQL
    Driver JDBC: postgresql-42.x.x.jar
    IDE: Eclipse

# Bibliotecas externas

    jfreechart-1.5.6.jar
    jcommon-1.0.24.jar

# Projeto

Este projeto foi desenvolvido no 3º semestre do curso de Ciência da Computação e tem como objetivo aplicar conceitos de Programação Orientada a Objetos (POO), organização em camadas e integração com banco de dados.

O sistema simula uma gestão financeira básica, contemplando regras de negócio, persistência de dados e interface gráfica, com separação clara de responsabilidades entre os módulos.

# Estrutura do projeto

O projeto está organizado nos seguintes pacotes:

    * dados:
        Classes responsáveis pelos dados do sistema como cliente (nome, cpf, senha...), gasto (nome do gasto, valor...) e categoria dos gastos, nesse utilizei enum.

    * conexao:
        Classes responsáveis pela conexão e comunicação com o banco de dados

    * negocio:
        Implementação das regras de negócio e controle do sistema
        Contém as classes principais, como Principal e Sistema

    * interface:
        Implementação da interface gráfica do usuário

# Conceitos aplicados

    * Programação Orientada a Objetos
    * Encapsulamento
    * Abstração
    * Separação de responsabilidades
    * Integração com banco de dados via JDBC
    * Interface gráfica em Java

# Execução

    1. Configure o banco de dados e ajuste a URL, usuário e senha da conexão;
    2. Após a configuração, verifique a classe responsável pela conexão na pasta conexao;
    3. Importe o projeto em uma IDE Java;
    4. Adicione as bibliotecas externas ao classpath do projeto;
    5. Execute a classe Principal localizada no pacote negocio;
    6. Após a execução, o sistema estará disponível para interação via interface gráfica.

# Observações

    Projeto desenvolvido com finalidade acadêmica;
    Estrutura voltada ao aprendizado de POO e organização de sistemas em Java;
    Pode ser expandido com melhorias arquiteturais, validações e novos recursos.
