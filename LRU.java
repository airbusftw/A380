import java.io.*;
import java.util.*;

public class LRU{
    public static void main(String [] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int hit=0,fault=0,frames,ref_len;
        int buffer[];
        int reference[];
        int mem_layout[][];
        
        System.out.println("Please enter the number of frames: ");
        frames = Integer.parseInt(br.readLine());
        System.out.println("Please enter the length of reference string: ");
        ref_len = Integer.parseInt(br.readLine());

        reference = new int[ref_len];
        buffer = new int[frames];
        mem_layout = new int[frames][ref_len];

        System.out.println("Please the elements in reference string: ");
        for(int i=0; i<ref_len; i++){
            reference[i]=Integer.parseInt(br.readLine());
        }

        for(int i=0;i<frames;i++){
            buffer[i]=-1;
        }

        for(int j=0;j<ref_len;j++){
            int search=-1;
            for(int i=0;i<frames;i++){
                if(buffer[i]==reference[j]){
                    search=i;
                    hit++;
                    break;
                }
            }
            if(search==-1){
                int index=-1;
                boolean emptySlotFound=false;

                for(int i=0;i<frames;i++){
                    if(buffer[i]==-1){
                        emptySlotFound=true;
                        index=i;
                        break;
                    }
                }

                if(!emptySlotFound){
                    int [] recent=new int[frames];
                    Arrays.fill(recent,-1);

                    for(int k=j-1,count=1;k>=0 && count<=frames;k--){
                        for(int i=0;i<frames;i++){
                            if(reference[k]==buffer[i] && recent[i]==-1){
                                recent[i]=count++;
                                break;
                            }
                        }
                    }

                    int maxRecent=-1;
                    for(int i=0;i<frames;i++){
                        if(recent[i]==-1){
                            index=i;
                            break;
                        }
                        else if(recent[i]>maxRecent){
                            maxRecent=recent[i];
                            index=i;
                        }
                    }
                }
                buffer[index]=reference[j];
                fault++;
            }
            for(int i=0;i<frames;i++){
                mem_layout[i][j]=buffer[i];
            }
        }
        for(int i=0;i<frames;i++){
            for(int j=0;j<ref_len;j++){
                System.out.printf("%3d ",mem_layout[i][j]);
            }
            System.out.println();
        }

        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    }
}




/*Maam's CODE:  



import java.util.*;

class LruAlgo {
    int[] p, fr, fs; // Arrays for page reference, frame, and status
    int n, m, index, flag1 = 0, flag2 = 0, pf = 0, frsize = 3, i, j;
    Scanner src = new Scanner(System.in);

    void read() {
        System.out.println("Enter page table size");
        n = src.nextInt();
        p = new int[n];
        
        System.out.println("Enter elements in page table");
        for (int i = 0; i < n; i++)
            p[i] = src.nextInt();
        
        System.out.println("Enter page frame size");
        m = src.nextInt();
        fr = new int[m];
        fs = new int[m];
    }

    void display() {
        System.out.println("\n");
        for (i = 0; i < m; i++) {
            if (fr[i] == -1)
                System.out.print("[ ] ");
            else
                System.out.print("[" + fr[i] + "] ");
        }
        System.out.println();
    }

    void lru() {
        // Initialize the page frames to -1
        for (i = 0; i < m; i++) {
            fr[i] = -1;
        }

        for (j = 0; j < n; j++) {
            flag1 = 0;
            flag2 = 0;

            // Check if the page is already in one of the frames
            for (i = 0; i < m; i++) {
                if (fr[i] == p[j]) {
                    flag1 = 1;
                    flag2 = 1;
                    break;
                }
            }

            if (flag1 == 0) {
                // Check if there is any empty frame
                for (i = 0; i < m; i++) {
                    if (fr[i] == -1) {
                        fr[i] = p[j];
                        flag2 = 1;
                        pf++;
                        break;
                    }
                }
            }

            if (flag2 == 0) {
                // If no empty frame and page not found, apply the LRU replacement
                Arrays.fill(fs, 0);

                // Mark the recently used pages in the frame
                for (int k = j - 1, l = 1; l <= frsize - 1 && k >= 0; l++, k--) {
                    for (i = 0; i < m; i++) {
                        if (fr[i] == p[k]) {
                            fs[i] = 1; // Mark as recently used
                        }
                    }
                }

                // Find the least recently used page (unmarked in fs)
                for (i = 0; i < m; i++) {
                    if (fs[i] == 0) {
                        index = i;
                        break;
                    }
                }

                fr[index] = p[j]; // Replace with new page
                pf++; // Increment page fault counter
            }

            System.out.print("Page : " + p[j]);
            display();
        }

        System.out.println("\nNumber of page faults: " + pf);
    }

    public static void main(String[] args) {
        LruAlgo a = new LruAlgo();
        a.read();
        a.lru();
    }
}
*/


