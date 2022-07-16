import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 509. 斐波那契数
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 746. 使用最小花费爬楼梯
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    /**
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1 && obstacleGrid[0][0] == 0) dp[i][j] = 1;
                else if (obstacleGrid[i - 1][j - 1] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * 343. 整数拆分
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i / 2 + 1; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * dp[j]);
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j <= i - 1; j++) {
                sum += dp[j] * dp[i - 1 - j];
            }
            dp[i] = sum;
        }
        return dp[n];
    }


    /**
     * 416. 分割等和子集
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int[] dp = new int[sum / 2 + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[sum / 2] == sum / 2;
    }

    /**
     * 1049. 最后一块石头的重量 II
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int[] dp = new int[sum / 2 + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = sum / 2; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[sum / 2] * 2;
    }

    /**
     * 494. 目标和
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        sum += Math.abs(target);
        if (sum % 2 == 1) return 0;
        int[] dp = new int[sum / 2 + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[sum / 2];
    }

    /**
     * 474. 一和零
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[] zeroWeight = new int[strs.length];
        int[] oneWeight = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            for (char c : strs[i].toCharArray()) {
                if (c == '0') zeroWeight[i]++;
                else oneWeight[i]++;
            }
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            for (int j = m; j >= zeroWeight[i]; j--) {
                for (int k = n; k >= oneWeight[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroWeight[i]][k - oneWeight[i]] + 1);
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 518. 零钱兑换 II
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /**
     * 377. 组合总和 Ⅳ
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    /**
     * 70. 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int j = 0; j <= n; j++) {
            for (int i = 1; i <= 2; i++) {
                if (j >= i) dp[j] += dp[j - i];
            }
        }
        return dp[n];
    }

    /**
     * 322. 零钱兑换
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 279. 完全平方数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (i + 1) * (i + 1);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= n; j++) {
                if (dp[j - nums[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - nums[i]] + 1);
                }
            }
        }
        return dp[n];
    }

    /**
     * 139. 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        int[] dp = new int[len + 1];
        for (int j = 0; j <= len; j++) {
            for (int i = 0; i < wordDict.size(); i++) {
                String sub = wordDict.get(i);
                if (j >= sub.length() && sub.equals(s.substring(j - sub.length(), j))) {
                    dp[j] = Math.max(dp[j], dp[j - sub.length()] + sub.length());
                }
            }
        }
        return dp[len] == len;
    }

    /**
     * 198. 打家劫舍
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[dp.length - 1];
    }

    /**
     * 213. 打家劫舍 II
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int len = nums.length;
        return Math.max(rob2Help(nums, 0, len - 1), rob2Help(nums, 1, len));
    }

    public int rob2Help(int[] nums, int start, int end) {
        int x = 0, y = 0, z = 0; // dp[i-2] dp[i-1] dp[i]
        for (int i = start; i < end; i++) {
            y = z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }

    /**
     * 337. 打家劫舍 III
     * res[0]: rob res[1]: no rob
     *
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        int[] ints = rob3Help(root);
        return Math.max(ints[0], ints[1]);
    }

    public int[] rob3Help(TreeNode node) {
        if (node == null) return null;
        int[] l = rob3Help(node.left);
        int[] r = rob3Help(node.right);
        int[] res = new int[2];
        res[0] = node.val + (l == null ? 0 : l[1]) + (r == null ? 0 : r[1]);
        res[1] = (l == null ? 0 : Math.max(l[0], l[1])) + (r == null ? 0 : Math.max(r[0], r[1]));
        return res;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 0: 买入或者持有
     * 1: 卖出或者未持有
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[dp.length - 1][1];
    }

    /**
     * 122. 买卖股票的最佳时机 II
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[dp.length - 1][1];
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * 0: 第一次买入或者持有
     * 1: 第一次卖出或者卖出后还未持有的状态
     * 2: 第二次买入或者持有
     * 3: 第二次卖出或者结束两次交易的状态
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return Math.max(dp[dp.length - 1][1], dp[dp.length - 1][3]);
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit4(int k, int[] prices) {
        if (prices.length == 0 || prices.length == 1 || k == 0) return 0;
        int[][] dp = new int[prices.length][2 * k];
        for (int i = 0; i < k; i++) {
            dp[0][2 * i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            for (int j = 1; j < k; j++) {
                dp[i][2 * j] = Math.max(dp[i - 1][2 * j], dp[i - 1][2 * j - 1] - prices[i]);
                dp[i][2 * j + 1] = Math.max(dp[i - 1][2 * j + 1], dp[i - 1][2 * j] + prices[i]);
            }
        }
        int max = 0;
        for (int i = 0; i < k; i++) {
            max = Math.max(max, dp[dp.length - 1][2 * i + 1]);
        }
        return max;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     * 0:买入或持有
     * 1:卖出
     * 2:冷冻期或未持有
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        return Math.max(dp[dp.length - 1][1], dp[dp.length - 1][2]);
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit6(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0] - fee;
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[dp.length - 1][1];
    }

    /**
     * 300. 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 674. 最长连续递增序列
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) dp[i] = dp[i - 1] + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 718. 最长重复子数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                max = Math.max(dp[i + 1][j + 1], max);
            }
        }
        return max;
    }

    /**
     * 1143. 最长公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = Math.max(dp[i][j], dp[i][j + 1]);
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i + 1][j]);
                if (text1.charAt(i) == text2.charAt(j)) dp[i + 1][j + 1] = dp[i][j] + 1;
            }
        }
        return dp[m][n];
    }

    /**
     * 1035. 不相交的线
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 53. 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = dp[i + 1][j];
                }
            }
        }
        return dp[m][n] == m;
    }

    /**
     * 115. 不同的子序列
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = dp[i][j + 1];
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1] += dp[i][j];
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 583. 两个字符串的删除操作
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance1(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]) + 1, dp[i][j] + 2);
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 72. 编辑距离
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 647. 回文子串
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) dp[i][j] = true;
                else if (i == j - 1 && s.charAt(i) == s.charAt(j)) dp[i][j] = true;
                else if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1];
                if (dp[i][j]) count++;
            }
        }
        return count;
    }

    /**
     * 516. 最长回文子序列
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) dp[i][j] = 1;
                else if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                count = Math.max(count, dp[i][j]);
            }
        }
        return count;
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
