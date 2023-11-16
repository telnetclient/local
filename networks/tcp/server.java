import java.io.*;
import java.net.*;
import java.util.*;
public class server {
    public static void main(String[] args) {
        String SERVER_IP = "192.168.117.205";
        int SERVER_PORT = 55555;
        try {
            Scanner s = new Scanner(System.in);
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT, 50, InetAddress.getByName(SERVER_IP));
            System.out.println("Server listening on " + SERVER_IP + ":" + SERVER_PORT);
	        while (true) {
	            Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected : " + clientSocket.getInetAddress());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String message = in.readLine();
                System.out.println("Received from client : " + message);
		        System.out.print("Enter the message : ");
            	String messageToSend = s.nextLine();
            	out.println(messageToSend);
		        clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}