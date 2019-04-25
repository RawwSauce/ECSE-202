//Rene Gagnon
//260801777

package package1;
/**
 * this class creates a queue
 * @author renet
 *
 */

public class Queue {
	
	ListNode front=null;
	ListNode rear=null;
	int size=0;
	
	/**
	 * adds a new node to the queue
	 * @param data the data to be added
	 */
	public void enqueue(String data){
		ListNode oldRear=rear;// copy the data from rear to oldRear
		rear=new ListNode();//creates a new node and now rear points to it, this is the new rear of the queue
		rear.payload=data;//copy the data inside the new rear node
		rear.next=null;//added element is at the end of the array so no next
		if(isQueueEmpty()){//if the queue is empty
			front=rear;// the front is equal to the rear
		}else{// the queue is not empty
			oldRear.next=rear; // the previous rear now the point to the new rear
		}
		size++;
	}
	/**
	 * remove an element from the front of the queue
	 * @return the removed element
	 */
	public String dequeue(){
		String elem="";
		elem=front.payload;
		front=front.next;
		size--;
		if(isQueueEmpty()){
			rear=null;//if the queue is empty the rear is now null
		}
		return elem;
	}
	/**
	 * To see if the queue is empty
	 * @return true when empty, false otherwise
	 */
	public boolean isQueueEmpty(){
		
		return(front==null);
	}
	/**
	 * method to know how many elements in the queue
	 * @return the number of elements
	 */
	public int sizeOfQueue(){
		return size;
	}
	/**
	 * a method to return the queue as a string, easier to print 
	 * @return the queue as one string
	 */
	public String StringQueue(){
		String queueString="";// the string that will hold the element of the queue
		
		while(rear!=null || front!=null){// while the queue is not empty we 
			//dequeue all of the elements and add them to a string
			queueString+=dequeue()+" ";
		}
		return queueString;
	}
	
}
