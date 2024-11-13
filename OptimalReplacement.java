import java.io.*;

public class OptimalReplacement{
        public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int frames,ref_len,hit=0,fault=0;
        int buffer[];
        int reference[];
        int mem_layout[][];

        System.out.println("enter the number of frames: ");
        frames=Integer.parseInt(br.readLine());

        System.out.println("enter the length of reference string: ");
        ref_len=Integer.parseInt(br.readLine());

        buffer=new int [frames];
        reference=new int[ref_len];
        mem_layout=new int[frames][ref_len];

        for(int i=0;i<frames;i++)
            buffer[i]=-1;

        System.out.println("Enter the string: ");
        for(int j=0;j<ref_len;j++){
            reference[j]=Integer.parseInt(br.readLine());
        }
        System.out.println();

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
                    int farthest=-1;
                    index=-1;

                    for(int i=0;i<frames;i++){
                        int nextuse=Integer.MAX_VALUE;

                        for(int k=j+1;k<ref_len;k++){
                            if(buffer[i]==reference[k]){
                                nextuse=k;
                                break;
                            }
                        }

                        if(nextuse>farthest){
                            farthest=nextuse;
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





/* 

Maam's CODE:-


import java.io.*;
public class OptimalReplacement {
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames, pointer = 0, hit = 0, fault = 0,ref_len;
        boolean isFull = false;
        int buffer[];
        int reference[];
        int mem_layout[][];
        
        System.out.println("Please enter the number of Frames: ");
        frames = Integer.parseInt(br.readLine());
        
        System.out.println("Please enter the length of the Reference string: ");
        ref_len = Integer.parseInt(br.readLine());
        
        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for(int j = 0; j < frames; j++)
                buffer[j] = -1;
        
        System.out.println("Please enter the reference string: ");
        for(int i = 0; i < ref_len; i++)
        {
            reference[i] = Integer.parseInt(br.readLine());
        }
        System.out.println();
        for(int i = 0; i < ref_len; i++)
        {
         int search = -1;
         for(int j = 0; j < frames; j++)
         {
          if(buffer[j] == reference[i])
          {
           search = j;
           hit++;
           break;
          } 
         }
         if(search == -1)
         {
          if(isFull)
          {
           int index[] = new int[frames];
           boolean index_flag[] = new boolean[frames];
           for(int j = i + 1; j < ref_len; j++)
           {
            for(int k = 0; k < frames; k++)
            {
             if((reference[j] == buffer[k]) && (index_flag[k] == false))
             {
              index[k] = j;
              index_flag[k] = true;
              break;
             }
            }
           }
           int max = index[0];
           pointer = 0;
           if(max == 0)
            max = 200;
           for(int j = 0; j < frames; j++)
           {
            if(index[j] == 0)
             index[j] = 200;
            if(index[j] > max)
            {
             max = index[j];
             pointer = j;
            }
           }
          }
          buffer[pointer] = reference[i];
          fault++;
          if(!isFull)
          {
           pointer++;
              if(pointer == frames)
              {
               pointer = 0;
               isFull = true;
              }
          }
         }
            for(int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        
        for(int i = 0; i < frames; i++)
        {
            for(int j = 0; j < ref_len; j++)
                System.out.printf("%3d ",mem_layout[j][i]);
            System.out.println();
        }
        
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    }
    
}


*/