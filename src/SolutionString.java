import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

/**
 * @author��JJL
 * @date: 2016��10��27��
 * @description:
 */
public class SolutionString {
	
    /**125. Valid Palindrome 
     * @param s
     * @return ���ģ�ֻ������ĸ�����ַ��������˴�Сд
     */
    public boolean isPalindrome(String s) {
        if (s.length() <= 1) {
        	return true;
        }
        int start = 0, end = s.length()-1;
        while (start <= end) {
        	char ch1 = s.charAt(start);
        	char ch2 = s.charAt(end);
        	if (!((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z') || (ch1 >= '0' && ch1 <= '9'))) {
        		start += 1;
        	} else if (!((ch2 >= 'a' && ch2 <= 'z') || (ch2 >= 'A' && ch2 <= 'Z') || (ch2 >= '0' && ch2 <= '9'))){
        		end -= 1;
        	} else if (Character.toLowerCase(ch1) != Character.toLowerCase(ch2)) { //!(ch1 == ch2 || ch1-ch2 == 32 || ch2-ch1 == 32)
        		return false;
        	} else {
        		start += 1;
        		end -= 1;
        	}
        }
        return true;
    }
    //�ж��ַ�����Ļ��a~z || A~Z, 97~122, 65~90
    //ͳһת��Ϊ��д����Сд�ٱȽ�, Character.toLowerCase()
    
    /**28. Implement strStr() 
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 == 0) {
        	return 0;
        }
        for (int i = 0; i <= len1-len2; i++) {
        	int j,ii;
        	for (j = 0, ii = i; j < len2 && ii < len1; j++, ii++) {
        		if (haystack.charAt(ii) != needle.charAt(j)) {
        			break;
        		} 
        	}
        	if (j == len2) {
        		return i;
        	}
        }
        return -1;
    }
    //ע��i�ķ�Χ������һֱ�Ƚϵ���1��ĩβ����len1-len2�Ϳ�����
    
    /**8. String to Integer (atoi)
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        int len = str.length();
        if (len <= 0) {
        	return 0;
        }
        boolean symbol = false;
        boolean number = false;
        String ret = "";
        int i = 0;
        for (; i < len; i++) {
        	if (str.charAt(i) != ' ') {
        		break;
        	}
        }
        
        for (; i < len; i++) {
        	char ch = str.charAt(i);
        	if (ch >= '0' && ch <= '9') {
        		number = true;
        		ret = ret + String.valueOf(ch);
        	} else if (ch == '+' || ch == '-') {
        		if (symbol == false && number == false) {
        			symbol = true;
        			ret = ret + String.valueOf(ch);
        		} else {
        			break;
        		}
        	} else {
        		break;
        	}
        }
        double num = 0;
        boolean flag = true;
        for (i = 0; i < ret.length(); i++) {
        	if (ret.charAt(i) == '-') {
        		flag = false;
        	} else if (ret.charAt(i) == '+') {
        		flag = true;
        	} else {
        		if (flag == true) {
        			num = 10*num + ret.charAt(i)-'0';
        			if (num > Integer.MAX_VALUE) {//= -2147483647
        				return 2147483647;
        			}
        		} else {
        			num = 10*num - (ret.charAt(i)-'0');
        			if (num < Integer.MIN_VALUE) { //= 2147483647
        				return -2147483648;
        			}
        		}
        	}
        }
        return (int)num;
    }
    //"  -0012a42", ����ret����
    //"-1"�� û�аѷ����ƽ�
    //"2147483648"->-2147483647, "-2147483649"->2147483647
    //Integer.MAX_VALUE,Integer.MIN_VALUE,����ʹ��double����long�������ж�
    //�����ڵ�һ�δ���ʱֱ�Ӽ���
    
    /**67. Add Binary
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int len = (len1 >= len2)? len1+1: len2 +1;
        int sum= 0;
        char[] ret = new char[len];
        for (int i = len-1, j = len1-1, k = len2-1; i >= 0; i--) {	
        	if (j >=0 && k >= 0) {
        		int na = a.charAt(j)-'0';
            	int nb = b.charAt(k)-'0';
        		sum = sum + na + nb;
        		j--;
        		k--;
        	} else if ( j >=0) {
        		int na = a.charAt(j)-'0';
        		sum = sum + na;
        		j--;
        	} else if (k >= 0) {
            	int nb = b.charAt(k)-'0';
        		sum = sum + nb;
        		k--;
        	}
        	if (sum >= 2) {
        		ret[i] = (char) ((sum-2) + '0');
        		sum = 1;
        	} else {
        		ret[i] = (char) (sum + '0') ;
        		sum = 0;
        	}
        }
        String str = "";
        if (ret[0] != '0') {
        	str += String.valueOf(ret[0]);
        }
        for (int i = 1; i < len; i++) {
        	str += String.valueOf(ret[i]);
        }
        return str;
    }
    //���ַ����ַ������˷���ʱ��
    
    /**5. Longest Palindromic Substring
     * @param s
     * @return �������Ӵ�
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
        	return "";
        }
        int maxlen  = 0;
        int len = s.length();
        String res = "";
        for (int i = 0 ; i < 2*len-1; i++) {
        	int left = i/2;
        	int right = i/2;
        	if (i%2 == 1) {
        		right++;
        	}
        	String str = lengthOfPalindrome(s, left, right);
        	if (maxlen < str.length()) {
        		maxlen = str.length();
        		res = str;
        	}
        }
        return res;
    }
    public String lengthOfPalindrome (String s, int left, int right) {
    	while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
    		left--;
    		right++;
    	}
    	return s.substring(left+1, right);
    	
    }
    //�ַ���n������Ϊn + n-1(��϶),��ÿһ������������ɨ�裬O(n^2)
    public String longestPalindrome1(String s) {
    	if (s == null || s.length() == 0) {
    		return "";
    	}
    	int len = s.length();
    	boolean[][] palin = new boolean[len][len];
    	String res = "";
    	int maxlen = 0;
    	for (int i = len-1; i >= 0; i--) {
    		for (int j = i; j < len; j++) {
    			if (s.charAt(i) == s.charAt(j) && (j-i<=2 || palin[i+1][j-1])) {
    				palin[i][j] = true;
    				if (maxlen < j-i+1) {
    					maxlen = j-i+1;
    					res = s.substring(i,j+1); 
    				}
    			}
    		}
    	}
    	return res;
    }
    //��̬�滮���������ռ�洢i~j�Ƿ����
    
    /**10. Regular Expression Matching
     * @param s
     * @param p
     * @return
     */
//    public boolean isMatch(String s, String p) {
//        
//    }
    
    /**14. Longest Common Prefix
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
        	return "";
        } else if (strs.length == 1) {
        	return strs[0];
        }
        int len = strs.length;
        String s = strs[0];
        for (int i = 1; i < len; i++) {
        	String t = strs[i];
        	int m=0, n=0;
        	while ( m<s.length() && n<t.length() && s.charAt(m) == t.charAt(n)) {
        		m++;
        		n++;
        	}
        	s = s.substring(0, m);
        }
        return s;
    }
    //����ֱ��ac������substringû�ô�
    
    /**12. Integer to Roman 
     * @param num
     * @return ����ת������
     */
    public String intToRoman(int num) {
    	 int[] n = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    	 String[] r = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    	 String s = "";
    	 for(int i = 0; num > 0; num %= n[i], ++ i)
    		 for(int j = 0, k = num / n[i]; j < k; ++ j) {
    			 s += r[i];
    		 }
    	return s;
    }
    
    /**13. Roman to Integer
     * @param s
     * @return ��������ת����
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int ret = map.get(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
        	if (map.get(s.charAt(i)) > map.get(s.charAt(i-1))) {
        		ret = ret + map.get(s.charAt(i)) - 2*map.get(s.charAt(i-1));
        	} else {
        		ret = ret + map.get(s.charAt(i));
        	}
        }
        return ret;
    }
    /*1. ������ͬ������д��ʾ��������Щ�������ֵĺͣ��� III=3��
      2. С�����ڴ�����ǰ���ʾ�������ô����ּ�ȥС���֣��� IV��4��
      3. С�����ڴ����ֺ����ʾ�������ô����ּ���С���֣��� VI=6;*/
    //ֱ�ӴӺ���ǰ�㣬���ÿ�����ô������ˣ�����С�ڴ�ǰ��Ҫ�ô��С֮�⣬�������ۼ�
    
    /**38. Count and Say
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
        	return "1";
        }
        String s = "1";
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        int i;
        for (int round = 1; round < n; round++) {
        	count = 1;
        	buffer.setLength(0);
        	for (i = 1; i < s.length(); i++) {
        		if (s.charAt(i) == s.charAt(i-1)) {
        			count++;
        		} else {
        			buffer.append(count).append(s.charAt(i-1));
        			count = 1;
        		}
        	}
        	buffer.append(count).append(s.charAt(i-1));
        	s  = buffer.toString();
        }
        return buffer.toString();
    }
    
    /**438. Find All Anagrams in a String
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
    	List<Integer> ret = new ArrayList<Integer>();
    	int slen = s.length();
    	int plen = p.length();
    	int[] h1 = new int[26];
    	int[] h2 = new int[26];
    	for (int i = 0; i < plen; i++) {
    		h1[p.charAt(i)-'a']++;
    	}
    	for (int i = 0; i < slen; i++) {
    		h2[s.charAt(i)-'a']++;
    		if (i >= plen) {
    			h2[s.charAt(i-plen)-'a']--;//ʼ�ձ���h2��ֻ��plen��ֵ
    		}
    		if (Arrays.equals(h1, h2)) {
    			ret.add(i-plen+1);
    		}
    	}
    	return ret;
    }
    //���Ĺ��ʷ������������ĸ���������Ŀû�иı䣬ֻ�Ǹı�����ĸ������˳��
    //����hash��˼�룬��������洢�����ַ��ĸ�����ע�����sʱ�� i >= plen,֮ǰ��Ԫ�ظ���Ҫ-1
    //Java���ж����������Ƿ���ȣ�Arrays.equals��arr1��arr2��
    
    /**49. Group Anagrams 
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ret = new ArrayList<List<String>>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str: strs) {
        	char[] arr = str.toCharArray();
        	Arrays.sort(arr);
        	String sortStr = String.valueOf(arr);
        	if (map.containsKey(sortStr)) {
        		List<String> list = map.get(sortStr);
        		list.add(str);
        		map.put(sortStr, list);
        	} else {
        		List<String> list = new ArrayList<String>();
        		list.add(str);
        		map.put(sortStr, list);
        	}
        }
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
        	Map.Entry entry = (Map.Entry) iter.next();
        	ret.add((List<String>) entry.getValue());
        }
//        for (Entry <String, List<String>> entry: map.entrySet()) {
//        	ret.add(entry.getValue());
//        }
        return ret;        
    }
    //sb���ҿ�ʼ��ҪΪÿһ��string����һ����Ӧ������
    //Ϊÿһ��string���򣬲�����map��������string�� ����ǰ��string�����������hashMapΪ��<stirng,list<string>>
    //��֪��Ϊʲô����һ�ֱ���map�ķ�ʽ�᣺ error: cannot find symbol: class Entry
    
    /**71. Simplify Path
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
    	Stack<String> s = new Stack<String>();
    	String[] strs = splitString (path);
    	for (int i = 0; i < strs.length; i++) {
    		if (strs[i].equals(".")) {
    			continue;
    		} else if (strs[i].equals("..")) {
    			if (!s.empty()) {
    				s.pop();
    			}
    		} else {
    			s.push(strs[i]);
    		}
    	}
    	String ret = "";
    	while (!s.empty()) {
    		ret = "/" + s.pop() + ret;
    	}
    	if (ret.isEmpty()) {
    		ret = "/";
    	}
        return ret;
    }
    public String[] splitString(String s) {
    	String temp = "";
    	Queue<String> arr = new LinkedList<String>();
    	int count = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == '/') {
    			if (temp != "") {
    				arr.offer(temp);
    				count++;
    			}
    			temp = "";
    		} else {
    			temp += s.charAt(i);
    		}
    	}
    	if (temp != "") {
    		arr.offer(temp);
    		count++;
    	}
    	String[] ret = new String[count]; 
    	int i = 0;
    	while (!arr.isEmpty()) {
    		ret[i++] = arr.poll();
    	}
    	return ret;
    }
    //Unix-style��path��: "."��ʾ��ǰĿ¼�µ���Ŀ¼��".."��ʾ������һ��Ŀ¼��"..."����
    /*������'/'����ַ�����֮����ջ�����������..���ջ�����ƴ�ӵ�ʱ�����'/' */
    //Queue: offer(), pop(), isEmpty()
    //String�Ƚ����: equals()
    
    /**58. Length of Last Word
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }
        int len = 0;
        boolean flag = false;
        for (int i = s.length()-1; i >= 0; i--) {
        	if (s.charAt(i) == ' ') {
        		if (flag) {
        			return len;
        		} else {
        			continue;
        		}
        		
        	} else {
        		len += 1;
        		flag = true;
        	}
        }
        return len;
    }
    //��������������һ����һ��ac��
    
    
    
}
