// An array contains both positive and negative numbers in random order. Rearrange the array elements so that all negative numbers appear before all positive numbers.

// Examples : 

// Input: -12, 11, -13, -5, 6, -7, 5, -3, -6
// Output: -12 -13 -5 -7 -3 -6 11 6 5
// Note: Order of elements is not important here.

import java.io.*;

class GFG{

// Function to shift all the
// negative elements on left side
static void shiftall(int[] arr, int left,
					int right)
{
	while(left < right) {
      	if (arr[left] > 0 && arr[right] < 0) {
          int temp = arr[left];
          arr[left] = arr[right];
          arr[right] = temp;
          left++;
          right--;
        } else if (arr[left] > 0) {
          right--;
        } else if (arr[right] < 0) {
          left++;
        } else {
          left++;
          right--;
        }
    }
}

// Function to print the array
static void display(int[] arr, int right)
{
	
	// Loop to iterate over the element
	// of the given array
	for(int i = 0; i <= right; ++i)
		System.out.print(arr[i] + " ");
		
	System.out.println();
}

// Drive code
public static void main(String[] args)
{
	int[] arr = { -12, 11, -13, -5,
				6, -7, 5, -3, 11 };
					
	int arr_size = arr.length;

	// Function Call
	shiftall(arr, 0, arr_size - 1);
	display(arr, arr_size - 1);
}
}