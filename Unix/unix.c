#include <unistd.h>
#include<stdio.h>
int glob=6;
void fork1()
{
int var;
pid_t pid;
var=88;
printf("before fork\n");
if((pid=fork())<0)
{
printf("fork error");
}
else if(pid==0)
{
glob++;
var++;
}
else
{
sleep(2);
}
printf("pid=%d,glob=%d,var=%d\n",getpid(),glob,var);
exit(0);
}

void vfork1()
{
int var;
pid_t pid;
var=88;
printf("before fork\n");
if((pid=vfork())<0)
{
printf("Vfork error");
}
else if(pid==0)
{
glob++;
var++;
exit(0);
}
printf("pid=%d,glob=%d,var=%d\n",getpid(),glob,var);
exit(0);
}

void wait1()
{
int ret_val,ret_code;
pid_t pid;
if((pid=fork())<0)
{
printf("Child process %x\n,getpid()");
exit(10);
}
ret_val=wait(&ret_code);
printf("wait ret_val %x ret_code c%x\n",ret_val,ret_code);
}

void execl1()
{
execl("/usr/bin/cal","cal","03","2018",(char*)0);
printf("Reached here");
}

void execv1()
{
char *args[]={"cal","03","2018",NULL};
execv("/usr/bin/cal",args);
printf("Reached here\n");
}

int main(int argc,char **argv)
{
int ch;
char ch1;

do
{
printf("1.Fork\n2.Vfork\n3.Wait\n4.Execl\n5.Execv\nEnter ur choice: ");
scanf("%d",&ch);
switch(ch)
{
case 1:
	fork1();
	break;
case 2:
	vfork1();
	break;
case 3:
	wait1();
	break;
case 4:
	execl1();
	break;
case 5:
	execv1();
	break;
}
printf("Do u want to continue:");
scanf("%c",&ch1);
}while(ch1=='y');
}

