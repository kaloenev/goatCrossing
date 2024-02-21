import java.util.Arrays;
import java.util.Scanner;

public class GoatCrossing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        String[] firstLine = scanner.nextLine().split(" ");
        int goatsNumber = Integer.parseInt(firstLine[0]);
        int tripsNumber = Integer.parseInt(firstLine[1]);

        int[] goatWeights = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(goatWeights);
        reverseArray(goatWeights);

        // Sums the total weight of the goats and finds the average
        int totalWeight = Arrays.stream(goatWeights).sum();
        int averageWeight = totalWeight / tripsNumber;

        int[] sums = new int[tripsNumber];
        // Add next heaviest goat to the next available sum (trip) which is smaller than the average
        for (int goat : goatWeights) {
            for (int i = 0; i < sums.length; i++) {
                if (sums[i] + goat <= averageWeight) {
                    sums[i] += goat;
                    break;
                }
                // if we reach the end of the trips and there are goats left add them to the smallest sum (trip)
                if (i == sums.length - 1) {
                    int lowestSum = Integer.MAX_VALUE;
                    int lowestIndex = Integer.MAX_VALUE;
                    for (int j = 0; j < sums.length; j++) {
                        if (sums[j] + goat < lowestSum) {
                            lowestIndex = j;
                            lowestSum = sums[j] + goat;
                        }
                    }
                    sums[lowestIndex] += goat;
                }
            }
        }
        // iterate through the sums and find the highest one to be printed out
        int highestSum = Integer.MIN_VALUE;
        for (int sum : sums) {
            if (sum > highestSum) {
                highestSum = sum;
            }
        }
        System.out.println(highestSum);
    }

    /**
     * Reverses a given array
     * @param arr array to be reversed
     */
    private static void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
