// Given an integer array nums, handle multiple queries of the following types:

// Update the value of an element in nums.
// Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
// Implement the NumArray class:

// NumArray(int[] nums) Initializes the object with the integer array nums.
// void update(int index, int val) Updates the value of nums[index] to be val.
// int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

// Example 1:

// Input
// ["NumArray", "sumRange", "update", "sumRange"]
// [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
// Output
// [null, 9, null, 8]

// Explanation
// NumArray numArray = new NumArray([1, 3, 5]);
// numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
// numArray.update(1, 2);   // nums = [1, 2, 5]
// numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 

// Constraints:

// 1 <= nums.length <= 3 * 104
// -100 <= nums[i] <= 100
// 0 <= index < nums.length
// -100 <= val <= 100
// 0 <= left <= right < nums.length
// At most 3 * 104 calls will be made to update and sumRange.

class NumArray {
    int[] nums;
    int[] segT;
    int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        n = nums.length;
        segT = new int[4*n];
        createSegmentTree(0, 0, n - 1);
    }
    
    public void update(int index, int val) {
        update(0, 0, n - 1, index, val);
    }
    
    public int sumRange(int left, int right) {
        return sumRange(0, 0, n - 1, left, right);
    }

    private void createSegmentTree(int index, int low, int high) {
        if (low == high) {
            segT[index] = nums[low];
            return;
        }

        int mid = (low + high) / 2;
        createSegmentTree(2 * index + 1, low, mid);
        createSegmentTree(2 * index + 2, mid + 1, high);

        segT[index] = segT[2 * index + 1] + segT[2 * index + 2];
    }

    private int sumRange(int index, int low, int high, int left, int right) {
        if (left > high || right < low) {
            return 0;
        }

        if (left <= low && high <= right) {
            return segT[index];
        }

        int mid = (low + high) / 2;

        if (left > mid) {
            return sumRange(2 * index + 2, mid + 1, high, left, right);
        } else if (right <= mid) {
            return sumRange(2 * index + 1, low, mid, left, right);
        }

        int l = sumRange(2 * index + 1, low, mid, left, right);
        int r = sumRange(2 * index + 2, mid + 1, high, left, right);

        return l + r;
    }

    private void update(int i, int low, int high, int index, int value) {
        if (low == high) {
            segT[i] = value;
            return;
        }

        int mid = (low + high) / 2;
        if (index > mid) {
            update(2 * i + 2, mid + 1, high, index, value);
        } else if (index <= mid) {
            update(2 * i + 1, low, mid, index, value);
        }

        segT[i] = segT[2 * i + 1] + segT[2 * i + 2];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */