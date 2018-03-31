//Jason Cutts 1288205

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

		badHeap = new String[maxHeapSize];
		//creates a new instance of the minheap object
		PriorityQueue<String> heap = new PriorityQueue(maxHeapSize);
		

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			BufferedWriter writer = new BufferedWriter(new FileWriter("Runs.txt"));
			String[] words;
			runCounter = 1;
			badHeapIndex = 0;
			currentRun = "";
			int count = 0;
			writer.write("\4" + System.lineSeparator());
			//loop until end of file
			while((line = br.readLine()) != null) 
			{
				
				words = line.split(" ");
				
				for(int i = 0;i < words.length; i++)//
				{

					//if heap is smaller than max size but not 0
					if(heap.size() < maxHeapSize && maxHeapSize > 0)
					{
						//add an item to the heap
						
						System.out.println("Added : " + words[i] + " 1st if statement");
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
							
							System.out.println("Added : " + words[i] + " 2nd if statement");
							heap.add(words[i]);
							writer.write(currentRun + " ");
						}
						//else item must be smaller than last item
						else
						{
							//add item to bad heap, decrement heap size and increment index of bad heap
							badHeap[badHeapIndex] = heap.poll();
							maxHeapSize--;
							badHeapIndex++;
							i--;
						}
					}
					//heap is 0
					else
					{
						//end run
						writer.write(System.lineSeparator() + "\4" + System.lineSeparator());
						runCounter++;
						currentRun = "";
						badHeapIndex = 0;
						maxHeapSize = totalSize;
						i--;
						for(int j = 0; j < badHeap.length; j++)
						{
							heap.add(badHeap[j]);
							badHeap[j] = null;
							
						}
						
					}


				}
				
			}
			writer.write(System.lineSeparator() + "\4" + System.lineSeparator());
			runCounter++;
			for(int j = 0; j<badHeap.length;j++)
			{
				if(badHeap[j]!=null && heap.size() < maxHeapSize)
					heap.add(badHeap[j]);			
			}
		
			while(heap.size() > 0)
			{
				currentRun = heap.poll();
				writer.write(currentRun + " ");
				
			}
			
			if(badHeap[0] != null)
			{
				for(int j = 0; j<badHeap.length;j++)
				{
					if(badHeap[j]!=null)
					heap.add(badHeap[j]);			
				}
				writer.write(System.lineSeparator() + "\4" + System.lineSeparator());
				runCounter++;
				while(heap.size() > 0)
				{
					currentRun = heap.poll();
					writer.write(currentRun + " ");
				}
			}
			

			writer.close();
			System.err.println("Runs : " + runCounter);
			
			//for(String item : heap){System.out.println(item);}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
	