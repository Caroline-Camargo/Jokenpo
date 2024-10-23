# ✊✋✌️ Jokenpo ✊✋✌️
O jogo de Jokenpo é jogado entre dois jogadores, onde cada um escolhe uma opção entre "pedra", "papel" ou "tesoura". O objetivo é determinar o vencedor com base nas regras clássicas:

  - Pedra vence Tesoura.
  - Tesoura vence Papel.
  - Papel vence Pedra.

Nesta aplicação foi implementado o modo contra o computador, onde o jogador enfrenta a máquina! 🤖

## 🚀 Tecnologias Utilizadas

### Backend
- *☕ Java*: Linguagem de programação utilizada.
- *🌱 Spring Boot*: Framework para criação de aplicações Java.
- *🐘 PostgreSQL*: Banco de dados utilizado.
- *🔄 Liquibase*: Ferramenta para versionamento de banco de dados.
- *🔧 Maven*: Gerencia dependências e automatiza o processo de build do projeto.

### Frontend
- *⚛ React*: Biblioteca JavaScript para construção de interfaces de usuário.
- *📝 TypeScript*: Superset do JavaScript que adiciona tipagem estática.
- *💅 Bootstrap*: Biblioteca de componentes que facilita o desenvolvimento de interfaces web.
- *📦 npm*: Gerenciador de pacotes para JavaScript, utilizado para instalar dependências do projeto.
- *📦 Webpack*: Empacotador de módulos que compila e otimiza os arquivos do projeto.
- *🔄 Babel*: Converte código JavaScript moderno em versões compatíveis com navegadores mais antigos.

## 🛠 Como Executar

### Pré-requisitos

- *☕ Java 12* ou superior
- *🟢 Node.js* e *📦 npm*
- *🐳 Docker* (para executar o banco de dados PostgreSQL)

### Backend

1. Navegue até o diretório backend:
    sh
    cd backend
    

2. Inicie o banco de dados PostgreSQL usando Docker:
    sh
    docker-compose up -d
    

3. Execute a aplicação Spring Boot:
    sh
    ./mvnw spring-boot:run
    

### Frontend

1. Navegue até o diretório frontend:
    sh
    cd frontend
    

2. Instale as dependências:
    sh
    npm install
    

3. Execute a aplicação:
    sh
    npm start
    

## 📡 Endpoints da API

A API do backend possui os seguintes endpoints principais:

### Jokenpo Game

- **POST /jokenpo/play**: Inicia uma rodada de Jokenpo enviando os dados do jogador e sua escolha (pedra, papel ou tesoura).

    Exemplo de JSON:
    ````
    {
    "player1": "Carol",
    "choice1": "pedra"
    }
    ````


### Jokenpo Match

- **POST /jokenpo/match**: Cria um novo registro de partida.

    Exemplo de JSON:
    ````
    {
        "player1": "Carol",
        "player2": "Computador",
        "choice1": "pedra",
        "choice2": "tesoura",
        "winner": "Carol",
        "date": "2024-10-10T14:30:00"
    }
    ````

- **GET /jokenpo/match**: Retorna todas as partidas registradas.
- **GET /jokenpo/match/{id}**: Retorna os detalhes de uma partida específica pelo seu ID.
- **PUT /jokenpo/match/{id}**: Atualiza as informações de uma partida existente pelo seu ID.
- **DELETE /jokenpo/match/{id}**: Deleta uma partida do sistema pelo seu ID

## 🎮 Fluxo do Jogo

<img width="956" alt="image" src="https://github.com/user-attachments/assets/70b11e29-45d6-4905-9169-c80d02078ad1">

1. O jogador inicia preenchendo seu nome e faz sua jogada
2. O backend retorna retorna um resultado com base na jogada do jogador e a lógica pré-definida no backend. Como o jogo é contra a máquina, a jogada do computador será gerada automaticamente 


### 👀 Visualização do histórico

<img width="959" alt="image" src="https://github.com/user-attachments/assets/93bdc6d2-4da2-4cef-8e9d-0b47faba1a45">

1. O jogador acessa a página de histórico de partidas clicando na aba "Histórico de Partidas".
2. O backend retorna o histórico de partidas cadastradas no banco.
3. É possível ver a lista de partidas, incluindo nome dos jogadores, qual foi a opção de jogada escolhida, o vencedor da partida e a data que a partida ocorreu.
4. Além disso, é possível filtrar a lista de partidas por nome do jogador e por vitória, derrota ou empate.


### ❌ Deletando Histórico de partidas

1. Na aba "Histórico de Partidas", localize a partida que deseja excluir.
2. Clique no botão de uma lixeira no canto superior direito da partida.
3. O backend remove a palavra do banco de dados.

|   Atualização do conteúdo de cada partida só pode ser realizada via endpoint


## ▶️ Testes no Postman

Você pode rodar os testes da coleção no Postman clicando no botão abaixo:

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/38568266-5f678e57-531e-483f-9f5f-50b6867e5505?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D38568266-5f678e57-531e-483f-9f5f-50b6867e5505%26entityType%3Dcollection%26workspaceId%3Dc6062a23-2dcc-4d80-8515-779b4e5a68a7)
