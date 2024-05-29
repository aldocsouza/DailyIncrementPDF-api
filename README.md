## DailyPDFGenerator-API

É uma API muito simples baseada em Spring Boot, projetada para testar biblíotecas para utilização em projetos futuros na automatização no incremento diário de uma coluna de banco de dados e gerar dados PDF. O projeto usa a anotação @Scheduler para executar uma tarefa agendada à meia-noite todos os dias, incrementando +1 na coluna number no banco de dados. Além disso, a API disponibiliza um endpoint que, ao ser acessado, gera um arquivo PDF contendo uma tabela com os dados de “Número de Orientação” e “Data de Emissão”, que com base neles, será gerado um código Hash 256 e um código de barras no arquivo PDF.

Este projeto é ideia minha para resolver um problema específico: a necessidade de atualizações automatizadas diárias e a geração de um arquivo PDF atualizado com os dados de atualização do dia. Ao automatizar esses processos, a API garante que os dados sejam atualizados com precisão e estejam prontamente disponíveis em um formato PDF fácil de usar.

## Aviso
Esta é uma API bem simples, portanto não contém validações, mas futuramente poderá ser implementado.
É um projeto com o intuíto de práticar novas ferramentas como Agendador de Tarefas, Gerador de PDF, Hash Code e Bar code.

## Funcionalidades

- **Incremento Diário:** Um método agendado que executa a cada meia-noite, incrementando em +1 a coluna do banco de dados.
- **Banco de Dados em Memória:** Utilização do H2 Database para facilitar o desenvolvimento e testes.
- **Dependências Essenciais:** Inclui dependências como Spring Web, Spring Dev Tools, e H2 Database para fornecer a funcionalidade completa do projeto.
- **Geração de PDF com iText:** Utiliza a biblioteca iText para gerar relatórios em PDF com um formato tabular.
- **Gerar Código de Barras com a lib Barbecue:** Utiliza a chave Hash contida no banco de dados para gerar um código de barras.

## Tecnologias Utilizadas

- **Spring Boot**
  - Spring Web
  - Spring Dev Tools
  - Spring Data JPA
  - H2 Database
  - [iText PDF](https://github.com/itext)
  - Barbecue - [Artigo Baeldung](https://www.baeldung.com/java-generating-barcodes-qr-codes)

## Pré-requisitos

- Java 11 ou superior
- Maven

## Como Executar o Projeto

1. **Clone o repositório:**

    ```bash
   git clone https://github.com/aldocsouza/DailyIncrementPDF-api.git

2. Compile e execute o projeto:
    ```bash
   mvn spring-boot:run

3. Para gerar o arquivo PDF, basta copiar no browser a URL endpoint abaixo:
   ```bash
   http://localhost:8080/generate-pdf

5. Acesse no browser o banco de dados H2 Database copiando a URL abaixo:

     ```bash
    http://localhost:8080/h2-console


## Configuração do Banco de Dados
O banco de dados H2 está configurado em src/main/resources/application.properties:

  ```bash
      spring.datasource.url=jdbc:h2:mem:testdb
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=password
      spring.h2.console.enabled=true
      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect      

````
## Exemplo de Funcionamento
A cada meia-noite, a aplicação executa um método agendado que incrementa a coluna "Number" da tabela DocumentTable no banco de dados H2.

  ```java
    @Service
    public class SchedulingService {
    
    
        private DocumentTableRepository tableRepository;
    
        public SchedulingService(DocumentTableRepository tableRepository) {
            this.tableRepository = tableRepository;
        }
    
        @Scheduled(cron = ("0 0 0 * * *")) // 0(Segundo), 0(Minuto), 0(Hora), 0(Dia), 0(Mês) e 0(Ano)
        //@Scheduled(fixedDelay = 30000) // Aqui você pode escolher os segundos
        public DocumentTable incrementNumberDocument() {
            Integer maxNumber = Optional.ofNullable(tableRepository.findMaxNumber()).orElse(0);
            int newNumber = maxNumber + 1;
            LocalDateTime data = LocalDateTime.now();
    
            DocumentTable table = new DocumentTable();
            table.setNumber(newNumber);
            table.setDate(data);
            table.setHash(hashService.generateValidationCode(table));
    
            return tableRepository.save(table);
        }
    
    }

```
Este projeto é ideal para aplicações que necessitam de atualizações regulares de dados e geração automatizada de relatórios, garantindo uma gestão eficiente de dados e acessibilidade.

