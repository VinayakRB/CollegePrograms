class Process
{
  int pid;
  int b_time;
  int arr_time;

  public Process(int pid,int b_time,int arr_time)
  {
    this.pid = pid;
    this.b_time = b_time;
    this.arr_time = arr_time;
  }
}
public class SJF
{
  static void calcWaitTime(Process process[],int n,int w_time[])
  {
    int rem_time[] = new int[n];
    for(int i = 0;i<n;i++)
      rem_time[i] = process[i].b_time;

    int end = 0,time_lap = 0, min = 500;
    int shortest = 0,finish_time;
    boolean flag = false;

    while(end!=n)
    {
      for(int i = 0;i<n;i++)
      {
        if((process[i].arr_time <= time_lap) && (rem_time[i] < min) && rem_time[i] > 0 )
        {
          min = rem_time[i];
          shortest = i;
          flag = true;
        }
      }

      if(flag == false)
      {
        time_lap++;
        continue;
      }

      rem_time[shortest]--;
      min = rem_time[shortest];
      if(min == 0)
        min = Integer.MAX_VALUE;

      if(rem_time[shortest] == 0)
      {
        end++;
        flag = false;
        finish_time = time_lap + 1;
        w_time[shortest] = finish_time - process[shortest].b_time - process[shortest].arr_time;

        if(w_time[shortest]<0)
          w_time[shortest] = 0;
      }

      time_lap++;
    }
  }

  static void calcTurnAroundTime(Process process[],int n,int w_time[],int ta_time[])
  {
    for(int i = 0;i<n;i++)
      ta_time[i] = process[i].b_time + w_time[i];
  }

  static void calcAvgTime(Process process[],int n)
  {
      int w_time[] = new int[n],ta_time[] = new int[n];
      int total_wt = 0,total_tat = 0;

      calcWaitTime(process,n,w_time);
      calcTurnAroundTime(process,n,w_time,ta_time);

      System.out.println("Processes " +
                         " Burst time " +
                         " Waiting time " +
                         " Turn around time");
     for(int i = 0;i<n;i++)
     {
       total_wt = total_wt + w_time[i];
       total_tat = total_tat + ta_time[i];
       System.out.println(process[i].pid + "\t\t"
                          + process[i].b_time + "\t\t"
                          + w_time[i] + "\t\t" + ta_time[i]);
     }
     System.out.println("Average waiting time = " + (float)total_wt/(float)n);
     System.out.println("Average turn around time = " + (float)total_tat/(float)n);
  }

  public static void main(String[] args)
  {
    Process process[] = { new Process(1,6,0),
                          new Process(2,8,1),
                          new Process(3,7,2),
                          new Process(4,3,3)
                        };
    int n = process.length;
    calcAvgTime(process,n);
  }
}
