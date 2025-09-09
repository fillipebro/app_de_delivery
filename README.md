# 🌱 Eco Tech Entregas (Spring Boot + MySQL + Carrinho Persistente)

Projeto integrando **Spring Boot** com **MySQL** e frontend em HTML/CSS/JS.

Agora o carrinho é salvo no banco de dados por usuário, e pode ser listado e limpo via API.

---

## ▶️ Endpoints

### Usuários
- `POST /api/users/register` → cadastrar usuário  
- `POST /api/users/login` → autenticar usuário  
- `GET /api/users` → listar usuários

### Carrinho
- `POST /api/cart/add/{email}` → adicionar item ao carrinho do usuário  
- `GET /api/cart/{email}` → listar itens do carrinho  
- `DELETE /api/cart/{email}` → limpar carrinho  

---

## ▶️ Como rodar

1. Criar banco no MySQL:
   ```sql
   CREATE DATABASE ecotech;
   ```

2. Ajustar credenciais em `src/main/resources/application.properties`  
   ```properties
   spring.datasource.username=root
   spring.datasource.password=123456
   ```

3. Rodar:
   ```bash
   mvn spring-boot:run
   ```

4. Acessar frontend:
   ```
   http://localhost:8080/index.html
   ```

✅ Backend com persistência de usuários e carrinho funcionando!
# app-de-delivery
