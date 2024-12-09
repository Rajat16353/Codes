// given

// nums = {8, 2, 5, 1, 4, 5, 3, 9, 6, 10};

// Find max for given ranges

// queries: [[1,3], [4, 9], [3,8]]

public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{8, 2, 5, 1, 4, 5, 3, 9, 6, 10};
        int[] query = new int[]{2,6};

        Solution solution = new Solution();
        System.out.println("maximum is: " + solution.maxInRange(nums, query));
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    int[] nums;
    int n;
    int[] segT;

    private void buildSegmentTree(int index, int low, int high) {
        if (low == high) {
            segT[index] = nums[low];
            return;
        }
        int mid = (low + high)/2;
        buildSegmentTree(2 * index + 1, low, mid);
        buildSegmentTree(2 * index + 2, mid + 1, high);

        segT[index] = Math.max(segT[2 * index + 1], segT[2 * index + 2]);
    }

    private int maxInRange(int index, int low, int high, int queryL, int queryR) {
        if (queryL <= low && high <= queryR) {
            return segT[index];
        }

        if (queryL > high || queryR < low) return Integer.MIN_VALUE;

        int mid = (low + high)/2;
        int left = maxInRange(2 * index + 1, low, mid, queryL, queryR);
        int right = maxInRange(2 * index + 2, mid + 1, high, queryL, queryR);

        return Math.max(left, right);
    }

    public int maxInRange(int[] nums, int[] query) {
        this.nums = nums;
        this.n = nums.length;
        segT = new int[4 * n];

        buildSegmentTree(0, 0, n - 1);

        return maxInRange(0, 0, n - 1, query[0], query[1]);
    }
}
