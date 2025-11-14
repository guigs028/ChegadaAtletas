# 🥊 MFC - Master Fighting Championship
## Sistema de Controle de Presença de Atletas

Sistema web desenvolvido para o evento Master Fighting Championship, permitindo o cadastro e controle de presença de atletas e treinadores.

---

## 🎯 Funcionalidades

✅ **Cadastro de Atletas**
- Nome, número da luta, equipe e cor
- Associação com até 2 treinadores
- Controle de presença individual

✅ **Cadastro de Treinadores**
- Nome e equipe
- Controle de presença
- Vínculo com múltiplos atletas

✅ **Gerenciamento**
- Listagem ordenada por número de luta
- Filtros: Todos / Presentes / Ausentes
- Busca por nome de atleta ou treinador
- Exclusão individual ou em massa

✅ **Estatísticas em Tempo Real**
- Total de atletas cadastrados
- Atletas presentes
- Atletas ausentes

---

## 🛠️ Tecnologias

### Backend:
- **Java 17**
- **Spring Boot 3.5.7**
  - Spring Data JPA
  - Spring Web
  - Spring Validation
- **PostgreSQL** (banco de dados)
- **Flyway** (migrations)
- **Lombok** (redução de código boilerplate)

### Frontend:
- **HTML5** + **CSS3** + **JavaScript**
- Design responsivo
- Identidade visual MFC (preto, dourado)

### DevOps:
- **Docker** + **Docker Compose**
- **Maven** (build)

---

## 🚀 Como Executar Localmente

### Pré-requisitos:
- Java 17+
- PostgreSQL 16+
- Maven 3.9+

### 1. Clonar Repositório:
```bash
git clone https://github.com/SEU_USUARIO/EntradaAtletas.git
cd EntradaAtletas
```

### 2. Configurar Banco de Dados:
```bash
# Criar banco no PostgreSQL
createdb EntradaAtletas
```

### 3. Configurar application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/EntradaAtletas
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
```

### 4. Executar:
```bash
# Com Maven
./mvnw spring-boot:run

# Ou no Windows
mvnw.cmd spring-boot:run
```

### 5. Acessar:
```
http://localhost:8080
```

---

## 🐳 Executar com Docker

### Opção mais fácil (tudo incluído):
```bash
# Iniciar aplicação + banco de dados
docker-compose up -d --build

# Ver logs
docker-compose logs -f app

# Parar
docker-compose down
```

### Acessar:
```
http://localhost:8080
```

---

## 📦 Deploy em Produção

Consulte o arquivo **[DEPLOY.md](./DEPLOY.md)** para instruções detalhadas de deploy em:
- ✅ **Render** (grátis, recomendado)
- ✅ **Railway** (grátis com limites)
- 💰 **Heroku**
- 🖥️ **VPS/Servidor Próprio**

### Deploy Rápido no Render (5 minutos):
1. Faça push do código no GitHub
2. Crie conta no [Render.com](https://render.com)
3. Crie PostgreSQL (Free)
4. Crie Web Service do seu repo GitHub
5. Configure variáveis de ambiente
6. Deploy automático! 🎉

---

## 📁 Estrutura do Projeto

```
EntradaAtletas/
├── src/
│   ├── main/
│   │   ├── java/com/guigs/EntradaAtletas/
│   │   │   ├── controller/      # Controllers REST
│   │   │   ├── entity/          # Entidades JPA
│   │   │   ├── repository/      # Repositórios
│   │   │   └── service/         # Lógica de negócio
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-prod.properties
│   │       ├── db/migration/    # Migrations Flyway
│   │       └── static/          # Frontend (HTML/CSS/JS)
│   └── test/                    # Testes
├── Dockerfile                   # Build Docker
├── docker-compose.yml           # Orquestração local
├── render.yaml                  # Config Render
├── Procfile                     # Config Heroku
├── pom.xml                      # Dependências Maven
├── DEPLOY.md                    # Guia de deploy
└── README.md                    # Este arquivo
```

---

## 🎨 Identidade Visual

O sistema segue a identidade visual do **Master Fighting Championship**:
- **Preto** (#000000) - Background principal
- **Dourado** (#c9a961) - Destaques e ações importantes
- **Cinza escuro** (#0a0a0a, #111111) - Cards e containers

---

## 📊 API Endpoints

### Atletas:
- `GET /api/atletas` - Listar todos
- `GET /api/atletas/{id}` - Buscar por ID
- `POST /api/atletas` - Criar atleta
- `PUT /api/atletas/{id}/presenca` - Atualizar presença
- `DELETE /api/atletas/{id}` - Deletar atleta
- `DELETE /api/atletas` - Deletar todos

### Treinadores:
- `GET /api/treinadores` - Listar todos
- `POST /api/treinadores` - Criar treinador
- `PUT /api/treinadores/{id}/presenca` - Atualizar presença
- `DELETE /api/treinadores/{id}` - Deletar treinador

### Estatísticas:
- `GET /api/atletas/stats` - Estatísticas de presença

---

## 🔐 Segurança

⚠️ **IMPORTANTE**: Nunca commite senhas ou credenciais no Git!

- Use variáveis de ambiente em produção
- `.gitignore` já configurado para ignorar arquivos sensíveis
- `application-prod.properties` usa variáveis de ambiente

---

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test

# Executar com coverage
./mvnw clean test jacoco:report
```

---

## 📝 Migrations do Banco

As migrations são gerenciadas pelo **Flyway** e estão em:
```
src/main/resources/db/migration/
├── V1__create_table_treinador.sql
└── V2__create_table_atleta.sql
```

Flyway aplica automaticamente ao iniciar a aplicação.

---

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch: `git checkout -b feature/nova-funcionalidade`
3. Commit: `git commit -m 'Adiciona nova funcionalidade'`
4. Push: `git push origin feature/nova-funcionalidade`
5. Abra um Pull Request

---

## 📄 Licença

Este projeto é de uso interno do **Master Fighting Championship**.

---

## 👤 Autor

**Guilherme** - Desenvolvedor

---

## 🎯 Roadmap

- [ ] Autenticação de usuários
- [ ] Exportação para Excel/PDF
- [ ] Notificações em tempo real
- [ ] App mobile
- [ ] Integração com sistema de lutas

---

## 🐛 Reportar Bugs

Encontrou um bug? Abra uma [issue](https://github.com/SEU_USUARIO/EntradaAtletas/issues) descrevendo:
- O que aconteceu
- O que era esperado
- Passos para reproduzir
- Screenshots (se aplicável)

---

## 📞 Suporte

Para suporte durante eventos MFC, entre em contato com a equipe técnica.

---

**Desenvolvido com ❤️ para o Master Fighting Championship** 🥊

