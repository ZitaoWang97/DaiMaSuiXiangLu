import java.util.*;

public class Solution {
    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     *
     * @param s
     * @return
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }

    /**
     * 150. 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                Integer pop = stack.pop();
                Integer pop1 = stack.pop();
                stack.push(pop + pop1);
            } else if (token.equals("-")) {
                Integer pop = stack.pop();
                Integer pop1 = stack.pop();
                stack.push(pop1 - pop);
            } else if (token.equals("*")) {
                Integer pop = stack.pop();
                Integer pop1 = stack.pop();
                stack.push(pop1 * pop);
            } else if (token.equals("/")) {
                Integer pop = stack.pop();
                Integer pop1 = stack.pop();
                stack.push(pop1 / pop);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }

    /**
     * 239. 滑动窗口最大值
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i <= nums.length - k; i++) {
            if (i == 0) {
                for (int j = 0; j < k; j++) {
                    if (deque.isEmpty()) {
                        deque.offerFirst(nums[j]);
                    } else {
                        while (!deque.isEmpty() && nums[j] > deque.peekLast()) {
                            deque.pollLast();
                        }
                        deque.offerLast(nums[j]);
                    }
                }
            } else {
                if (nums[i - 1] == deque.peekFirst()) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && nums[i - 1 + k] > deque.peekLast()) {
                    deque.pollLast();
                }
                deque.offerLast(nums[i - 1 + k]);
            }
            res[i] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(((o1, o2) -> {
            return o1.getValue() - o2.getValue();
        }));
        for (Map.Entry<Integer, Integer> entry : entries) {
            priorityQueue.offer(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll().getKey();
        }
        return res;
    }
}

/**
 * 232. 用栈实现队列
 */
class MyQueue {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * 225. 用队列实现栈
 */
class MyStack {
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q1.offer(x);
    }

    public int pop() {
        int top = getTop();
        turnBack();
        return top;
    }

    public int top() {
        int top = getTop();
        q2.offer(top);
        turnBack();
        return top;
    }

    public int getTop() {
        while (!q1.isEmpty()) {
            Integer poll = q1.poll();
            if (q1.isEmpty()) return poll;
            q2.offer(poll);
        }
        return 0;
    }

    public void turnBack() {
        while (!q2.isEmpty()) {
            q1.offer(q2.poll());
        }
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}

