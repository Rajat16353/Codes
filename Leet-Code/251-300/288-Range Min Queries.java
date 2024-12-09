// given

// nums = {8, 2, 5, 1, 4, 5, 3, 9, 6, 10};

// Find max for given ranges

// queries: [[1,3], [4, 9], [3,8]]

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{8, 2, 5, 1, 4, 5, 3, 9, 6, 10};
        int[] query = new int[]{4,9};

        Solution solution = new Solution();
        System.out.println("maximum is: " + solution.minInRange(nums, query));
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    int[] nums;
    int n;
    int[] segT;

    private void buildMinSegmentTree(int index, int low, int high) {
        if (low == high) {
            segT[index] = nums[low];
            return;
        }
        int mid = (low + high)/2;
        buildMinSegmentTree(2 * index + 1, low, mid);
        buildMinSegmentTree(2 * index + 2, mid + 1, high);

        segT[index] = Math.min(segT[2 * index + 1], segT[2 * index + 2]);
    }

    private int minInRange(int index, int low, int high, int queryL, int queryR) {
        if (queryL <= low && high <= queryR) {
            return segT[index];
        }

        if (queryL > high || queryR < low) return Integer.MAX_VALUE;

        int mid = (low + high)/2;
        int left = minInRange(2 * index + 1, low, mid, queryL, queryR);
        int right = minInRange(2 * index + 2, mid + 1, high, queryL, queryR);

        return Math.min(left, right);
    }

    public int minInRange(int[] nums, int[] query) {
        this.nums = nums;
        this.n = nums.length;
        segT = new int[4 * n];

        buildMinSegmentTree(0, 0, n - 1);
        System.out.println(Arrays.toString(segT));

        return minInRange(0, 0, n - 1, query[0], query[1]);
    }
}
