# 🌍 Aventuras na Terra-média

Bem-vindo(a) a **Aventuras na Terra-média**, um RPG baseado em turnos inspirado no universo clássico de J.R.R. Tolkien! Monte sua Sociedade no Condado, desbrave as regiões perigosas e destrua o Um Anel no Monte da Perdição.

---

## 📸 Visão Geral do Jogo

O jogo é uma aplicação **Fullstack**, dividida em duas camadas principais:
- **Frontend (Angular)**: Uma interface rica e estilizada inspirada no estilo épico da Terra-média, com design responsivo (usando Bootstrap) e gerenciamento de estado interativo.
- **Backend (Spring Boot / Java)**: A inteligência do jogo. Gerencia cálculos de dano, XP, evolução de nível, inventário e controle de rotas pelo mapa.

---

## 🎮 Mecânicas Principais

1. **Formação da Sociedade**: Escolha 1 Herói Principal (que você controlará) e até 2 Ajudantes. Cada um possui ataques, passivas e eventos únicos.
2. **Combate em Turnos**: Explore locais para encontrar inimigos. Gerencie sua `Stamina` e seu `HP`. Se o seu HP chegar a 0, a Sombra vencerá.
3. **Mundo Linear**: Você só pode viajar para áreas adjacentes seguindo o percurso do livro (Condado ➔ Bree ➔ Valfenda ➔ ... ➔ Monte da Perdição).
4. **Sistema de Progressão (XP)**: Derrote inimigos para ganhar Experiência e Moedas. Suba de nível para restaurar sua vida e expandir seus atributos máximos.
5. **Eventos Épicos (Padrão Facade & Strategy)**: Cada herói possui um **Ataque Épico Secreto** em sua região de destino. Descubra as condições e acione-o para um efeito devastador na narrativa!

---

## 🛠️ Padrões de Projeto (Design Patterns) Utilizados

No Backend, aplicamos a tríade fundamental de padrões GoF de estruturação de software:
- **Singleton**: O controle da partida atual e do inventário do jogador é centralizado na classe `SessaoAventura`. Isso garante uma única fonte de verdade (State) durante o gameplay, sem inconsistências.
- **Facade**: O deslocamento e mapa do mundo são orquestrados pela `JornadaFacade`, que simplifica as regras complexas de distâncias e nós do mapa e fornece apenas os destinos válidos de acordo com as regras de negócio.
- **Strategy (via polimorfismo/injeção)**: O sistema de ações, especializações (heróis) e evolução de habilidades usa abstrações onde as diferentes execuções são chamadas uniformemente sem inflar os controladores.

---

## 🚀 Como Rodar o Projeto na Máquina Local

Para executar o projeto, você precisará do **Java 17+**, **Maven**, e **Node.js (LTS)** instalados na sua máquina.

### Passo 1: Executando o Backend (Spring Boot)
1. Abra o terminal na pasta raiz do projeto e navegue até a pasta do backend:
   ```bash
   cd backend
   ```
2. Baixe as dependências e inicie o servidor rodando:
   ```bash
   mvn spring-boot:run
   ```
   *O backend iniciará e ficará aguardando conexões na porta `8080`.*

### Passo 2: Executando o Frontend (Angular)
1. Em um **novo terminal**, navegue até a pasta do frontend:
   ```bash
   cd frontend
   ```
2. Instale as dependências do Node (necessário apenas na primeira vez):
   ```bash
   npm install
   ```
3. Inicie o servidor de desenvolvimento do Angular:
   ```bash
   ng serve --open
   ```
   *O Angular irá compilar o código e automaticamente abrirá o jogo no seu navegador, no endereço `http://localhost:4200`.*

---

## 📜 Licença e Créditos
Projeto de cunho educacional para exercitar boas práticas de Design Patterns em Java, microsserviços, e arquiteturas frontend reativas.
"Um Anel para a todos governar, Um Anel para encontrá-los..."
