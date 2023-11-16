import java.io.*;
import java.net.*;
public class client {
    public static void main(String[] args) {
        String serverAddress = "192.168.160.1"; // Server address
        int serverPort = 54321; // Server port
        String filePath = "sample.txt"; // File to transfer

        try (Socket socket = new Socket(serverAddress, serverPort);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             FileInputStream fileInputStream = new FileInputStream(filePath)) {

            // Receive the welcome message from the server
            String welcomeMessage = dataInputStream.readUTF();
            System.out.println("Server response: " + welcomeMessage);

            // Send the file name and size to the server
            File file = new File(filePath);
            dataOutputStream.writeUTF(file.getName());
            dataOutputStream.writeLong(file.length());

            // Send the file contents
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
