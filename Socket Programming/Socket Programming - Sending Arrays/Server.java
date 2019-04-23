//Server2.java
//server program to read array and print sorted array
import java.io.*;
import java.net.*;
import java.util.*;	

class Server2
{
	public static void main(String []args)
	{
		try
		{
			ServerSocket server = new ServerSocket(6754);
			System.out.print("Server now ready to connect on port 6754\nWaiting for client...\n");
			Socket socket = server.accept();
			System.out.print("Connection established.\nWaiting for data...\n\n");
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			int[] array = (int[]) in.readObject();
			System.out.println("Recieved array is >>>");
			for(int i = 0;i<array.length;i++)
			{
				System.out.print(array[i] + " ");	
			}
			Arrays.sort(array);
			System.out.println("\nSorted array is >>>");
			for(int i = 0;i<array.length;i++)
			{
				System.out.print(array[i] + " ");	
			}
			System.out.println("\n");
			socket.close();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}
}