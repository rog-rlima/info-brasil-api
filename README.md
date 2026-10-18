# InfoBrasil Service 🇧🇷

O **InfoBrasil Service** é uma API REST corporativa desenvolvida em Java e Spring Boot projetada para centralizar consultas de utilitários do território brasileiro, integrando serviços de busca de CEP, cálculo de frete e geolocalização.

O projeto foi construído seguindo as melhores práticas de mercado, com foco em imutabilidade, alta performance de mapeamento de dados e conteinerização completa para ambientes de produção.

## 🛠️ Tecnologias e Arquitetura

*   **Java 21** & **Spring Boot 3.3.x** - Core da aplicação com a stack mais estável do ecossistema.
*   **Spring Cloud OpenFeign** - Integração limpa e desacoplada com APIs externas (ViaCEP).
*   **MapStruct** - Mapeamento de DTOs em tempo de compilação, garantindo performance máxima sem uso de Reflection.
*   **Lombok** - Redução de código boilerplate e garantia de imutabilidade.
*   **SLF4J & Logback** - Sistema de logging estruturado.
*   **Docker & Docker Compose** - Empacotamento em Multi-stage Build com imagens Alpine de segurança otimizada.

---

## 🚀 Como Executar o Projeto com Docker

Você não precisa ter o Java ou o Maven instalados na sua máquina para rodar este projeto. O Docker cuida de todo o processo de compilação e execução.

### Pré-requisitos
*   [Docker](https://docker.com) instalado e rodando.

### 1. Clonar o repositório
```bash
git clone https://github.com
cd info-brasil-service
```

### 2. Compilar e Iniciar a API
Execute o comando abaixo na raiz do projeto para compilar o código fonte de forma isolada e subir o container em segundo plano (`detached mode`):
```bash
docker compose up -d --build
```

### 3. Acompanhar os Logs da Aplicação
Para visualizar a inicialização do Spring Boot e as mensagens do Logback em tempo real:
```bash
docker compose logs -f info-brasil-api
```

### 4. Parar a Aplicação
Para encerrar a execução dos containers e liberar as portas do seu computador:
```bash
docker compose down
```

---

## 🏁 Endpoints Disponíveis

Após subir os containers, a API estará disponível em `http://localhost:9091`.

### Consulta de CEP
Retorna os dados completos do endereço e aplica a validação de regras de negócio internas.

*   **URL:** `/api/v1/zipcode/{cep}`
*   **Método:** `GET`
*   **Exemplo de Requisição:** `http://localhost:8080/api/v1/zipcode/01001000`

---

## 🔧 Estrutura de Tratamento de Erros

A API possui um **Global Exception Handler** integrado que intercepta falhas de infraestrutura e erros de negócio, convertendo stacktraces técnicos em respostas JSON amigáveis com os respectivos códigos de status HTTP:
*   `400 Bad Request` - Formato de CEP inválido ou malformado.
*   `404 Not Found` - CEP inexistente na base de dados nacional.
*   `502 Bad Gateway` - Falha de comunicação ou indisponibilidade no serviço externo (ViaCEP).
