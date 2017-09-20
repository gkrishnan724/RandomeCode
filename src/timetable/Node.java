package timetable;
import java.util.*;
public class Node {

	ArrayList<Node> map = new ArrayList<Node>();
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
		int n,i,j,el;
	    String name;
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
	    for(i=0;i<n;i++){
	    	Node temp;
	        name = input.next();
	        temp = nodearray[i];
	        temp.name = name;
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
	    
	
	}

}
