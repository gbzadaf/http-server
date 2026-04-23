package com.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    public final String method;
    public final String path;
    public final Map<String, String> headers;

    public HttpRequest(String method, String path, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.headers = headers;
    }

    public static HttpRequest parse(InputStream input) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        // Lê a primeira linha: "GET /index.html HTTP/1.1"
        String requestLine = br.readLine();
        if (requestLine == null || requestLine.isEmpty())
            return null;

        String[] parts = requestLine.split(" ");
        String method = parts[0];
        String path = parts[1];

        // Lê os headers
        Map<String, String> headers = new HashMap<>();

        String line;
        while (!(line = br.readLine()).isEmpty()) {
            String[] header = line.split(": ", 2);
            if (header.length == 2) {
                headers.put(header[0], header[1]);
            }
        }
        return new HttpRequest(method, path, headers);
    }

    @Override
    public String toString() {
        return method + " " + path;
    }

}
