# 🚀 Guia Completo de Deploy - MFC Entrada de Atletas

## 📋 Índice
1. [Deploy Local com Docker](#1-deploy-local-com-docker)
2. [Deploy no Render (RECOMENDADO - GRÁTIS)](#2-deploy-no-render-recomendado---grátis)
3. [Deploy no Railway (GRÁTIS)](#3-deploy-no-railway-grátis)
4. [Deploy no Heroku](#4-deploy-no-heroku)
5. [Deploy em VPS/Servidor Próprio](#5-deploy-em-vpsservidor-próprio)

---

## 1. 🐳 Deploy Local com Docker

### Pré-requisitos:
- Docker Desktop instalado
- Docker Compose instalado

### Passo a Passo:

#### 1.1 Build e Start:
```bash
# Navegar até a pasta do projeto
cd "C:\Users\Guigs z\Desktop\Projetos\EntradaAtletas"

# Iniciar tudo com um comando
docker-compose up -d --build
```

#### 1.2 Verificar Status:
```bash
# Ver logs
docker-compose logs -f app

# Ver status dos containers
docker-compose ps
```

#### 1.3 Acessar:
- **Aplicação**: http://localhost:8080
- **Banco de dados**: localhost:5432

#### 1.4 Parar:
```bash
# Parar containers
docker-compose down

# Parar e remover volumes (LIMPA TUDO)
docker-compose down -v
```

---

## 2. 🌐 Deploy no Render (RECOMENDADO - GRÁTIS)

### Por que Render?
✅ **100% GRÁTIS** para aplicações web  
✅ **PostgreSQL grátis** (500MB)  
✅ **SSL automático** (HTTPS)  
✅ **Deploy automático** do GitHub  
✅ **Fácil configuração**  

### Passo a Passo:

#### 2.1 Preparar Repositório GitHub:
```bash
# Inicializar Git (se ainda não fez)
git init
git add .
git commit -m "Preparar para deploy no Render"

# Criar repositório no GitHub e fazer push
git remote add origin https://github.com/SEU_USUARIO/EntradaAtletas.git
git branch -M main
git push -u origin main
```

#### 2.2 Configurar no Render:

1. **Criar conta**: https://render.com (pode usar GitHub)

2. **Criar Banco de Dados PostgreSQL**:
   - Dashboard → New → PostgreSQL
   - Name: `mfc-postgres`
   - Database: `EntradaAtletas`
   - User: `mfcuser`
   - Region: `Oregon (US West)` ou mais próximo
   - Plan: **Free**
   - Clique em **Create Database**
   - ⚠️ **ANOTE** as credenciais: `Internal Database URL`

3. **Criar Web Service**:
   - Dashboard → New → Web Service
   - Connect seu repositório GitHub
   - Name: `mfc-entrada-atletas`
   - Region: mesma do banco
   - Branch: `main`
   - Runtime: **Docker**
   - Instance Type: **Free**
   
4. **Configurar Variáveis de Ambiente**:
   - Clique em **Environment**
   - Adicione:
     ```
     SPRING_PROFILES_ACTIVE=prod
     DATABASE_URL=<Internal Database URL do passo 2>
     DATABASE_USERNAME=mfcuser
     DATABASE_PASSWORD=<password do banco>
     PORT=8080
     ```

5. **Deploy**:
   - Clique em **Create Web Service**
   - Aguarde o build (5-10 minutos)
   - Acesse a URL fornecida!

#### 2.3 URL Final:
```
https://mfc-entrada-atletas.onrender.com
```

#### 2.4 Atualizações Automáticas:
```bash
# Qualquer push no GitHub faz deploy automático
git add .
git commit -m "Atualização"
git push
```

---

## 3. 🚂 Deploy no Railway (GRÁTIS)

### Por que Railway?
✅ **$5 grátis/mês** de crédito  
✅ **PostgreSQL incluído**  
✅ **Deploy super rápido**  
✅ **SSL automático**  

### Passo a Passo:

#### 3.1 Criar Conta:
- Acesse: https://railway.app
- Login com GitHub

#### 3.2 Deploy:
1. **New Project** → **Deploy from GitHub repo**
2. Selecione seu repositório
3. **Add variables**:
   ```
   SPRING_PROFILES_ACTIVE=prod
   ```
4. **Add PostgreSQL**:
   - Clique em **+ New**
   - Selecione **Database** → **PostgreSQL**
   - Railway conecta automaticamente!

#### 3.3 Configurar Domínio:
1. Vá em **Settings**
2. **Generate Domain**
3. Anote a URL

#### 3.4 URL Final:
```
https://seu-app.up.railway.app
```

---

## 4. 💜 Deploy no Heroku

### Pré-requisitos:
- Conta Heroku
- Heroku CLI instalado

### Passo a Passo:

#### 4.1 Login:
```bash
heroku login
```

#### 4.2 Criar App:
```bash
# Criar app
heroku create mfc-entrada-atletas

# Adicionar PostgreSQL
heroku addons:create heroku-postgresql:essential-0
```

#### 4.3 Configurar Variáveis:
```bash
heroku config:set SPRING_PROFILES_ACTIVE=prod
```

#### 4.4 Deploy:
```bash
git push heroku main
```

#### 4.5 Abrir App:
```bash
heroku open
```

#### 4.6 Ver Logs:
```bash
heroku logs --tail
```

---

## 5. 🖥️ Deploy em VPS/Servidor Próprio

### Pré-requisitos:
- VPS com Ubuntu 22.04+ (DigitalOcean, AWS, Azure, etc)
- Acesso SSH
- Domínio (opcional)

### Passo a Passo:

#### 5.1 Conectar ao Servidor:
```bash
ssh usuario@seu-servidor.com
```

#### 5.2 Instalar Docker:
```bash
# Atualizar sistema
sudo apt update && sudo apt upgrade -y

# Instalar Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Instalar Docker Compose
sudo apt install docker-compose -y

# Adicionar usuário ao grupo docker
sudo usermod -aG docker $USER
```

#### 5.3 Clonar Repositório:
```bash
git clone https://github.com/SEU_USUARIO/EntradaAtletas.git
cd EntradaAtletas
```

#### 5.4 Configurar Variáveis:
```bash
# Criar arquivo .env
nano .env
```

Adicione:
```env
DATABASE_URL=jdbc:postgresql://postgres:5432/EntradaAtletas
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=SENHA_SEGURA_AQUI
SPRING_PROFILES_ACTIVE=prod
```

#### 5.5 Iniciar com Docker Compose:
```bash
docker-compose up -d --build
```

#### 5.6 Configurar Nginx (opcional - para domínio):
```bash
sudo apt install nginx -y
```

Criar configuração:
```bash
sudo nano /etc/nginx/sites-available/mfc
```

Adicione:
```nginx
server {
    listen 80;
    server_name seu-dominio.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

Ativar:
```bash
sudo ln -s /etc/nginx/sites-available/mfc /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx
```

#### 5.7 SSL com Let's Encrypt:
```bash
sudo apt install certbot python3-certbot-nginx -y
sudo certbot --nginx -d seu-dominio.com
```

---

## 📊 Comparação de Plataformas

| Plataforma | Custo | PostgreSQL | SSL | Deploy | Dificuldade |
|------------|-------|------------|-----|---------|-------------|
| **Render** | ✅ Grátis | ✅ 500MB grátis | ✅ Auto | 🔄 Auto GitHub | ⭐ Fácil |
| **Railway** | 💰 $5/mês grátis | ✅ Incluído | ✅ Auto | 🔄 Auto GitHub | ⭐ Fácil |
| **Heroku** | 💰 $5-7/mês | 💰 Pago | ✅ Auto | 🔄 Git push | ⭐⭐ Médio |
| **VPS** | 💰 $5+/mês | ⚙️ Manual | ⚙️ Manual | ⚙️ Manual | ⭐⭐⭐ Difícil |
| **Docker Local** | ✅ Grátis | ✅ Incluído | ❌ Não | ⚙️ Manual | ⭐⭐ Médio |

---

## 🎯 Recomendação para Eventos MFC

### Para Eventos Únicos/Ocasionais:
**Render (GRÁTIS)** ✅
- Deploy antes do evento
- Grátis e funcional
- Fica inativo após 15min sem uso (leva ~30s para "acordar")

### Para Uso Profissional/Frequente:
**Railway ($5/mês)** 💰
- Sempre ativo
- Mais rápido
- Banco maior

### Para Aprendizado/Desenvolvimento:
**Docker Local** 🐳
- Total controle
- Sem custos
- Apenas na sua máquina

---

## 🔧 Solução de Problemas

### Erro: "Cannot connect to database"
```bash
# Verificar se DATABASE_URL está correto
# Deve ser: jdbc:postgresql://HOST:5432/DATABASE_NAME
```

### Erro: "Port already in use"
```bash
# Mudar porta no docker-compose.yml
ports:
  - "8081:8080"  # Usar 8081 ao invés de 8080
```

### Erro de Build no Render/Railway:
```bash
# Verificar se Dockerfile está na raiz do projeto
# Verificar se pom.xml está correto
```

### App muito lento no Render Free:
```
Isso é normal! No plano gratuito o app "dorme" após 15min sem uso.
Primeira requisição demora ~30s.
Considere upgrade ou usar Railway.
```

---

## 📝 Checklist Antes do Deploy

- [ ] Código commitado no GitHub
- [ ] `.gitignore` configurado (não commitar senhas!)
- [ ] `application-prod.properties` criado
- [ ] Variáveis de ambiente configuradas
- [ ] Banco de dados criado na plataforma
- [ ] Migrations do Flyway no projeto
- [ ] Testado localmente

---

## 🎉 URLs Importantes

### Render:
- Dashboard: https://dashboard.render.com
- Docs: https://render.com/docs

### Railway:
- Dashboard: https://railway.app/dashboard
- Docs: https://docs.railway.app

### Heroku:
- Dashboard: https://dashboard.heroku.com
- Docs: https://devcenter.heroku.com

---

## 💡 Dicas Pro

### 1. Monitoramento:
```bash
# Render/Railway/Heroku tem logs integrados
# Acesse pelo dashboard
```

### 2. Backup do Banco:
```bash
# No Render, vá em Database → Backups
# Railway faz backup automático
```

### 3. Domínio Customizado:
```bash
# No Render: Settings → Custom Domain
# Adicione seu domínio e configure DNS
```

### 4. Limpar Dados Após Evento:
```bash
# Use o botão "Limpar Todos os Dados" no app
# Ou acesse o banco e execute:
DELETE FROM atleta;
DELETE FROM treinador;
```

---

## 🚀 Deploy Rápido (Render - 5 minutos)

```bash
# 1. Push para GitHub
git add .
git commit -m "Ready for deploy"
git push

# 2. Render.com → New → PostgreSQL (Free)
# 3. Render.com → New → Web Service → GitHub repo
# 4. Adicionar variáveis de ambiente
# 5. Deploy! 🎉

# URL estará disponível em: https://seu-app.onrender.com
```

---

**Pronto! Seu sistema MFC está pronto para deploy! 🥊✨**

Escolha a plataforma que melhor se adequa e siga o guia passo a passo.

**Recomendação**: Comece com **Render (grátis)** para testar!

