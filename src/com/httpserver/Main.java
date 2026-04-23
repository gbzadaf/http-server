package com.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor rodando em http://localhost:" + PORT);

        while (true) {

            Socket clientSocket = serverSocket.accept();

            HttpRequest request = HttpRequest.parse(clientSocket.getInputStream());
            if (request != null) {
                System.out.println("Requisição recebida: " + request);

                new HttpResponse()
                        .status(200, "OK")
                        .header("Content-Type", "text/html; charset=utf-8")
                        .body("""
                        <html>
                            <body>
                                <h1> Servidor HTTP funcionando!</h1>
                                <p>Você acessou: <b>""" + request.path + """
                                </b></p>
                            </body>
                        </html>
                        """)
                        .send(clientSocket.getOutputStream());
            }
            clientSocket.close();

        }
    }

}
