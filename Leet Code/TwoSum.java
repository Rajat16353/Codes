class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i=0,j=0;
        ab: for(i=0;i<nums.length;i++)
        {
                for(j=i;j<nums.length;j++)
                {
                    if(i!=j)
                    {
                        if(target == (nums[i]+nums[j]))
                            break ab;
                    }
                }
        }
        int ans[] = {i,j};
        return ans;
    }
}
