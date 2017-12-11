package timetable;
import java.util.*;
public class Node {
	int mark = 0; //a variable to check wheter if a particular node has been colored or not
	int semester;
	String branch;
	int color;	//contains the color of the node
	ArrayList<Node> map = new ArrayList<Node>();	//contains the list of nodes with which a particular node is mapped
	ArrayList<Integer> nocolor = new ArrayList<Integer>();	//contains the list of colors with which a particular node cannot be colored
	int numberOfStudents;
	String name;
	public String toString(){
		return "name: " + this.name + " strength: " + this.numberOfStudents;
	}
	
	public static void copyNodes(Node n1, Node n2){			
		n1.name = n2.name;
		n1.numberOfStudents = n2.numberOfStudents;
		n1.map = (ArrayList<Node>)n2.map.clone();
	}
	
	public static void swapNodes(Node n1, Node n2){
		Node temp = new Node();
		Node.copyNodes(temp, n1);
		Node.copyNodes(n1,n2);
		Node.copyNodes(n2, temp);
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n,i,j,el,sem,tcolor;
	    String name,branch;
	    n = input.nextInt();
	    int[][] data = new int[n][n];
	    Node[] nodearray =  new Node[n];
	    for (i=0;i<n;i++){
	        Node temp = new Node();
	        nodearray[i] = temp;
	    }
	    for (i=0;i<n;i++){
	        Node temp = nodearray[i];
	        for (j=0;j<n;j++){
	            el = input.nextInt();
	            if (el != 0){
	                if (i == j){
	                    temp.numberOfStudents = el;
	                }
	                else{
	                	temp.map.add(nodearray[j]);
	                }
	            }
	            data[i][j] = el;
	        }
	    }
	    for(i=0;i<n;i++){		//taking input of the names of the courses followed by it's branch and semester must improve it in such a way that it automatically gets branch and semester from the course name itself
	    	Node temp;
	        name = input.next();
	        sem = input.nextInt();
	        branch = input.next();
	        temp = nodearray[i];
	        temp.name = name;
	        temp.branch = branch;
	        temp.semester = sem;
	    }
	    for (i=0;i<n;i++){	
	        for (j=0;j<n-1;j++){
	            if (nodearray[j].numberOfStudents < nodearray[j+1].numberOfStudents){
	                Node.swapNodes(nodearray[j], nodearray[j+1]);
	            }
	        }
	    }
	    for (i=0;i<n;i++){
	        System.out.println("no of nodes in common for "+ nodearray[i].name+" is:");
	        for(Node obj:nodearray[i].map){
	        	System.out.println(obj);
	        }
	    }
	    System.out.println("Enter the number of days we have");
	    no = input.nextInt();
	    System.out.println("Enter the maximum number of students who can have an exam in one session");
	    max = input.nextInt();
	    for (i=0;i<no;i++){		//algorithm starts here. We are iterating through all the days in this loop.
	    	sessionno = 2;
	    	while (sessionno > 0){	//We are iterating through all sessions over here.
	    		sum = 0;
		    	for (j=0;j<n;j++){	//For each day and session we go through all the nodes in this loop.
		    		if (nodearray[j].mark == 0){	//check if the node is colored or not
		    			if (nodearray[j].numberOfStudents + sum <= max){	//check whether if the max-capacity is reached for a session
		    				boolean flag = true;
		    				for (l=0;l<size(nodearray[j].nocolor);l++){		//check whether if the node can be colored with that color or not
		    					if (nodearray[j].nocolor[l] == i*10+sessionno){
		    						flag = false;
		    						break;
		    					}
		    				}
		    				if (flag){
		    					nodearray[j].mark = 1;
		    					nodearray[j].color = i*10+sessionno;
		    					sum = sum + nodearray[j].numberOfStudents;
		    					for (k=0;k<size(nodearray[j].map);k++){		//update the nocolor arraylist with this color for all the adjacent nodes.
		    						nodearray[j].map[k].nocolor.add(i*10+sessionno);
		    						if (nodearray[k].branch == nodearray[j].branch && nodearray[k].semester == nodearray[j].semester){ 	//not sure how to write an explanation for this :( we need to efficiensize this. I think it is better to use a set instead of an array list as if a particular color is inserted into the nocolor list we need not again add it. As size of data increases so does the no of duplicate elements in the arraylist and the time for execution.
										if (nodearray[j].semester == 1){
											tcolor = 2;
										}
										else{
											tcolor = 1;
										}
										for (s=0;s<no;s++){
											nodearray[k].nocolor.add(s*10+tcolor);
										}
									}
		    					}	
		    				}
		    			}
		    		}
		    	}
		    	sessionno = sessiono - 1;
		    }
	    }
	    for (i=0;i<n;i++){	//printing the output. Need to improve this also.
	    	System.out.println("Course Name " + nodearray[i].name + " Number of Students in this course are " + nodearray[i].numberOfStudents + " color of the course is " + nodearray[i].color);
	    }
	}
}
