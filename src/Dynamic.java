import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**动态规划
 * @author lenovo
 *
 */
public class Dynamic {
    
	/**120. Triangle
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0)
        	return 0;
        int size = triangle.size();
        int[] dp = new int[size];
        for (int i=size-1; i>=0; i--) {
        	for (int j=0; j<=i; j++) {
        		if (i==size-1) {
        			dp[j] = triangle.get(i).get(j);
        		} else {
        			dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
        		}
        	}
        }
        return dp[0];
    }
    //转
    
    
    /**53. Maximum Subarray
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        int max = nums[0];
        int sum = max;
        for (int i=1; i<nums.length; i++) {
        	if (sum + nums[i] > nums[i])
        		sum += nums[i];
        	else 
        		sum = nums[i];
        	if (sum > max)
        		max = sum;
        }
        return max;
    }
    //beats 29.60%
    /*对于一个元素，要么跟别人一组，要么自己单独开一组。取决于自己开一组大，还是跟别人一组和大
     * */
    
    
    /**132. Palindrome Partitioning II 
     * @param s
     * @return
     */
    public int minCut(String s) {
        int min = 0;
        int len = s.length();
        boolean[][] matrix = new boolean[len][len];
        int[] cut = new int [len+1];
        
        if (s == null || len == 0)
        	return 0;
        for (int i=0; i<len; i++) {
        	cut[i] = len-i;
        }
    	for (int i=len-1; i>=0; --i) {
    		for (int j=i; j<len; ++j) {
    			if ((s.charAt(i) == s.charAt(j) && j-i<2) || 
    					s.charAt(i) == s.charAt(j) && matrix[i+1][j-1]) {
    				matrix[i][j] = true;
    				cut[i] = Math.min(cut[i], cut[j+1]+1);
    			}
    		}
    	}
    	min = cut[0]-1;
    	return min;
    }
    //转，beats 82.56%
    /*
     1. matrixs[i+1][j-1]是回文且 s.charAt(i) == s.charAt(j)。
	 2. i==j（i，j是用一个字符）
	 3. j=i+1（i，j相邻）且s.charAt(i) == s.charAt(j)
	 当字符串[i,j]是回文后，说明从第i个位置到字符串第len位置的最小cut数可以被更新了，
	 那么就是从j+1位置开始到第len位置的最小cut数加上[i,j]|[j+1,len - 1]中间的这一cut。
	 即，Math.min(cuts[i], cuts[j+1]+1) 
	 最后返回cuts[0]-1。把多余加的那个对于第len位置的切割去掉，即为最终结果。
     * */
    
    
    /**85. Maximal Rectangle
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        	return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[] height = new int[n];
        for (int i=0; i<m; i++) {
        	for (int j=0; j<n; j++) {
        		if (matrix[i][j] == '0') {
        			height[j] = 0;
        		} else {
        			height[j] += 1;
        		}
        	}
        	max = Math.max(largestRectangleArea(height), max);
        }
        return max;
    }
    public int largestRectangleArea (int[] height) {
    	Stack<Integer> stack = new Stack<Integer>();
    	int i = 0;
    	int maxArea = 0;
    	int[] h = new int[height.length+1];
    	h = Arrays.copyOf(height, height.length+1);
    	while (i<h.length) {
    		if (stack.isEmpty() || h[stack.peek()] < h[i]) {
    			stack.push(i);
    			i++;
    		} else {
    			int t = stack.pop();
    			int s = -1;
    			if (stack.isEmpty()) {
    				s = h[t]*i;
    			} else {
    				int x = i-stack.peek()-1;
    				s = h[t]*x;
    			}
    			maxArea = Math.max(maxArea, s);
    		}
    	}
    	return maxArea;
    }
    //http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
    
       
    /**123. Best Time to Buy and Sell Stock III 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
        	return 0;
        }
        
        int min = prices[0];
        int len = prices.length;
        int[] array1 = new int[len];  
        for (int i=1; i<len; i++) {
        	min = Math.min(min, prices[i]);
        	array1[i] = Math.max(array1[i-1], prices[i]-min);
        }
        
        int max = prices[len-1];
        int[] array2 = new int[len];
        for (int i=len-2; i>=0; i--) {
        	max = Math.max(max, prices[i]);
        	array2[i] = Math.max(array2[i+1], max-prices[i]);
        }
        
        int profit = 0;
        for (int i=0; i<len; i++) {
        	profit = Math.max(array1[i]+array2[i], profit);
        }
        return profit;
    }
    //转，beats 76.58%
    //一次划分的最大利益为：Profit[i] = MaxProfit(区间[0,i]) + MaxProfit(区间[i,len-1]);
    
    
    /**97. Interleaving String
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
    	if(s2.length() != s1.length()+s2.length())
    		return false;
    	
    	return false;
    }
    
    
}
