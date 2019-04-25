package package1;


/**
 * 
 * @author renet
 *This class create a stack
 *
 */
public class Stack {

	ListNode stack_buffer=null;
	/**
	 * adds an element on top of the stack
	 * @param data its the data we want to push on top of the stack
	 */
	public void push(String data){
		ListNode node = new ListNode();
		node.payload=data;
		node.next=stack_buffer;
		stack_buffer=node;
	}
	/**
	 * removes the last element added to the stack, the top element in other words
	 * @return the top element
	 */
	public String pop(){
		if(stack_buffer!=null){
			String data1=stack_buffer.payload;
			stack_buffer=stack_buffer.next;
			return data1;
		}else{
			return null;
		}
	}
	
	/**
	 * prints the stacks as a string
	 * @return a string containing every element of the stack
	 */
	public String printStack(){
		String mystack="";
		String popdata="";
		while(popdata!=null){
			popdata=pop();
			if(popdata!=null){
				mystack+=popdata+" ";
			}
		}
		return mystack;
	}

	/**
	 * peaks at the top element
	 * @return the top element
	 */
	public String peak(){
		return stack_buffer.payload;
	}
	/**
	 * check if the stack is empty
	 * @return true if stack empty, false otherwise
	 */
	public boolean isStackEmpty(){
		return (stack_buffer==null);
	}
}
