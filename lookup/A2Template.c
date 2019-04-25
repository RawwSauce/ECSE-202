/*

 Assignment 2 ECSE202
 This program assumes that
 a names and IDs data file (location and name) is passed as the first argument
 a marks data files is passed as the second argument
 an ID number is passed as the third argument
 */


#define MAXRECORDS 100
#define MAXNAMELENGTH 15
#include <stdio.h>
#include <stdlib.h>
//Define structure to hold student data
struct StudentRecord // global from of each structure, 4 elements
{
	char FirstNames[MAXNAMELENGTH];
	char LastNames[MAXNAMELENGTH];
	int IDNums;
	int Marks;
};

void swap(char*a, char*b){
	char temp=*a;
	*a=*b;
	*b=temp;
}

void quicksort(unsigned int left, unsigned int right){
	unsigned int i, mid;
	char pivot;
	if(left>=right){
		return;
	}
	swap(arr+left,arr+(left+right)/2);//we take the middle value and swap it with the first one the left one
	pivot=arr[mid=left];// the pivot is now the first value

	for(i=left+1;i<=right;i++){ // we compare each value to the pivot
		if(strcmp(arr[i],pivot)==-1){ // if the value we are at, the i value is smaller than the pivot
			swap(arr + ++mid,arr+i); //we put the value the position right after the pivot, and then the next one and so on
		}
	}
	swap(arr+left,arr+mid); // mid is the point where are values to the left of that point are smaller than the pivot

	if(mid>left){ // if mid is bigger than left means were not done
		quicksort(left, mid-1);
	}
	if(mid<right){ // if mid is smaller than right were not done
		quicksort(mid+1,right);
	}
}



int main(int argc, char * argv[]) {

	struct StudentRecord SRecords[MAXRECORDS];// we declare an array of 100 spaces filled with structures
    	int numrecords, nummarks, recordnum;
	

	//Read in Names and ID data
	FILE * NamesIDsDataFile;
	if((NamesIDsDataFile = fopen(argv[1], "r")) == NULL){
		printf("Can't read from file %s\n", argv[1]);
		exit(1);
	}
	
	numrecords=0;
    	while (fscanf(NamesIDsDataFile,"%s%s%d",&(SRecords[numrecords].FirstNames[0]),
		      				&(SRecords[numrecords].LastNames[0]),
		      				&(SRecords[numrecords].IDNums)) != EOF) {
	  numrecords++;
 	}
	  
	fclose(NamesIDsDataFile);
	
	//Read in marks data
	FILE * MarksDataFile;
	if((MarksDataFile = fopen(argv[2], "r")) == NULL){
		printf("Can't read from file %s\n", argv[2]);
		exit(1);
	}
	nummarks=0;
	while(fscanf(MarksDataFile,"%d",&(SRecords[nummarks].Marks)) != EOF) {
	    nummarks++;
	}
	
	fclose(MarksDataFile);
	
	//Print out data as read in
	for(recordnum=0;recordnum<numrecords;recordnum++){
		printf("%s %s %d %d\n",SRecords[recordnum].FirstNames,SRecords[recordnum].LastNames,SRecords[recordnum].IDNums, SRecords[recordnum].Marks);
	}
	printf("A total of %d records printed.\n",numrecords);

	return EXIT_SUCCESS;
}
