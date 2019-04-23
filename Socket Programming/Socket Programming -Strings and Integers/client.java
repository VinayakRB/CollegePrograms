//client.java
import java.io.*;
import java.net.*;
import java.util.Scanner;


class client
{
	public static void main(String []args)
	{
		int portAddress,choice;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Port Address : ");
		portAddress = scan.nextInt();

		try
		{
			Socket socket = new Socket("localhost",portAddress);
			System.out.println("Connected to Port : "+portAddress+"\nSending Data ... \n");
			DataOutputStream op = new DataOutputStream(socket.getOutputStream());
			DataInputStream ip = new DataInputStream(System.in);

			System.out.print("\n --- Server-Client Communtication --- \n1.Send Message\n2.Send Numbers\n->");
			choice = scan.nextInt();
			if (choice == 1)
			{
				op.writeInt(choice);
				String message = "";
				while(!message.equals("//"))
				{
					message = ip.readLine();
					op.writeUTF(message);
				}	
			}
			else if(choice == 2)
			{
				op.writeInt(choice);
				int n = 0;
				while(n!=-1)
				{
					n = scan.nextInt();
					op.writeInt(n);
				}
			}
			else
			{
				System.out.println("Invalid Choice\nProcess Terminated...");
			}
			op.close();
		}
		catch (IOException i)
		{
			System.out.println(i);
		}
	}
}