/*
SPANNING TREE:- if total no of vertex is V then total no of edges is V-1 ND connected undirected graph

MINIMUM SPANNING TREE:- we have to find out minimum weighted spanning tree among all the spanning tree

PRIM ALGO STEP:
STEP1:- all the node weight are w except source 
STEP2:- we need to select source 1st
STEP3:- select node with minimum weight (start at source )
STEP4:-include selected node in set MST
STEP5:- compute all adjacent edges and repeat step 1 ,2,3 unless all the vertices are included in MST

ALGORITHMS:-
MST-Prim(G,w,r)
1. for each u belong V[G]
2.     do key[u]<-infinity
3.     pi[u]<-NIL
4.     kry[r]<-0
5.    Q<-V[G]
6.   while Q not equal to 0
7.    do u <-Extract-MIN(Q)
8.    for each v belong to Adj[u]
9.    do if v belong to Q and W(u,v)<key[v]
10.   then pi[v]<-u
11. key[v]<-w(u,v)
*/

// CODE:- 
public class prim { 
     private static final int countOfVertices = 9;   
    
     // finding the vertex v that has minimum key-value and that is not added MST yet  
     int findMinKeyVertex(int keys[], Boolean setOfMST[])   
     {   
        
         int minimum_index = -1;   
         int minimum_value = Integer.MAX_VALUE;  
           
         //iterate over all vertices to find minimum key-value vertex  
         for (int vertex = 0; vertex < countOfVertices; vertex++)   
             if (setOfMST[vertex] == false && keys[vertex] < minimum_value) {   
                 minimum_value = keys[vertex];   
                 minimum_index = vertex;   
             }   
     
         return minimum_index;   
     }     
     void showMinimumSpanningTree(int mstArray[], int graphArray[][])   
     {   
         System.out.println("Edge \t\t Weight");   
         for (int j = 1; j < countOfVertices; j++)   
             System.out.println(mstArray[j] + " <-> " + j + "\t \t" + graphArray[j][mstArray[j]]);   
     }   
      
     void designMST(int graphArray[][])   
     {   
         //countOfVertices for storing the MST  
         int mstArray[] = new int[countOfVertices];   
     
         // create keys[] array for selecting an edge having minimum weight in cut ie for parent 
         int keys[] = new int[countOfVertices];   
     
         // create setOfMST array of type boolean for representing the set of vertices included in MST   
         Boolean setOfMST[] = new Boolean[countOfVertices];   
     
         // set the value of the keys to infinite   
         for (int j = 0; j < countOfVertices; j++) {   
             keys[j] = Integer.MAX_VALUE;   
             setOfMST[j] = false;   
         }     
         keys[0] = 0; // it select as first vertex   
         mstArray[0] = -1; // set first value of mstArray to -1 to make it root of MST   
     
         // The vertices in the MST will be equal to the countOfVertices   
         for (int i = 0; i < countOfVertices - 1; i++) {   
             // select the vertex having minimum key and that is not added in the MST yet from the set of vertices   
             int edge = findMinKeyVertex(keys, setOfMST);   
     
             // Add the selected minimum key vertex to the setOfMST   
             setOfMST[edge] = true;   
     
             // change the key value and the parent index of all the adjacent vertices of the selected vertex. The vertices that are not yet included in Minimum Spanning Tree are only considered.   
             for (int vertex = 0; vertex < countOfVertices; vertex++)   
     
                 // The value of the graphArray[edge][vertex] is non zero only for adjacent vertices of m setOfMST[vertex] is false for vertices not yet included in Minimum Spanning Tree   
                 // when the value of the graphArray[edge][vertex] is smaller than key[vertex], we update the key  
                 if (graphArray[edge][vertex] != 0 && setOfMST[vertex] == false && graphArray[edge][vertex] < keys[vertex]) {   
                     mstArray[vertex] = edge;   
                     keys[vertex] = graphArray[edge][vertex];   
                 }   
         }   
         showMinimumSpanningTree(mstArray, graphArray);   
     }   
 
  

public static void main(String[] args) {
    prim mst = new prim();   
        int graphArray[][] = new int[][]{{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },   
                    { 4, 0, 8, 0, 0, 0, 0, 11, 0 },   
                    { 0, 8, 0, 7, 0, 4, 0, 0, 2 },   
                    { 0, 0, 7, 0, 9, 14, 0, 0, 0 },   
                    { 0, 0, 0, 9, 0, 10, 0, 0, 0 },  
                    { 0, 0, 4, 14, 10, 0, 2, 0, 0 },  
                    { 0, 0, 0, 0, 0, 2, 0, 1, 6 },  
                    { 8, 11, 0, 0, 0, 0, 1, 0, 7 },  
                    { 0, 0, 2, 0, 0, 0, 6, 7, 0 }};   
    
        // Print the Minimum Spanning Tree solution   
        mst.designMST(graphArray);   
}

}
