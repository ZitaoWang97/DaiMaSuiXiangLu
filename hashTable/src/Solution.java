import org.junit.Test;

import java.util.*;

public class Solution {
    @Test
    public void test() {

    }

    /**
     * 242. 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt != 0) return false;
        }
        return true;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnts = new int[26];
        for (char c : magazine.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt < 0) return false;
        }
        return true;
    }

    /**
     * 49. 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] cnts = new int[26];
            for (char c : str.toCharArray()) {
                cnts[c - 'a']++;
            }
            String s = "";
            for (int i = 0; i < 26; i++) {
                if (cnts[i] > 0) {
                    for (int j = 0; j < cnts[i]; j++) {
                        s += ('a' + i);
                    }
                }
            }
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(str);
        }
        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] cnts = new int[26];
        for (char c : p.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            if (i == 0) {
                for (int j = 0; j < p.length(); j++) {
                    cnts[s.charAt(j) - 'a']--;
                }
            } else {
                cnts[s.charAt(i - 1) - 'a']++;
                cnts[s.charAt(i + p.length() - 1) - 'a']--;
            }
            boolean flag = true;
            for (int j = 0; j < 26; j++) {
                if (cnts[j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) res.add(i);
        }
        return res;
    }

    /**
     * 349. 两个数组的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            if (set1.contains(i)) set2.add(i);
        }
        int[] res = new int[set2.size()];
        int i = 0;
        for (Integer integer : set2) {
            res[i++] = integer;
        }
        return res;
    }

    /**
     * 350. 两个数组的交集 II
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            res[i++] = integer;
        }
        return res;
    }

    /**
     * 202. 快乐数
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = isHappyHelp(n);
        }
        return true;
    }

    private int isHappyHelp(int n) {
        int sum = 0;
        while (n != 0) {
            int b = n % 10;
            sum += b * b;
            n /= 10;
        }
        return sum;
    }

    /**
     * 1. 两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * 454. 四数相加 II
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                int sum = n1 + n2;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int res = 0;
        for (int n3 : nums3) {
            for (int n4 : nums4) {
                int sum = n3 + n4;
                if (map.containsKey(-sum)) res += map.get(-sum);
            }
        }
        return res;
    }

    /**
     * 15. 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    /**
     * 18. 四数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if ((long) nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if ((long) nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) continue;
                int l = j + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum > target) {
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                        l++;
                    }
                }
            }
        }
        return res;
    }
}
