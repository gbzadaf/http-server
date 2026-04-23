package com.httpserver;

import java.io.IOException;

public class FileHandler {

    public static void handle(HttpRequest request, HttpResponse response) throws IOException {
        switch (request.path) {
            case "/", "/index.html" -> response
                    .status(200, "OK")
                    .header("Content-Type", "text/html; charset=utf-8")
                    .body("""
                    <!DOCTYPE html>
                    <html lang="pt-br">
                    <head>
                        <meta charset="UTF-8">
                        <title>Meu Servidor HTTP</title>
                        <style>
                            body {
                                font-family: Arial, sans-serif;
                                max-width: 800px;
                                margin: 40px auto;
                                background-color: #f5f5f5;
                                color: #333;
                            }
                            h1 { color: #2c3e50; }
                            a { color: #2980b9; }
                        </style>
                    </head>
                    <body>
                        <h1>Servidor HTTP do zero!</h1>
                        <p>Esse HTML esta sendo servido por um servidor feito em Java puro.</p>
                        <ul>
                            <li><a href="/sobre">Pagina Sobre</a></li>
                            <li><a href="/pagina-inexistente">Testar 404</a></li>
                        </ul>
                    </body>
                    </html>
                    """);

            case "/sobre" -> response
                    .status(200, "OK")
                    .header("Content-Type", "text/html; charset=utf-8")
                    .body("""
                    <!DOCTYPE html>
                    <html lang="pt-br">
                    <head>
                        <meta charset="UTF-8">
                        <title>Sobre</title>
                    </head>
                    <body>
                        <h1>Sobre</h1>
                        <p>Servidor HTTP construido do zero em Java, sem bibliotecas externas.</p>
                        <a href="/">Voltar</a>
                    </body>
                    </html>
                    """);

            default -> response
                    .status(404, "Not Found")
                    .header("Content-Type", "text/html; charset=utf-8")
                    .body("""
                    <!DOCTYPE html>
                    <html lang="pt-br">
                    <head>
                        <meta charset="UTF-8">
                        <title>404</title>
                    </head>
                    <body>
                        <h1>404 - Pagina nao encontrada</h1>
                        <p>O caminho <b>""" + request.path + """
                        </b> nao existe.</p>
                        <a href="/">Voltar</a>
                    </body>
                    </html>
                    """);
        }
    }
}