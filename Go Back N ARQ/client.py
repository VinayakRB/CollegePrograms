# client.py

import socket

def Main():
	host = "127.0.0.1"
	message = " "
	i = 0
	selective_list = []
	port = input('Enter port to connect : ')
	S = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
	S.connect((host,int(port)))
	print('Connected to port : ',port)

	print('Enter numbers to send!\n')
	while i<10:
		message = input('>>')
		selective_list.append(int(message))
		S.send(message.encode('utf-8'))
		data = S.recv(1024)
		data = str(data.decode('utf-8'))
		print(data)
		i = i+1
	i = S.recv(1024)
	i = int(i.decode('utf-8'))
	print('\nACK not recieved for ',selective_list[i])
	print('\nBy Go Back N ARQ Retransmitting data for position : ',i+1)

	while i<10:
		S.send(str(selective_list[i]).encode('utf-8'))
		i = i + 1
		data = S.recv(1024)
		data = str(data.decode('utf-8'))
		print(data)
	S.close()
	print('Connection closed by server!!!\n')

if __name__ == '__main__':
	Main()