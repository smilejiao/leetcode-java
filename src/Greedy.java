import java.util.HashSet;
import java.util.Set;

/**贪心*/
public class Greedy {

    /**55. Jump Game
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
    	int max = 0;
    	for (int start = 0; start <= max && start<nums.length; start++) {
    		if (nums[start]+start >max)
    			max = nums[start]+start;
    		if (max >= nums.length-1)
    			return true;
    	}
    	return false;
    }
	//转，beats 58.08%
	
    public boolean canJump1(int [] nums) {
    	int reach = 0;
    	int i = 0;
    	for (; i < nums.length && i <= reach; i++) {
    		reach = Math.max(reach, i+nums[i]); //之前可达到， 当前可到达 的最大数为reach
    	}
    	return (i== nums.length);
    }
    //正向思维， beats 24.92%                                                                          
    
    public boolean canJump2(int[] A) {  
        int last = A.length - 1;  
        for (int i = A.length - 2; i >= 0; i--) {  
            if (i + A[i] >= last) {  
                last = i;  
            }  
        }  
        return (last <= 0);  
    }  
	//逆向思维
    
    
    /**45. Jump Game II 
     * @param nums
     * @return 最小跳动次数
     */
    public int jump(int[] nums) {
    	if (nums.length == 1){
    		return 0;
    	}
    	int len = nums.length;
        int[][] reach = new int[len][2];
        int min = len+1;
        for (int i=0; i<len; i++) {
        	reach[i][1] = Math.min(nums[i]+i, len-1);
        	if (reach[i][1] == len-1 && reach[i][0] < min) {
        		min = reach[i][0];
        		break;
        	}
        	
        	for (int j=0; j<nums[i]; j++) {
        		if (i+j+1 < len) {
        			if (reach[i+j+1][0] == 0)
        				reach[i+j+1][0] = reach[i][0] + 1;
        			else 
        				reach[i+j+1][0] = Math.min(reach[i+j+1][0], reach[i][0] + 1);
        		}
        		
        	}
        }

        return min+1;
    }
    //TLE,pass 90/91
    
    public int jump1(int[] nums) {
    	int ret = 0;
    	int curMax = 0;
    	int curReach = 0;
    	for (int i=0; i<nums.length; i++) {
    		if (curReach < i) {
    			ret++;
    			curReach = curMax;
    		}
    		curMax = Math.max(curMax, nums[i]+i);
    	}
    	return ret;
    }
    //转， beats 53.99%
    /*
     * ret:目前为止的jump数
     * curMax:能到达的最大范围
     * curReach:从nums[0]进行ret次，达到的最大范围
     * 当curReach < i，说明ret次jump已经不足以覆盖当前第i个元素，因此需要增加一次jump，使之达到记录的curMax。
     */
    
    public int jump2(int[] nums) {
    	if (nums == null || nums.length <= 1)
    		return 0;
    	int[] reach = new int[nums.length];
    	for (int i=0; i<nums.length; i++) {
    		
    	}
    	return 0;
    }
    
    
    /**121. Best Time to Buy and Sell Stock
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) {
        	return 0;
        }
        int min = prices[0];
        int max = 0;
        for (int i=1; i<prices.length; i++) {
        	min = Math.min(min, prices[i]);
        	max = Math.max(max, prices[i]-min);
        }
        return max;
    }
    //转
    /*从前向后遍历数组，记录当前出现过的最低价格，作为买入价格，
     * 并计算以当天价格出售的收益，作为可能的最大收益，整个遍历过程中，出现过的最大收益就是所求。*/
    
    /**122. Best Time to Buy and Sell Stock II
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
    	if(prices == null || prices.length <= 1) {
        	return 0;
        }
    	int max = 0;
    	for(int i=1; i<prices.length; i++) {
    		if (prices[i] > prices[i-1])
    			max = prices[i]-prices[i-1];
    	}
    	return max;
    }
    //beats 58.28%
    //从前向后遍历数组，只要当天的价格高于前一天的价格，就算入收益。
    
    
    /**11. Container With Most Water
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length <= 1) {
        	return 0;
        }
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while (left < right) {
        	max = Math.max(max, Math.min(height[left],height[right])*(right-left) );
        	if (height[left] <height[right])
        		left++;
        	else
        		right--;
        }
        return max;
    }
    //更新短板 beats 51.83%
    
    /**3. Longest Substring Without Repeating Characters
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
        	return 0;
        Set<Character> set = new HashSet<Character>();
        int max = 0;
        int num = 0;
        for (int i=0; i<s.length(); i++) {
        	if (!set.contains(s.charAt(i))) {
        		set.add(s.charAt(i));
        		num += 1;
        	} else {
        		set.clear();
        		set.add(s.charAt(i));
        		num = 1;
        		for (int j=i-1; j>=0 && s.charAt(j) != s.charAt(i); j--) {
        			set.add(s.charAt(j));
        			num += 1;
        		}
        		
        	}
        	max = Math.max(max, num);
        }
        return max;
    }
    // beats 9.88%, 从上一个重复的点开始重新计算
    
    public int lengthOfLongestSubstring1(String s) {
        if(s==null || s.length()==0)
            return 0;
        
        HashSet<Character> set = new HashSet<Character>();
        int max = 0;
        int walker = 0;
        int runner = 0;
        while(runner<s.length()){
            if(set.contains(s.charAt(runner))){
                if(max<runner-walker)
                    max = runner-walker;
                
                while(s.charAt(walker)!=s.charAt(runner)){
                    set.remove(s.charAt(walker));
                    walker++;
                }
                walker++;
            }else
                set.add(s.charAt(runner));
                
            runner++;
        }
        max = Math.max(max,runner-walker);
        return max;
    }
    //转，beats 74.56%，对一个窗口进行移动，runner一直向右，当遇到重复的walker向右
    
    
    
    
    
}
