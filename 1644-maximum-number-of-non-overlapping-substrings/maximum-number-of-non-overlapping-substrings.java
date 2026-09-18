class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';

            first[idx] = Math.min(first[idx], i);
            last[idx] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try creating a valid substring starting from each character
        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            for (int i = l; i <= r; i++) {

                int idx = s.charAt(i) - 'a';

                // This character occurs before l,
                // so we cannot make a valid substring starting at l.
                if (first[idx] < l) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences of this character
                r = Math.max(r, last[idx]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        // Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > prevEnd) {
                ans.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return ans;
    }
}