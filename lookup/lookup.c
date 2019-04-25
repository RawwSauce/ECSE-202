//Rene Gagnon
//#260801777
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define MAXRECORDS 100
#define MAXNAMELENGTH 15

struct StudentRecord // The structure form that's going to hold all the data relative to one person, two array for its name and two integers for his id and grade
{
	char FirstNames[MAXNAMELENGTH];
	char LastNames[MAXNAMELENGTH];
	int IDNums;
	int Marks;
};

int b_search(unsigned int left,unsigned int right, struct StudentRecord* SRecords[],char *query){
	// algorithm use to search the sorted array
	int mid;
	while(left<right){
		mid=(left+(right-1))/2;

		if(strcmp(query,SRecords[mid]->LastNames)==0){
			return mid;
		}else if(strcmp(query,SRecords[mid]->LastNames)<0){// if the last name is smaller than the the middle value of the array, we go and look in the left part of the sorted array
			right=mid;
		}else{ // else we go and look in the right part of the array
			left=mid+1;
		}

	}
return -1; // when the query not found
}

void swap(int a, int b, struct StudentRecord *SRecords[]){ // swap function to swap to given pointers.
	struct StudentRecord* temp;
	temp=SRecords[a];
	SRecords[a]=SRecords[b];
	SRecords[b]=temp;
}

void quicksort(int left, int right, struct StudentRecord **SRecords){ // sorting algorithm, takes two the first index and last index of the array
	unsigned i, mid;
	struct StudentRecord* pivot; // we defined the pivot as a pointer, because we have an array of pointers
	if(left>=right){ // if left is equal or greater than right we have nothing to sort
		return; // we get out of the function
	}
	swap(left,((left+right)/2),SRecords); //we swap the pivot with the complete left pointer of the array
	pivot=SRecords[mid=left];
	for(i=left+1;i<=right;i++){ // we compare go through every value left of the pivot until the right
		if(strcmp(SRecords[i]->LastNames,pivot->LastNames)<0){ // compare a string with our pivot
			swap(++mid,i,SRecords); // if the string is smaller we swap it
		}
	}
	swap(left,mid,SRecords);
	if(mid>left){ // if mid is bigger than left means were not done
		quicksort(left, mid-1,SRecords);
	}
	if(mid<right){ // if mid is smaller than right were not done
		quicksort(mid+1,right,SRecords);
	}


}

int main(int argc, char*argv[]){

	char Last_Name[MAXNAMELENGTH]; //array to hold the last name entered by the user
	int i;
	int result; // variable to hold the result of our binary search
	int numrecords, nummarks, recordnum;
	struct StudentRecord SRecords[MAXRECORDS]; // initialize an array of structure, each containing 4 info on the person
	struct StudentRecord* pSRecords[MAXRECORDS]; // initialize an array of pointers which points to each individual struct of the original array of struct


	switch(argc){
			case 4: //if we have 4 arguments from the user
				sscanf(argv[3],"%s",Last_Name); // get the last name from the user
				break;
			default:
				printf("Wrong number of arguments \n"); // print an error message when wrong number of arguments
				break;
		}

	//Read in Names and ID data
	FILE * NamesIDsDataFile;
	if((NamesIDsDataFile = fopen(argv[1], "r")) == NULL){
		printf("Can't read from file %s\n", argv[1]);
		exit(1);
	}

	numrecords=0;
	while (fscanf(NamesIDsDataFile,"%s%s%d",&(SRecords[numrecords].FirstNames[0]),&(SRecords[numrecords].LastNames[0]),&(SRecords[numrecords].IDNums)) != EOF) {
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

	for(i=0;i<numrecords;i++){
		pSRecords[i]=&SRecords[i]; //we fill the array of pointers with pointers to location of every struct from the original array
		}

	quicksort(0,numrecords-1,pSRecords); //we call the function to sort the array of pointers

	result = b_search(0,numrecords,pSRecords,Last_Name); // we look for through the sorted array for a particular last name
	if(result!=-1){ //we print the record if the last name match
		printf("The following record was found:\n");
		printf("Name: %s %s \nStudent ID: %d \nStudent Grade: %d",pSRecords[result]->FirstNames,pSRecords[result]->LastNames,pSRecords[result]->IDNums,pSRecords[result]->Marks);
	}else if(result==-1){// if no match
		printf("No record was found for student with last name %s",Last_Name);// print this
	}
	return EXIT_SUCCESS;
}


