<h1 align="center" id="title">Highly Scalable URL Shortener</h1>

<p align="center"><img src="https://socialify.git.ci/EduardoBombonatto/UrlShortener/image?language=1&amp;owner=1&amp;name=1&amp;stargazers=1&amp;theme=Light" alt="project-image"></p>

<p id="description">Um encurtador de URLs de alta performance baseado na arquitetura proposta pelo livro <b>System Design Interview (Alex Xu)</b>. O sistema utiliza um gerador de IDs customizado (Snowflake de 41-bits) acoplado à conversão Base62 para garantir URLs curtas de exatamente 7 caracteres sem colisões além de um cache em memória com Redis para redirecionamentos ultrarrápidos.</p>

<p align="center"><img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&amp;logo=openjdk&amp;logoColor=white" alt="shields"><img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&amp;logo=spring&amp;logoColor=white" alt="shields"><img src="https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&amp;logo=redis&amp;logoColor=white" alt="shields"><img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&amp;logo=docker&amp;logoColor=white" alt="shields"></p>

<h2>🚀 Demo</h2>

<img src="https://i.imgur.com/2T1aYJy.png" width="800">

  
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   **Custom Snowflake ID Generator**: Algoritmo de geração de IDs distribuídos adaptado para 41-bits (baseado em tempo de época personalizado e Machine ID) evitando a necessidade de checagem de colisão no banco de dados.
*   **Base62 Encoding**: Conversão matemática para encurtar IDs numéricos gigantes em strings alfanuméricas de estritos 7 caracteres.
*   **Caching de Alta Performance (Redis)**: O sistema salva o mapeamento da URL na memória interceptando chamadas repetidas e poupando leituras massivas no banco de dados relacional (otimizado para fluxos com alta taxa de leitura).
*   **Redirecionamento HTTP 302**: Otimizado para permitir tracking e analytics (diferente do 301 obriga o navegador a consultar o servidor).
*   **Infraestrutura Dockerizada**: Facilidade de deploy com containers.

<h2>🛠️ Installation Steps:</h2>

<p>1. Clone o repositório</p>

```
git clone https://github.com/EduardoBombonatto/UrlShortener.git
```

<p>2. Suba o Redis via Docker Compose</p>

```
docker-compose up -d
```

<p>3. Execute a aplicação Spring Boot</p>

```
./mvnw spring-boot:run
```

  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Java
*   Spring Boot
*   JPA
*   Redis
*   H2 Database
*   Docker
*   Maven

<h2>💖Like my work?</h2>

Se este projeto te ajudou a entender melhor Design de Sistemas considere dar uma ⭐️ no repositório! Para dúvidas ou feedbacks abra uma Issue no projeto.
