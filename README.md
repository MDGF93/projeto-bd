## Modelos

### Modelo físico do banco de dados

<img src="https://i.imgur.com/6atFeZI.png">

## Modelo lógico do banco de dados

<img src="https://i.imgur.com/64qlV8R.png">

### Modelo conceitual do banco de dados

<img src="https://i.imgur.com/Um4BPXy.png">


## SQL

### Criação das tabelas
A criação de tabela foi toda feita por intermédio da dependência `Hibernate` e as manipulações foram feitas por meio do `Spring Data JPA`.
#### Exemplos
```sql
    create table poster (
       id  bigserial not null,
        url varchar(255),
        movie_id int8,
        primary key (id)
    );
```

```sql
    create table room (
    id bigserial not null,
    capacity int4,
    primary key (id)
    );
```

```sql
    create table session (
       id  bigserial not null,
        available_seats int4,
        date date,
        end_time time,
        session_movie_title varchar(255),
        start_time time,
        movie_id int8,
        room_id int8,
        primary key (id)
    );
```

```sql
    create table ticket (
       id  bigserial not null,
        is_credit_card boolean not null,
        seat_number int4,
        ticket_type int4,
        session_id int8,
        primary key (id)
    );
```

```sql
    create table session_tickets (
       session_id int8 not null,
        tickets_id int8 not null
    );
alter table session_tickets
    add constraint UK_l3xo4milgpredg1tmi2sb92dp unique (tickets_id);
```

### Manipulação das tabelas

#### Exemplos
Para ilustrar a view abaixo o ``Hibernate`` executou a seguinte query:

Query:
```sql
select
    snack0_.id as id1_6_,
    snack0_.name as name2_6_,
    snack0_.price as price3_6_,
    snack0_.url as url4_6_
from
    snack snack0_
```
Resultado no HTML:
<img src="https://i.imgur.com/Q7gTzwH.png">

Ao clicar em "Adicionar Snack" somos levados para a seguinte página:
<img src="https://i.imgur.com/x8j4L13.png">

E o ``Hibernate`` para gerar essa imagem acima executou o seguinte comando:
```sql
Hibernate: 
    insert 
    into
        snack
        (name, price, url) 
    values
        (?, ?, ?)
-- Esses são os valores que foram passados pelo usuário
-- Através de um POST para o endpoint /snacks, e irá  adicioná-los
-- na tabela snack.
```

## Sobre

### Alunos e matrículas
- Gabriel De Lucca Vieira - **20180042775**
- Herbert Gomes Mendes - **20200109822**
- Marcos Dantas Guimarães - **20200133309**


### O que é o projeto?

O projeto é um sistema para o gerenciamento de um cinema.

### O que o sistema faz?

O sistema permite que o usuário faça o cadastro de filmes, salas, sessões e compra de ingressos.

### Quais são as entidades do sistema?

- Movie
- Actor
- Poster
- Room
- Session
- Ticket
- Snack
- SnackOrder
- Transaction

### Qual a arquitetura do sistema?

O sistema é composto por uma API RESTful, que é consumida por um aplicativo web. O padrão de design seguido foi o MVC (*Model Viewer Controller*).
<img src="https://i.imgur.com/YwfHFG6.png">

### Quais foram as tecnologias utilizadas?

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- HTML
- Thymeleaf


