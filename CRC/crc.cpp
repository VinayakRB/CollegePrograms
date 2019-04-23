#include<iostream>
#include<vector>
using namespace std;

int crc_gen(vector<int> &vect)
{
	vect.push_back(1);
	vect.push_back(0);
	vect.push_back(0);
	vect.push_back(1);
	return (vect.size() - 1);
}

void genPolynomial(vector<int> &vect,vector<int> &vect2)
{
	vector<int> :: iterator app;
	for(app = vect2.begin();app!=vect2.end();++app)
	{
		vect.push_back(*app);
	}
}

int errDetection(vector<int> &v,vector<int> &crc,int crc_len)
{
	vector<int> :: iterator itr,cit;
	itr = v.begin();
	cit = crc.begin();
	int data_ptr = v.size(),i = 0;
	while(data_ptr > crc_len)
	{
		i = 0;
		while(i<=crc_len)
		{
			*itr = *itr^(*cit);
			++cit;
			++itr;
			i++;
		}
		cit = crc.begin();
		data_ptr = v.size();
		for(itr = v.begin();*itr!=1;++itr)
		{
			if(itr == v.end())
				break;
			--data_ptr;	
		}
	}
	return data_ptr;
}

int main()
{
	cout<<"*** CRC : CYCLIC REDUNDANCY CHECK *** \n"
		  "*Enter Data Word of your choice\n"
		  "*Wait for system to generate redundancy bits\n"
		  "*Enter appropriate bit position to generate error\n"
		  "*Enter a number out of bounds not to generate error\n"
		  "*Wait for Error to be detected"<<endl;

	int lenth,num,crc_len,data_ptr,i,err;
	vector<int> v,crc,temp,app;
	vector<int> :: iterator itr,cit,tem;
	cout<<"\nEnter length of data word : ";
	cin>>lenth;
	cout<<"Enter data word >>>"<<endl;
	for(int i  = 0;i<lenth;i++)
	{
		cin>>num;
		v.push_back(bool(num));
	}

	cout<<"Entered data word is : ";
	for(itr = v.begin();itr!=v.end();++itr)
	{
		temp.push_back(*itr);
		cout<<*itr<<" ";
	}

	cout<<"\nGenerating Polynomial : x^3 + 1"<<endl;
	crc_len = crc_gen(crc);
	cout<<endl<<"CRC len is : "<<crc_len<<endl;
	for(int i = 0;i<crc_len;i++)
	{
		app.push_back(0);
	}
	genPolynomial(v,app);

	cout<<"CRC Polynomial bit is : ";
	for(itr = crc.begin();itr!=crc.end();++itr)
	{
		cout<<*itr<<" ";
	}
	
	data_ptr = errDetection(v,crc,crc_len);

	i = 0;
	itr = v.end();
	while(i < data_ptr)
	{
		--itr;
		i++;
	}
	app.clear();
	for(;itr!=v.end();++itr)
	{
		app.push_back(*itr);
	}
	cout<<endl;	

	genPolynomial(temp,app);

	cout<<"\nData word with redundancy bits : ";
	for(tem = temp.begin();tem!=temp.end();++tem)
	{
		cout<<*tem<<" ";
	}

	cout<<"\nEnter position of bit to generate error : ";
	cin>>err;
	if(err > 0 && err < temp.size())
		temp[err] = !temp[err];

	data_ptr = errDetection(temp,crc,crc_len);
	if(data_ptr == 0)
		cout<<"No error!";
	else
		cout<<"Error Detected!"<<endl;
}

