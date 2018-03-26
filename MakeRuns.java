import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class MakeRuns
{
	private int maxHeapSize;
	private int heapSize;
	private int heapIndex = 0;
	private int badHeapIndex = 0;
	private String fileName;
	private String[] currentRun;
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
		maxHeapSize = Integer.parseInt(args[0]);
		fileName = args[1];
		currentRun = new String[maxHeapSize * 3];
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
						
						//addItemToHeap(words,br,heap);
						
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
			//while not ends of file
			while(line != null)
			{
				//remove top item
				String a = heap.peek(); 
				System.out.println(a);
				//if current run is empty
				if(currentRun[0] == null)
				{
					currentRun[0] = heap.poll();
					heapIndex = 0;
				}
				//if next item is larger than last item in run
				else if(currentRun[heapIndex].compareTo(a)<= 0)
				{
					heapIndex++;
					currentRun[heapIndex] = heap.poll();
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
						badHeap[heapIndex] = heap.poll();
						badHeapIndex++;
						maxHeapSize--;
					}
				}
				//if heap is empty
				if(heap.peek() == null)
				{
					//break;
					//print the run
					System.out.println("");System.out.println("");System.out.println("");
					int i = 0;
					String run = "";
					while(currentRun[i] != null)
					{
						i++;
						run += currentRun[i] + " ";
					}
					System.out.println(run);
					writer.write(run);
					//writer.close();
				}
				//System.out.println(heap.size());
				//if item/items needs adding to heap			
				/*while(heap.size() < maxHeapSize)
				{
					
					//read new line
					if(temp >= words.length)
					{
						line = br.readLine();
						words = line.split(" ");
						temp = 0;
					}
					heap.add(words[temp]);
					temp++;
				}*/

			}

			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
	