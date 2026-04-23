package com.httpserver;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() { // lê a requisição e monta a resposta

        try {
            HttpRequest request = HttpRequest.parse(clientSocket.getInputStream());
            if (request != null) {
                System.out.println("[Thread " + Thread.currentThread().getId() + "] " + request);

                HttpResponse response = new HttpResponse();
                FileHandler.handle(request, response);
                response.send(clientSocket.getOutputStream());
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { clientSocket.close(); } catch (IOException ignored) {}
        }
    }
}
