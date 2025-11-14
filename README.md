# Sistema de Entrada de Atletas 🥋

Sistema web para controle de presença de atletas e treinadores em eventos de artes marciais.

## 🚀 Tecnologias Utilizadas

- **Backend**: Spring Boot 3.5.7
- **Banco de Dados**: PostgreSQL
- **Frontend**: HTML, CSS, JavaScript (Vanilla)
- **Persistência**: JPA/Hibernate
- **Validação**: Bean Validation

## 📋 Funcionalidades

### Interface Principal
- Busca em tempo real por nome de atleta ou treinador
- Filtros: Todos / Presentes / Ausentes
- Estatísticas em tempo real (Total, Presentes, Ausentes)
- Botão "Cadastrar Atleta" abre modal

### Modal de Cadastro de Atleta
- Formulário com todos os dados do atleta
- Nome, número da luta, cor, equipe
- Lista de treinadores com checkbox (selecione 1 ou 2)
- Botão "Novo Treinador" dentro do modal
- Validação de 1 a 2 treinadores obrigatório
- Limite automático de seleção (máx 2)

### Modal de Cadastro de Treinador
- Abre dentro do modal de atleta
- Nome e equipe
- Ao cadastrar, atualiza lista automaticamente
- Fecha e retorna ao modal de atleta

### Lista de Atletas
- Cards com todas as informações
- Número da luta, cor, equipe
- Sublista com treinadores do atleta
- Status de presença visual (verde/vermelho)
- Botão para marcar/desmarcar presença
- Status dos treinadores visível

## 🔧 Configuração e Instalação

### Pré-requisitos
- Java 17 ou superior
- PostgreSQL instalado e rodando
- Maven (incluído via Maven Wrapper)

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE EntradaAtletas;
```

2. Crie um usuário (ou use o existente):
```sql
CREATE USER entradaAtletas WITH PASSWORD 'Guilherme028!';
GRANT ALL PRIVILEGES ON DATABASE EntradaAtletas TO entradaAtletas;
```

3. As configurações estão em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/EntradaAtletas
spring.datasource.username=entradaAtletas
spring.datasource.password=Guilherme028!
spring.jpa.hibernate.ddl-auto=update
```

**IMPORTANTE**: Altere a senha no arquivo `application.properties` conforme sua configuração.

### Executando a Aplicação

#### Windows:
```powershell
.\mvnw.cmd spring-boot:run
```

#### Linux/Mac:
```bash
./mvnw spring-boot:run
```

### Acessando o Sistema

Após iniciar a aplicação, acesse:
- **Frontend**: http://localhost:8080
- **API REST**: http://localhost:8080/api

## 📡 Endpoints da API

### Treinadores

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/treinadores | Listar todos |
| GET | /api/treinadores/{id} | Buscar por ID |
| GET | /api/treinadores/buscar?nome={nome} | Buscar por nome |
| POST | /api/treinadores | Criar treinador |
| PATCH | /api/treinadores/{id}/presenca | Marcar/desmarcar presença |

### Atletas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/atletas | Listar todos |
| GET | /api/atletas?busca={busca} | Buscar por nome ou treinador |
| GET | /api/atletas?presente={true/false} | Filtrar por presença |
| GET | /api/atletas/{id} | Buscar por ID |
| POST | /api/atletas | Criar atleta |
| PUT | /api/atletas/{id} | Atualizar atleta |
| DELETE | /api/atletas/{id} | Deletar atleta |
| PATCH | /api/atletas/{id}/presenca | Marcar/desmarcar presença |

## 📝 Exemplos de Uso da API

### Criar Treinador
```json
POST /api/treinadores
{
  "nome": "João Silva",
  "equipe": "Academia Alpha"
}
```

### Criar Atleta
```json
POST /api/atletas
{
  "nome": "Maria Santos",
  "numeroLuta": 15,
  "cor": "Azul",
  "equipe": "Academia Alpha",
  "treinadoresIds": [1, 2]
}
```

### Buscar Atletas com Filtros
```
GET /api/atletas?busca=Maria&presente=true
```

## 🎨 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/guigs/EntradaAtletas/
│   │       ├── controller/       # Controllers REST
│   │       ├── dto/               # Data Transfer Objects
│   │       ├── entity/            # Entidades JPA
│   │       ├── exception/         # Exception handlers
│   │       ├── repository/        # Repositories
│   │       └── service/           # Lógica de negócio
│   └── resources/
│       ├── static/                # Frontend (HTML, CSS, JS)
│       └── application.properties # Configurações
```

## 🎯 Fluxo de Uso

1. **Acesse o sistema em http://localhost:8080**

2. **Cadastrar primeiro atleta:**
   - Clique no botão "➕ Cadastrar Atleta"
   - Um modal se abrirá
   - Se não houver treinadores, clique em "➕ Novo Treinador"
   - Cadastre o treinador (nome e equipe)
   - O modal fecha e a lista de treinadores atualiza
   - Selecione 1 ou 2 treinadores (checkbox)
   - Preencha os dados do atleta
   - Clique em "Cadastrar Atleta"

3. **Visualizar atletas:**
   - A lista mostra todos os atletas cadastrados
   - Cada card mostra: número da luta, cor, equipe
   - Abaixo aparecem os treinadores do atleta
   - Status de presença visível (verde = presente, vermelho = ausente)

4. **Marcar presença:**
   - Clique em "Marcar Presente" no card do atleta
   - O status muda automaticamente

5. **Buscar e filtrar:**
   - Use a caixa de busca para encontrar atleta ou treinador
   - Use os botões: Todos / Presentes / Ausentes

## 🔒 Validações

- **Atleta**: Nome, número da luta, cor e equipe são obrigatórios
- **Atleta**: Deve ter entre 1 e 2 treinadores
- **Treinador**: Nome e equipe são obrigatórios

## 🛠️ Desenvolvimento

### Compilar o projeto
```bash
.\mvnw.cmd clean compile
```

### Executar testes
```bash
.\mvnw.cmd test
```

### Gerar JAR
```bash
.\mvnw.cmd clean package
```

## 📄 Licença

Este projeto é de uso livre para fins educacionais.

## 👨‍💻 Desenvolvido por

Guilherme - Estagiário aprendendo Spring Boot

---

**Bons treinos! 🥋**

