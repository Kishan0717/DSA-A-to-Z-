class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Find the maximum difference to size our frequency array
        int maxDiff = 0;
        int[] diffCount = new int[100005];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            diffCount[diff]++;
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Greedily reduce the largest differences
        for (int i = maxDiff; i > 0 && totalK > 0; i--) {
            if (diffCount[i] == 0) continue;
            
            // Number of operations we can apply to elements having difference i
            long operationsToUse = Math.min(totalK, diffCount[i]);
            
            diffCount[i] -= operationsToUse;
            diffCount[i - 1] += operationsToUse;
            totalK -= operationsToUse;
        }
        
        // Calculate the minimum sum of squared differences
        long minSumSqDiff = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (diffCount[i] > 0) {
                minSumSqDiff += (long) diffCount[i] * i * i;
            }
        }
        
        return minSumSqDiff;
    }
}