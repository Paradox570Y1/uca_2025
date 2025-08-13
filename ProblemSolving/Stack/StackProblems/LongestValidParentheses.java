import java.util.Stack;
public class LongestValidParentheses {
    private static int calMaxLength(Stack<Character> st){
        int open = 0;
        int close = 0;
        int res=0;
        while(!st.isEmpty()){
            if(st.pop() == ')')close++;
            else open++;
            if(open>close){
                res = Math.max(res,2*close);
                close=0;
                open=0;
            }
        }
        if(close != 0)res = Math.max(res,2*close);
        return res;
    }
    public static int longestValidParentheses(String s) {
        Stack<Character> st = new Stack<>();
        int maxLength=0;
        int open=0,close=0;
        for(char ch:s.toCharArray()){
            if((st.isEmpty() && ch == '(') || ch=='('){
                st.push(ch);
                open++;
            }
            else if(open>close){
                st.push(ch);
                close++;
            }
            else{
                maxLength = Math.max(maxLength,calMaxLength(st));
                open=0;
                close=0;
            }
        }
        maxLength = Math.max(maxLength,calMaxLength(st));
        return maxLength;
    }
    public static void main(String[] args){
      String pattern = ")()())";
      LongestValidParentheses obj = new LongestValidParentheses();
      System.out.println("Solution for pattern \"" + pattern + "\" is " + obj.longestValidParentheses(pattern));
    }
}
