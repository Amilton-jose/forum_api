# Challenge ONE | Back End | Turma 4 Back-end com Java | Projeto de criação de uma Api de um forum, proposto pela Alura Latam

<p align="center" >
     <img width="200" height="200" src="https://user-images.githubusercontent.com/78982435/209698701-28dedb2e-855b-44b2-8872-afa45e3b35aa.png" alt="">
</p>

### Tecnologias utilizadas:

- [Intellij](https://www.jetbrains.com/)
- [MySql](https://www.mysql.com/)
- [Java](https://www.java.com/pt-BR/)
- [Spring Security](https://start.spring.io/)
- [Token JWT](https://jwt.io/)

# Challenge Back End - API REST com Spring

Bem-vindo ao nosso mais recente desafio Challenge Back End!

O Fórum Alura é um lugar onde todos os alunos da plataforma alura podem colocar suas perguntas sobre determinados
cursos. Este lugar mágico está cheio de muita aprendizagem e colaboração entre alunos, professores e moderadores.

Este desafio visa replicar o processo do fórum no nível do back end, criando uma API REST usando Spring. A API será
focada nos tópicos, respostas e criação de usuários e permitirá aos usuários realizar as seguintes operações:

Essas operações correspondem ao que conhecemos normalmente como CRUD (CREATE, READ, UPDATE, DELETE).

Ao final do processo, teremos uma API REST com rotas implementadas seguindo as melhores práticas do modelo REST,
validações realizadas segundo as regras de negócio, implementação de uma base de dados para a persistência da informação
e serviço de autenticação/autorização para restringir o acesso à informação.

## Como executar o projeto com docker

1. Instale o docker desktop em sua máquina
2. Faça o clone deste repositório: `git clone https://github.com/Amilton-jose/forum_api.git
3. Entre na pasta do projeto: `cd forum_api`
4. Instale as dependências via terminal com o comando : `nvm clean install`
5. Execute o seguinte comando no terminal: `docker-compose up`
6. Agora que temos a imagen do banco de dados rodando no docker
7. Vamos usar o seguinte comando no terminal para iniciar a aplicação: `mvn spring-boot:run`


6. Acesse a API pelo endereço `http://localhost:8080`

## Endpoints - Usuário

- `GET /Usuarios`: retorna todos os Usuários
- `GET /Usuarios/:id`: retorna um Usuário específica pelo seu id
- `POST /Usuarios`: cria um Usuário com senha criptografada
- `PUT /Usuarios/:id`: atualiza um Usuário existente pelo seu id

## Endpoints - Login

- `POST /login`: Login de Usuario para obter token de acesso

## Endpoints - Topico

- `GET /topics`: retorna todos os tópicos
- `GET /topics/:id`: retorna um tópico específico pelo seu id
- `POST /topics`: cria um tópico
- `PUT /topics/:id`: atualiza um tópico existente pelo seu id
- `DELETE /topics/:id`: exclui um tópico existente pelo seu id

## Endpoints - Resposta

- `GET /respostas`: retorna todos ass Respostas
- `GET /respostas/:id`: retorna uma Resposta específica pelo seu id
- `POST /respostas`: cria uma Resposta
- `PUT /respostas/:id`: atualiza uma Resposta existente pelo seu id
- `DELETE /respostas/:id`: exclui uma Resposta existente pelo seu id





