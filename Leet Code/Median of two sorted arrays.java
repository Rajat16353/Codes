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
