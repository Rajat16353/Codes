class Solution {

    static class Pair {
        int min;
        int max;
    }

    static Pair minimumMaximum(int[] nums) {
        Pair minMax = new Pair();

        if (nums.length == 1) {
            minMax.min = nums[0];
            minMax.max = nums[0];
            return minMax;
        }    
        
        int i, len = nums.length;
        if (len % 2 == 0) {
            if (nums[0] > nums[1]){
                minMax.min = nums[1];
                minMax.max = nums[0];
            } else {
                minMax.min = nums[0];
                minMax.max = nums[1];
            }
            i = 2;
        } else {
            minMax.min = minMax.max = nums[0];
            i = 1;
        }
        for (; i+1 < len;) {
            if (nums[i] > nums[i+1]) {
                if (nums[i] > minMax.max) {
                    minMax.max = nums[i];
                }
                if (nums[i+1] < minMax.min) {
                    minMax.min = nums[i+1];
                }
            } else {
                if (nums[i+1] > minMax.max) {
                    minMax.max = nums[i+1];
                }
                if (nums[i] < minMax.min) {
                    minMax.min = nums[i];
                }
            }
            i += 2;
        }
        return minMax;
    }
}