/* Rene Gagnon
 * McGill ID # 260801777
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{

	int nbr=0; // number to convert
	int base=0; // base to convert to
	int rest; // quotient of an int division
	int remain; // remainder of an int division
	int Q; // variable used in the conversion algorithm
	int i=0; // variable to count the digit needed
	char digit; // variable use to store digits of the converted number

	switch(argc){
		case 2: // if we only have two arguments from the user
			sscanf(argv[1],"%d",&nbr); //gets the number from the user
			base=2; // since no base input from the user, we set base 2
			break;
		case 3: //if we have 3 arguments from the user
			sscanf(argv[1],"%d",&nbr); // get the number from the user
			sscanf(argv[2],"%d",&base); // get the base from the user
			break;
		default:
			printf("Wrong number of arguments \n"); // print an error message when wrong number of arguments
			break;
	}

	if(base>0 && base<=35){
		rest = nbr;
		while(rest > 0){ //loop to know the number of digit we need
			// we basically convert the number to the desired based and register the number of iteration it took, which is our number of digit
			Q = rest/base;
			remain = rest%base;
			rest = Q;
			i++; // we increment by one at each iteration to know the number of digit we need
		}

		rest=nbr;
		char answer[i+1]; // we declare the array using to print the answer, we need one more place for the null character
		answer[i]='\0'; // we add a null character at the end to fix the length of the array
		i--; // decrement by one to be able to place the digit in the right array position
		while(rest>0){ // loop to convert the number and assign the corresponding digit to the right place in the array
			Q = rest/base;
			remain = rest%base;
			rest = Q;

			if(remain>9){ //Here we convert the number to the corresponding character
			remain+=('A'-10); // numbers bigger than 9 gets converted to letters
			}else{
				remain+='0'; // numbers smaller than 9 get converted to a numbers in the character format
			}
			digit = (char)remain; // takes the character value of the variable remain and puts it in a char variable, otherwise sometimes won't print the answer correctly
			answer[i]=digit; // place the digit in the array
			i--; // decrement by one to place the next digit in the right position
		}

	printf("Base-%d of %d is: %s \n", base, nbr,answer); // print the answer
	}
	return 0;
}
