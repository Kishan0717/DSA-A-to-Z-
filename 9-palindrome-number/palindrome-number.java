class Solution {
    public boolean isPalindrome(int x) {

        // Negative numbers are not palindrome
        if (x < 0) {
            return false;
        }

        // Numbers ending in 0 are not palindrome (except 0)
        if (x % 10 == 0 && x != 0) {
            return false;
        }

        int reversedHalf = 0;

        // Reverse only half of the number
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x = x / 10;
        }

        // Even digits: x == reversedHalf
        // Odd digits: x == reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}