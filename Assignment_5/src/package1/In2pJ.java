//Rene Gagnon
//260801777

package package1;

import java.util.StringTokenizer; 
import acm.program.ConsoleProgram;


@SuppressWarnings("serial")
public class In2pJ extends ConsoleProgram{

	/**
	 *method containing all the command to be executed
	 *
	 */
	public void run() { 
		this.setSize(500,500);// set the window console bigger
	    String str = readLine("Enter string: "); //read a mathematical expression from the user
	    // we create a new tokenizer to tokenize the string entered
	    StringTokenizer st = new StringTokenizer(str,"+-*/()",true);
	    Queue outputQueue =new Queue();
	    Stack operatorStack = new Stack();
	    boolean error=false; // variable used to say if there is a problem 
	    
	    
	    String currentToken="";// the string to old the current token
	    
	    while(st.hasMoreTokens()){// while we have more token we continue applying the algorithm on the next token
	    	
	    	currentToken=st.nextToken();
	    	
	    	if(isTokenNumber(currentToken)){
	    		outputQueue.enqueue(currentToken);// if token is number then goes directly on the output queue
	    	}
	    	
	    	if(isTokenOperator(currentToken)){// if token is an operator, we put it on the stack, but before we check if 
	    		//there is operator with higher precedence and if yes we enqueue them in the output queue
	    		while(!operatorStack.isStackEmpty() && isHigherPrecedence(operatorStack.peak(),currentToken)){
	    			outputQueue.enqueue(operatorStack.pop());
	    		}
	    		operatorStack.push(currentToken);
	    	}
	    	
	    	if(currentToken.equals("(")){
	    		operatorStack.push(currentToken);
	    	}
	    	
	    	if(currentToken.equals(")")){
	    		
	    		// and the stack is not empty
	    		// as long as the operator on the stack not a left parenthesis
	    		while(!operatorStack.isStackEmpty()&&!operatorStack.peak().equals("(")){
	    			
	    			outputQueue.enqueue(operatorStack.pop());
	    		}
	    		
	    		operatorStack.pop();
	    		
	    		if(operatorStack.isStackEmpty()){
	    			// handle the error of a right parenthesis missing
    				println("Error: mismatch parenthesis!");
    				println("A left parenthesis is missing!");
    				error=true;
	    			break;
    			}
    			
	    	}
	    	
	    	if(!st.hasMoreTokens()){// we enqueue the rest of the operator from the operator stack
	    		while(!operatorStack.isStackEmpty()){
	    			outputQueue.enqueue(operatorStack.pop());
	    			
	    			if(!operatorStack.isStackEmpty()&&operatorStack.peak().equals("(")){
	    				// handle the error of a left parenthesis missing
	    				error=true;
	    				println("Error: mismatch parenthesis!");
	    				println("A right parenthesis is missing!");
	    			}
	    			
	    		}
	    	}
	    	
	    }
	    
	    if(!error){
	    println("Postfix: "+outputQueue.StringQueue());
	    }
	    else if(error){
	    	println("Error somewhere in the program!");
	    }
	} 
	/**
	 * method to determine if one operator has higher precedence than an other one
	 * @param op1 operator 1
	 * @param op2 operator 2
	 * @return true when operator 1 has higher/equal precedence than operator 2, false when smaller precedence)
	 */
	private boolean isHigherPrecedence(String op1, String op2){
		//integer values representing the precedence of each operator
		int op1value=0;
		int op2value=0;

		switch(op1){//assign a value corresponding with op1
			case "+":
				op1value=1;
				break;
			case "-":
				op1value=1;
				break;
			case "*":
				op1value=2;
				break;
			case "/":
				op1value=2;
				break;
		}
		switch(op2){//assign value corresponding to op2
			case "+":
				op2value=1;
				break;
			case "-":
				op2value=1;
				break;
			case "*":
				op2value=2;
				break;
			case "/":
				op2value=2;
				break;
		}
		if(op1value>=op2value){//compare the two and return the appropriate result
			return true;
		}else{
			return false;
		}
		
	}
	/**
	 * check if a token is a number 
	 *
	 * @param token the token to be checked, in the form of a string
	 * @return true if number false otherwise
	 */
	private boolean isTokenNumber(String token){
		if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/")||token.equals("(")||token.equals(")")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * check if token is a operator
	 * @param token the token to be checked, in the form of a string
	 * @return true if operator false otherwise
	 */
	private boolean isTokenOperator(String token){
		if(token.equals("+")||token.equals("-")||token.equals("*")||token.equals("/")){
			return true;
		}else{
			return false;
		}
	}


}
	

