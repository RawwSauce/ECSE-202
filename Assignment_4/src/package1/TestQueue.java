package package1;

import java.util.Scanner;

public class TestQueue {
		public static void main(String[] args){
			Queue myQueue = new Queue();
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter a list of integers/strings to add to the queue");
			while (true) {
				System.out.print("--> ");
				String x = sc.nextLine();
				if (x.equals("?")) break;
				myQueue.enqueue(x);
				System.out.println("inserted "+x);
			}
			System.out.println("size of queue:"+myQueue.size);
			
			
			System.out.println("the queue as a string: "+myQueue.StringQueue());
			
		}	
	
}
