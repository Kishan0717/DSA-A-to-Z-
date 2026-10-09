class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int open = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                open++;
            } else {
                // If we see a ')'
                // Check if there is a consecutive ')' next
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // consume the next ')' as well
                } else {
                    // We are missing one ')' to form a pair
                    res++;
                }
                
                // Now we have a valid pair of ')' (either found or inserted)
                if (open > 0) {
                    open--; // Match with an existing '('
                } else {
                    res++; // No '(' available, we need to insert one '('
                }
            }
        }
        
        // Each remaining open parenthesis requires two ')'
        return res + open * 2;
    }
}