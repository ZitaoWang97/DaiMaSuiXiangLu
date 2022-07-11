import org.junit.Test;

public class Solution {
    @Test
    public void test() {
        String s = "We are happy.";
        replaceSpace(s);
    }

    /**
     * 344. 反转字符串
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * 541. 反转字符串 II
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int times = s.length() / (2 * k);
        for (int i = 0; i < times; i++) {
            reverseStrHelp(chars, 2 * k * i, 2 * k * i + k - 1);
        }
        if (s.length() % (2 * k) < k) {
            reverseStrHelp(chars, times * 2 * k, s.length() - 1);
        } else {
            reverseStrHelp(chars, times * 2 * k, times * 2 * k + k - 1);
        }
        String res = "";
        for (char c : chars) {
            res = res + c;
        }
        return res;
    }

    public void reverseStrHelp(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }


    /**
     * 剑指 Offer 05. 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        for (; i < s.length(); i++) {
            if (sb.charAt(i) == ' ') sb.append("  ");
        }
        i = s.length() - 1;
        int j = sb.length() - 1;
        while (i >= 0) {
            if (sb.charAt(i) != ' ') {
                sb.setCharAt(j, sb.charAt(i));
            } else {
                sb.setCharAt(j--, '0');
                sb.setCharAt(j--, '2');
                sb.setCharAt(j, '%');
            }
            i--;
            j--;
        }
        return sb.toString();
    }

    /**
     * 151. 颠倒字符串中的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        int head = 0;
        while (s.charAt(head) == ' ') {
            head++;
        }
        int tail = s.length() - 1;
        while (s.charAt(tail) == ' ') {
            tail--;
        }
        s = s.substring(head, tail + 1);
        char[] chars = s.toCharArray();
        int slow = 0, fast = 0;
        while (fast < s.length()) {
            if (!(fast > 0 && chars[fast] == chars[fast - 1] && chars[fast] == ' ')) {
                chars[slow++] = chars[fast];
            }
            fast++;
        }
        char[] newChars = new char[slow];
        for (int k = 0; k < slow; k++) {
            newChars[k] = chars[k];
        }
        chars = newChars;
        reverseWordsHelp(chars, 0, chars.length - 1);
        int i = 0;
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] == ' ') {
                reverseWordsHelp(chars, i, j - 1);
                i = j + 1;
            }
        }
        reverseWordsHelp(chars, i, chars.length - 1);
        return new String(chars);
    }

    public void reverseWordsHelp(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        reverseLeftWordsHelp(chars, 0, s.length() - 1);
        reverseLeftWordsHelp(chars, 0, s.length() - n - 1);
        reverseLeftWordsHelp(chars, s.length() - n, s.length() - 1);
        return new String(chars);
    }

    public void reverseLeftWordsHelp(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }


    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int[] next = getNext(needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && needle.charAt(j + 1) != haystack.charAt(i)) {
                j = next[j];
            }
            if (needle.charAt(j + 1) == haystack.charAt(i)) {
                j++;
            }
            if (j == needle.length() - 1) return i - needle.length() + 1;
        }
        return -1;
    }

    public int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        int j = -1;
        next[j + 1] = j;
        for (int i = 1; i < needle.length(); i++) {
            while (j >= 0 && needle.charAt(j + 1) != needle.charAt(i)) {
                j = next[j];
            }
            if (needle.charAt(j + 1) == needle.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * 459. 重复的子字符串
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int[] next = getNext(s);
        return (s.length() % (s.length() - next[next.length - 1] - 1) == 0) && (next[next.length - 1] != -1);
    }
}
