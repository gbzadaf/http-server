package com.httpserver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {

    private int status;
    private String statusText;
    private Map<String, String> headers;
    private byte[] body;

    public HttpResponse() {
        this.headers = new LinkedHashMap<>();

    }

    public HttpResponse status(int status, String statusText) {
        this.status = status;
        this.statusText = statusText;
        return this;

    }

    public HttpResponse header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public HttpResponse body(String body) {
        this.body = body.getBytes();
        header("Content-Length", String.valueOf(this.body.length));
        return this;
    }

    public void send(OutputStream output) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(status).append(" ").append(statusText).append("\r\n");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }

        sb.append("\r\n"); // linha em branco obrigatória
        output.write(sb.toString().getBytes());
        if (body != null) {
            output.write(body);
        }
        output.flush();
    }

}
