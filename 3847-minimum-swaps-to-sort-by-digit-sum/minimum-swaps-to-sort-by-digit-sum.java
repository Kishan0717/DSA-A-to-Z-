import java.util.*;

class Solution {
    // Helper function to calculate the sum of digits of a number
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Store indices to track their original positions
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices based on digit sum, then by value as a tie-breaker
        Arrays.sort(indices, (a, b) -> {
            int sumA = getDigitSum(nums[a]);
            int sumB = getDigitSum(nums[b]);
            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        // Find the minimum number of swaps using cycle detection
        boolean[] visited = new boolean[n];
        int cycles = 0;

        for (int i = 0; i < n; i++) {
            // If already visited, move on
            if (visited[i]) {
                continue;
            }

            // Traverse the cycle
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = indices[curr];
            }
            cycles++;
        }

        return n - cycles;
    }
}