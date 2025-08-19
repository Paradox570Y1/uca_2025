import java.util.PriorityQueue;
public class kthLargestElement{
  int[] array;
  kthLargestElement(int[] arr){
    this.array = arr;
  }
  public int getKthLargestElement(int k){
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);
    for(int ele:array){
      maxHeap.offer(ele);
    }
    while(k>1){
      maxHeap.poll();
      k--;
    }
    return maxHeap.peek();
  }
  public static void main(String[] args){
    int arr[] = {3,2,4,1,5,2,5};
    kthLargestElement obj = new kthLargestElement(arr);
    System.out.println(obj.getKthLargestElement(3));
  }
}
