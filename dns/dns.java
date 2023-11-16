import java.net.*;
import java.io.*;
import java.util.*;
public class dns
{
 public static void main(String[] args)
 {
  int n;
  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try
    {
     System.out.println("Enter Host Name ");
     String hname=in.readLine();
     InetAddress address;
     address = InetAddress.getByName(hname);
     System.out.println("Host Name: " + address.getHostName());
     System.out.println("IP: " + address.getHostAddress());
    }
    catch(IOException ioe)
    {
     ioe.printStackTrace();
 }
 }
}
