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
#### Para Linux/macOS:  
```sh
cd "1. TESTE DE WEB SCRAPING/webscraping"
mvn clean install
mvn spring-boot:run
```

#### Para Windows (cmd):  
```bat
cd "1. TESTE DE WEB SCRAPING\webscraping"
mvn clean install
mvn spring-boot:run
```

#### Para Windows (PowerShell):  
```powershell
Set-Location "1. TESTE DE WEB SCRAPING\webscraping"
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

#### Para Linux/macOS:
```sh
cd "2. TESTE DE TRANSFORMAÇÃO DE DADOS/transformacaodedados"
mvn clean install
mvn spring-boot:run
```

#### Para Windows (cmd):
```bat
cd "2. TESTE DE TRANSFORMAÇÃO DE DADOS\transformacaodedados"
mvn clean install
mvn spring-boot:run
```

#### Para Windows (PowerShell):
```powershell
Set-Location "2. TESTE DE TRANSFORMAÇÃO DE DADOS\transformacaodedados"
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
Como foi solicitado na descrição dos testes, o arquivo zipado:

```
TESTES-DE-NIVELAMENTO-v.250321-IntuitiveCare/
├── 2. TESTE DE TRANSFORMAÇÃO DE DADOS/
│   └── Teste_Guilherme_Beckman.zip
```

---

## Teste 3: Banco de Dados (PostgreSQL)

### Requisitos

- **PostgreSQL 10+ instalado e rodando.**
- O PostgreSQL deve conseguir acessar a pasta `/tmp/teste-3/`.
- **Criação das pastas necessárias:**

  - **Linux/macOS:**
    ```sh
    mkdir -p /tmp/teste-3/"Dados cadastrais das Operadoras Ativas na ANS"
    mkdir -p /tmp/teste-3/"Arquivos dos últimos 2 anos do repositório"
    ```
  
  - **Windows (cmd):**  
    *(Observação: No Windows, recomenda-se adaptar o caminho para uma pasta local, por exemplo, `C:\tmp\teste-3\`.)*
    ```bat
    mkdir "C:\tmp\teste-3\Dados cadastrais das Operadoras Ativas na ANS"
    mkdir "C:\tmp\teste-3\Arquivos dos últimos 2 anos do repositório"
    ```

- Os arquivos CSV devem estar localizados nos seguintes caminhos:
  - `/tmp/teste-3/Dados cadastrais das Operadoras Ativas na ANS/Relatorio_cadop.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/1T2023.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/2T2023.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/3T2023.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/4T2023.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/1T2024.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/2T2024.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/3T2024.csv`
  - `/tmp/teste-3/Arquivos dos últimos 2 anos do repositório/4T2024.csv`

### Executando os Scripts SQL

```sh
psql -U postgres -c "CREATE DATABASE teste3_db;"

psql -U postgres -d teste3_db -f "3. TESTE DE BANCO DE DADOS/scripts/create_tables.sql"
psql -U postgres -d teste3_db -f "3. TESTE DE BANCO DE DADOS/scripts/import_data.sql"
psql -U postgres -d teste3_db -f "3. TESTE DE BANCO DE DADOS/scripts/top_10_ano.sql"
psql -U postgres -d teste3_db -f "3. TESTE DE BANCO DE DADOS/scripts/top_10_trimestre.sql"
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
cd "4. TESTE DE API/api-test"
python3 -m venv venv
source venv/bin/activate
pip install flask pandas flask-cors
```

**Windows:**
```sh
cd "4. TESTE DE API/api-test"
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
