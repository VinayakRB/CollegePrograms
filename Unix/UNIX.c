#include<unistd.h>
#include<sys/types.h>
#include<errno.h>
#include<stdio.h>
#include<sys/wait.h>
#include<stdlib.h>
// #include<stdc++.h>

int global;

int main()
{
  int choice;
  printf("Enter choice :-\n1. Unix Fork and Wait\n2. Unix ps\n3. Unix Execl\n4. Unix Execv\n->");
  scanf("%d",&choice);
  pid_t child_pid;
  int status;
  int local;
  switch(choice)
  {
    case 1:
    {
      child_pid = fork();
      if(child_pid <= 0)
      {
        if(child_pid == 0)
        {
          printf("\nChild Process!\n");
          local++;
          global++;
          printf("\nChild PID : %d, Parent PID : %d",getpid(),getppid());
          printf("\nChild's local : %d, Child's Global : %d\n",local,global);
        }
        else
        {
          printf("\nParent Process!\n");
          printf("\nParent PID : %d, Child PID : %d",getpid(),child_pid);
          wait(&status);
          printf("Child Exit Code : %d",WEXITSTATUS(status));
          printf("\nParents's local : %d, Parents's Global : %d\n",local,global);
          exit(0);
        }
      }
      else
      {
        perror("Fork");
        exit(0);
      }
    }
    break;

    case 2:
    {
      printf("$ >ps\n");
      system("ps");
    }
    break;

    case 3:
    {
      return execl("/usr/bin/cal","cal","04","2018",(char*)0);
      printf("Reached Here!\n");
    }
    break;

    case 4:
    {
      char *cmd[] = {"whoami",(char*)0};
      return execv("/usr/bin/whoami",cmd);
    }
    break;

    default:system("pause");
      break;
  }

  return 0;
}
