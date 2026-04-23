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
    public void run() {
        try {
            HttpRequest request = HttpRequest.parse(clientSocket.getInputStream());
            if (request != null) {
                System.out.println("[Thread " + Thread.currentThread().getId() + "] " + request);

                new HttpResponse()
                        .status(200, "OK")
                        .header("Content-Type", "text/html; charset=utf-8")
                        .body("""
                                <html>
                                    <body>
                                        <h1>Servidor HTTP funcionando!</h1>
                                        <p>Você acessou: <b>""" + request.path + """
                                        </b></p>
                                    </body>
                                </html>
                                """)
                        .send(clientSocket.getOutputStream());
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar requisição: " + e.getMessage());
        } finally {
            try { clientSocket.close();
            } catch (IOException ignored) {}
        }
    }
}
