import java.util.*;

public class Solution {

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversalRecursion(root, list);
        return list;
    }

    public void preorderTraversalRecursion(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        preorderTraversalRecursion(node.left, list);
        preorderTraversalRecursion(node.right, list);
    }

    public List<Integer> preorderTraversalIterate(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
        return list;
    }

    public List<Integer> preorderTraversalIterateTemplate(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                if (pop.right != null) stack.push(pop.right);
                if (pop.left != null) stack.push(pop.left);
                stack.push(pop);
                stack.push(null);
            } else {
                TreeNode node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }

    /**
     * 145. 二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversalRecursion(root, list);
        return list;
    }

    public void postorderTraversalRecursion(TreeNode node, List<Integer> list) {
        if (node == null) return;
        postorderTraversalRecursion(node.left, list);
        postorderTraversalRecursion(node.right, list);
        list.add(node.val);
    }

    public List<Integer> postorderTraversalIterate(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            list.add(pop.val);
            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> postorderTraversalIterateTemplate(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop != null) {
                stack.push(pop);
                stack.push(null);
                if (pop.right != null) stack.push(pop.right);
                if (pop.left != null) stack.push(pop.left);
            } else {
                TreeNode node = stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                root = pop.right;
            }
        }
        return list;
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 107. 二叉树的层序遍历 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 199. 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                if (i == len - 1) res.add(poll.val);
            }
        }
        return res;
    }


    /**
     * 637. 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            double sum = 0;
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                sum += poll.val;
            }
            res.add(sum / len);
        }
        return res;
    }


    /**
     * 515. 在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                max = Math.max(max, poll.val);
            }
            res.add(max);
        }
        return res;
    }

    /**
     * 226. 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        invertTreeHelp(root);
        return root;
    }

    public void invertTreeHelp(TreeNode node) {
        if (node == null) return;
        invertTreeHelp(node.left);
        invertTreeHelp(node.right);
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }

    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricHelp(root.left, root.right);
    }

    public boolean isSymmetricHelp(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null ^ r == null) return false;
        return (l.val == r.val) && (isSymmetricHelp(l.right, r.left)) && (isSymmetricHelp(l.left, r.right));
    }


    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    int maxDep = 0;

    public int maxDepth(TreeNode root) {
        maxDepthHelp(root, 1);
        return maxDep;
    }

    public void maxDepthHelp(TreeNode node, int level) {
        if (node == null) return;
        maxDep = Math.max(level, maxDep);
        maxDepthHelp(node.left, level + 1);
        maxDepthHelp(node.right, level + 1);
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 222. 完全二叉树的节点个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null ^ root.right == null) return 2;
        int leftDep = 0;
        TreeNode l = root.left;
        while (l != null) {
            l = l.left;
            leftDep++;
        }
        int rightDep = 0;
        TreeNode r = root.right;
        while (r != null) {
            r = r.left;
            rightDep++;
        }
        if (leftDep == rightDep) {
            return (1 << leftDep) + countNodes(root.right);
        } else {
            return (1 << rightDep) + countNodes(root.left);
        }
    }


    /**
     * 110. 平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelp(root) != -1;
    }

    public int isBalancedHelp(TreeNode node) {
        if (node == null) return 0;
        int l = isBalancedHelp(node.left);
        int r = isBalancedHelp(node.right);
        if (Math.abs(l - r) > 1) return -1;
        if (l == -1 || r == -1) return -1;
        return Math.max(l, r) + 1;
    }

    /**
     * 257. 二叉树的所有路径
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) return res;
        binaryTreePathsRecursion(root, res, path);
        return res;
    }

    public void binaryTreePathsRecursion(TreeNode node, List<String> res, List<Integer> path) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(binaryTreePathsUtil(path));
            return;
        }
        if (node.left != null) {
            binaryTreePathsRecursion(node.left, res, path);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            binaryTreePathsRecursion(node.right, res, path);
            path.remove(path.size() - 1);
        }
    }

    public String binaryTreePathsUtil(List<Integer> path) {
        String s = "";
        for (int i = 0; i < path.size(); i++) {
            s += path.get(i);
            if (i != path.size() - 1) s += "->";
        }
        return s;
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int l = sumOfLeftLeaves(root.left);
        int r = sumOfLeftLeaves(root.right);
        int bonus = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            bonus = root.left.val;
        }
        return l + r + bonus;
    }

    /**
     * 513. 找树左下角的值
     *
     * @param root
     * @return
     */
    int findBottomLeftValueMaxDep = 0;
    int findBottomLeftValueRes;

    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValueHelp(root, 1);
        return findBottomLeftValueRes;
    }

    public void findBottomLeftValueHelp(TreeNode node, int level) {
        if (node == null) return;
        if (level > findBottomLeftValueMaxDep) {
            findBottomLeftValueMaxDep = level;
            findBottomLeftValueRes = node.val;
        }
        findBottomLeftValueHelp(node.left, level + 1);
        findBottomLeftValueHelp(node.right, level + 1);
    }


    /**
     * 112. 路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        boolean l = false;
        if (root.left != null) l = hasPathSum(root.left, targetSum - root.val);
        boolean r = false;
        if (root.right != null) r = hasPathSum(root.right, targetSum - root.val);
        return l || r;
    }

    /**
     * 113. 路径总和 II
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        pathSumHelp(root, targetSum, res, path);
        return res;
    }

    public void pathSumHelp(TreeNode node, int targetSum, List<List<Integer>> res, List<Integer> path) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            if (targetSum == node.val) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (node.left != null) {
            pathSumHelp(node.left, targetSum - node.val, res, path);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            pathSumHelp(node.right, targetSum - node.val, res, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeHelp(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeHelp(int[] inorder, int inLeft, int inRight,
                                  int[] postorder, int postLeft, int postRight) {
        if (inLeft == inRight) return new TreeNode(inorder[inLeft]);
        if (inLeft > inRight) return null;
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);
        int rootInorderIndex = 0;
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == rootVal) rootInorderIndex = i;
        }
        root.left = buildTreeHelp(inorder, inLeft, rootInorderIndex - 1,
                postorder, postLeft, postLeft + (rootInorderIndex - inLeft - 1));
        root.right = buildTreeHelp(inorder, rootInorderIndex + 1, inRight,
                postorder, postLeft + (rootInorderIndex - inLeft), postRight - 1);
        return root;
    }


    /**
     * 654. 最大二叉树
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeHelp(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTreeHelp(int[] nums, int l, int r) {
        if (l == r) return new TreeNode(nums[l]);
        if (l > r) return null;
        int max = nums[l];
        int maxIndex = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTreeHelp(nums, l, maxIndex - 1);
        root.right = constructMaximumBinaryTreeHelp(nums, maxIndex + 1, r);
        return root;
    }

    /**
     * 617. 合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }

    /**
     * 700. 二叉搜索树中的搜索
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        else if (root.val > val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }

    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
    TreeNode prev = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (!left) return false;
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        prev = root;
        boolean right = isValidBST(root.right);
        return right;
    }


    /**
     * 530. 二叉搜索树的最小绝对差
     *
     * @param root
     * @return
     */
    int getMinimumDifferenceRes = Integer.MAX_VALUE;
    TreeNode getMinimumDifferencePreNode = null;

    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceHelp(root);
        return getMinimumDifferenceRes;
    }

    public void getMinimumDifferenceHelp(TreeNode node) {
        if (node == null) return;
        getMinimumDifferenceHelp(node.left);
        if (getMinimumDifferencePreNode != null) {
            getMinimumDifferenceRes = Math.min(getMinimumDifferenceRes, node.val - getMinimumDifferencePreNode.val);
        }
        getMinimumDifferencePreNode = node;
        getMinimumDifferenceHelp(node.right);
    }


    /**
     * 501. 二叉搜索树中的众数
     *
     * @param root
     * @return
     */
    int findModeMaxFreq = 0;
    int findModeCurValFreq = 0;
    TreeNode findModePrev = null;
    List<Integer> findModeRes;

    public int[] findMode(TreeNode root) {
        findModeRes = new ArrayList<>();
        findModeHelp(root);
        int[] res = new int[findModeRes.size()];
        for (int i = 0; i < findModeRes.size(); i++) {
            res[i] = findModeRes.get(i);
        }
        return res;
    }

    public void findModeHelp(TreeNode node) {
        if (node == null) return;
        findModeHelp(node.left);
        if (findModePrev != null) {
            if (node.val == findModePrev.val) {
                findModeCurValFreq++;
            } else {
                findModeCurValFreq = 1;
            }
        } else {
            findModeCurValFreq = 1;
        }
        if (findModeCurValFreq == findModeMaxFreq) {
            findModeRes.add(node.val);
        } else if (findModeCurValFreq > findModeMaxFreq) {
            findModeRes.clear();
            findModeRes.add(node.val);
            findModeMaxFreq = findModeCurValFreq;
        }
        findModePrev = node;
        findModeHelp(node.right);
    }


    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l == null && r == null) return null;
        else if (l == null ^ r == null) return l == null ? r : l;
        else return root;
    }


    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val || root.val == q.val) return root;
        else if (root.val > Math.max(p.val, q.val)) return lowestCommonAncestorBST(root.left, p, q);
        else if (root.val < Math.min(p.val, q.val)) return lowestCommonAncestorBST(root.right, p, q);
        else return root;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val);
        return root;
    }

    /**
     * 450. 删除二叉搜索树中的节点
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode[] nodes = deleteNodeHelp(root, key);
        TreeNode parent = nodes[0];
        TreeNode target = nodes[1];
        if (target == null) return root;
        if (target.left == null && target.right == null) {
            if (parent == null) return null;
            if (parent.left == target) parent.left = null;
            else parent.right = null;
        } else if (target.left == null ^ target.right == null) {
            if (parent == null) return target.left == null ? target.right : target.left;
            if (parent.left == target) {
                if (target.left != null) parent.left = target.left;
                if (target.right != null) parent.left = target.right;
            } else {
                if (target.left != null) parent.right = target.left;
                if (target.right != null) parent.right = target.right;
            }
        } else {
            TreeNode tmp = target.right;
            TreeNode kill = tmp;
            while (tmp != null) {
                kill = tmp;
                tmp = tmp.left;
            }
            deleteNode(root, kill.val);
            target.val = kill.val;
        }
        return root;
    }

    public TreeNode[] deleteNodeHelp(TreeNode root, int key) {
        TreeNode[] nodes = new TreeNode[2];
        TreeNode parent = null;
        while (root != null && root.val != key) {
            parent = root;
            if (key < root.val) root = root.left;
            else root = root.right;
        }
        nodes[0] = parent;
        nodes[1] = root;
        return nodes;
    }


    /**
     * 108. 将有序数组转换为二叉搜索树
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelp(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBSTHelp(int[] nums, int l, int r) {
        if (l >= r) return null;
        int rootIdx = l + ((r - l - 1) >> 1);
        TreeNode root = new TreeNode(nums[rootIdx]);
        root.left = sortedArrayToBSTHelp(nums, l, rootIdx);
        root.right = sortedArrayToBSTHelp(nums, rootIdx + 1, r);
        return root;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     *
     * @param root
     * @return
     */
    int convertBSTPrevSum = 0;

    public TreeNode convertBST(TreeNode root) {
        convertBSTHelp(root);
        return root;
    }

    public void convertBSTHelp(TreeNode node) {
        if (node == null) return;
        convertBSTHelp(node.right);
        convertBSTPrevSum += node.val;
        node.val = convertBSTPrevSum;
        convertBSTHelp(node.left);
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