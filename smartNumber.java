/*
A number is called smart number if its digits can be partitioned into two sets such that the sum of the digits in both sets are equal . 37965 -> 3+ 7+5 = 9+6
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // Sort the candidates to handle duplicates effectively
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        helper(candidates, target, 0, comb, res);
        return res;
    }

    private void helper(int[] candidates, int remain, int idx, List<Integer> comb, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // Skip duplicates to avoid redundant combinations
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > remain) {
                break; // No need to continue if the current candidate exceeds the remaining target
            }
            comb.add(candidates[i]);
            helper(candidates, remain - candidates[i], i + 1, comb, res); // Move to the next index
            comb.remove(comb.size() - 1); // Backtrack
        }
    }
}
