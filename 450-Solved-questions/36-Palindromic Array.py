# Given a Integer array A[] of n elements. Your task is to complete the function PalinArray. Which will return 1 if all the elements of the Array are palindrome otherwise it will return 0.

 

# Example:
# Input:
# 2
# 5
# 111 222 333 444 555
# 3
# 121 131 20

# Output:
# 1
# 0

# Explanation:
# For First test case.
# n=5;
# A[0] = 111    //which is a palindrome number.
# A[1] = 222   //which is a palindrome number.
# A[2] = 333   //which is a palindrome number.
# A[3] = 444  //which is a palindrome number.
# A[4] = 555  //which is a palindrome number.
# As all numbers are palindrome so This will return 1.

# Constraints:
# 1 <=T<= 50
# 1 <=n<= 20
# 1 <=A[]<= 10000

# Your task is to complete this function
# Function should return True/False or 1/0
def PalinArray(arr ,n):
    # Code here
    for num in arr:
        s = str(num)
        
        if s != s[::-1]:
            return 0
    return 1


#{ 
 # Driver Code Starts
# Driver Program
if __name__=='__main__':
    t=int(input())
    for i in range(t):
        n = int(input())
        arr = list(map(int, input().strip().split()))
        if PalinArray(arr, n):
            print(1)
        else:
            print(0)
# Contributed By: Harshit Sidhwa

# } Driver Code Ends