# Guia de Configuração e Execução dos Testes

Este documento fornece as instruções necessárias para clonar o repositório, configurar e rodar cada um dos testes.

## Clonando o Repositório
Para obter o código-fonte, execute o seguinte comando:
```sh
git clone https://github.com/Guilherme-Beckman/TESTES-DE-NIVELAMENTO-v.250321-IntuitiveCare.git
```

---

## Teste 1: Web Scraping (Java Spring + Postman)
### Requisitos
- Java 17+
- Maven
- Postman (para testar as requisições, coleções disponíveis na pasta do teste)

### Executando o Teste
```sh
cd "1. TESTE DE WEB SCRAPING"
mvn clean install
mvn spring-boot:run
```

### Testando com Postman
- **Endpoint:** `GET http://localhost:8080/zip`
- **Descrição:** Retorna um arquivo ZIP gerado pelo serviço
- **Resposta esperada:**
  ```http
  HTTP/1.1 200 OK
  Content-Disposition: attachment; filename=arquivo.zip
  Content-Type: application/octet-stream
  ```

---

## Teste 2: Transformação de Dados (Java Spring + Postman)
### Requisitos
- Java 17+
- Maven
- Postman (coleções disponíveis na pasta do teste)

### Executando o Teste
```sh
cd "2. TESTE DE TRANSFORMAÇÃO DE DADOS"
mvn clean install
mvn spring-boot:run
```

### Testando com Postman
- **Endpoint:** `GET http://localhost:8080/zip`
- **Descrição:** Retorna um arquivo ZIP contendo os dados transformados
- **Resposta esperada:**
  ```http
  HTTP/1.1 200 OK
  Content-Disposition: attachment; filename=Teste_Guilherme_Beckman.zip
  Content-Type: application/octet-stream
  ```

### Estrutura de Arquivos Resultante do Teste 2
Como foi solicitado na descrição dos testes, o arquivo contendo os arquivos zipados está em:

```
TESTES-DE-NIVELAMENTO-v.250321-IntuitiveCare/
├── 2. TESTE DE TRANSFORMAÇÃO DE DADOS/
│   └── Teste_Guilherme_Beckman.zip
```

---

## Teste 3: Banco de Dados (PostgreSQL)
### Requisitos
- PostgreSQL instalado e rodando
- Permissão de escrita na pasta `/tmp/teste-3/`
- Os arquivos CSV devem estar localizados na pasta correta

### Executando os Scripts SQL
```sh
psql -U seu_usuario -d seu_banco -f "3. TESTE DE BANCO DE DADOS/scripts/script.sql"
```

---

## Teste 4: API (Python + Vue.js + Postman)
### Requisitos
- Python 3.9+
- Navegador web moderno
- Postman (para testar a API)

### Configuração do Ambiente Python
**Linux/macOS:**
```sh
cd "4. TESTE DE API"
python3 -m venv venv
source venv/bin/activate
pip install flask pandas flask-cors
```

**Windows:**
```sh
cd "4. TESTE DE API"
python -m venv venv
venv\Scripts\activate
pip install flask pandas flask-cors
```

### Iniciando o Servidor Backend
```sh
python server.py
```
O servidor estará rodando em `http://localhost:5000`.

### Acessando o Frontend
1. Abra o arquivo `vue/index.html` no seu navegador
2. Digite um termo de busca e clique em "Pesquisar"
3. Verifique os resultados retornados da API

### Testando com Postman
- **Endpoint:** `GET http://localhost:5000/search?query=SEU_TERMO_DE_BUSCA`
- **Descrição:** Realiza uma busca textual nos dados do CSV
- **Resposta esperada:**
  ```json
  [
      {
          "Bairro": "Centro",
          "CEP": 12345678,
          "CNPJ": 12345678000190,
          "Cidade": "São Paulo",
          "Nome_Fantasia": "Empresa Saúde",
          "Razao_Social": "Empresa Saúde S/A",
          "Registro_ANS": 123456
      }
  ]
  ```

---
