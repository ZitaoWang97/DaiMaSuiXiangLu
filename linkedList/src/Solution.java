import java.util.List;

public class Solution {
    /**
     * 203. 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 206.反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        ListNode next = head.next;
        ListNode prev = null;
        while (true) {
            cur.next = prev;
            if (next == null) break;
            prev = cur;
            cur = next;
            next = next.next;
        }
        return cur;
    }

    public ListNode reverseList2(ListNode head) {
        return reverseListRecurForward(head, null);
    }

    public ListNode reverseListRecurForward(ListNode cur, ListNode prev) {
        if (cur == null) return prev;
        ListNode next = cur.next;
        cur.next = prev;
        return reverseListRecurForward(next, cur);
    }

    public ListNode reverseList3(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode tail = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode cur = head;
        ListNode next = head.next;
        while (true) {
            cur.next = next.next;
            next.next = cur;
            prev.next = next;
            prev = cur;
            cur = cur.next;
            if (cur == null) break;
            next = cur.next;
            if (next == null) break;
        }
        return dummyHead.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        head = dummyHead;
        int sz = 0;
        while (head.next != null) {
            head = head.next;
            sz++;
        }
        head = dummyHead;
        for (int i = 0; i < sz - n; i++) {
            head = head.next;
        }
        head.next = head.next.next;
        return dummyHead.next;
    }

    /**
     * 面试题 02.07. 链表相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pointA = headA;
        ListNode pointB = headB;
        int countA = 0, countB = 0;
        while (pointA.next != null) {
            pointA = pointA.next;
            countA++;
        }
        while (pointB.next != null) {
            pointB = pointB.next;
            countB++;
        }
        if (pointA != pointB) return null;
        pointA = headA;
        pointB = headB;
        if (countA >= countB) {
            for (int i = 0; i < countA - countB; i++) {
                pointA = pointA.next;
            }
        } else {
            for (int i = 0; i < countB - countA; i++) {
                pointB = pointB.next;
            }
        }
        while (pointA != pointB) {
            pointA = pointA.next;
            pointB = pointB.next;
        }
        return pointA;
    }

    /**
     * 142. 环形链表 II
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode s = head, f = head;
        while (true) {
            if (f.next == null || f.next.next == null) return null;
            s = s.next;
            f = f.next.next;
            if (s == f) break;
        }
        s = head;
        while (s != f) {
            s = s.next;
            f = f.next;
        }
        return s;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * 707. 设计链表
 */
class MyLinkedList {
    int size;
    ListNode dummyHead;

    public MyLinkedList() {
        size = 0;
        dummyHead = new ListNode(0);
    }

    public int get(int index) {
        ListNode cur = dummyHead.next;
        if (index >= size) return -1;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = dummyHead.next;
        dummyHead.next = node;
        size++;
    }

    public void addAtTail(int val) {
        ListNode cur = dummyHead;
        while (cur.next != null) {
            cur = cur.next;
        }
        ListNode node = new ListNode(val);
        cur.next = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        ListNode cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode node = new ListNode(val);
        node.next = cur.next;
        cur.next = node;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index >= size) return;
        ListNode cur = dummyHead;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;
    }
}
