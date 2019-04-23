//Client2.java
//client program to send array
import java.io.*;
import java.net.*;
import java.util.Scanner;


class Client2
{
	public static void main(String []args)
	{
		int lenth,elem,portAddress;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter port Address : ");
		portAddress = scan.nextInt();
		
		try
		{	
			Socket socket = new Socket("localhost",portAddress);
			System.out.println("Connected to server on port 6754\n");
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());	

			System.out.print("Enter the lenght of array : ");
			lenth = scan.nextInt();
			int array[] = new int[lenth];
			System.out.println("Enter array elements >>>");
			for(int i = 0;i<lenth;i++)
			{
				elem = scan.nextInt();
				array[i] = elem;
			}

			out.writeObject(array);
			
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}
}