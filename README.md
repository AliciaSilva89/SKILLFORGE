# SKILLFORGE

Documentação do Projeto Final

Visão Geral
Este projeto foi desenvolvido com o objetivo de criar uma aplicação robusta e escalável, composta por dois módulos principais:

Hard Skills:  Um jogo interativo que aborda habilidades de clean code.
Soft Skills: Um jogo interativo que aborda diversidade e inclusão.

A aplicação utiliza uma arquitetura baseada em micro serviços, com um backend desenvolvido em Java e um BFF em python, com o frontend em Next, além de um banco de dados relacional para armazenamento de informações.

## Arquitetura do Sistema
    Descrição Geral

    A arquitetura do sistema segue o modelo cliente-servidor, com separação clara entre o frontend e o backend. O backend é responsável por fornecer APIs RESTful para o frontend, que consome esses serviços para exibir as informações ao usuário.

### Componentes Principais.

    => Frontend desenvolvido em Next.
    Responsável pela interface do usuário e pela interação com as APIs do backend.

    => Backend for Frontend (BFF) desenvolvido em Python 3.13.1.
    Responsável pela conexão entre o java e o next.

    =>Backend desenvolvido em Java usando Spring Boot.
    Responsável pela lógica de negócios, manipulação de dados e fornecimento de APIs RESTful.

    =>Banco de Dados PostgreSQL como banco de dados relacional.
    Armazena informações relacionadas aos módulos do sistema, como desafios de programação e cenários de soft skills.

### Hospedagem e Infraestrutura
    
    Provedor de Nuvem: AWS
    =>Serviços utilizados:

    Frontend hospedade em uma intância EC2 da AWS,
    Backend hospedado em uma instância EC2 da AWS,
    BackForFront hospedado em uma instância EC2 da AWS,
    Banco de dados configurado na Amazon RDS.



### Arquitetura Detalhada

    =>Fluxo de Dados
    O usuário interage com o frontend (React), que conectado ao sistema em python  faz chamadas às APIs RESTful do backend e para o Stackspot batendo nos quickcomands.

    O backend processa as requisições, aplica a lógica de negócios e interage com o banco de dados para recuperar ou armazenar informações.
    
    As respostas são enviadas de volta ao python que manda para o frontend, que exibe os dados ao usuário.


### Contêinerização
    
    Todos os componentes do backend (BFF e backend em java) foram empacotados em containers Docker para garantir portabilidade e consistência no ambiente de execução.

    O uso de contêineres facilita o gerenciamento e a escalabilidade da aplicação, permitindo que os serviços sejam implantados de forma independente.
