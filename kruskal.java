/*
In Kruskal’s algorithm, sort all edges of the given graph in increasing order.
Then it keeps on adding new edges and nodes in the MST if the newly added edge does not form a cycle.
It picks the minimum weighted edge at first and the maximum weighted edge at last.
Thus we can say that it makes a locally optimal choice in each step in order to find the optimal solution. Hence this is a Greedy Algorithm.

STEP FOR KRUSKAL ALGO. FOR FINDING MST
STEP 1:-Sort all the edges in non-decreasing order of their weight. 
STEP 2:-Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If the cycle is not formed, include this edge. Else, discard it. 
STEP 3:-Repeat step#2 until there are (V-1) edges in the spanning tree.
STEP 4:-uses the Union-Find algorithm to detect cycles. 

ALGORITHMS:-
MST-Kruskal(G,W)
1.A<-Q
2.for each vertex v belong to V[G]
3.   do MAKE-SET(v)
4.  sort the edges of E into non decreasing order by weight w
5.  for each edge (u,v) belong to E ,taken in non decreasing order by weight
6.  do if FIND-SET(U) not equal to FIND-SET(V)
7.  then A<-A union {(u,v)}
8.  union(u,v)   
9.   return A

*/
// CODE IMPLIMENTATION
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
 
public class KruskalsMST {
 
    // Defines edge structure
    static class Edge {
        int src, dest, weight;
 
        public Edge(int src, int dest, int weight)
        {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
 
    // Defines subset element structure
    static class Subset {
        int parent, rank;
 
        public Subset(int parent, int rank)
        {
            this.parent = parent;
            this.rank = rank;
        }
    }
 
    // Starting point of program execution
    public static void main(String[] args)
    {
        int V = 4;
        List<Edge> graphEdges = new ArrayList<Edge>(
            List.of(new Edge(0, 1, 10), new Edge(0, 2, 6),
                    new Edge(0, 3, 5), new Edge(1, 3, 15),
                    new Edge(2, 3, 4)));
 
        // Sort the edges in non-decreasing order
        // (increasing with repetition allowed)
        graphEdges.sort(new Comparator<Edge>() {
            @Override public int compare(Edge o1, Edge o2)
            {
                return o1.weight - o2.weight;
            }
        });
 
        kruskals(V, graphEdges);
    }
 
    // Function to find the MST
    private static void kruskals(int V, List<Edge> edges)
    {
        int j = 0;
        int noOfEdges = 0;
 
        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[V];
 
        // Allocate memory for results
        Edge results[] = new Edge[V];
 
        // Create V subsets with single elements
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset(i, 0);
        }
 
        // Number of edges to be taken is equal to V-1
        while (noOfEdges < V - 1) {
 
            // Pick the smallest edge. And increment
            // the index for next iteration
            Edge nextEdge = edges.get(j);
            int x = findRoot(subsets, nextEdge.src);
            int y = findRoot(subsets, nextEdge.dest);
 
            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }
 
            j++;
        }
 
        // Print the contents of result[] to display the
        // built MST
        System.out.println(
            "Following are the edges of the constructed MST:");
        int minCost = 0;
        for (int i = 0; i < noOfEdges; i++) {
            System.out.println(results[i].src + " -- "
                               + results[i].dest + " == "
                               + results[i].weight);
            minCost += results[i].weight;
        }
        System.out.println("Total cost of MST: " + minCost);
    }
 
    // Function to unite two disjoint sets
    private static void union(Subset[] subsets, int x,
                              int y)
    {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);
 
        if (subsets[rootY].rank < subsets[rootX].rank) {
            subsets[rootY].parent = rootX;
        }
        else if (subsets[rootX].rank
                 < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        }
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }
 
    // Function to find parent of a set
    private static int findRoot(Subset[] subsets, int i)
    {
        if (subsets[i].parent == i)
            return subsets[i].parent;
 
        subsets[i].parent
            = findRoot(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
}
// Thi
