package timetable;
import java.util.*;
public class Node {

	ArrayList<Node> map = new ArrayList<Node>();
	int numberOfStudents;
	String name;
	int pos;
	public String toString(){
		return "name: " + this.name + " strength: " + this.numberOfStudents;
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
	        temp.pos = i;
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
	                temp.map.add(nodearray[j]);
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
	                int tem = nodearray[j].pos;
	                nodearray[j].pos = nodearray[j+1].pos;
	                nodearray[j+1].pos = tem;
	            }
	        }
	    }
	    for (i=0;i<n;i++){
	    	System.out.println("index is " + nodearray[i].pos);
	        System.out.println(nodearray[i].numberOfStudents);
	        System.out.println("no of nodes in common is");
	        for(Node obj:nodearray[i].map){
	        	System.out.println(obj);
	        }
	    }
	    
	
	}

}
