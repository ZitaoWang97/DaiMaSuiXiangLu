import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {
    /**
     * 807. 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        // [l,r) 左闭右开
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) return m;
            else if (nums[m] > target) r = m;
            else l = m + 1;
        }
        return -1;
    }


    /**
     * 35. 搜索插入的位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        // [l,r) 左闭右开
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) return m;
            else if (nums[m] > target) r = m;
            else l = m + 1;
        }
        return l;
    }

    /**
     * 34. 查找元素的第一个位置和最后一个位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int l = 0;
        int r = nums.length;
        int lb = -1;
        int rb = -1;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                if (lb == -1) lb = m;
                else lb = Math.min(lb, m);
            }
            if (nums[m] >= target) r = m;
            else l = m + 1;
        }
        if (lb == -1) return new int[]{-1, -1};
        l = 0;
        r = nums.length;
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (nums[m] == target) {
                if (rb == -1) rb = m;
                else rb = Math.max(rb, m);
            }
            if (nums[m] > target) r = m;
            else l = m + 1;
        }
        return new int[]{lb, rb};
    }

    /**
     * 69. x的平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int l = 1;
        int r = x / 2;
        while (l < r) {
            int m = l + ((r - l + 1) >> 1);
            if (x / m == m) return m;
            else if (x / m < m) r = m - 1;
            else l = m;
        }
        return l;
    }

    /**
     * 367. 有效的完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) return true;
        int l = 1;
        int r = num;
        // [l,r]
        while (l <= r) {
            int m = ((r - l) >> 1) + l;
            if (m * m == num) return true;
            else if (num / m < m) r = m - 1;
            else l = m + 1;
        }
        return false;
    }

    /**
     * 27. 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int s = 0;
        int f = 0;
        while (f < nums.length) {
            if (nums[f] != val) {
                nums[s] = nums[f];
                s++;
            }
            f++;
        }
        return s;
    }

    /**
     * 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int s = 1;
        int f = 1;
        int now = nums[0];
        while (f < nums.length) {
            if (nums[f] != now) {
                now = nums[f];
                nums[s] = now;
                s++;
            }
            f++;
        }
        return s;
    }

    /**
     * 283. 移动零
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int s = 0;
        int f = 0;
        while (f < nums.length) {
            if (nums[f] != 0) {
                nums[s] = nums[f];
                s++;
            }
            f++;
        }
        while (s < nums.length) {
            nums[s++] = 0;
        }
    }

    /**
     * 844. 比较含退格的字符串
     *
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        while (i >= 0 && j >= 0) {
            while (i >= 0 && !(s.charAt(i) != '#' && skipS == 0)) {
                if (s.charAt(i) == '#') skipS++;
                else skipS--;
                i--;
            }
            while (j >= 0 && !(t.charAt(j) != '#' && skipT == 0)) {
                if (t.charAt(j) == '#') skipT++;
                else skipT--;
                j--;
            }
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) return false;
                i--;
                j--;
            }
        }
        while (i >= 0 && !(s.charAt(i) != '#' && skipS == 0)) {
            if (s.charAt(i) == '#') skipS++;
            else skipS--;
            i--;
        }
        while (j >= 0 && !(t.charAt(j) != '#' && skipT == 0)) {
            if (t.charAt(j) == '#') skipT++;
            else skipT--;
            j--;
        }
        return (i == -1 && j == -1);
    }

    /**
     * 977. 有序数组的平方
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int i = nums.length - 1;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            if (nums[l] * nums[l] >= nums[r] * nums[r]) {
                res[i--] = nums[l] * nums[l];
                l++;
            } else {
                res[i--] = nums[r] * nums[r];
                r--;
            }
        }
        return res;
    }

    /**
     * 209. 长度最小的子数组
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int minLen = 0;
        for (; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                if (minLen == 0) minLen = r - l + 1;
                else minLen = Math.min(minLen, r - l + 1);
                sum -= nums[l++];
            }
        }
        return minLen;
    }

    /**
     * 904. 水果成篮
     *
     * @param fruits
     * @return
     */
    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) return 0;
        int l = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int r = 0; r < fruits.length; r++) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);
            while (map.keySet().size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }

    /**
     * 76. 最小覆盖子串
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int[] cnts = new int[60];
        for (char c : t.toCharArray()) {
            cnts[c - 'A']++;
        }
        char[] chars = s.toCharArray();
        int head = 0;
        int minLen = chars.length + 1;
        int pos = 0;
        int num = t.length();
        for (int tail = 0; tail < chars.length; tail++) {
            if (cnts[chars[tail] - 'A']-- > 0) num--;
            while (head < tail && cnts[chars[head] - 'A'] < 0) {
                cnts[chars[head] - 'A']++;
                head++;
            }
            if (num == 0 && tail - head + 1 < minLen) {
                minLen = tail - head + 1;
                pos = head;
            }
        }
        return minLen == chars.length + 1 ? "" : s.substring(pos, pos + minLen);
    }

    /**
     * 59. 螺旋矩阵
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int loop = n >> 1;
        int count = 1;
        for (int l = 0; l < loop; l++) {
            for (int j = l; j < n - l - 1; j++) {
                matrix[l][j] = count++;
            }
            for (int i = l; i < n - l - 1; i++) {
                matrix[i][n - l - 1] = count++;
            }
            for (int j = n - l - 1; j > l; j--) {
                matrix[n - l - 1][j] = count++;
            }
            for (int i = n - l - 1; i > l; i--) {
                matrix[i][l] = count++;
            }
        }
        if (n % 2 == 1) matrix[n / 2][n / 2] = count++;
        return matrix;

    }


    /**
     * 54. 螺旋矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int loop = Math.min(m, n) / 2;
        ArrayList<Integer> res = new ArrayList<>();
        for (int l = 0; l < loop; l++) {
            for (int j = l; j < n - 1 - l; j++) {
                res.add(matrix[l][j]);
            }
            for (int i = l; i < m - 1 - l; i++) {
                res.add(matrix[i][n - 1 - l]);
            }
            for (int j = n - 1 - l; j > l; j--) {
                res.add(matrix[m - 1 - l][j]);
            }
            for (int i = m - 1 - l; i > l; i--) {
                res.add(matrix[i][l]);
            }
        }
        if (m < n && m % 2 == 1) {
            for (int j = loop; j < n - loop; j++) {
                res.add(matrix[m / 2][j]);
            }
        }
        if (m >= n && n % 2 == 1) {
            for (int i = loop; i < m - loop; i++) {
                res.add(matrix[i][n / 2]);
            }
        }
        return res;
    }


}
