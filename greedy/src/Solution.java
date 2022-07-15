import java.util.*;

public class Solution {

    /**
     * 455. 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int point = 0;
        int count = 0;
        for (int num : s) {
            if (point == g.length) return count;
            if (g[point] <= num) {
                count++;
                point++;
            }
        }
        return count;
    }

    /**
     * 376. 摆动序列
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int curDiff = 0;
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            curDiff = nums[i] - nums[i - 1];
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            res = Math.max(res, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return res;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[] prof = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            prof[i - 1] = prices[i] - prices[i - 1];
        }
        int res = 0;
        for (int i : prof) {
            if (i > 0) res += i;
        }
        return res;
    }

    /**
     * 55. 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int p = 0;
        int range = nums[0];
        while (p <= range && p < nums.length) {
            range = Math.max(range, nums[p] + p);
            p++;
        }
        return p == nums.length;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;
        int count = 1;
        int curRange = nums[0];
        int nextRange = nums[0];
        while (nextRange < nums.length - 1) {
            for (int i = 0; i <= curRange; i++) {
                nextRange = Math.max(nextRange, i + nums[i]);
            }
            curRange = nextRange;
            count++;
        }
        return count;
    }

    /**
     * 1005. K 次取反后最大化的数组和
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int idx = largestSumAfterKNegationsHelp(nums);
            nums[idx] *= -1;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public int largestSumAfterKNegationsHelp(int[] nums) {
        int i = 0;
        int min = nums[0];
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] < min) {
                min = nums[j];
                i = j;
            }
        }
        return i;
    }

    /**
     * 134. 加油站
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];
        int bal = 0;
        for (int i = 0; i < gas.length; i++) {
            diff[i] = gas[i] - cost[i];
            bal += diff[i];
        }
        if (bal < 0) return -1;
        int start = 0;
        int sum = 0;
        for (int i = 0; i < diff.length; i++) {
            sum += diff[i];
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }
        return start;
    }

    /**
     * 135. 分发糖果
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }
        int sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }

    /**
     * 860. 柠檬水找零
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        HashMap<Integer, Integer> money = new HashMap<>();
        money.put(5, 0);
        money.put(10, 0);
        for (int bill : bills) {
            if (bill == 5) {
                money.put(5, money.get(5) + 1);
            }
            if (bill == 10) {
                money.put(10, money.get(10) + 1);
                if (money.get(5) == 0) return false;
                money.put(5, money.get(5) - 1);
            }
            if (bill == 20) {
                if (money.get(10) > 0 && money.get(5) > 0) {
                    money.put(10, money.get(10) - 1);
                    money.put(5, money.get(5) - 1);
                } else if (money.get(5) > 2) {
                    money.put(5, money.get(5) - 3);
                } else return false;
            }
        }
        return true;
    }

    /**
     * 406. 根据身高重建队列
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[1] - o2[1]);
        Arrays.sort(people, (o1, o2) -> o2[0] - o1[0]);
        ArrayList<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        int[][] res = new int[people.length][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 452. 用最少数量的箭引爆气球
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int board = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] <= board) {
                board = Math.min(board, point[1]);
            } else {
                count++;
                board = point[1];
            }
        }
        return count;
    }

    /**
     * 435. 无重叠区间
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int board = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            int[] point = intervals[i];
            if (point[0] < board) {
                board = Math.min(board, point[1]);
            } else {
                count++;
                board = point[1];
            }
        }
        return intervals.length - count;
    }

    /**
     * 763. 划分字母区间
     *
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {
        int[] exist = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            exist[chars[i] - 'a'] = i;
        }
        ArrayList<Integer> res = new ArrayList<>();
        int board = 0;
        int p = 0;
        int lastP = 0;
        while (p < s.length()) {
            while (p <= board) {
                board = Math.max(board, exist[chars[p] - 'a']);
                p++;
            }
            res.add(p - lastP);
            lastP = p;
            if (p < s.length()) board = exist[chars[p] - 'a'];
        }
        return res;
    }

    /**
     * 56. 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        List<int[]> list = new ArrayList<>();
        int leftBoard = intervals[0][0];
        int rightBoard = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] <= rightBoard) {
                rightBoard = Math.max(interval[1], rightBoard);
            } else {
                list.add(new int[]{leftBoard, rightBoard});
                leftBoard = interval[0];
                rightBoard = interval[1];
            }
        }
        list.add(new int[]{leftBoard, rightBoard});
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 738. 单调递增的数字
     *
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        String s = "" + n;
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) <= s.charAt(i + 1)) {
            i++;
        }
        if (i == s.length() - 1) return n;
        char[] chars = s.toCharArray();
        if (s.charAt(i) == '1') {
            chars = new char[chars.length - 1];
            Arrays.fill(chars, '9');
        } else if (i == 0 || s.charAt(i) != s.charAt(i - 1)) {
            chars[i] = (char) (chars[i] - 1);
            for (int j = i + 1; j < s.length(); j++) {
                chars[j] = '9';
            }
        } else {
            int j = i;
            while (j >= 0 && chars[j] == chars[i]) {
                j--;
            }
            j++;
            chars[j] = (char) (chars[j] - 1);
            for (int k = j + 1; k < chars.length; k++) {
                chars[k] = '9';
            }
        }
        String s1 = new String(chars);
        return Integer.parseInt(s1);
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int sum = 0;
        for (int price : prices) {
            if (price + fee < buy) {
                buy = price + fee; // 换手
            } else if (price > buy) {
                sum += (price - buy);
                buy = price;
            }
        }
        return sum;
    }

    /**
     * 968. 监控二叉树
     * 0:无覆盖
     * 1:装摄像头
     * 2:有覆盖
     *
     * @param root
     * @return
     */
    int minCameraCoverRes = 0;

    public int minCameraCover(TreeNode root) {
        int i = minCameraCoverHelp(root);
        return i == 0 ? minCameraCoverRes + 1 : minCameraCoverRes;
    }

    public int minCameraCoverHelp(TreeNode node) {
        if (node == null) return 2;
        int l = minCameraCoverHelp(node.left);
        int r = minCameraCoverHelp(node.right);
        if (l == 0 || r == 0) {
            minCameraCoverRes++;
            return 1;
        } else if (l == 1 || r == 1) {
            return 2;
        } else {
            return 0;
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
