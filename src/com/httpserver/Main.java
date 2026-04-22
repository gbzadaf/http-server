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
                System.out.println("Headers: " + request.headers);
            }

        }
    }

}
