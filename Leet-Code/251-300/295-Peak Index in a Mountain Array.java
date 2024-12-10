// You are given an integer mountain array arr of length n where the values increase to a peak element and then decrease.

// Return the index of the peak element.

// Your task is to solve it in O(log(n)) time complexity.

 

// Example 1:

// Input: arr = [0,1,0]

// Output: 1

// Example 2:

// Input: arr = [0,2,1,0]

// Output: 1

// Example 3:

// Input: arr = [0,10,5,2]

// Output: 1

 

// Constraints:

// 3 <= arr.length <= 105
// 0 <= arr[i] <= 106
// arr is guaranteed to be a mountain array.

class Solution {
    private boolean isMountainPeek(int[] arr, int index) {
        if (index > 0 && index < arr.length - 1) {
            if (arr[index] > arr[index - 1] && arr[index] > arr[index + 1]) return true;
        } else {
            if (index > 0 && arr[index] > arr[index - 1]) return true;
            if (index < arr.length - 1 && arr[index] > arr[index + 1]) return true;
        }
        return false;
    }
    private int binarySearch(int[] arr, int low, int high) {
        int mid = (low + high)/2;

        if (isMountainPeek(arr, mid)) {
            return mid;
        } else if (mid > 0 && arr[mid] < arr[mid - 1]) {
            return binarySearch(arr, low, mid);
        } else {
            return binarySearch(arr, mid + 1, high);
        }
    }

    public int peakIndexInMountainArray(int[] arr) {
        return binarySearch(arr, 0, arr.length - 1);
    }
}