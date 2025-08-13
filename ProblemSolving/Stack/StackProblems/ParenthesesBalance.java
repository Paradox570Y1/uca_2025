import java.util.Stack;

public class ParenthesesBalance{

  public static boolean isBalanced(String s){
    Stack<Character> st = new Stack<>();
    for(char ch:s.toCharArray()){
      if(ch == '(' || ch == '['){
        st.push(ch);
      } 
      else if(ch == ')' || ch == ']') {
        if(st.isEmpty()) return false;
	if((ch == ')' && st.peek() == '(') || (ch == ']' && st.peek() == '[')){
	  st.pop();
	} else {
	  return false;
	}
      } else {
        return false;
      }
    }
    return st.isEmpty();
  }

  public static void main(String[] args){
    String line = "))](]]]][)";
    if (line.length()%2 == 1) System.out.println("No");
    else System.out.println(isBalanced(line)?"Yes":"No");
  }
}
