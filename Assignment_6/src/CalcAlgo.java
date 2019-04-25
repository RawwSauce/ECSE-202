//Rene Gagnon
//260801777




import java.util.StringTokenizer; 




public class CalcAlgo{

	/**
	 *method containing all the command to be executed
	 *
	 */
	public String run(String str) { 
		
		
		
			
		    // we create a new tokenizer to tokenize the string entered
		    StringTokenizer st = new StringTokenizer(str,"+-*/()",true);
		    Queue outputQueue =new Queue();
		    Stack operatorStack = new Stack();
		    boolean error=false; // variable used to say if there is a problem 
		    String errorCause="";
		    double answer;
		    
		    
		    String currentToken="";// the string to old the current token
		    
		    while(st.hasMoreTokens()&& !error){// while we have more token we continue applying the algorithm on the next token
		    	
		    	currentToken=st.nextToken();
		    	//println("Current Token: "+currentToken);
		    	

		    	if(!isTokenNumber(currentToken)&&!isTokenOperator(currentToken)&&!currentToken.equals("(")&&!currentToken.equals(")")){
		    		errorCause=("Invalid expression");
		    		error=true;
		    		
		    	}
		    	
		    	if(isTokenNumber(currentToken)&&!error){
		    		outputQueue.enqueue(currentToken);// if token is number then goes directly on the output queue
		    		//println("pushed:"+currentToken);
		    	}
		    	
		    	if(isTokenOperator(currentToken)&&!error){// if token is an operator, we put it on the stack, but before we check if 
		    		//there is operator with higher precedence and if yes we enqueue them in the output queue
		    		while(!operatorStack.isStackEmpty() && isHigherPrecedence(operatorStack.peak(),currentToken)){
		    			outputQueue.enqueue(operatorStack.pop());
		    		}
		    		operatorStack.push(currentToken);
		    	}
		    	
		    	if(currentToken.equals("(")&&!error){
		    		operatorStack.push(currentToken);
		    	}
		    	
		    	if(currentToken.equals(")")&&!error){
		    		
		    		
		    		// enqueue operators as long as the operator on the stack is not a left parenthesis
		    		while(!operatorStack.isStackEmpty()&&!operatorStack.peak().equals("(")&&!error){
		    			
		    			//println("1"+operatorStack.peak());
		    			outputQueue.enqueue(operatorStack.pop());
		    			
		    			if(operatorStack.isStackEmpty()){
			    			// handle the error of a left parenthesis missing
		    				errorCause=("Error a left parenthesis is missing");
		    				error=true;
			    	
		    			}
		    		}	
		    		//println("2"+operatorStack.peak());
		    		
		    		@SuppressWarnings("unused")
					String temp=operatorStack.pop();
		    	}
		    	
		    	
		    	if(!st.hasMoreTokens()&& !error){// we enqueue the rest of the operator from the operator stack
		    		while(!operatorStack.isStackEmpty()&&!error){
		    					    			
		    			if(!operatorStack.isStackEmpty()&&operatorStack.peak().equals("(")){
		    				// handle the error of a right parenthesis missing
		    				error=true;
		    				errorCause=("Error a right parenthesis is missing");
		    				
		    			}
		    			//println("3"+operatorStack.peak());
		    			outputQueue.enqueue(operatorStack.pop());
		    		}
		    	}
		    	
		    }
		    
		    if(!error){
		    	try{
		    		
		    	String PostFixExp=outputQueue.StringQueue();
		    	//println("Postfix: "+PostFixExp);
		    	answer=EvaluatePostFix(PostFixExp);
		    	}
		    	catch(Exception e){
		    		return "Invalid expression";
		    	}
		    	return Double.toString(answer);
		    
		    }
		    else{
		    	return errorCause;
		    }
		    
		}
	    
	 
	/**
	 * 
	 * 
	 * @param postfix postfix expression to be evaluated
	 * @return the numerical value of the postfix expression
	 */
	private double EvaluatePostFix(String postfix){
		
		StringTokenizer st2 = new StringTokenizer(postfix," ",false); // to tokenize our postfix expression
    	Stack ComputeStack = new Stack();// stack used when computing the result
    	String currentToken="";// the string to old the current token
    	
    	while(st2.hasMoreTokens()){
    		
    		currentToken=st2.nextToken();
    		
    		if(isTokenNumber(currentToken)){
    		
    			ComputeStack.push(currentToken);
    		
    		}
    		else{
    			//println(ComputeStack.peak());
    			Double num2=Double.parseDouble(ComputeStack.pop());
    			//println(ComputeStack.peak());
    			Double num1=Double.parseDouble(ComputeStack.pop());
    			String operator=currentToken;
    			//println(operator);
    			
    			switch(operator){
    			
    			case "+":
    				ComputeStack.push(Double.toString(num1+num2));
    				break;
    			case "-":
    				ComputeStack.push(Double.toString(num1-num2));
    				break;
    			case "*":
    				ComputeStack.push(Double.toString(num1*num2));
    				break;
    			case "/":
    				ComputeStack.push(Double.toString(num1/num2));
    				break;
    			}
    		}
    	}
	return Double.parseDouble(ComputeStack.pop());

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
	public static boolean isTokenNumber(String token){
		
		try{// we "try" to convert the token to a number
			Double.parseDouble(token);
			return true;// if no error we return true
		}catch (NumberFormatException e){// we catch the exception thrown when we cannot convert to a double
			return false;
		}catch (NullPointerException e){
			return false;
		}catch (Exception e){
			return false;
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
	

