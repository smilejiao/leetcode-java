import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author：JJL
 * @date: 2016年11月2日
 * @description:
 */
public class SolutionStack {
	
    /**20. Valid Parentheses
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        Map <Character, Integer> map = new HashMap<Character, Integer>();
        map.put('(', 1);
        map.put(')', 1);
        map.put('[', 2);
        map.put(']', 2);
        map.put('{', 3);
        map.put('}', 3);
        for (int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if (stack.isEmpty()) {
        		stack.push(ch);
        	} else{	
        		if (ch != stack.peek() && map.get(ch) == map.get(stack.peek())) {
        			stack.pop();
        		} else {
        			stack.push(ch);
        		}
        	}
        }
        if (stack.isEmpty()) {
        	return true;
        } else {
        	return false;
        }
    }
    //出栈条件：不相等 && hashMap的value相同
    //不使用hashmap，因为出栈的条件是栈顶='('，ch=')',所以当ch=')'时判断即可
    /*Stack
     * push(),栈顶添加
     * peek(),返回栈顶
     * pop(),返回栈顶，并删除
     * empty(),空
     * search(), 返回元素在堆栈中位置
     */
    
    /**22. Generate Parentheses 
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
    	ArrayList<String> list = new ArrayList<String>();
    	String str = new String();
        back(list, str, n, n);
        return list;
    }
    public void back(ArrayList<String> list, String str, int left, int right) {
    	if (left > right) {
    		return;
    	}
    	if (left == 0 && right == 0) {
    		list.add(str);
    	}
    	if (left > 0) {
    		back(list, str+"(", left-1, right);
    	}
    	if (right > 0) {
    		back(list, str+")", left, right-1);
    	}
    }
    //递归
    
    /**32. Longest Valid Parentheses
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int len = 0;
        int last = -1;//")"的位置
        for (int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if (ch == '(') {
        		stack.push(i);
        	} else if (ch == ')') {
        		if (stack.isEmpty()) {
        			last = i;
        		} else {
        			stack.pop();
        			if (stack.isEmpty()) {
        				len = Math.max(len, i-last);
        			} else {
        				len = Math.max(len, i-stack.peek());
        			}
        		}
        	}     		
        }
        return len;
    }
    //http://www.acmerblog.com/leetcode-solution-longest-valid-parentheses-6260.html
    
    
    /**301. Remove Invalid Parentheses
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        return null;
    }
    //http://blog.csdn.net/foreverling/article/details/49740665
    
    
    /**150. Evaluate Reverse Polish Notation
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < tokens.length; i++) {
        	String s = tokens[i];
        	if (!isSymbol(s)) {
        		stack.push(s);
        	} else {
        		int second = Integer.valueOf(stack.pop());
        		int first = Integer.valueOf(stack.pop());
        		int res = 0;
        		if (s.equals("+")) {
        			res = first + second;
        		}else if (s.equals("-")) {
        			res = first - second;
        		}else if (s.equals("*")) {
        			res = first * second;
        		} else if (s.equals("/")) {
        			res = first / second;
        		}
        		stack.push(String.valueOf(res));
        	}
        }
        return Integer.valueOf(stack.pop());
    }
    boolean isSymbol(String s) {
    	if (s.length() == 1) {
    		char ch = s.charAt(0);
    		if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
    			return true;
    		}
    	}
    	return false; 
    }
    //果然还是medium
    
    /**84. Largest Rectangle in Histogram
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        return 0;
    }
    //http://www.acmerblog.com/leetcode-solution-largest-rectangle-in-histogram-6230.html
    
    
}
