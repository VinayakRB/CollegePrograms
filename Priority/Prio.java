import java.util.Scanner;

class Process
{
  int pid;
  int b_time;
  int arr_time;
  int priority;

  public Process(int pid,int b_time,int arr_time,int priority)
  {
    this.pid = pid;
    this.b_time = b_time;
    this.arr_time = arr_time;
    this.priority = priority;
  }
}
public class Prio
{
  static void sortbyPriority(Process process[],int n)
  {
    Process temp = new Process(0,0,0,0);
    for(int i = 0;i<n;i++)
    {
      for(int j = 0;j<n;j++)
      {
        if(process[i].arr_time < process[j].arr_time)
        {
          temp = process[i];
          process[i] = process[j];
          process[j] = temp;
        }
        else if(process[i].arr_time == process[j].arr_time)
        {
          if(process[i].priority < process[j].priority)
          {
            temp = process[i];
            process[i] = process[j];
            process[j] = temp;
          }
        }
      }
    }
  }

  static void calcWaitTime(Process process[],int w_time[],int n)
  {
    int service_time[] = new int[n];
    service_time[0] = 0;
    w_time[0] = 0;
    for(int i = 1;i<n;i++)
    {
      service_time[i] = service_time[i-1] + process[i-1].b_time;
      w_time[i] = service_time[i] - process[i].arr_time;
      if(w_time[i]<0)
        w_time[i] = 0;
    }
  }
  static void calcTATime(Process process[],int ta_time[],int w_time[],int n)
  {
    for(int i = 0;i<n;i++)
    {
      ta_time[i] = process[i].b_time + w_time[i];
    }
  }
  static void calcAvgTime(Process process[],int n)
  {
    int w_time[] = new int[n], ta_time[] = new int[n];
    calcWaitTime(process,w_time,n);
    calcTATime(process,ta_time,w_time,n);
    System.out.println("PID\tBurstTime\tArrivalTime\tPriority\tWaitTime\tTurnAroundTime");
    for(int i = 0;i<n;i++)
    {
      System.out.println(process[i].pid + "\t" + process[i].b_time + "\t\t"
                         + process[i].arr_time + "\t\t" + process[i].priority + "\t\t"
                         + w_time[i] + "\t\t" + ta_time[i]);
    }
  }
  public static void main(String[] args)
  {
    Scanner S = new Scanner(System.in);
    int n,PID,BT,AT,PRIORITY;
    System.out.print("Enter number of processess : ");
    n = S.nextInt();
    Process process[] = new Process[n];
    for(int i = 0;i<n;i++)
    {
      System.out.println("\nEnter PID, BT, AT and Priority for process : "
                          + (i + 1));
      PID = S.nextInt();
      BT = S.nextInt();
      AT = S.nextInt();
      PRIORITY = S.nextInt();

      process[i] = new Process(PID,BT,AT,PRIORITY);
    }
    sortbyPriority(process,n);
    calcAvgTime(process,n);
  }
}
