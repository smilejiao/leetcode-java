package again;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Jiaojl
 *
 */
public class Solution_String {
    /**125. Valid Palindrome 
     * @param s
     * @returnboolean 判断是不是回文数
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1)
        	return true;
        for (int i=0, j=s.length()-1; i<=j; ) {
        	char ch1 = s.charAt(i);
        	char ch2 = s.charAt(j);
        	if (isValidChar(ch1) && isValidChar(ch2)) {
        		if(Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) {
        			i++;
        			j--;
        		} else {
        			return false;
        		}	
        	} else if (!isValidChar(ch1)){
        		i++;
        	} else if (!isValidChar(ch2)) {
        		j--;
        	}
        }
        return true;
    }
    public boolean isValidChar(char ch1) {
    	if ((ch1 >= 'a' && ch1 <= 'z') || (ch1 >= 'A' && ch1 <= 'Z') || (ch1 >= '0' && ch1 <= '9'))
        	return true;
    	return false;
    }
    
    
    
    /**28. Implement strStr() 
     * @param haystack
     * @param needle
     * @returnint   needle是不是haystack的子串，是的话就返回这个子串
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null)
        	return -1;
        if (haystack.length() < needle.length())
        	return -1;
        int hlen = haystack.length();
        int nlen = needle.length();
        for (int i=0; i<=hlen-nlen; i++) {
        	String str = haystack.substring(i, i+nlen);
        	if (str.equals(needle)) {
        		return i;
        	}
        }
        return -1;
    }

	public int strStr1(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}
    
    
	
    /**8. String to Integer (atoi) 
     * @param str
     * @returnint 字符串->数字
     */
	   public int myAtoi(String str) {
	        if (str == null || str.length() == 0)
	        	return 0;
	        boolean negetive = true;
	        boolean start = false;
	        double ret = 0;
	        for (int i=0, len=str.length(); i<len; i++) {
	        	char ch = str.charAt(i);
	        	if (!start && ch == '-') {
	        		start = true;
	        		negetive = false;
	        	} else if (!start && ch == '+') {
	        		start = true;
	        		negetive = true;
	        	}else if (!start && ch == ' '){
	        		continue;
	        	}else if (!(ch>='0' && ch<='9')) {      		
	        		break;
	        	}else {
	        		start = true;
	        		ret = ret*10 + (ch-'0');
	        	}
	        }
	        ret = negetive? ret: -ret;
	        if (negetive && ret > Integer.MAX_VALUE) {
	            return Integer.MAX_VALUE;
	        }else if (!negetive && ret < Integer.MIN_VALUE) {
	        	return Integer.MIN_VALUE;
	        }
	        return (int)ret;
	    }
    
    
    
    /**67. Add Binary 
     * @param a
     * @param b
     * @returnString
     */
	   public String addBinary(String a, String b) {
	        if (a == null || a.length() ==0)
	        	return b;
	        if (b == null || b.length() == 0)
	        	return a;
	        String ret = "";
	        char add = '0';
	        int i=a.length()-1, j=b.length()-1;
	        while (i>=0 && j>=0) {
	        	int sum = (a.charAt(i)-'0') + (b.charAt(j)-'0') + (add - '0');
	        	if (sum >= 2) {
	        		add = '1';
	        		String s = (sum-2)==0? "0" :"1";
	        		ret = s+ret;
	        	} else {
	        		add = '0';
	        		ret = String.valueOf(sum)+ret;
	        	}
	        	i--;
	        	j--;
	        }
	        while(i>=0) {
	        	int sum = (a.charAt(i)-'0') +  (add - '0');
	        	if (sum >= 2) {
	        		add = '1';
	        		String s = (sum-2)==0? "0" :"1";
	        		ret = s+ret;
	        	}else {
	        		add = '0';
	        		ret = String.valueOf(sum)+ret;
	        	}
	        	i--;
	        }
	        while(j>=0) {
	        	int sum = (b.charAt(j)-'0') +  (add - '0');
	        	if (sum >= 2) {
	        		add = '1';
	        		String s = (sum-2)==0? "0" :"1";
	        		ret = s+ret;
	        	}else {
	        		add = '0';
	        		ret = String.valueOf(sum)+ret;
	        	}
	        	j--;
	        }
	        if (add != '0') {
	        	ret = String.valueOf(add) + ret;
	        }
	        return ret;
	    }
    
