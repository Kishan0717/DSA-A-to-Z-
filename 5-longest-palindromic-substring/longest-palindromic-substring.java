class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Expand for odd length palindrome (center is i)
            int len1 = expandAroundCenter(s, i, i);
            // Expand for even length palindrome (center is between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get the maximum length from the two expansions
            int len = Math.max(len1, len2);
            
            // If we found a longer palindrome, update start and end indices
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        // Return the substring from start to end (inclusive)
        return s.substring(start, end + 1);
    }
    
    // Helper method to expand around the given left and right pointers
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome found
        return right - left - 1;
    }
}