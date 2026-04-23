#  Servidor HTTP — Do Zero em Java

Servidor HTTP construído do zero em Java, sem nenhuma biblioteca externa. O projeto implementa todas as etapas de um servidor web real: aceitar conexões, interpretar requisições HTTP, rotear URLs e enviar respostas.

---

##  Arquitetura

```
Navegador → ServerSocket → ClientHandler (Thread) → FileHandler → HttpResponse → Navegador
```

| Componente | Responsabilidade |
|---|---|
| `Main` | Abre o servidor e distribui conexões pro thread pool |
| `HttpRequest` | Lê e interpreta a requisição HTTP do navegador |
| `HttpResponse` | Monta e envia a resposta HTTP |
| `ClientHandler` | Processa cada conexão em uma thread separada |
| `FileHandler` | Roteia URLs e serve o conteúdo correto |

---

##  Funcionalidades

- Aceita conexões HTTP na porta 8080
- Interpreta método, caminho e headers da requisição
- Suporta múltiplas conexões simultâneas via thread pool
- Roteamento de URLs com respostas diferentes por caminho
- Página 404 para rotas inexistentes
- Padrão Builder para montagem de respostas

---

##  Exemplo de uso

Acesse no navegador após rodar o `Main.java`:

```
http://localhost:8080          → Página principal
http://localhost:8080/sobre    → Página sobre
http://localhost:8080/outro    → Página 404
```

---

## ⚙ Como funciona o protocolo HTTP

O navegador envia texto puro pelo socket:

```
GET /index.html HTTP/1.1
Host: localhost:8080
User-Agent: Mozilla/5.0
```

O servidor responde:

```
HTTP/1.1 200 OK
Content-Type: text/html; charset=utf-8
Content-Length: 123

<html>...</html>
```

---

##  Como rodar

1. Clone o repositório
2. Abra no IntelliJ IDEA
3. Execute a classe `Main.java`
4. Acesse `http://localhost:8080` no navegador

Requer **Java 14+** (usa switch expressions e text blocks).

---

## O que aprendi

- Como funciona o protocolo **HTTP** por baixo dos panos
- Como usar **ServerSocket** para abrir uma porta e aceitar conexões
- Como usar **threads e thread pool** para múltiplas conexões simultâneas
- Como montar e interpretar **requisições e respostas HTTP** manualmente
- O padrão de projeto **Builder** para construção de objetos

---


