import java.io.*;
import java.net.*;

public class client {

    public static void main(String[] args) throws IOException {
        String serverAddress = "http://localhost:54321/upload"; // Server address
        String filePath = "sample.txt"; // File to transfer

        try {
            // Create a connection to the server
            URL url = new URL(serverAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Read the file and send it to the server
            try (OutputStream outputStream = connection.getOutputStream();
                 FileInputStream fileInputStream = new FileInputStream(filePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Get the response from the server
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("File sent successfully.");
            } else {
                System.err.println("File transfer failed. Server returned: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
