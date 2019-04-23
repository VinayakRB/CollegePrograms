// Program for implementation of FCFS scheduling algorithm
// 1. Find Waiting Time
// 2. Find TurnAround Time
// 3. Find Average Waiting and TurnAround Time
public class Fcfs
{
  static void wTime(int process[],int n,int burst_time[],int wait_time[],int arr_time[])
  {
    int service_time[] = new int[n];
    wait_time[0] = 0;
    service_time[0] = 0;

    for(int i = 1;i<n;i++)
    {
      service_time[i] = service_time[i-1] + burst_time[i-1];
      wait_time[i] = service_time[i] - arr_time[i];
      if(wait_time[i]<0)
        wait_time[i] = 0;
    }
  }

  static void taTime(int process[],int n,int wait_time[],int burst_time[],int ta_time[])
  {
    for(int i = 0;i<n;i++)
    {
      ta_time[i] = burst_time[i] + wait_time[i];
    }
  }

  static void avgTime(int process[],int n,int burst_time[],int arr_time[])
  {
      int wait_time[] = new int[n];
      int ta_time[] = new int[n];

      wTime(process,n,burst_time,wait_time,arr_time);
      taTime(process,n,burst_time,wait_time,ta_time);
      int total_wt = 0;
      int total_tat = 0;
      System.out.print("Processes " + " Burst Time " + " Arrival Time "
        + " Waiting Time " + " Turn-Around Time "
        + " Completion Time \n");
      for(int i = 0;i<n;i++)
      {
        total_wt = total_wt + wait_time[i];
        total_tat = total_tat + ta_time[i];
        int com_time = ta_time[i] + arr_time[i];
        System.out.println(i+1 + "\t\t" + burst_time[i] + "\t\t"
         + arr_time[i] + "\t\t" + wait_time[i] + "\t\t"
         + ta_time[i] + "\t\t" + com_time);
      }
      System.out.println("Average Wait Time : " + (float)total_wt/(float)n);
      System.out.println("Average TurnAround Time : " + (float)total_tat/(float)n);
  }

  public static void main(String[] args)
  {
    int process[] = {1,2,3};
    int n = process.length;
    int burst_time[] = {5,3,6};
    int arr_time[] = {0,3,8};
    avgTime(process,n,burst_time,arr_time);

  }
}
