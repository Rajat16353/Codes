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
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int low = 0, high = n1;
        int left = (n1 + n2 + 1)/2;
        int n = n1 + n2;

        while (low <= high) {
            int mid1 = (low + high)/2;
            int mid2 = left - mid1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (mid1 < n1) r1 = nums1[mid1];
            if (mid2 < n2) r2 = nums2[mid2];
            if (mid1 - 1 >= 0) l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0) l2 = nums2[mid2 - 1];

            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 != 0) return Math.max(l1, l2);
                return (Math.max(l1, l2) + Math.min(r1, r2))/2.0;
            } else if (l1 > r2) {
                high = mid1 - 1;
            } else {
                low = mid1 + 1;
            }
        }
        return 0;
    }
}