import java.util.*;

public class MergeKLists {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        static ListNode fromArray(int[] arr) {
            ListNode dummy = new ListNode(-1), temp = dummy;
            for (int x : arr) {
                temp.next = new ListNode(x);
                temp = temp.next;
            }
            return dummy.next;
        }
        int[] toArray() {
            List<Integer> list = new ArrayList<>();
            ListNode curr = this;
            while (curr != null) {
                list.add(curr.val);
                curr = curr.next;
            }
            return list.stream().mapToInt(i -> i).toArray();
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) minHeap.add(node);
        }
        ListNode dummy = new ListNode(-1), temp = dummy;
        while (!minHeap.isEmpty()) {
            ListNode root = minHeap.poll();
            temp.next = new ListNode(root.val);
            temp = temp.next;
            if (root.next != null) minHeap.add(root.next);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        MergeKLists sol = new MergeKLists();

        ListNode[] lists1 = {
            ListNode.fromArray(new int[]{1,4,5}),
            ListNode.fromArray(new int[]{1,3,4}),
            ListNode.fromArray(new int[]{2,6})
        };
        int[] expected1 = {1,1,2,3,4,4,5,6};
        assert Arrays.equals(sol.mergeKLists(lists1).toArray(), expected1);

        ListNode[] lists2 = {};
        assert sol.mergeKLists(lists2) == null;

        ListNode[] lists3 = {null, null};
        assert sol.mergeKLists(lists3) == null;

        ListNode[] lists4 = {ListNode.fromArray(new int[]{1,2,3})};
        int[] expected4 = {1,2,3};
        assert Arrays.equals(sol.mergeKLists(lists4).toArray(), expected4);

        ListNode[] lists5 = {
            ListNode.fromArray(new int[]{1,10,20}),
            ListNode.fromArray(new int[]{5,15}),
            ListNode.fromArray(new int[]{2,3,4,6,7,8})
        };
        int[] expected5 = {1,2,3,4,5,6,7,8,10,15,20};
        assert Arrays.equals(sol.mergeKLists(lists5).toArray(), expected5);

        System.out.println("All assertions passed!");
    }
}

