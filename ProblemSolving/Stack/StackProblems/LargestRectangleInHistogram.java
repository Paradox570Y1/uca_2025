import java.util.Stack;
class Solution {
   }
public class LargestRectangleInHistogram{
  public static int largestRectangleArea(int[] heights) {
    int n = heights.length;
    if(n==1)return heights[0];
    Stack<Integer> st = new Stack<>();
    int nse[] = new int[n];
    for(int i=0;i<n;i++){
      nse[i] = n;
      while(!st.isEmpty() && heights[st.peek()]>heights[i]){
        nse[st.pop()] = i;
      }
      st.push(i);
    }
    int maxi = 0;
    st.clear();
    for(int i=0;i<n;i++){
      while(!st.isEmpty() && heights[st.peek()]>heights[i])st.pop();
	int pse = (st.isEmpty())?-1:st.peek();
        int area = heights[i]*(nse[i]-pse-1);
        maxi = Math.max(maxi,area);
        st.push(i);
      }
    return maxi;
  }

  public static void main(String[] args){
    Solution obj = new Solution();
    int arr[] = {2,1,5,6,2,3};
    int maxArea = largestRectangleArea(arr);
    System.out.println("Area of largest Rectangle is " + maxArea);
  }
}
