//Rene Gagnon
//#260801777

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define MAXNAMELENGTH 15

int ptree_counter=0;// counter used in the ptree function

struct StudentRecord // The structure that's going to hold all the data relative to one person, two array for its name and two integers for his id and grade
{
	char FirstNames[MAXNAMELENGTH];
	char LastNames[MAXNAMELENGTH];
	int IDNums;
	int Marks;
	struct StudentRecord* left;
	struct StudentRecord* right;
};

int b_search(unsigned int left,unsigned int right, struct StudentRecord* SRecords[],char *query){ // we receive the left and right index of the array(or part of the array we need to search)
	// algorithm use to search the sorted array
	int mid;
	while(left<right){
		mid=(left+(right-1))/2;// we choose a reference value to witch we are going to compare our last name

		if(strcasecmp(query,SRecords[mid]->LastNames)==0){// if we find the query, we return its index in the pointer array
			return mid;
		}else if(strcasecmp(query,SRecords[mid]->LastNames)<0){// if the last name is smaller than the the middle value of the array, we go and look in the left part of the sorted array
			right=mid;
		}else{ // else we go and look in the right part of the array
			left=mid+1;
		}

	}
return -1; // when the query not found
}

void ptree(struct StudentRecord* root,struct StudentRecord** pSRecords){

	if(root->left!=NULL){// we go all the way down the left side of the root
		ptree(root->left,pSRecords);
	}
	//when we are done with the left, left points to null, so the next element that should be added is the root
	pSRecords[ptree_counter]=root;// we put the pointer to our root in the array of pointers
	ptree_counter++; // we increment our global counter to be able to place the next pointer in the next index of the array
	if(root->right!=NULL){// we go all the way down the right side of the root
		ptree(root->right,pSRecords);
	}
}

struct StudentRecord* addnode(struct StudentRecord* root,struct StudentRecord* data ){//function needs the pointer to the start

	if(root==NULL){// if root points to nowhere, is empty , were going to put our data there!
		root=(struct StudentRecord*)malloc(sizeof(struct StudentRecord)); // we create a memory block (big in off for our structure) and
		// now root points to that particular memory block,
		strcpy(root->FirstNames,data->FirstNames);// we write the data(name,id,mark,etc..) from data to our new node (root)
		strcpy(root->LastNames,data->LastNames);
		root->IDNums=data->IDNums;
		root->Marks=data->Marks;
		root->left=NULL;// both left and right are null because we are the end of the linked list
		root->right=NULL;
		return root;//return a pointer to the node we just added, necessary when adding the first node and also when recursively calling addnode
	}
	else if(strcasecmp(data->LastNames,root->LastNames)<0){// if root is not empty and the data is smaller than what we have in root
		// then we add that data where the left pointer of roots points to,
		root->left=addnode(root->left,data);
	}
	else{
		root->right=addnode(root->right,data);// if the data is bigger we are going to try to add it where the right pointer of roots points to,
		// if that pointer pointed to null, now it will point to our new node
	}
return root;//return a pointer to the node we just added, necessary when adding the first node and also when recursively calling addnode
}


int main(int argc, char*argv[]){

	char Last_Name[MAXNAMELENGTH];//variable to hold the last name of the record in question
	struct StudentRecord* head,*p; // head is the pointer to the beginning of our tree and p is a variable used when adding a new node
	struct StudentRecord temp; // temporary struct to hold the data read from the databases
	head=NULL;// at first the tree is empty so head points to nothing
	int counter=0;// counter to know the number of records we read from the databases
	int result;// use to hold the result of our binary search
	switch(argc){
				case 4: //if we have 4 arguments from the user
					sscanf(argv[3],"%s",Last_Name); // get the last name from the user
					break;
				default:
					printf("Wrong number of arguments \n"); // print an error message when wrong number of arguments
					break;
			}




	FILE * NamesIDsDataFile; // open both database
	FILE * MarksDataFile;
	if((NamesIDsDataFile = fopen(argv[1], "r")) == NULL){
			printf("Can't read from file %s\n", argv[1]);
			exit(1);
		}
	if((MarksDataFile = fopen(argv[2], "r")) == NULL){
			printf("Can't read from file %s\n", argv[2]);
			exit(1);
		}

	while(fscanf(NamesIDsDataFile,"%s%s%d",&temp.FirstNames,&temp.LastNames,&temp.IDNums) != EOF && fscanf(MarksDataFile,"%d",&temp.Marks) != EOF){//we read all the data and put it in a temporary StudentRecord
		if(counter==0){
			head=addnode(head,&temp); // we create the first node and copy the data from temp to that first node

		}
		if(counter!=0){
			p=addnode(head,&temp); // we create a new node and copy the data from temp to it, from now on were are going to use head as the start of our tree
		}
		counter++; // we increment a counter to know how many StudentRecord we have
	}
	fclose(NamesIDsDataFile);//close both database
	fclose(MarksDataFile);
	struct StudentRecord**pSRecords; // we create a pointer to a pointer, this will point to the start of the array of pointer
	pSRecords=(struct StudentRecord**)malloc(counter*sizeof(struct StudentRecord*)); // we allocate enough memory so that the pointer pSRecord points to the start of an array which has the right size
	ptree(head,pSRecords);// we call our ptree function, we give it a pointer to the start of the btree and a pointer to our array of pointers so it can place the pointers in there
	result=b_search(0,counter,pSRecords,Last_Name);// we look for the last name in the sorted array of pointers
	if(result!=-1){ //we print the record if the last name match
			printf("The following record was found:\n");
			printf("Name: %s %s \nStudent ID: %d \nStudent Grade: %d",pSRecords[result]->FirstNames,pSRecords[result]->LastNames,pSRecords[result]->IDNums,pSRecords[result]->Marks);
		}else if(result==-1){// if no match
			printf("No record found for student with last name %s.",Last_Name);// print this
		}
	return EXIT_SUCCESS;
}
