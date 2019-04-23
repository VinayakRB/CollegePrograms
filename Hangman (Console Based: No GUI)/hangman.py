#rand.py

from random import *
from cities import word
from man import pic

print(pic)
tempWord = []
tempDic = {}
#word is a list which is imported from cities.py
#random function selects a random city from word according to position
randWord = word[randint(0,len(word)-1)]
sizeofword = len(randWord)
deduce = int(sizeofword/2)
for j in range(sizeofword):
	tempWord.append(randWord[j].upper())

x = []
z = []
tempWord2 = tempWord.copy()
i = 0
while i < deduce:
	y = randint(0,sizeofword-1)
	w = randWord[y]
	if y not in x and w not in z:
		x.append(y)
		z.append(w)
		tempDic[tempWord[y]] = y
		tempWord[y] = '_'
		i = i+1

print('\n THE HANGMAN GAME \n')
print('\n*** Guess The City Name ***\n')
print(tempWord)
i = 0
#we set the counter as 5, so that the user gets 5 chances to guess the word
count = 5
while tempWord != tempWord2:
	letter = input('\nEnter letter : ')
	letter = letter.upper()
	if letter in tempDic:
		index = tempDic[letter]
		for i in tempDic:
			if i == letter:
				tempWord[index] = i

		del tempDic[letter]
		print(tempWord)

	elif count == 0:
		print("\nSorry! You're hanged to death\n")
		tempWord = tempWord2
		print(tempWord)

	else:
		print('Wrong Answer ',count,'attempts remaining')
		count = count - 1
