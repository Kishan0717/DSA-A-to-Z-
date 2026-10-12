import java.util.List;
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[30];      
        // Count the frequency of each bit across all numbers
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }      
        long mod = 1_000_000_007;
        long totalSum = 0;       
        // Reconstruct the top k numbers greedily
        for (int step = 0; step < k; step++) {
            long currentNum = 0;
            for (int i = 0; i < 30; i++) {
                if (bitCount[i] > 0) {
                    currentNum |= (1 << i);
                    bitCount[i]--;
                }
            }
            totalSum = (totalSum + (currentNum * currentNum) % mod) % mod;
        }     
        return (int) totalSum;
    }
}