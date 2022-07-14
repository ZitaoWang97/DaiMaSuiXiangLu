import java.util.*;

public class Solution {
    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combineHelp(res, path, n, k, 1);
        return res;
    }

    public void combineHelp(List<List<Integer>> res, List<Integer> path, int n, int k, int l) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = l; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineHelp(res, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combinationSum3Help(res, path, k, n, 1);
        return res;
    }

    public void combinationSum3Help(List<List<Integer>> res, List<Integer> path, int k, int n, int startIdx) {
        if (n < 0) return;
        if (path.size() == k) {
            if (n == 0) res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIdx; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            combinationSum3Help(res, path, k, n - i, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    Map<Character, String> map;

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        List<Character> path = new ArrayList<>();
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        letterCombinationsHelp(res, path, digits, 0);
        return res;
    }

    public void letterCombinationsHelp(List<String> res, List<Character> path, String digits, int k) {
        if (path.size() == digits.length()) {
            res.add(letterCombinationsToString(path));
            return;
        }
        char c = digits.charAt(k);
        String s = map.get(c);
        for (int i = 0; i < s.length(); i++) {
            path.add(s.charAt(i));
            letterCombinationsHelp(res, path, digits, k + 1);
            path.remove(path.size() - 1);
        }
    }

    public String letterCombinationsToString(List<Character> path) {
        String s = "";
        for (int i = 0; i < path.size(); i++) {
            s += path.get(i);
        }
        return s;
    }


    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combinationSumHelp(res, path, candidates, target, 0);
        return res;
    }

    public void combinationSumHelp(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int k) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = k; i < candidates.length; i++) {
            path.add(candidates[i]);
            combinationSumHelp(res, path, candidates, target - candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 40. 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2Help(res, path, candidates, target, 0);
        return res;
    }

    public void combinationSum2Help(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int k) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = k; i < candidates.length; i++) {
            if (i > k && candidates[i] == candidates[i - 1]) continue;
            path.add(candidates[i]);
            combinationSum2Help(res, path, candidates, target - candidates[i], i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 131. 分割回文串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitionHelp(res, path, 0, s);
        return res;
    }

    public void partitionHelp(List<List<String>> res, List<String> path, int idx, String s) {
        if (idx == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            String substring = s.substring(idx, i + 1);
            if (partitionHuiwen(substring)) {
                path.add(substring);
                partitionHelp(res, path, i + 1, s);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean partitionHuiwen(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    /**
     * 93. 复原 IP 地址
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        List<String> partition = new ArrayList<>();
        restoreIpAddressesHelp(res, partition, s, 0);
        return res;
    }

    public void restoreIpAddressesHelp(List<String> res, List<String> partition, String s, int idx) {
        if (partition.size() == 4) {
            if (idx == s.length()) {
                res.add(restoreIpAddressesGetString(partition));
            }
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            String sub = s.substring(idx, i + 1);
            if (restoreIpAddressesValid(sub)) {
                partition.add(sub);
                restoreIpAddressesHelp(res, partition, s, i + 1);
                partition.remove(partition.size() - 1);
            }
        }
    }

    public String restoreIpAddressesGetString(List<String> partition) {
        String s = "";
        for (int i = 0; i < partition.size(); i++) {
            s += partition.get(i);
            if (i != partition.size() - 1) s += ".";
        }
        return s;
    }

    public boolean restoreIpAddressesValid(String s) {
        if (s.length() > 3) return false;
        if (s.charAt(0) == '0' && s.length() > 1) return false;
        return Integer.parseInt(s) <= 255;
    }

    /**
     * 78. 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        subsetsHelp(nums, res, path, 0);
        return res;
    }

    public void subsetsHelp(int[] nums, List<List<Integer>> res, List<Integer> path, int idx) {
        res.add(new ArrayList<>(path));
        if (idx == nums.length) {
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsHelp(nums, res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 90. 子集 II
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupHelp(nums, res, path, 0);
        return res;
    }

    public void subsetsWithDupHelp(int[] nums, List<List<Integer>> res, List<Integer> path, int idx) {
        res.add(new ArrayList<>(path));
        if (idx == nums.length) {
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            subsetsWithDupHelp(nums, res, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 491. 递增子序列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        findSubsequencesHelp(nums, res, path, 0);
        return res;
    }

    public void findSubsequencesHelp(int[] nums, List<List<Integer>> res, List<Integer> path, int idx) {
        if (path.size() >= 2) {
            res.add(new ArrayList<>(path));
        }
        if (idx == nums.length) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && set.contains(nums[i])) continue;
            set.add(nums[i]);
            if (path.size() == 0 || nums[i] >= path.get(path.size() - 1)) {
                path.add(nums[i]);
                findSubsequencesHelp(nums, res, path, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 46. 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] used = new int[nums.length];
        permuteHelp(nums, used, res, path);
        return res;
    }

    public void permuteHelp(int[] nums, int[] used, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 0) {
                path.add(nums[i]);
                used[i] = 1;
                permuteHelp(nums, used, res, path);
                path.remove(path.size() - 1);
                used[i] = 0;
            }
        }
    }

    /**
     * 47. 全排列 II
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] used = new int[nums.length];
        Arrays.sort(nums);
        permuteUniqueHelp(nums, used, res, path);
        return res;
    }

    private void permuteUniqueHelp(int[] nums, int[] used, List<List<Integer>> res, List<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) continue;
            if (used[i] == 0) {
                path.add(nums[i]);
                used[i] = 1;
                permuteUniqueHelp(nums, used, res, path);
                path.remove(path.size() - 1);
                used[i] = 0;
            }
        }
    }


    /**
     * 332. 重新安排行程
     *
     * @param tickets
     * @return
     */
    Map<String, Map<String, Integer>> itineraryMap;
    List<String> res;

    public List<String> findItinerary(List<List<String>> tickets) {
        itineraryMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (itineraryMap.containsKey(from)) {
                Map<String, Integer> map = itineraryMap.get(from);
                map.put(to, map.getOrDefault(to, 0) + 1);
                itineraryMap.put(from, map);
            } else {
                TreeMap<String, Integer> map = new TreeMap<>();
                map.put(to, 1);
                itineraryMap.put(from, map);
            }
        }
        res = new ArrayList<>();
        res.add("JFK");
        findItineraryHelp(tickets.size() + 1);
        return res;
    }

    public boolean findItineraryHelp(int len) {
        if (res.size() == len) return true;
        String from = res.get(res.size() - 1);
        if (itineraryMap.containsKey(from)) {
            Map<String, Integer> map = itineraryMap.get(from);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                if (count > 0) {
                    String to = entry.getKey();
                    res.add(to);
                    entry.setValue(count - 1);
                    if (findItineraryHelp(len)) return true;
                    res.remove(res.size() - 1);
                    entry.setValue(count);
                }
            }
        }
        return false;
    }


    /**
     * 51. N 皇后
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] ckbd = new int[n];
        solveNQueensHelp(ckbd, 0, res);
        return res;
    }

    public void solveNQueensHelp(int[] ckbd, int idx, List<List<String>> res) {
        if (idx == ckbd.length) {
            res.add(solveNQueensGetStringList(ckbd));
            return;
        }
        for (int i = 0; i < ckbd.length; i++) {
            ckbd[idx] = i;
            if (solveNQueensValid(ckbd, idx)) {
                solveNQueensHelp(ckbd, idx + 1, res);
            }
        }
    }

    public boolean solveNQueensValid(int[] ckbd, int idx) {
        for (int i = 0; i < idx; i++) {
            if (ckbd[idx] == ckbd[i]) return false;
            if (Math.abs(ckbd[idx] - ckbd[i]) == Math.abs(idx - i)) return false;
        }
        return true;
    }

    public List<String> solveNQueensGetStringList(int[] ckbd) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < ckbd.length; i++) {
            int pos = ckbd[i];
            String s = "";
            for (int j = 0; j < pos; j++) {
                s += ".";
            }
            s += "Q";
            for (int j = pos + 1; j < ckbd.length; j++) {
                s += ".";
            }
            res.add(s);
        }
        return res;
    }


    /**
     * 37. 解数独
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        solveSudokuHelp(board);
    }

    public boolean solveSudokuHelp(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char k = '1'; k <= '9'; k++) {
                    if (solveSudokuValid(board, i, j, k)) {
                        board[i][j] = k;
                        if (solveSudokuHelp(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean solveSudokuValid(char[][] board, int i, int j, char c) {
        for (int k = 0; k < 9; k++) {
            if (c == board[k][j]) return false;
        }
        for (int k = 0; k < 9; k++) {
            if (c == board[i][k]) return false;
        }
        int m = i / 3;
        int n = j / 3;
        for (int k = m * 3; k < m * 3 + 3; k++) {
            for (int l = n * 3; l < n * 3 + 3; l++) {
                if (board[k][l] == c) return false;
            }
        }
        return true;
    }


}
