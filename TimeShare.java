import java.util.*;
import java.io.*;

public class TimeShare
{
  public static void main(String[] args) throws CloneNotSupportedException
  {
    //Queue variable to manipulate get file info
    Queue in = new Queue();
    
    clearScreen();    //clear the screen
    in = inputQueue(in, args[0]);    //retreive the file information and store it in the in queue
    
    header();  //display the header for the output of the program
    program(in);    //start the program
  }
  
  //post: runs the program
  public static void program(Queue in)
  {
    //Queue variable to manipulate job items
    Queue jobs = new Queue();
    
    //ArrayList to holld the wait times of the jobs to calculate the average wait time
    ArrayList<Integer> wait = new ArrayList<Integer>();
    
    //clock variable, idle time variable, and use time variable at start time
    int clock = 0;
    double idle = 0;
    double use = 0;
    
    //Loop while the in queue or jobs queue are not empty
    while (!in.isEmpty() || !jobs.isEmpty())
    {
      //condition: if in is not empty and the arrival time of the next job is equal to the current clock time
      if(!in.isEmpty() && ((((Job)in.front()).arrivalTime) == clock))
      {
        jobs.enqueue(in.front());  //queue the next job from the in queue
        in.dequeue();  //remove the queued job from this queue
      }
      
      //condition: if the job queue is not empty
      if(!jobs.isEmpty())
      {
        //condition: if the current clock time minus the run time of the job equals the start time of the job
        if(clock - (((Job)jobs.front()).runTime) == (((Job)jobs.front()).startTime))
        {
          System.out.println(((Job)jobs.front()).toString());    //print the job
          use += ((Job)jobs.front()).runTime;      //update the usage variable
          jobs.dequeue();          //dequeue the job
        }
      }
      
      //condition: if the job queue is not empty
      if(!jobs.isEmpty()) 
      {
        //condition: if the arrival time of the current job is less than or equal to the current clock value and the start time of the current job still has the default value
        if (((((Job)jobs.front()).arrivalTime) <= clock) && ((((Job)jobs.front()).startTime) == -1))
        {
          ((Job)jobs.front()).startTime = clock;    //set the start time of the current job = to the current clock time
          ((Job)jobs.front()).waitTime = clock - (((Job)jobs.front()).arrivalTime);    //set the wait time of the current job equal to the current clock time minus arrival time
          (((Job)jobs.front()).turnTime) = ((Job)jobs.front()).waitTime + ((Job)jobs.front()).runTime;    //set the turnaround time of the current job equal to the wait time of the current job plus the run time of the current job
          wait.add(((Job)jobs.front()).waitTime);    //add the wait time of the current job to the wait time arraylist
        }
      }
      
      //condition: if the jobs queue is empty, the in queue is not empty, and the current clock value is not zero
      if((jobs.isEmpty()) && (!in.isEmpty()) && (clock != 0))
        idle++;    //increment the idle counter
      //condition: if the in queue is not emty or the jobs queue is not empty
      if((!in.isEmpty()) || (!jobs.isEmpty()))
        clock++;    //increment the clock counter
    }
    
    double avg = avgWait(wait);    //get the average wait time
    print(avg, use, idle, clock);   //print the data for the report
  }
  //post: prints the header for the program output
  public static void header()
  {
    System.out.println("Job Control Analysis: Summary Report");
    System.out.println("------------------------------------");
    System.out.println("");
    System.out.println("Job ID      Arrival    Start    Run     Wait    Turnaround");
    System.out.println("            Time       Time     Time    Time    Time");
    System.out.println("------      -------    -----    ----    ----    ----------");
    System.out.println("");
  }
  
  //post: gets the file information for the program to use
  public static Queue inputQueue(Queue in, String user)
  {
    try
      {
        Scanner sc = new Scanner(new File(user));
        
        //This loop will continue to add data to the queue as long as there is content in the file used for the program
        while(sc.hasNext())
        {
          Scanner sc2 = new Scanner(sc.nextLine());
          String jobName = sc2.next();
          int arrivalTime = sc2.nextInt();
          int runTime = sc2.nextInt();
          in.enqueue(new Job(jobName, arrivalTime, runTime));
        }
      }
      catch(IOException e)
      {
        System.out.println(e.getMessage());
      }
      
      return in;
  }
  
  //post: calculates the average wait time of all the jobs
  public static double avgWait(ArrayList<Integer> wait)
  {
    double avg = 0;    //average wait time variable
    
    //This loop will run as long as there is information in the ArrayList
    for(int i = 0;i < wait.size();i++)
      avg += wait.get(i);    //calculate the total wait time
    
    avg /= wait.size();    //calculate the average wait time
    return avg;
  }
  
  //post: prints the data of the job queue
  public static void print(double wait, double use, double idle, int clock)
  {
    double percent = (use/clock) * 100;    //calculates the percentage of the CPU used
    
    System.out.print("Average Wait Time => ");    //print the average wait time
    System.out.printf("%.2f", wait);
    System.out.print("\n");
    System.out.println("CPU Usage => " + use);    //print how much time the cpu is used
    System.out.println("CPU Idle => " + idle);    //print how much time the cpu was idle
    System.out.print("CPU Usage (%) => ");        //print the percentage of cpu used
    System.out.printf("%.2f", percent);
    System.out.print(" %\n");
  }
  
  //Post: clears the screen
  private static void clearScreen()
  {
      System.out.println("\u001b[H\u001b[2J");
  }
}