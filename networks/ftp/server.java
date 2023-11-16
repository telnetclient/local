import java.io.*;
import java.net.*;

public class server {

    public static void main(String[] args) {
        int port = 54321; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("FTP Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream)
            ) {

                dataOutputStream.writeUTF("220 Welcome to My FTP Server");
                dataOutputStream.flush();


                String fileName = dataInputStream.readUTF();
                long fileSize = dataInputStream.readLong();
                System.out.println("Receiving file: " + fileName + " (Size: " + fileSize + " bytes)");


                try (FileOutputStream fileOutputStream = new FileOutputStream("received_" + fileName)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);

                        System.out.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("\nFile received successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
