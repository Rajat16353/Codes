// Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].

 

// Example 1:

// Input: nums = [5,2,6,1]
// Output: [2,1,1,0]
// Explanation:
// To the right of 5 there are 2 smaller elements (2 and 1).
// To the right of 2 there is only 1 smaller element (1).
// To the right of 6 there is 1 smaller element (1).
// To the right of 1 there is 0 smaller element.
// Example 2:

// Input: nums = [-1]
// Output: [0]
// Example 3:

// Input: nums = [-1,-1]
// Output: [0,0]
 

// Constraints:

// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sorted = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            int idx = bisectLeft(sorted, num);
            res.add(idx);
            sorted.add(idx, num);
        }

        Collections.reverse(res);
        return res;
    }

    private int bisectLeft(List<Integer> list, int val) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high)/2;

            if (list.get(mid) >= val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}