/* 
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
Example 3:

Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000
Example 4:

Input: nums1 = [], nums2 = [1]
Output: 1.00000
Example 5:

Input: nums1 = [2], nums2 = []
Output: 2.00000
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //System.out.println(""+nums1[0]);
        int arr[]=new int[nums1.length+nums2.length],i=0,j=0,k=0;
        double median;
        while(i<nums1.length && j<nums2.length)
        {
            if(nums1[i] < nums2[j])
            {
                arr[k++] = nums1[i];
                i++;
            }
            else if(nums1[i] > nums2[j])
            {
                arr[k++] = nums2[j];
                j++;
            }
            else
            {
                arr[k++] = nums1[i++];
                arr[k++] = nums2[j++];
            }
        }
        while(i < nums1.length)
        {
            arr[k++] = nums1[i++];
        }
        while(j < nums2.length)
        {
            arr[k++] = nums2[j++];
        }
        //System.out.println(""+arr.length);
        if(arr.length%2 == 0 )
        {
            median = (arr[arr.length/2-1]+arr[arr.length/2])/2.0;
            //System.out.println(""+arr[arr.length/2-1]+" " +arr[arr.length/2]);
        }
        else
        {
            median = arr[arr.length/2];
        }
        return median;
    }
}


// Binar search

class Solution {
    public double findMedianSortedArrays(int[] a, int[] b) {
        int totalLen = (a.length + b.length);
        int half = totalLen / 2;
        if (a.length > b.length) {
            int[] temp = new int[b.length];
            temp = b;
            b = a;
            a = temp;
        }   
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
}