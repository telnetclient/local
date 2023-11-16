import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

public class server{

    public static void main(String[] args) throws IOException {
        int port = 54321; // Port for the HTTP server

        // Create a HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create a context for file upload
        server.createContext("/upload", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try (InputStream inputStream = exchange.getRequestBody();
                     OutputStream outputStream = new FileOutputStream("received_file.txt")) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

                // Send a response to the client
                String response = "File received successfully.";
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }

                // Display the contents of the received file
                try (BufferedReader reader = new BufferedReader(new FileReader("received_file.txt"))) {
                    String line;
                    System.out.println("Contents of received file:");
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                // Store the contents in another file
                try (BufferedReader reader = new BufferedReader(new FileReader("received_file.txt")))
                      {
                    String line;
                    while ((line = reader.readLine()) != null) {
                       // writer.write(line);
                      //  writer.newLine();
                    }
                }
                System.out.println("Contents of received file stored in 'received_file.txt'.");
            }
        });

        server.start();
        System.out.println("HTTP Server started on port " + port);
    }
}
