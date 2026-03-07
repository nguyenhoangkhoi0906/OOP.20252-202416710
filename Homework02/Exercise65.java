import java.util.Scanner;

public class Exercise65{
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        int sum = 0;

        for(int i = 0; i < n; i++){
            System.out.print("Element " + i + ": ");
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        //Bubble sort
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        double avg = (double) sum / n;

        System.out.println("Sorted array:");
        for(int x : arr){
            System.out.print(x + " ");
        }

        System.out.println("\nAverage: " + avg);
    }
}