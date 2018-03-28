import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class MakeRuns
{
	private int totalSize;
	private int maxHeapSize;
	private int heapSize;
	private int badHeapIndex = 0;
	private String fileName;
	private String currentRun;
	private String[] badHeap;
	private String line;
	private int temp;
	//String line;
	//String[] words;
	public static void main(String[] args)
	{
		try
		{
			MakeRuns runs = new MakeRuns();
			runs.run(args);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void run(String[] args)
	{
		//gets args
		totalSize = Integer.parseInt(args[0]);
		maxHeapSize = totalSize;
		fileName = args[1];
		//currentRun = new String[maxHeapSize * 3];
		badHeap = new String[maxHeapSize];
		//creates a new instance of the minheap object
		PriorityQueue<String> heap = new PriorityQueue(maxHeapSize);
		
		//adds x number of elements to heap
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			BufferedWriter writer = new BufferedWriter(new FileWriter("Runs.txt"));
			//fills the heap
			while(heap.size() < maxHeapSize)
			{
				line = br.readLine();
				String[] words = line.split(" ");
			
				for(int i = 0; i<words.length;i++)
				{
					
					if(heap.size() < maxHeapSize)
					{
			
						heap.add(words[i]);	
						temp = i;
						
						//add 1 new item to hep, replacing the old one
						
					}
					else	
					{
						break;
					}
				
				}
			}
			
			for(String item : heap) {System.out.println(item);}
			System.out.println("   ");
			String words[] = line.split(" ");
			//current run is empty
			currentRun = "";
			
			//while not ends of file
			while(line != null)
			{
				System.out.print(currentRun + " ");
				//remove top item
				String a = heap.peek(); 
				//System.out.println(a);
				//System.out.println(badHeap[16]);
				
				///CHECK TOP ITEM IN HEAP AND ADD IT TO RUN
				//if current run is empty
				if(currentRun == "")
				{
					//set last item in run
					currentRun = heap.poll();
					writer.write(currentRun + " ");
				}
				//if next item is larger than last item in run
				else if(currentRun.compareTo(a)<= 0)
				{
					currentRun = heap.poll();
					//add new item
					
					if(temp >= words.length)
						{
							line = br.readLine();
							words = line.split(" ");
							temp = 0;
						}
						heap.add(words[temp]);
						temp++;
						writer.write(currentRun + " ");
				}
				//item is smaller, move to bad heap and reduce size of heap
				else
				{
					if(badHeap[0] == null)
					{
						badHeap[0] = heap.poll();
						badHeapIndex = 1;
						maxHeapSize --;
					}
					else
					{
						badHeap[badHeapIndex] = heap.poll();
						badHeapIndex++;
						maxHeapSize--;
					}
				}
				
				///CHECK IF THE HEAP IS NOW EMPTY
				//if heap is empty
				//if(heap.peek() == null)
				if(heap.size() == 0)
				{
					System.out.println("");
					
					//add all elements in bad heap back into heap
					writer.close();
					maxHeapSize = totalSize;
					badHeapIndex = 0;
					System.out.println(badHeap.length);
					System.out.println(heap.size());
					for(int i = 0;i<badHeap.length;i++)
					{
						System.out.print(heap.size() + " ");
						heap.add(badHeap[i]);
						badHeap[i] = null;

						//EXCEPTION THROWN
					}
					
					
				}


			}

			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
	