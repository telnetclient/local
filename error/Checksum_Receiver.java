import java.net.*;
import java.io.*;
import java.util.*;
public class Checksum_Receiver {
	private Socket s = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	public Checksum_Receiver(InetAddress ip,int port)throws IOException
	{
		s = new Socket(ip,port);
		dis = new DataInputStream(s.getInputStream());
		dos = new DataOutputStream(s.getOutputStream());
		while (true)
		{ Scanner sc = new Scanner(System.in);
			int i, l, nob, sum = 0, chk_sum;
			l = dis.readInt();
			int c_data[] = new int[l];
			int data[] = new int[l];
			System.out.println("Data received (along with checksum) is");
			for(i = 0; i< data.length; i++)
			{ 
				data[i] = dis.readInt();
				System.out.println(data[i]);
				nob = (int)(Math.floor(Math.log(data[i]) / Math.log(2))) + 1; 
				c_data[i] = ((1 << nob) - 1) ^ data[i];
				sum += c_data[i];
			}
			System.out.println("Sum(in ones complement) is : "+sum);
			nob = (int)(Math.floor(Math.log(sum) / Math.log(2))) + 1; 
			sum = ((1 << nob) - 1) ^ sum;
			System.out.println("Calculated Checksum is : "+sum);
			if(sum == 0)
			{ 
				dos.writeUTF("success");
				break;
			}	 
			else
			{ 
				dos.writeUTF("failure");
				break;
		}
		dis.close();
		dos.close();
		s.close();
	}
	public static void main(String args[])throws IOException { 
		InetAddress ip = InetAddress.getLocalHost();
		Checksum_Receiver cr = new Checksum_Receiver(ip,5000);
	} 
}
OUTPUT:

 
 
RESULT:
	Thus, Error correction and error detection is implemented successfully in JAVA.
