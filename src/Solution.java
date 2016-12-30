import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import DataStructure.TreeNode;

/**
 * @author：JJL
 * @date: 2016年9月13日
 * @description:
 */

public class Solution {

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	public int getSum(int a, int b) {
		if (b == 0)
			return a;
		int sum = a ^ b;
		int carry = (a & b) << 1;
		return getSum(sum, carry);
	}

	/**
	 * 389. Find the Difference
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	/*
	 * public char findTheDifference(String s, String t) { char[] arrayT =
	 * t.toCharArray(); for (int i = 0; i < arrayT.length; i++) { String strT =
	 * String.valueOf(arrayT[i]); if (!s.contains(strT)) { return
	 * strT.charAt(0); } } return 0; }
	 */
	// 两个数组分别统计出现次数然后比较
	public char findTheDifference(String s, String t) {
		int[] sa = new int[26];
		int[] ta = new int[26];
		for (int i = 0; i < s.length(); i++) {
			sa[(int) s.charAt(i) - 97] += 1;
		}
		for (int i = 0; i < t.length(); i++) {
			ta[(int) t.charAt(i) - 97] += 1;
		}
		for (int i = 0; i < 26; i++) {
			if (sa[i] != ta[i]) {
				return (char) (i + 97);
			}
		}
		return 0;
	}

