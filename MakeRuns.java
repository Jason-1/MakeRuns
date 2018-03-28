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
	private int runCounter;
	
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

		badHeap = new String[maxHeapSize];
		//creates a new instance of the minheap object
		PriorityQueue<String> heap = new PriorityQueue(maxHeapSize);
		

		try
		{
			//initialise all variables
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			BufferedWriter writer = new BufferedWriter(new FileWriter("Runs.txt"));
			String[] words;
			runCounter = 1;
			badHeapIndex = 0;
			currentRun = "";
			int count = 0;
			
			//loop until end of file
			while((line = br.readLine()) != null) 
			{
				
				words = line.split(" ");
				
				for(int i = 0;i < words.length; i++)
				{

					//if heap is smaller than max size but not 0
					if(heap.size() < maxHeapSize && maxHeapSize > 0)
					{
						//add an item to the heap
						heap.add(words[i]);
						
					}
					//heap is at max size
					else if(heap.size() == maxHeapSize && maxHeapSize > 0)
					{
						String first = heap.peek();
						
						//if first item is larger than last item in current run or current run is empty
						if(currentRun.compareTo(first)<= 0 || currentRun == "")
						{
							//append the item to the current run
							currentRun = heap.poll();
							writer.write(currentRun + " ");
						}
						//else item must be smaller than last item
						else
						{
							//add item to bad heap, decrement heap size and increment index of bad heap
							badHeap[badHeapIndex] = heap.poll();
							maxHeapSize--;
							badHeapIndex++;
						}
					}
					//heap is 0
					else
					{
						//end run
						writer.write(System.lineSeparator());
						runCounter++;
						currentRun = "";
						badHeapIndex = 0;
						maxHeapSize = totalSize;
						
						for(int j = 0; j < badHeap.length; j++)
						{
							heap.add(badHeap[j]);
							badHeap[j] = null;
							
						}
						
					}


				}
				
			}
			//new run
			writer.write(System.lineSeparator());
			runCounter++;
			//take as many elements as possible out of the bad heap
			for(int j = 0; j<badHeap.length;j++)
			{
				if(badHeap[j]!=null && heap.size() < maxHeapSize)
					heap.add(badHeap[j]);			
			}
			//print a run
			while(heap.size() > 0)
			{
				currentRun = heap.poll();
				writer.write(currentRun + " ");
				
			}
			//put all elements into heap
			for(int j = 0; j<badHeap.length;j++)
			{
				if(badHeap[j]!=null)
					heap.add(badHeap[j]);			
			}
			//new run
			writer.write(System.lineSeparator());
			runCounter++;
			//print last run
			while(heap.size() > 0)
			{
				currentRun = heap.poll();
				writer.write(currentRun + " ");
			}
			//close writer and print the number of runs produced
			writer.close();
			System.err.println("Runs : " + runCounter);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
	