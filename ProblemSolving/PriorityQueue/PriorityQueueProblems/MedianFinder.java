import java.util.PriorityQueue;

class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap; 
    
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a,b) -> b - a); 
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return (maxHeap.size() > minHeap.size()) ? maxHeap.peek() : minHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        
        mf.addNum(1);
        assert mf.findMedian() == 1.0 : "Test 1 failed";
        
        mf.addNum(2);
        assert mf.findMedian() == 1.5 : "Test 2 failed";
        
        mf.addNum(3);
        assert mf.findMedian() == 2.0 : "Test 3 failed";
        
        mf.addNum(4);
        assert mf.findMedian() == 2.5 : "Test 4 failed";
        
        mf.addNum(5);
        assert mf.findMedian() == 3.0 : "Test 5 failed";
        
        mf.addNum(6);
        assert mf.findMedian() == 3.5 : "Test 6 failed";
        
        mf.addNum(10);
        assert mf.findMedian() == 4.0 : "Test 7 failed";
        
        mf.addNum(-5);
        assert mf.findMedian() == 3.5 : "Test 8 failed";
        
        System.out.println("All test cases passed!");
    }
}
