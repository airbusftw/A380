import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number Of Processes You want to Execute:");
        int n = s.nextInt();
        
        // Arrays to store process details
        int[] arrival = new int[n];
        int[] cpu = new int[n];
        int[] finish = new int[n];
        int[] turntt = new int[n];
        int[] wait = new int[n];
        int[] process = new int[n];
        
        // Input Process Details
        for(int i = 0; i < n; i++) {
            System.out.println("Enter arrival time of Process " + (i+1) + ": ");
            arrival[i] = s.nextInt();
            System.out.println("Enter CPU time of Process " + (i+1) + ": ");
            cpu[i] = s.nextInt();
            process[i] = i + 1;
        }
        
        // Create a list of process indices
        List<Integer> processOrder = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            processOrder.add(i);
        }
        
        // Sort processes based on arrival time
        Collections.sort(processOrder, new Comparator<Integer>() {
            public int compare(Integer p1, Integer p2) {
                if(arrival[p1] != arrival[p2])
                    return Integer.compare(arrival[p1], arrival[p2]);
                else
                    return Integer.compare(p1, p2); // If arrival times are same, sort by process ID
            }
        });
        
        // Calculate Finish, Turnaround, and Waiting Times
        float total_tt = 0, total_waiting = 0;
        int currentTime = 0;
        
        for(int i = 0; i < n; i++) {
            int index = processOrder.get(i);
            if(currentTime < arrival[index]) {
                currentTime = arrival[index]; // CPU is idle till the process arrives
            }
            currentTime += cpu[index];
            finish[index] = currentTime;
            turntt[index] = finish[index] - arrival[index];
            wait[index] = turntt[index] - cpu[index];
            
            total_tt += turntt[index];
            total_waiting += wait[index];
        }
        
        // Display Process Information
        System.out.println("\nProcess\tAT\tCPU_T\tFT\tTT\tWT");
        for(int i = 0; i < n; i++) {
            System.out.println(process[i] + "\t" + arrival[i] + "\t" + cpu[i] + "\t" + finish[i] + "\t" + turntt[i] + "\t" + wait[i]);
        }
        
        // Display Average Turnaround Time and Waiting Time
        System.out.println("\nAverage Turnaround Time: " + (total_tt / n));
        System.out.println("Average Waiting Time: " + (total_waiting / n));
        
        s.close();
    }
}