	public String addBinary1(String a, String b) {
		int m = a.length();
		int n = b.length();
		int carry = 0;
		String res = "";
		int maxLen = Math.max(m, n);
		for (int i = 0; i < maxLen; i++) {
			int p = 0, q = 0;
			if (i < m)
				p = a.charAt(m - 1 - i) - '0';
			else
				p = 0;

			if (i < n)
				q = b.charAt(n - 1 - i) - '0';
			else
				q = 0;

			int tmp = p + q + carry;
			carry = tmp / 2;
			res += tmp % 2;
		}
		return (carry == 0) ? res : "1" + res;
	}
	
	
    /**5. Longest Palindromic Substring 
     * @param s
     * @returnString 最大回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1){
        	return s;
        }
        String ret = "";
        int maxLen = 0;
        for (int i=0, len = s.length(); i<len*2-1; i++) {
        	int left = i/2;
        	int right = i/2+i%2;
        	String curStr = PalindromLength(s, left, right);
        	if (curStr.length() > maxLen) {
        		maxLen = curStr.length();
        		ret = curStr;
        	}
        }
        return ret;
    }
    public String PalindromLength(String str, int left, int right) {
    	for (; left>=0 && right < str.length(); left--, right++) {
    		if (str.charAt(left) != str.charAt(right)) {
    			break;
    		}
    	}
    	return str.substring(left+1, right);
    }
    //字符串n，中心为n + n-1(空隙),从每一个中心往两边扫描，O(n^2)
    
    
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
    //动态规划，申请额外空间存储i~j是否回文
    
    
    
    /**10. Regular Expression Matching
     * @param s
     * @param p
     * @returnboolean 正则匹配，字符串s是否满足p，支持'.' and '*'.
     */
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
        	return s.length() == 0;
        }
        if (p.length() == 1)
        	return (s.length() == 1) && (p.charAt(0) == s.charAt(0)|| p.charAt(0) == '.');
        if (p.charAt(1) != '*') {
        	if (s.length() == 0)
        		return false;
        	else {
        		return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
        	}
        } else {//下一个是*
        	while (s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
        		if (isMatch(s, p.substring(2)))
        			return true;
        		s = s.substring(1);
        	}
        }
        return isMatch(s, p.substring(2));
        	
    }
    //递归
    
    public boolean isMatch1(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() == 1) {
            return s.length() == 1
                && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) ;
        }
        // p.length() i bigger than 1 here
        if (p.charAt(1) == '*') {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            return s.length() > 0
                && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))
                && isMatch(s.substring(1), p);
        } else {
            return s.length() > 0
                && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))
                && isMatch(s.substring(1), p.substring(1));
        }
    }
    
    
    
    /**14. Longest Common Prefix 
     * @param strs
     * @returnString 数组中，最长的相同的前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) 
        	return "";
        if (strs.length == 1)
        	return strs[0];
        int index = 0;
        while (index < strs[0].length()) {
        	boolean flag = true;
        	for (int i=1, len = strs.length; i<len; i++) {
            	if (index >= strs[i].length() || strs[0].charAt(index) != strs[i].charAt(index)) {
            		flag = false;
            		break;
            	}
            }
        	if (!flag) {
        		break;
        	}
        	index += 1;
        }
        return strs[0].substring(0, index);
    } 
    
    
    /**12. Integer to Roman 
     * @param num
     * @returnString
     */
    public String intToRoman(int num) {
    	int[] n = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
   	 	String[] r = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
   	 	String ret = "";
   	 	for (int i=0; num>0; num=num%n[i], i++) {
	   	 	for(int j = 0, k = num / n[i]; j < k; ++ j) {
				 ret += r[i];
			 }
   	 	}
   	 	return ret;
    }
    //从最大往下找
    
    public String intToRoman1(int num) {
        if(num>=1000) return "M"+intToRoman(num-1000);
        if(num>=900) return "CM"+intToRoman(num-900);
        if(num>=500) return "D"+intToRoman(num-500);
        if(num>=400) return "CD"+intToRoman(num-400);
        if(num>=100) return "C"+intToRoman(num-100);
        if(num>=90) return "XC"+intToRoman(num-90);
        if(num>=50) return "L"+intToRoman(num-50);
        if(num>=40) return "XL"+intToRoman(num-40);
        if(num>=10) return "X"+intToRoman(num-10);
        if(num>=9) return "IX"+intToRoman(num-9);
        if(num>=5) return "V"+intToRoman(num-5);
        if(num>=4) return "IV"+intToRoman(num-4);
        if(num>=1) return "I"+intToRoman(num-1);
        return "";
    }
    
    
    /**13. Roman to Integer 
     * @param s
     * @returnint
     */
    public int romanToInt(String s) {
        if (s == null || s.length() == 0)
        	return 0;
        Map<Character , Integer> map = new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int ret = map.get(s.charAt(0));
        for (int i=1, len=s.length(); i<len; i++) {
        	int cur = map.get(s.charAt(i));
        	int pre = map.get(s.charAt(i-1));
        	if (cur <= pre) {
        		ret += cur;
        	} else {
        		ret += cur-2*pre;
        	}
        }
        return ret;
    }
    
    
    
    /**38. Count and Say 
     * @param n
     * @returnString
     */
    public String countAndSay(int n) {
        if (n == 0)
        	return "";
        else if (n == 1)
        	return "1";
        String ret = "1";
        for (int i=2; i<=n; i++) {
        	String cur = "";
        	for (int j=0; j<ret.length();) {
        		int k=j+1;
        		for (; k<ret.length() && ret.charAt(k)==ret.charAt(k-1); k++);
        		cur += String.valueOf(k-j) + String.valueOf(ret.charAt(j));
        		j = k;
        	}
        	ret = cur;
        }
        return ret;
    }
    
    public String countAndSay1(int n) {
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
     * @returnList<Integer> 找到子串的开始位置
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if (s == null || s.length() == 0)
        	return list;
        int sLen = s.length();
        int pLen = p.length();
        int[] sArr = new int[26];
        int[] pArr = new int[26];
        for (int i=0; i<pLen; i++) {
        	pArr[p.charAt(i)-'a']++;
        }
        for (int i=0; i<sLen; i++) {
        	sArr[s.charAt(i)-'a']++;
        	if (i >= pLen) {
        		sArr[s.charAt(i-pLen)-'a']--;
        	}
        	if (Arrays.equals(pArr, sArr)) {
        		list.add(i-pLen+1);
        	}
        }
        return list;
    }
    
    
    
    /**49. Group Anagrams
     * @param strs
     * @returnList<List<String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> list = new ArrayList<List<String>>();
    	if (strs == null || strs.length == 0)
    		return list;
    	for (int i=0; i<strs.length; i++) {
    		char[] arr = strs[i].toCharArray();
    		Arrays.sort(arr);
    		strs[i] = String.valueOf(arr);
    	}
    	List<String> item = new ArrayList<String>();
    	item.add(strs[0]);
    	
    	for (int i=1; i<strs.length; i++) {
    		if (strs[i].equals(strs[i-1])) {
    			item.add(strs[i]);
    		} else {
    			list.add(new ArrayList(item));
    			item.clear();
    			item.add(strs[i]);
    		}
    	}
    	list.add(new ArrayList(item));
    	return list;
    }
    //这样不行啊！！！，改变了字符串！！！
    
    public List<List<String>> groupAnagrams1(String[] strs) {
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
        return ret;        
    }
    //为每一个string排序，并存入map（排序后的string， 排序前的string），所以设计hashMap为：<stirng,list<string>>

    
    /**71. Simplify Path 
     * @param path
     * @returnString
     */
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0)
        	return "";
        Stack<String> s = new Stack<String>();
        String[] strs = splitString(path);
        for (int i=0, len=strs.length; i<len; i++) {
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
    public String[] splitString(String str) {
    	Queue<String> q = new LinkedList<String>();
    	String s = "";
    	int count = 0;
    	for (int i=0, len=str.length(); i<len; i++) {
    		char ch = str.charAt(i);
    		if (ch == '/' ) {
    			if (!s.equals("")) {
    				q.offer(s);
    				count ++;
    			}
    			s = "";
    		} else {
    			s += String.valueOf(ch);
    		}
    	}
    	if (!s.equals("")) {
    		q.offer(s);
			count ++;
    	}
    	String[] ret = new String[count]; 
    	int i = 0;
    	while (!q.isEmpty()) {
    		ret[i++] = q.poll();
    	}
    	return ret;
    }
    //Unix-style的path中: "."表示当前目录下的子目录，".."表示返回上一级目录，"..."保留
    /*首先以'/'拆分字符串，之后用栈来操作，如果..则出栈，最后拼接的时候加上'/' */
    //Queue: offer(), pop(), isEmpty()
    //String比较相等: equals()
    
    
    /**58. Length of Last Word
     * @param s
     * @returnint
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ') {
        	end--;
        }
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ') {
        	start--;
        }
        return end-start;
    }
    //顺序！！！先判断index是否有效，在判断值
}

