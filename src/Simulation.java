import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import DataStructure.Interval;


/**模拟
 * @author lenovo
 *
 */
public class Simulation {
	
    /**7. Reverse Integer
     * @param x
     * @return
     */
	public int reverse(int x) {
		boolean n = true;
		if (x < 0) {
			n = false;
			x = -x;
		}
		int r = 0;
		while (x>0) {
			if (r!=0 && Integer.MAX_VALUE/r <10)
				return 0;
			r = 10*r + x%10;
			x = x/10;
		}
		if (n)
			return r;
		else 
			return -r;
	}
	//32.77%
	
	public int reverse1(int x) {
		int r = 0;
		while (x!=0) {
			if (r!=0 && Integer.MAX_VALUE/r <10 && Integer.MAX_VALUE/r >-10)
				return 0;
			r = 10*r + x%10;
			x = x/10;
		}
		return r;
	}
	// beats 82.00
	
    public int reverse0(int x) {
    	String s;
    	boolean n;
        if (x >= 0) {
        	n= true;
        	s = String.valueOf(x);
        } else {
        	n = false;
        	s = String.valueOf(-x);
        }
        s = reverseString(s);
        if (n)
        	return Integer.valueOf(s);
        else 
        	return -Integer.valueOf(s);
    }
    public String reverseString (String s) {
    	if (s.length() == 1)
    		return s;
    	char[] str = s.toCharArray();//!!!!!!!!!!!!!
    	int left = 0;
    	int right = s.length()-1;
    	while (left <= right) {
    		char t = str[left];
    		str[left] = str[right];
    		str[right] = t;
    		left++;
    		right--;
    	}
    	return str.toString();
    }
    
    
    /**9. Palindrome Number
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
    	if (x<0)
    		return false;
    	int xx = x;
        int y = 0;
        while (x != 0) {
        	y = y*10 + x%10;
        	x = x/10;
        }
        return (xx==y);    
    }
    // beats 80.35%, 不需要分别正负,但是可能会有溢出
    
    public boolean isPalindrome1(int x) { 
    	if (x < 0)
    		return false;
    	if (x <10)
    		return true;
    	int digits = 0;
    	int t = x;
    	int d = 0;
    	while (t!=0) {
    		t = t/10;
    		++d;
    	}
    	int left = (int) Math.pow(10, d-1);
    	int right = 1;
    	while (left >= right) {
    		if (x/left %10 != x/right %10) 
    			return false;
    		left = left/10;
    		right = right*10;
    	}
    	return true;
    }
    //beats 50.91%
    
    
    /** 57. Insert Interval
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert0(List<Interval> intervals, Interval newInterval) {
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        
        List<Interval> ret = new ArrayList<Interval>();
        for (Interval i: intervals) {
        	if (newStart >= i.start && newEnd <= i.end)//新的包含在里
        		return intervals;
        	else if (newStart >= i.start && newStart <= i.end && newEnd > i.end) {//右
        		newStart = i.start;    		
        	} else if (newStart < i.start && newEnd >= i.start && newEnd <= i.end) {//左交集
        		newEnd = i.end;
        	} else if (newStart > i.end || newEnd < i.start){
        		ret.add(i);
        	}
        }
        Interval val = new Interval(newStart, newEnd);
        ret.add(val);
        Compare c = new Compare();
        Collections.sort(ret, c);
    	return ret;
    }
    //wrong, 314没有排在313后???????????????

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int newStart = newInterval.start;
        int newEnd = newInterval.end;
        boolean add = false;
        List<Interval> ret = new ArrayList<Interval>();
        for (Interval i: intervals) {
        	if (newStart >= i.start && newEnd <= i.end)//新的包含在里
        		return intervals;
        	else if (newStart >= i.start && newStart <= i.end && newEnd > i.end) {//右
        		newStart = i.start;    		
        	} else if (newStart < i.start && newEnd >= i.start && newEnd <= i.end) {//左交集
        		newEnd = i.end;
        	} else if (newEnd < i.start) {//新的在左
        		if (!add) {
        			Interval val = new Interval(newStart, newEnd);
            		ret.add(val);
            		add = true;
        		}      		
        		ret.add(i);
        	} else if (newStart > i.end) {
        		ret.add(i);
        	}
        }
        if (!add) {
        	Interval val = new Interval(newStart, newEnd);
            ret.add(val);
        }
        
    	return ret;
    }
    //beats 75.22%
    
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) { 
        List<Interval> res = new ArrayList<Interval>();
        
        for(Interval each: intervals){
            if(each.end < newInterval.start){
                res.add(each);
            }else if(each.start > newInterval.end){
                res.add(newInterval);
                newInterval = each;        
            }else if(each.end >= newInterval.start || each.start <= newInterval.end){
                int nstart = Math.min(each.start, newInterval.start);
                int nend = Math.max(newInterval.end, each.end);
                newInterval = new Interval(nstart, nend);
            }
        }
         res.add(newInterval); 
        return res;
    }
    // beats 75.22%, 转http://www.cnblogs.com/springfor/p/3872333.html
    
    
    /**56. Merge Intervals
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<Interval>();
        for (Interval i: intervals) {
        	ret = insert2(ret, i);
        }   
    	return ret;
    }
    //beats 94.67%
    
    
    /**76. Minimum Window Substring
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        String ret = "";
        if (s == null || t == null || s.length() ==0 || t.length() == 0) 
        	return ret;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();//保存t中字符出现个数
        for (int i=0; i<t.length(); i++) {
        	if (!map.containsKey(t.charAt(i))) {
        		map.put(t.charAt(i), 1);
        	} else {
        		map.put(t.charAt(i), map.get(t.charAt(i))+1);
        	}
        }
        
        int count = 0;
        int pre = 0;
        int minlen = s.length() + 1;
        
        for (int i=0; i<s.length(); i++) {
        	if (map.containsKey(s.charAt(i))) {//s中出现t的字符
        		map.put(s.charAt(i), map.get(s.charAt(i))-1);
        		if (map.get(s.charAt(i)) >= 0) {
        			count++;//
        		}
        		while (count == t.length()) {
        			if (map.containsKey(s.charAt(pre))) {
        				map.put(s.charAt(pre), map.get(s.charAt(pre))+1);
        				
        				if (map.get(s.charAt(pre)) > 0) {//以pre开头的串满足map
        					if (minlen > i-pre+1) {
        						ret = s.substring(pre, i+1);
            					minlen = i-pre+1;
        					}
        					count--;
        				}
        			}
        			pre++;
        		}
        	}
        	
        }
        return ret;
    }
    //转，http://www.cnblogs.com/springfor/p/3872559.html
    
    
    /**30. Substring with Concatenation of All Words
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
    	List<Integer> res = new ArrayList<Integer>();
        if (s == null || s.length() == 0|| words == null || words.length == 0)
        	return res;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
    	for (int i=0; i<words.length; i++) {
    		if (!map.containsKey(words[i])) {
    			map.put(words[i], 1);
    		} else {
    			map.put(words[i], map.get(words[i])+1);
    		}
    	}
    	int strlen = words[0].length();
    	for (int i=0; i<strlen; i++) {
    		HashMap<String, Integer> cur = new HashMap<String, Integer>();
    		int count = 0;
    		int left = i;
    		for (int j=i; j<=s.length()-strlen; j+=strlen) {
    			String str = s.substring(j, j+strlen);
    			if (map.containsKey(str)) {
    				if (!cur.containsKey(str)) {
    					cur.put(str, 1);
    				} else {
    					cur.put(str, cur.get(str)+1);
    				}
    				if (cur.get(str) <= map.get(str)) {
    					count++;
    				} else {
    					while (cur.get(str) > map.get(str)) {
    						String temp = s.substring(left, left+strlen);
    						if (cur.containsKey(temp)) {
    							cur.put(temp, cur.get(temp)-1);
    							if (cur.get(temp) < map.get(temp))
    								count--;
    						}
    						left+=strlen;
    					}
    				}
    				if (count == words.length) {
    					res.add(left);
    					String temp = s.substring(left, left+strlen);
    					if (cur.containsKey(temp)) {
    						cur.put(temp, cur.get(temp)-1);
    					}
    					count--;
    					left+=strlen;
    				}
    				
    			} else {
    				cur.clear();
    				count = 0;
    				left = j+strlen;
    			}
    			
    		}
    		
    	}
    	return res;
    }
    //转，http://blog.csdn.net/linhuanmars/article/details/20342851
    
    
    /**43. Multiply Strings
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
    	if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
    		return "";
    	}
    	if (num1.equals("0") || num2.equals("0"))
    		return "0";
        int n1 = num1.length();
        int n2 = num2.length();
        int[] sum = new int[n1+n2];
        
        for (int i=n1-1; i>=0; i--) {
        	for (int j=n2-1; j>=0; j--) {
        		int tmp = (int)(num1.charAt(i)-'0')*(int)(num2.charAt(j)-'0');
        		int index = n1-i-1+n2-j-1;
        		sum[n1-i-1+n2-j-1] += tmp;
        	}
        }
        String res = "";
        for (int i=0; i<n1+n2; i++) {
        	if (sum[i]>9) {
        		int tmp = sum[i];
        		sum[i] = tmp%10;
        		sum[i+1] += tmp/10;
        	}
        	res = String.valueOf(sum[i]) + res;
        }
        if (res.substring(0, 1).equals("0")) {
        	int i=0;
        	for (; i<res.length(); i++) {
            	if (res.charAt(i) != '0')
            		break;
            }
        	res = res.substring(i, res.length());
        }
        return res;
    }
    // beats 42.39%, 一把辛酸泪
    
    
    /**119. Pascal's Triangle II
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        if (rowIndex < 0)
        	return res;
        res.add(1);
        for (int i=1; i<=rowIndex; i++) {
        	List<Integer> tmp = new ArrayList<Integer>(res);
        	res.clear();
        	for (int j=0; j<=i; j++) {
        		if (j==0 || j==i) 
        			res.add(1);
        		else 
        			res.add(tmp.get(j-1)+tmp.get(j));
        	}
        }
        return res;
    }
    //beats 14.47%
    
    
    /**118. Pascal's Triangle
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	if (numRows <= 0)
    		return res;
    	List<Integer> p = new ArrayList<Integer>();
    	p.add(1);
    	res.add(p);
    	for (int i=1; i<numRows; i++) {
    		List<Integer> r = new ArrayList<Integer>();
    		List<Integer> pre = res.get(i-1);
    		for (int j=0; j<=i; j++) {
    			if (j==0 || j==i)
    				r.add(1);
    			else 
    				r.add(pre.get(j-1)+pre.get(j));
    		}
    		res.add(r);
    	}
    	return res;
    }
    //beats 26.58%
    
    
    /**54. Spiral Matrix 
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        	return res;
        int row = matrix.length;
        int col = matrix[0].length;
        int left=0, right=col-1;
        int up=0, down=row-1;
        while(left<=right && up<=down) {
        	for (int i=left; i<=right; i++) {//往右
        		res.add(matrix[up][i]);
        	}
        	for (int i=up+1; i<=down; i++) {//往下
        		res.add(matrix[i][right]);
        	}
        	if (up<down) {
        		for (int i=right-1; i>=left; i--) {//往左
            		res.add(matrix[down][i]);
            	}
        	}
        	if (left < right) {
	        	for (int i=down-1; i>up; i--) {//往上
	        		res.add(matrix[i][left]);
	        	}
        	}
        	left+=1;
        	right-=1;
        	up+=1;
        	down-=1;
        }
        return res;
    }
    //beats 77.87%, 注意单行或者单列
    
    
    /**59. Spiral Matrix II 
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n==0) {
        	return new int[0][0];
        }
        int[][] matrix = new int[n][n];
        int left=0, right=n-1;
        int up=0, down=n-1;
        int count = 1;
        while(left<=right && up<=down) {
        	for (int i=left; i<=right; i++) {//往右
        		matrix[up][i]=count++;
        	}
        	for (int i=up+1; i<=down; i++) {//往下
        		matrix[i][right]=count++;
        	}
        	for (int i=right-1; i>=left; i--) {//往左
            	matrix[down][i]=count++;
            }
	        for (int i=down-1; i>up; i--) {//往上
	        	matrix[i][left]=count++;
	        }
        	left+=1;
        	right-=1;
        	up+=1;
        	down-=1;
        }
        
        return matrix;
    }
    // beats 15.95%
    
    
    /**6. ZigZag Conversion
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s == null || s.length()==0 || numRows<=1)
        	return s;
        StringBuilder res = new StringBuilder();
        int len = s.length();
        int size = 2*numRows-2;
        for (int i=0; i<numRows; i++) {
        	for (int j=i; j<len; j+=size) {
        		res.append(s.charAt(j));
        		if (i != 0 && i != numRows-1) {
        			int tmp = j+size-2*i;//高，简直高
        			if (tmp<len)
        				res.append(s.charAt(tmp));
        		}
        	} 
        }
        return res.toString();
    }
    // beats 95.65%, 转
    
    
    /**29. Divide Two Integers
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide0(int dividend, int divisor) {
        if (dividend == 0)
        	return 0;
        else if (divisor == 0)
        	return Integer.MAX_VALUE;
        else if (dividend == divisor)
        	return 1;
        else if (dividend == -divisor)
        	return -1;
        boolean d1 = false;
        boolean d2 = false;
        if (dividend < 0 ) {
        	d1 = true;
        	dividend = -dividend;
        } 
        if (divisor < 0) {
        	d2 = true;
        	divisor = - divisor;
        }
        int res = 0;
        int div = dividend-divisor;
        while (div>0) {
        	res+=1;
        	dividend = div;
        	div = dividend-divisor;
        	if (res == Integer.MAX_VALUE || res == Integer.MIN_VALUE)
        		return res;
        }
        if (d1==d2)
        	return res;
        else 
        	return -res;
    }
    // Time Limit Exceeded
    
    public int divide(int dividend, int divisor) {
    	
    }
    
    
    
}
class Compare implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Interval i1 = (Interval) o1;
		Interval i2 = (Interval) o2;
		if (i1.start < i2.start)
			return 0;
		else 
			return -1;
	}
	
}