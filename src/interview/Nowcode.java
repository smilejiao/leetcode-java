package interview;

import java.util.Stack;

public class Nowcode {
	/**��Ѷ ��һ���ַ����Ĵ�д��ĸ�ŵ��ַ����ĺ��棬�����ַ������λ�ò��䣬�Ҳ����������Ŀռ䡣 */
	public static String remove(String str) {
		if (str == null || str.length() <= 1)
			return str;
		int len = str.length();
		for (int i=0; i<len; i++) {
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				str = str.substring(0, i) + str.substring(i+1, len) + str.substring(i, i+1);
			}
		}
		
		return str;
	}
	
	
	public static String reverse(String str) {
		if (str == null || str.length() <= 1)
			return str;
		String[] arr = str.split(" ");
		String ret = "";
		Stack stack = new Stack();
		for (int i=0, len=arr.length; i<len; i++) {
			stack.push(arr[i]);
		}
		while(!stack.isEmpty()) {
			ret = ret + " " + stack.pop();
		}
		return ret;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println(reverse("I like beijing."));
	}
	
	
	
}
