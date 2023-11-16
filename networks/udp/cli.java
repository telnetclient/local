import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.net.SocketException;

public class cli
{
    public static void main(String args[]) throws IOException
    {
        DatagramSocket dr = new DatagramSocket(4321);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
        while (true)
        {
            System.out.println("Enter Message: ");
            String inp = sc.nextLine();
            buf = inp.getBytes();
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 54321);
            ds.send(DpSend);
            DpReceive = new DatagramPacket(receive, receive.length);
            dr.receive(DpReceive);

            System.out.println("Message from Server: " + data(receive));
                        if (data(receive).toString().equals("bye"))
                        {
                                System.out.println("Exit");
                                break;
                        }
                        receive = new byte[65535];
            if (inp.equals("bye"))
                break;
        }
    }

    public static StringBuilder data(byte[] a)
        {
                if (a == null)
                        return null;
                StringBuilder ret = new StringBuilder();
                int i = 0;
                while (a[i] != 0)
                {
                        ret.append((char) a[i]);
                        i++;
                }
                return ret;
        }
}