	/**
	 * 283 Move Zeroes
	 * 
	 * @param nums
	 * @return
	 */
	public int[] moveZeroes1(int[] nums) {
		Stack<Integer> s = new Stack<Integer>();
		Stack<Integer> t = new Stack<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				t.push(0);
			} else {
				s.push(nums[i]);
			}
		}
		for (int i = nums.length - 1; i >= 0; i--) {
			if (!t.empty()) {
				nums[i] = t.pop();
			} else {
				if (!s.empty()) {
					nums[i] = s.pop();
				}
			}
		}
		return nums;
	}

	public int[] moveZeroes(int[] nums) {
		int i = 0;
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != 0) {
				if (j != i) {
					nums[i++] = nums[j];
					nums[j] = 0;
				} else {
					i++;
				}
			}
		}
		return nums;
	}

	/**
	 * 104. Maximum Depth of Binary Tree
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth1(TreeNode root) {
		return root == null ? 0 : Math.max(maxDepth(root.left),
				maxDepth(root.right)) + 1;
	}

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.push(root);
		int result = 0;
		while (!q.isEmpty()) {
			result += 1;
			int len = q.size();
			for (int i = 0; i < len; i++) {
				TreeNode t = q.pop();
				if (t.left != null) {
					q.push(t.left);
				}
				if (t.right != null) {
					q.push(t.right);
				}
			}
		}
		return result;
	}

	/**
	 * 404. Sum of Left Leaves
	 * 
	 * @param root
	 * @return
	 */
	/*
	 * public int sumOfLeftLeaves(TreeNode root) { if (root == null) { return 0;
	 * } if (root.left != null && root.left.left == null && root.left.right ==
	 * null){ return root.left.val + sumOfLeftLeaves(root.right); } return
	 * sumOfLeftLeaves(root.right); }
	 */
	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left != null && root.left.left == null
				&& root.left.right == null)
			return root.left.val + sumOfLeftLeaves(root.right);
		else
			return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);

	}

	/**
	 * 100. Same Tree
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null & q == null) {
			return true;
		}
		if (p != null && q != null && p.val == q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
		return false;
	}

	/**383. Ransom Note
	 * @param ransomNote
	 * @param magazine
	 * @return
	 */
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] a = new int[26];
		int[] b = new int[26];
		for (int i = 0; i < ransomNote.length(); i++) {
			a[(int) ransomNote.charAt(i) - 97] += 1;
		}
		for (int i = 0; i < magazine.length(); i++) {
			b[(int) magazine.charAt(i) - 97] += 1;
		}
		for (int i = 0; i < 26; i++) {
			if (a[i] > b[i]) {
				return false;
			}
		}
		return true;
	}
	/**
	 解法三：这是LeetCode Discuss中的最热代码，它的原理就是列出了magazine的字母表，
	 然后算出了出现个数，然后遍历ransomNote，保证有足够的字母可用，代码非常清晰。

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if(--arr[ransomNote.charAt(i)-'a'] < 0) {
                return false;
            }
        }
        return true;
    }
	 **/
	
	/**349. Intersection of Two Arrays
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	//可以去掉变量k，直接判断list.size==0
	public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = -1;
        ArrayList List = new ArrayList(); 
        while(i < nums1.length && j < nums2.length) {
        	if (nums1[i] == nums2[j]) {
        		if(k >= 0 && (int)List.get(k) != nums1[i] || k <0) {
        			List.add(nums1[i]);
        			k++;
        		}       		
        		i++;
        		j++;
        	} else if (nums1[i] < nums2[j]){
        		i++;
        	} else {
        		j++;
        	}
        }
        int[] a=new int[List.size()];
        for (int l = 0; l < List.size(); l++) {
        	a[l] = (int) List.get(l);
        }
        return a;
    }
	/**使用hashset
	 public int[] Intersection(int[] nums1, int[] nums2) {
        var hashtable = new HashSet<int>();
        foreach(var n in nums1)
        {
            hashtable.Add(n);
        }
        var res = new List<int>();
        foreach(var n in nums2 )
        {
            if(hashtable.Contains(n) && !res.Contains(n))
            {
                res.Add(n);
            }
        }
        return res.ToArray();
    }
	 * */
	
	/**17. Letter Combinations of a Phone Number
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		char [][] phone = {{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},
				{'m','n','o'},{'p','q','r','s'},{'t','u','v'}, {'w','x','y','z'}};
		int[] arr = new int[digits.length()];
		for(int i = 0; i < digits.length(); i++) {
			char c = digits.charAt(i);
			int n = Integer.parseInt(c+"");
			arr[i] = n;
		}
		
        return null;
    }
	
	/**168. Excel Sheet Column Title
	 * @param n
	 * @return
	 */
	public String convertToTitle(int n) {
		String s = "";
        while(n > 0) {
        	s = String.valueOf((char)((n-1)%26 + 'A')) + s;
        	n = (n-1)/26;
        	/*int a = (n-1)%26;
        	s = String.valueOf((char)(a + 65)) + s;
        	n = (n-1)/26;*/
        }
        return s;
    }
	
	/**171. Excel Sheet Column Number
	 * @param s
	 * @return
	 */
	public int titleToNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
        	num = num*26 + (s.charAt(i) - 'A') + 1;
        }
        return num;
    }
	/*二进制<->26进制*/
	
	/**242. Valid Anagram
	 * @param s
	 * @param t
	 * @return
	 */
	/*计数*/
	/*public boolean isAnagram(String s, String t) {
        int[] a = new int [26];
        int[] b = new int [26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            b[t.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }*/
	/*排序后比较*/
	public boolean isAnagram(String s, String t) {
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
		Arrays.sort(a);
		Arrays.sort(b);
        s = Arrays.toString(a);
        t = Arrays.toString(b);
        return s.equals(t);
    }
	
	/**49. Group Anagrams
	 * @param strs
	 * @return
	 */
	/*public List<List<String>> groupAnagrams(String[] strs) {
        
    }*/
	
	/**387. First Unique Character in a String
	 * @param s
	 * @return
	 */
	public int firstUniqChar(String s) {
		HashMap<Character, Integer> scoreboard = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (scoreboard.containsKey(c)) {
				scoreboard.put(c, scoreboard.get(c) + 1);
			} else {
				scoreboard.put(c, 1);
			}
		} // since HashMap doesn't maintain order, going through string again
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (scoreboard.get(c) == 1) {
				return i;
			}
		}
		return -1;
	}
	/**
	 /*开个26个数的数组，然后先对字符串过一遍，统计每个字母出现的次数，
	 然后从头再国一遍，第一个字母数为1的即为首先出现并且只出现一次的字母。
    public int firstUniqChar(String s) {
        int[] a = new int[26];
        for(int i = 0; i < s.length(); i++)
        	a[s.charAt(i) - ‘a‘]++;
        for(int i = 0; i < s.length(); i++){
        	if(a[s.charAt(i) - ‘a‘] == 1)
        		return i;
        }
        return -1;
    }
	 */
	
	/**169. Majority Element
	 * @param nums
	 * @return
	 */
	public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
	
	/**217. Contains Duplicate
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
        	int a = nums[i];
        	if (map.containsKey(a)) {
        		return true;
        	} else {
        		map.put(a, 1);
        	}
        }
        return false;
    }
	/*HashSet搞定,not hashmap*/
	/**26. Remove Duplicates from Sorted Array
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
        Arrays.sort(nums);
        int len = 0;
        if (nums.length > 0) {
        	len = 1;
        }
        for (int i = 1; i < nums.length; i++) {
        	if (nums[i] != nums[i-1]) {
        		nums[len] = nums[i];  
        		len++;
        	}
        }
        return len;
    }
	/*len初始化，nums[i]和len++顺序*/
	
	/**80. Remove Duplicates from Sorted Array II
	 * @param nums
	 * @return
	 */
	public int removeDuplicates2(int[] nums) {
        boolean twice = false;
        int len = 0;
        if (nums.length > 0) {
        	len = 1;
        }
        for (int i = 1; i < nums.length; i++) {
        	if (twice == false && nums[i] == nums[i-1]) {
        		twice = true;
        		nums[len++] = nums[i];
        	} else if (nums[i] != nums[i-1]) {
        		twice = false;
        		nums[len++] = nums[i];
        	}
        }
        return len;
    }

	/**81. Search in Rotated Sorted Array II
	 * @param nums
	 * @param target
	 * @return
	 */
	/**public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }*/
	/**循环还可以这么用
	 * for(int num:nums)
        if(num==target)
    */
	/**进行二分查找：左边有序 或者 右边有序*/
	public boolean search(int[] nums, int target) {		
        return binarySearch(nums, 0, nums.length-1, target);
    }
	public boolean binarySearch(int[] nums, int start, int end, int target) {
		if (start > end) {
			return false;
		}
		int middle = (start + end)/2;
		if (nums[middle] == target) {
			return true;
		}
		if (nums[middle] < nums[end]) {//后半边升序
			if(target > nums[middle] && target <= nums[end]) {//在后半部分
				return binarySearch(nums, middle+1, end, target);
			}else {
				return binarySearch(nums, start, middle, target);
			} 
		} else if (nums[middle] > nums[end]) {//左半边升序
			if (target < nums[middle] && target >= nums[start]) {//在左半部分
				return binarySearch(nums, start, middle-1, target);
			} else {
				return binarySearch(nums, middle+1, end, target);
			}
		} else {//middle=end，无法判断
		    for (int n : nums) {
		        if (n == target) {
		            return true;
		        }
		    }
		}		
		return false;
	}
	/**循环解决*/
	public boolean search2(int[] nums, int target) {		
        int s = 0, e = nums.length-1;      
        while(s < e) {
        	int m = (s+e)/2;
        	if (nums[m] == target) {
        		return true;
        	} else if (nums[m] < nums[e]) {//后，有序
        		if (target > nums[m] && target <= nums[e]) {
        			s = m+1;
        		} else {
        			e = m-1;
        		}
        	}else if (nums[m] > nums[e]){//前，有序
        		if (target < nums[m] && target >= nums[s]) {
        			e = m-1;
        		} else {
        			s = m+1;
        		}
        	}else {//中间元素等于末尾元素
        		e--;
        	}
        }
        return false;
    }
	
	/**33. Search in Rotated Sorted Array
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search3(int[] nums, int target) {
        int s = 0, e = nums.length-1;      
        while (s <= e) {
        	int m = (s+e)/2;
        	if(nums[m] == target) {
        		return m;
        	}
        	if (nums[m] >= nums[s]) {//前面有序
        		if (nums[m] > target && nums[s] <= target) {
        			e = m-1;
        		} else {
        			s = m+1;
        		}
        	}
        	if (nums[m] <= nums[e]) {//后面有序
        		if (nums[m] < target && nums[e] >= target) {
        			s = m +1;
        		} else {
        			e = m-1;
        		}
        	}
        	
        }
        return -1;
    }
	
    /**4. Median of Two Sorted Arrays
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int []nums = new int[nums1.length + nums2.length]; 
        int i = 0;
        for (int n : nums1) {
        	nums[i++] = n;
        }
        for (int n : nums2) {
        	nums[i++] = n;
        }
        Arrays.sort(nums);
        if (nums.length%2 == 1) {
        	return nums[nums.length/2];
        } else {
        	int hlen = nums.length/2;
        	double m = (double)(nums[hlen] + nums[hlen-1])/2;
        	return m;
        }    
    }
    //此方法复杂度为nlogn，有时间在看
	
/*    
    public double findMedianSortedArrays(int A[], int B[]) {  
        int k = A.length + B.length;  
        return k % 2 == 0 ?  (findK(A, 0, A.length - 1, B, 0, B.length - 1, k/2 + 1) +   
        findK(A, 0, A.length - 1, B, 0, B.length - 1, k/2)) / 2  
        : findK(A, 0, A.length - 1, B, 0, B.length - 1, k/2 + 1);  
    }  
    //返回两个数组中第k大的元素。  
    public double findK(int a[], int s1, int e1, int b[], int s2, int e2, int k) {  
        int m = e1 - s1 + 1;  
        int n = e2 - s2 + 1;  
        if (m > n) return findK(b, s2, e2, a, s1, e1, k); //a的长度比b的小。  
        if (s1 > e1) return b[s2 + k - 1];   
        if (s2 > e2) return a[s1 + k - 1];  
        if (k == 1) return Math.min(a[s1], b[s2]);  
        int midA = Math.min(k/2, m), midB = k - midA;   
        //如果a的第midA大的元素比b的第midB大的元素小，  
        //那么删掉a的前midA个元素，在剩余的数中找第k-midA大的。  
        if (a[s1 + midA - 1] < b[s2 + midB - 1])   
            return findK(a, s1 + midA, e1, b, s2, e2, k - midA);  
        else if (a[s1 + midA - 1] > b[s2 + midB - 1])   
            return findK(a, s1, e1, b, s2 + midB, e2, k - midB);  
        else  
            return a[s1 + midA - 1];  
    }  
*/
    
    /**128. Longest Consecutive Sequence
     * @param nums
     * @return
     */   
    public int longestConsecutive0(int[] nums) {
        Arrays.sort(nums);
        int len = 0;
        if (nums.length >= 1) {
        	len = 1;
        }
        int l = 1;
        for (int i= 1; i < nums.length; i++) {
        	if (nums[i] - nums[i-1] == 1) {
        		l++;
        		if (l > len) {
        			len = l;
        		}
        	} else if (nums[i] == nums[i-1]){
        		continue;//continue啊，不能什么也不写
        	} else {
        		l = 1;
        	}
        }
        return len;
    } //次方法复杂度为nlog(n)
    
    public int longestConsecutive(int[] nums) {
    	int len = 0, count = 1;
    	Set<Integer> s = new HashSet<Integer>();
    	if (nums.length >= 1) {
    		len = 1;
    	}
    	for (int n :nums) {
    		s.add(n);
    	}
    	for (int n :nums) {
    		int l = n - 1;
    		int r = n + 1;
    		while(s.contains(l)) {
    			l--;
    			count++;
    		}
    		while(s.contains(r)) {
    			r++;
    			count++;
    		}
    		if (count > len) {
    			len = count;
    		}
    		count = 1;
    	}
    	return len;
    }
    //Time Limit Exceeded,and why????????????????????????????
    
    /**1. Two Sum
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    arr[0] = i;
                    arr[1] = j;
                   
                }
            }
        }
         return arr;
    }
    
    /**15. 3Sum 
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum0(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();  
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
        	for (int j= i+1; j < nums.length; j++) {
            	for (int k = j+1; k < nums.length; k++) {
                	if (nums[i] + nums[j] + nums[k] == 0 ) {
                		List<Integer> e = new ArrayList<Integer>();
                		e.add(nums[i]);
                		e.add(nums[j]);
                		e.add(nums[k]);
                		list.add(e);
                	}
                	while (k <nums.length-1 && nums[k] == nums[k+1] ) {
                		k++;
                	}				
                }
            	while (j <nums.length-1 && nums[j] == nums[j+1]) {
            		j++;
            	}
            }
        	while ( i <nums.length-1 && nums[i] == nums[i+1] ) {
    			i++;
    		}
        } 	
        return list;
    }
    //去重花费了一定时间，没考虑好，应该是本循环结束后，如果后面有相同的，略去，因为前一个已经算过了
    //先判断i大小，再执行k+1，否则会越界！！
    
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	Arrays.sort(nums);
    	for (int i = 0; i < nums.length-2; i++) {
    		if (i > 0 && nums[i] == nums[i-1]) {
    			continue;
    		} else {
    			int s = i+1;
    			int e = nums.length-1;
    			int num = nums[i];
    			while (s < e) {
    	    		if (nums[s] + nums[e] == -num) {
    	    			List<Integer> l = new ArrayList<Integer>();
    	    			l.add(num);
    	    			l.add(nums[s]);
    	    			l.add(nums[e]);
    	    			list.add(l);
    	    			while(s < nums.length-2 && nums[s] == nums[s+1]) {
    	        			s++;
    	        		}
    	        		while(e > 0 && nums[e] == nums[e-1]) {
    	        			e--;
    	        		}
    	        		s++;
    	        		e--;
    	    		}else if (nums[s] + nums[e] > -num) {
    	    			e--;
    	    		}else if (nums[s] + nums[e] < -num) {
    	    			s++;
    	    		}
    	    		
    	    	}
    		}
    	}
    	return list;
    }
    //先找一个a，然后在剩下的里面枚举-a
    //去除重复的时候，向前比较n[i] == n[i-1],而不能n[i] == n[i+1]
    
    /**16. 3Sum Closest
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
    	Arrays.sort(nums);
    	int min = Math.abs(nums[0] + nums[1] + nums[2] - target);
    	int sum = nums[0] + nums[1] + nums[2];
    	for (int i = 0; i < nums.length-2; i++) {
    		if (i > 0 && nums[i] == nums[i-1]) {
    			continue;
    		} else {
    			int s = i+1;
    			int e = nums.length-1;
    			while (s < e) {
    				int x =  Math.abs(nums[s] + nums[e] + nums[i] - target);
    	    		if ( x < min) {
    	    			min = x;
    	    			sum = nums[s] + nums[e] + nums[i];
    	    			while(s < nums.length-2 && nums[s] == nums[s+1]) {
    	        			s++;
    	        		}
    	        		while(e > 0 && nums[e] == nums[e-1]) {
    	        			e--;
    	        		}
    	    		} else if (nums[s] + nums[e] + nums[i] > target){
    	    			e--;
    	    		} else if (nums[s] + nums[e] + nums[i] < target){
    	    			s++;
    	    		} else {
    	    			break;
    	    		}
    	    	}
    		}
    	}
    	return sum;
    }
    //两个指针移动的条件，不要为了移动而移动！
    //循环跳出条件
    
    /**18. 4Sum
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	List<List<Integer>> list = new ArrayList <List<Integer>>();
    	Arrays.sort(nums);
    	int len = nums.length;
    	for(int i = 0; i < len-3; i++) {
    		if(i > 0 && nums[i] == nums[i-1]) {
    			continue;
    		} else {
    			for(int j = i+1; j < len-2; j++) {
    				if (j > i+1 && nums[j] == nums[j-1]) {
    					continue;
    				} else {
    					int s = j + 1;
    					int e = len - 1;
    					int n = nums[i] + nums[j];
    					while (s < e) {
    						if (nums[s] + nums[e] + n == target) {
    							List<Integer> l = new ArrayList<Integer>();
    							l.add(nums[i]);
    							l.add(nums[j]);
    							l.add(nums[s]);
    							l.add(nums[e]);
    							list.add(l);
    							while(s < e && nums[s] == nums[s+1]) {
    								s++;
    							}
    							while(s < e && nums[e] == nums[e-1]) {
    								e--;
    							}
    							s++;
    							e--;
    						} else if (nums[s] + nums[e] + n < target) {
    							s++;
    						} else {
    							e--;
    						}
    					}
    				}
    			}
    		}
    	}
    	return list;
    }
    //target啊！！！！！不是全算等于0的！注意读题
    //去重一直有问题，不是J>0 而是j>i+1
    
    /**27. Remove Element
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int len = 0;
        for(int i = 0; i < nums.length; i++) {
        	if (nums[i] == val) {
        		len++;
        	} else {
        		nums[i-len] = nums[i];
        	}
        }
    	return nums.length-len;
    }
    //两个指针思想，注意nums[i-len]的使用
    
    /**31. Next Permutation
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        boolean flag = false;
        for (int i = len-1; i > 0; i--) {
        	if (nums[i] > nums[i-1]) {
        		//从i开始找到最小的那个
        		int n= findMin(nums, i-1, len-1);
        		int temp = nums[i-1];
        		nums[i-1] = nums[n];
        		nums[n] = temp;
        		sort(nums, i, len-1);
        		flag = true;
        		break;
        	}
        }
        if (flag == false) {
        	Arrays.sort(nums);
        }
    }
    public void sort(int[] nums, int s, int e) {
    	for(int i = s; i <= e; i++) {
    		for(int j = i+1; j <= e; j++) {
    			if (nums[i] > nums[j]) {
    				int temp = nums[i];
    				nums[i] = nums[j];
    				nums[j] = temp;
    			}
    		}
    	}	
    }
    public int findMin(int[] nums, int s, int e) {
    	int x = nums[s];
    	int n = s+1;
    	for(int i = s+2; i <= e; i++) {
    		if (nums[i] < nums[n] && nums[i] > x) {
    			n = i;
    		}
    	}
    	return n;
    }
    //找到替换的那个数，后面之中比要替换的大的最小的一个
    
    /**60. Permutation Sequence
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
        	nums[i] = i+1;
        }
        for (int i = 1; i < k; i++) {
        	nextPermutation(nums);
        }
        String s = "";
        for (int i = 0; i < n; i++){
        	s += String.valueOf(nums[i]);
        }
        return s;
    }
    //String.valueOf();
    
    /**36. Valid Sudoku 
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> set = new HashSet<Character>();
        // Check for each row
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                if (set.contains(board[i][j]))
                    return false;
                set.add(board[i][j]);
            }
            set.clear();
        }

        // Check for each column
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (board[i][j] == '.')
                    continue;
                if (set.contains(board[i][j]))
                    return false;
                set.add(board[i][j]);
            }
            set.clear();
        }

        // Check for each sub-grid
        for (int k = 0; k < 9; k++) {
            for (int i = k/3*3; i < k/3*3+3; i++) {
                for (int j = (k%3)*3; j < (k%3)*3+3; j++) {
                    if (board[i][j] == '.')
                        continue;
                    if (set.contains(board[i][j]))
                        return false;
                    set.add(board[i][j]);
                }
            }
            set.clear();
        }
        
        return true;
    }
    // HashSet的唯一性来帮助check
    
    public boolean isValidSudoku0(char[][] board) {
		HashSet set1 = new HashSet();
		HashSet set2 = new HashSet();
        for (int i = 0; i < 9; i++) {
        	set1.clear();
        	set2.clear();
        	for (int j = 0; j < 9; j++) {
        		char ch1 = board[i][j];
        		char ch2 = board[j][i];
        		if (ch1 == '.' || ch2 == '.') {
        			continue;
        		} else {
        			if (set1.contains(ch1) || set2.contains(ch2)) {
        				return false;
        			} else {
        				set1.add(ch1);
        				set2.add(ch2);
        			}
        		}
        	}
        }
        
        for (int i = 0; i < 9; i=i+3) {
        	for (int j = 0; j < 9; j=j+3) {
        		for(int x = i; x < i+3; x++) {
        			set1.clear();
        			set2.clear();
        			for(int y=j; y<j+3; y++) {
        				char ch1 = board[x][y];
                		char ch2 = board[y][x];
                		if (ch1 == '.' || ch2 == '.') {
                			continue;
                		} else if (set1.contains(ch1) || set2.contains(ch2)) {
                				return false;
                		} else {
                				set1.add(ch1);
                				set2.add(ch2);
                		}
        			}
        		}
        	}
        }
        return true;
    }
    
    /**42. Trapping Rain Water
     * @param height
     * @return
     */
    public int trap(int[] A) {
        if (A == null || A.length == 0) return 0;
        int res = 0;
        int start = 0;
        int end = A.length - 1;
        int height = Math.min(A[start], A[end]);
        //start end 记录最右边和最左边的位置
        //height记录当前的高度，每次移动一位，更新最大的盛水量。
        while (start < end) {
            if (A[start] < A[end]) {
                res += Math.max(height - A[start+1], 0);
                height = Math.max(height, Math.min(A[start+1], A[end]));
                ++start;
            }
            else {
                res += Math.max(height - A[end-1], 0);
                height = Math.max(height, Math.min(A[start], A[end-1]));
                --end;
            }
            
        }
        return res;
    }
    //先遍历一遍找到塔顶，然后分别从两边开始，
    //往塔顶所在位置遍历，水位只会增高不会减小，且一直和最近遇到的最大高度持平，这样知道了实时水位，就可以边遍历边计算面积。
    public int trap1(int [] A) {
    	int n = A.length;
    	if(n <= 2) return 0;
        int max = -1, maxInd = 0;
        int i = 0;
        for(; i < n; ++i){
            if(A[i] > max){
                max = A[i];
                maxInd = i;
            }
        }
        int area = 0, root = A[0];
        for(i = 0; i < maxInd; ++i){
            if(root < A[i]) root = A[i];
            else area += (root - A[i]);
        }
        for(i = n-1, root = A[n-1]; i > maxInd; --i){
            if(root < A[i]) root = A[i];
            else area += (root - A[i]);
        }
        return area;
    }
    //划分为二维数组
    public int trap0(int[] height) {
    	if (height.length == 0) {
    		return 0;
    	}
    	int max = height[0];//max代表行
    	int len = height.length;//len代表列
    	for(int i = 1; i < len; i++) {
    		if(height[i] > max) {
    			max = height[i];
    		}
    	}
    	int[][] nums = new int[max][len];
    	for(int i = 0; i < len; i++) {
    		for(int j = 0; j < height[i]; j++) {
				nums[j][i] = 1;
			}
    	}
    	int count = 0;
    	for(int i = 0; i < max; i++) {
    		int l = -1;
    		int r = len;
    		for(int j = 0; j < len; j++) {
    			if (nums[i][j] == 1) {
    				l = j;
    				break;
    			}
    		}
    		for(int j = len-1; j >= 0; j--) {
    			if (nums[i][j] == 1) {
    				r = j;
    				break;
    			}
    		}
    		if (l != -1 && r != len) {
    			for(int k = l; k < r; k++) {
    				if(nums[i][k] == 0) {
    					count++;
    				}
    			}
    		}
    	}
    	return count;
    }
    
    /**48. Rotate Image 
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix[0].length;
        for (int i = 0; i <= (len-1)/2; i++) {
        	for (int j = i; j < len-1-i; j++) {
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[len-1-j][i];
        		matrix[len-1-j][i] = matrix[len-1-i][len-1-j];
        		matrix[len-1-i][len-1-j] = matrix[j][len-1-i];
        		matrix[j][len-1-i] = temp;
        	}
        }
    }//转圈，从最外层开始
    
    /**66. Plus One
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
    	int one = 1;
    	int i;
    	for(i = digits.length-1; i >= 0; i--) {
    		int sum = digits[i] + one;
    		if (sum > 9) {
    			digits[i] = sum-10;
    			one = sum/10;
    		}else {
    			digits[i] = sum;
    			one = 0;
    		}
    		
    	}
    	if (i == -1 && one == 1) {
		    int[] nums = new int[digits.length+1];
		    for(int j = nums.length-1; j > 0; j--) {
		    	nums[j] = digits[j-1];
		    }
		    nums[0] = one;
		    return nums;
		}
        return digits;
    }
    //处理个位、十位数
    //新建一个数组赋值时注意下边范围，-1
    
    /**70. Climbing Stairs
     * @param n
     * @return
     */
    public int climbStairs(int n) {
    	int a = 0;
    	int b = 1;
    	int sum = 0;
    	for(int i = 1; i <= n; i++){
    		sum = a + b;
    		a = b;
    		b = sum;
    	}
    	return sum;
    }//循环
    public int climbStairs1(int n) {
        if(n <= 2){  
            return n;  
        }else {  
            int[] step = new int[n];              
            step[0] = 1;  
            step[1] = 2;               
            for(int i = 2; i < n; i++) {  
                step[i] = step[i-1] + step[i-2];  
            }  
            return step[n-1];  
        } 
    }//数组  
    public int climbStairs0(int n) {
        if (n == 0) {
        	return 0;
        } else if (n == 1) {
        	return 1;
        } else if (n == 2) {
        	return 2;
        } else {
        	return climbStairs0(n-1) + climbStairs0(n-2);
        }
    }//递归，超时了，n=44
    
    /**89. Gray Code 
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<Integer>();
        int count = (int) Math.pow(2,n);
        int[] nums = new int[n];
        if (n == 0) {
        	list.add(0);
        	return list;
        }
        for (int i = 0; i < count; i++) {
        	int j = i;
        	int k = n-1;
        	while(j > 0) {
        		nums[k--] = j%2;
        		j = j/2;
        	}
        	int val = 0;
        	j = 1;
        	for(k = n-1; k > 0; k--) {
        		nums[k] = nums[k]^nums[k-1];
        		val += nums[k]*j;
        		j = j*2;
        	}
        	val += nums[0]*j;
        	list.add(val);
        }
        return list;
    }
    //n=0时输出[0]
    public List<Integer> grayCode1(int n) {
        List<Integer> gray = new ArrayList<Integer>();
        for(int i = 0 ; i < 1<<n ; i++) {
            int temp = i>>1;
            gray.add(i^temp);
        }
        return gray;        
    }
    
    /**73. Set Matrix Zeroes
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet();
        Set<Integer> col = new HashSet();
        for (int i = 0; i < matrix.length; i++) {
//        	if (row.contains(i)) {
//        		continue;
//        	}
        	for (int j = 0; j < matrix[i].length; j++) {
//        		if (col.contains(j)) {
//        			continue;
//        		}else 
        		if (matrix[i][j] == 0) {
        			row.add(i);
        			col.add(j);
        		}
        	}
        }
        for (int i = 0; i < matrix.length; i++) {
        	for (int j = 0; j < matrix.length; j++) {
        		if (row.contains(i) || col.contains(j)) {
        			matrix[i][j] = 0;
        		}
        	}
        }
    }
    //注释部分存在一个问题，a[0][0] = 0,此时会跳过处理a[1][0],但如果a[1][0]=0,则无法加入row(1)
    
    /**134. Gas Station
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
    	int len = gas.length;
    	int max = 0;
    	int sum = 0;
    	int pos = 0;
        for (int i = 0; i < len; i++) {
        	sum += gas[i] - cost[i];
        	if (max < 0) {//没有油就从下一个开始计算
        		max = gas[i] - cost[i];
        		pos = i;
        	} else {
        		max += gas[i] - cost[i];
        	}
        }
        if (sum < 0) {
        	return -1;
        } else {
        	return pos;
        }       
    }
    
    /**135. Candy
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
        	return 0;
        }
        int[] nums = new int[len];
        nums[0] = 1;
        for (int i = 1; i < len; i++) {
        	if(ratings[i] > ratings[i-1]) {
        		nums[i] = nums[i-1] + 1;
        	} else {
        		nums[i] = 1;
        	}
        }
        int res = nums[len-1];
        for (int i = len-2; i >= 0; i--) {
        	int cur = 1;  
            if(ratings[i]>ratings[i+1])  
            {  
                cur = nums[i+1]+1;  
            }  
            res += Math.max(cur,nums[i]);  
            nums[i] = cur; 
        }
        return res;
    }
    
    /**137. Single Number II
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
        	if (nums[i] == nums[i+1]) {
        		i = i+2;
        	} else {
        		return nums[i];
        	}
        }
		return nums[nums.length-1];
    }
    //第一次这么快ac，哈哈哈
    
}
