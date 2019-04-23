//server.java
import java.io.*;
import java.net.*;


class server
{
	public static void main(String []args)
	{
		int portAddress = 6754;
		try
		{
			System.out.println("Server is ready to connect on Port : "+portAddress);
			ServerSocket server = new ServerSocket(portAddress);
			Socket socket = server.accept();
			System.out.println("Client Connected");
			DataInputStream ip = new DataInputStream(socket.getInputStream());
			int choice = ip.readInt();
			if(choice == 1)
			{
				System.out.println("\nRecieving Messages ...");
				String msg = "";
				while(!msg.equals("//"))
				{
					msg = (String)ip.readUTF();
					System.out.println(msg);	
				}	
			}
			else if(choice == 2)
			{
				System.out.println("\nRecieving Numbers ...");
				int n = 0,result = 0;
				while(n!=-1)
				{
					n = ip.readInt();
					result += n;
				}
				System.out.println("Addition of numbers is : "+(result+1));
			}
			else
			{
				System.out.println("No Data Recieved!!!");
			}
			
			server.close();
		}
		catch (IOException i)
		{
			System.out.println(i);
		}
	}
}