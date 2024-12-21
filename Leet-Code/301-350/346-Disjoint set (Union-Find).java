// Given an array A[] that stores all number from 1 to N (both inclusive and sorted) and K queries.

// The task is to do the following operations on array elements :

// 1. UNION X Z : Perform union of X and Z i.e. parent of Z will become the parent of X.
// 2. FIND X: Find the ultimate parent of X and print it.

// Note: Initially all are the parent of themselves.The ultimate parent is the topmost node such that par[node]=node.

// Input:
// N = 5, K = 4
// queries[] = {{find 4},
//              {find 1},
//              {unionSet 3 1},
//              {find 3}}
// Output:
// 4 1 1
// Explanation:
// 1. The parent of 4 is 4. Hence the output is 4.
// 2. The parent of 1 is 1. Hence the output is 1.
// 3. After performing unionSet 3 1, parent of 3 becomes 1,
//    since, parent of 1 is currently 1 itself.
// 4. The parent of 3 is now 1. Hence, the output is 1.

// Your Task:  
// You don't need to read input or print anything. Your task is to complete the functions- find() which takes an array A[] and an integer X as an input parameter and return the parent of X and the function unionSet() which takes an array A[] and two integers X and Z and performs the union of X and Z.

// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(1)

// Constraints:
// 1 <= N, K <= 100

//{ Driver Code Starts
import java.util.*;
class Disjoint{
	public static void main(String[] args){
		Scanner sc=new Scanner (System.in);
		int t=sc.nextInt();
		while(t-->0){
			int n=sc.nextInt();
			int[] a=new int[n+1];
			for(int i=1;i<=n;i++)
				a[i]=i;
			int k=sc.nextInt();
			GfG g=new GfG();
			for(int i=0;i<k;i++){
				String s=sc.next();
				if(s.equals("UNION")){
					int x=sc.nextInt();
					int z=sc.nextInt();
					g.unionSet(a,x,z);
				}
				else{
					int x=sc.nextInt();
					int parent=g.find(a,x);
					System.out.print(parent+" ");
				}
			}
			System.out.println();
		
System.out.println("~");
}
	}
}
// } Driver Code Ends


/*Complete the function below*/
class GfG
{
	int find(int a[],int x) {
        if (a[x] == x) {
            return x;
        }
        
        return a[x] = find(a, a[x]);
	}
	void unionSet(int a[],int x,int z) {
        int lx = find(a, x);
        int lz = find(a, z);
        
        if (lx != lz) {
            a[lx] = lz;
        }
	}
}
