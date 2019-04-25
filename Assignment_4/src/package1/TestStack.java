package package1;

import acm.program.*;
import java.util.Scanner;

public class TestStack {
	public static void main(String[] args){
		Stack myStack = new Stack();
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Enter a list of integers/strings to add to the stack");
		while (true) {
			System.out.print("--> ");
			String x = sc.nextLine();
			if (x.equals("?")) break;
			myStack.push(x);
			System.out.println("pushed "+x);
		}
		System.out.println("peak at first on top:"+myStack.peak());
		System.out.println("peak at first on top:"+myStack.peak());
		System.out.println("Pushed data:");
		System.out.println(myStack.printStack());
		
	}	
}
	
