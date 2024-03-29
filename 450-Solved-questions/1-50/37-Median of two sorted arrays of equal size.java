// Given two sorted arrays, a[] and b[], the task is to find the median of these sorted arrays, 
// in O(log n + log m) time complexity, when n is the number of elements in the first array, 
// and m is the number of elements in the second array. 

// Input: ar1[] = {-5, 3, 6, 12, 15}
//         ar2[] = {-12, -10, -6, -3, 4, 10}
// Output : The median is 3.
// Explanation : The merged array is :
//         ar3[] = {-12, -10, -6, -5 , -3,
//                  3, 4, 6, 10, 12, 15},
//        So the median of the merged array is 3

// Input: ar1[] = {2, 3, 5, 8}
//         ar2[] = {10, 12, 14, 16, 18, 20}
// Output : The median is 11.
// Explanation : The merged array is :
//         ar3[] = {2, 3, 5, 8, 10, 12, 14, 16, 18, 20}
//         if the number of the elements are even, 
//         so there are two middle elements,
//         take the average between the two :
//         (10 + 12) / 2 = 11.

public class GFG {

	static double Median(int[] A, int[] B)
	{
		int totalLen = (a.length + b.length);
        int half = totalLen / 2;
        int l = 0, r = a.length-1;
        while(true) {
            int i = r >= 0 ? (l + r)/ 2 : (l + r)/ 2 - 1;
            int j = half - i - 2;
            
            double aLeft = i >= 0 ? a[i] : Double.NEGATIVE_INFINITY;
            double aRight = (i+1) < a.length ? a[i+1] : Double.POSITIVE_INFINITY;
            double bLeft = j >= 0 ? b[j] : Double.NEGATIVE_INFINITY;
            double bRight = (j+1) < b.length ? b[j+1] : Double.POSITIVE_INFINITY;
            
            if (aLeft <= bRight && bLeft <= aRight) {
                if (totalLen % 2 != 0)
                    return Math.min(aRight, bRight);
                else
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2;
            }
            else if (aLeft > bRight)
                r = i - 1;
            else
                l = i + 1;
        }
	}

// Driver code
	public static void main(String[] args) {
		int[] arr1 = { -5, 3, 6, 12, 15 };
		int[] arr2 = { -12, -10, -6, -3, 4, 10 };
		System.out.println("Median of the two arrays are");
		System.out.println(Median(arr1, arr2));
	}
}