# server.py

import socket
from random import *
import sys

def threadFnc(c):
	selective_list = []
	for j in range(10):
		selective_list.append(-5)
	i = 0
	random_err = randint(0,10)
	print('\nRecieving data ...')

	while True and i<10:
		data = c.recv(1024)
		if not data:
			print('Bye!!!\n')
			break

		selective_list[i] = data.decode('utf-8')
		selective_list[random_err] = -1
		print(selective_list[i],' ')
		
		if selective_list[i] == -1:
			c.send("ACK not recieved".encode('utf-8'))
		else:
			c.send("ACK recieved".encode('utf-8'))
		i = i+1
	
	print("\nWARNING!!! Data lost requesting retransmission\n")
	c.send(str(random_err).encode('utf-8'))
	data = c.recv(1024)
	selective_list[int(random_err)] = data
	print('\nData Recieved\n')
	c.send("ACK recieved".encode('utf-8'))
	sys.stdout.flush()
	c.close()

def Main():
	host = ""
	data = " "
	port = randint(1200,1300)
	s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
	s.bind((host,port))
	print('Socket binded to port : ',port)
	s.listen(5)
	print('\nSocket is on listen mode!\n')
	i = 0
	while i<1:
		c, addr = s.accept()
		print("Client with port ",addr,"is connected to server")
		threadFnc(c)
		# c.send(4+2)
		i = i + 1
		c.close()
	print('\nClosing server...')

if __name__ == '__main__':
	Main()
	sys.exit()