import java.util.Collections;
import java.util.List;


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
    
}
