public class BubbleSort {

    public static void sort (int[] arr, int N) {
        for(int i=0; i<N-1; i++) {

            for(int j=0; j<N-i-1; j++) {
                if (arr[j] >arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {39,26,81,46,55,41,87,49,28,44,21,92,71,95,100,50,85,67,72,2,6,93,53,79,30,7,63,43,59,91,10,31,47,37,83,65,98,60,62,69,57,94,22,68,58,1,48,64,35,19,56,42,9,11,45,25,13,61,78,14,8,54,80,29,5,82,27,66,20,99,86,12,97,75,96,16,73,36,23,18,3,77,33,32,34,4,24,38,17,52,90,40,84,51,89,76,88,74,15,70};
        int N = arr.length;
        sort(arr,N);
        for(int i: arr) {
            System.out.println(i);
        }
    }
}