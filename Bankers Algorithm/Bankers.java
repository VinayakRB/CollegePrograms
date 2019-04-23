import java.util.Scanner;

public class Bankers
{
  Scanner S = new Scanner(System.in);
  int n = 3; //Number of processes
  int m = 3; //Number of Resources
  int need[][] = new int[n][m];
  int [][]max =  new int[n][m];
  int [][]alloc = new int[n][m];
  int []avail = new int[m];
  int safeSequence[] = new int[n];

  void getVal(int n,int m,int array[][])
  {
    for(int i = 0;i<n;i++)
    {
      System.out.println("\nProcess P" + i);
      for(int j = 0;j<m;j++)
      {
        System.out.print("->");
        int temp = S.nextInt();
        array[i][j] = temp;
      }
    }
  }

  void initVal()
  {
    System.out.println("Enter allocation values for processes->");
    getVal(n,m,alloc);
    System.out.println("\nEnter max values for processes->");
    getVal(n,m,max);
    System.out.println("\nEnter available value -> ");
    for(int i = 0;i<m;i++)
    {
      System.out.print("->");
      int temp = S.nextInt();
      avail[i] = temp;
    }
  }

  void calcNeed()
  {
    for(int i = 0;i<n;i++)
    {
      for(int j = 0;j<m;j++)
      {
        need[i][j] = max[i][j] - alloc[i][j];
      }
    }
  }

  void isSafe()
  {
    int count = 0;
    boolean visited[] = new boolean[n];
    for(int i = 0;i<n;i++)
      visited[i] = false;

    int work[] = new int[m];
    for(int i = 0;i<m;i++)
      work[i] = avail[i];

    while(count<n)
    {
      boolean flag = false;
      for(int i = 0;i<n;i++)
      {
        if(visited[i] == false)
        {
          int j;
          for(j = 0;j<m;j++)
          {
            if(need[i][j]>work[j])
              break;
          }
          if(j == m)
          {
            safeSequence[count++] = i;
            visited[i] = true;
            flag = true;
            for(j = 0;j<m;j++)
              work[j] = work[j] + alloc[i][j];
          }
        }
      }
      if(flag == false)
        break;
    }
    if(count<n)
      System.out.println("System is Unsafe!");
    else
    {
      System.out.println("Following is the safe sequence ->");
      for(int i = 0;i<n;i++)
      {
        System.out.print("P" + safeSequence[i]);
        if(i == n)
          break;
        else
          System.out.print(" >> ");
      }
    }
  }

  public static void main(String[] args)
  {
    Bankers bankers = new Bankers();
    bankers.initVal();
    bankers.calcNeed();
    bankers.isSafe();
  }
}
