//hammingCode.cpp
//author@Vinayak R Baghel

#include<iostream>
#include<math.h>
using namespace std;

int return_R(int data_len)
{
	int r = 1;
	while(pow(2,r) < (data_len + r + 1))
	{
		r++;
	}
	return r;
}

bool isKthbitSet(int n,int k)
{
	if(n & (1 << (k-1)))
		return true;
	else
		return false;
}

int main()
{
	int data_len,red,total_bits,position;
	cout<<"Enter length of data word\n";
	cin>>data_len;

	int data[data_len + 1];
	cout<<"Enter data word"<<endl;
	for(int i = 1;i<=data_len;i++)
	{
		cin>>data[i];
	}

	red = return_R(data_len);
	cout<<"\nThe number of reduntant bits are : "<<red<<endl;
	total_bits = data_len + red;

	//**********************************************************
	//CLACULATE INTERMEDIATE DATA WORD

	int inter_data[total_bits + 1];
	inter_data[0] = 0	;

	int z = 1,i = 1,k = 1;
	while(z<=total_bits)
	{
		if(z == k)
		{
			inter_data[z] = -1;
			k = k*2;
		}
		else
		{
			inter_data[z] = data[i];
			i++;
		}
	z++;
	}

	cout<<">>> Intermediate data word in temporary form \n>>> where -1 indicates Reduntant Bit : ";
	for(int i = 1;i<=total_bits;i++)
	{
		cout<<inter_data[i]<<" ";
	}
	cout<<endl<<endl;

	k = 1;z = 0;
	while(z < red)
	{
		inter_data[k] = 0;
		k = k*2;
		z++;
	}

	int temp[total_bits + 1];
	for(int y = 1;y<=total_bits;y++)
	{
		temp[y] = inter_data[y];
	}

	//***********************************************************
	//CALCULATE PARITY BITS

	int p_bits[red],j = 0,K;
	k = 1;
	K = 1;
	while(j<red)
	{
		for(int i = 1;i <= total_bits;i++)
		{
			if(isKthbitSet(i,k) && temp[i]!=-1)
			inter_data[K] ^= temp[i];
		}
	cout<<"Parity Bit "<<K<<" : "<<inter_data[K]<<endl;
	j++;
	k++;
	K = K*2;
	}

	cout<<"\n>>> Intermediate data word in final form : ";
	for(int i = 1;i<=total_bits;i++)
	{
		cout<<inter_data[i]<<" ";
	}
	cout<<endl;
	cout<<endl;

	//************************************************************
	//ERROR DETECTION

	cout<<"\nEnter the bit position you want to change to generate error : ";
	cin>>position;
	if(position<1 || position>total_bits)
		cout<<"Invalid bit position!"<<endl;
	else
		inter_data[position] = !inter_data[position];

	int cnt = 0,count = 0,Err_bit = 0;
	k = 1;
	K = 0;
	int c_bits[red];
	for(int i = 0;i<red;i++)
		c_bits[i] = 0;


	while(cnt<red)
	{
		for(int i = 1;i<=total_bits;i++)
		{
			if(isKthbitSet(i,k))
			{
				c_bits[K] ^= inter_data[i]; 
			}
		}
	K++;
	k++;
	cnt++;
	}

	cout<<"Check bits are as follows : ";
	for(int m = red-1;m>=0;m--)
	{
		cout<<c_bits[m]<<" ";
	}
	cout<<endl;

	

	for(int j = 0;j<red;j++)
	{
		if(c_bits[j]!=0)
			count++;
	}

	if(count == 0)
		cout<<"No error in data transfer!"<<endl;
	else
	{
		for(int l = 0;l<red;l++)
		{
			Err_bit += c_bits[l]*pow(2,l);
		}
	cout<<"Error is at bit position : "<<Err_bit<<endl;
	}
	return 0;
}