import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Solution {


    /**
     * 739. 每日温度
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && stack.peek()[1] < temperatures[i]) {
                int[] pop = stack.pop();
                res[pop[0]] = i - pop[0];
            }
            stack.push(new int[]{i, temperatures[i]});
        }
        return res;
    }


    /**
     * 496. 下一个更大元素 I
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.fill(res, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                Integer pop = stack.pop();
                if (map.containsKey(pop)) {
                    Integer idx = map.get(pop);
                    res[idx] = nums2[i];
                }
            }
            stack.push(nums2[i]);
        }
        return res;
    }

    /**
     * 503. 下一个更大元素 II
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<int[]> st = new Stack<>();
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length; i++) {
            while (!st.isEmpty() && nums[i] > st.peek()[1]) {
                int[] pop = st.pop();
                res[pop[0]] = nums[i];
            }
            st.push(new int[]{i, nums[i]});
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > st.peek()[1]) {
                int[] pop = st.pop();
                res[pop[0]] = nums[i];
            }
        }
        return res;
    }


    /**
     * 42. 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <= 2) return 0;
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        stack.push(0);
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
            }
//            else if (height[i] == height[stack.peek()]) {
//                stack.pop();
//                stack.push(i);}
            else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int mid = stack.pop();
                    if (!stack.isEmpty()) {
                        int l = stack.peek();
                        int r = i;
                        int h = Math.min(height[l], height[r]) - height[mid];
                        int w = r - l - 1;
                        sum += h * w;
                    }
                }
                stack.push(i);
            }
        }
        return sum;
    }

    /**
     * 84. 柱状图中最大的矩形
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int[] newHeights = new int[heights.length + 2];
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int res = 0;
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int mid = stack.pop();
                    int l = stack.peek();
                    int r = i;
                    int h = heights[mid];
                    int w = r - l - 1;
                    res = Math.max(res, h * w);
                }
                stack.push(i);
            }
        }
        return res;
    }


}
