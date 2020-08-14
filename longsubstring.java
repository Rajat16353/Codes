class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i=0,j=0,k,max=0;
        String a = "";
        while(i != s.length())
        {
            for(k=0;k<j;k++)
            {
                if(a.charAt(k)==s.charAt(i))
                    break;
            }
            if(k==j)
            {
                a = a+s.charAt(i);
                j++;
            }
            else
            {
                if(max < a.length())
                    max = a.length();
                a = a.substring(k+1)+s.charAt(i); 
                j = a.length();
            }
            i++;
        }
        if(max < a.length())
            max = a.length();
        return max;
    }
}
