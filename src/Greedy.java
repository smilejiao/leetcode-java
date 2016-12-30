import java.util.HashSet;
import java.util.Set;

/**̰��*/
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
	//ת��beats 58.08%
	
    public boolean canJump1(int [] nums) {
    	int reach = 0;
    	int i = 0;
    	for (; i < nums.length && i <= reach; i++) {
    		reach = Math.max(reach, i+nums[i]); //֮ǰ�ɴﵽ�� ��ǰ�ɵ��� �������Ϊreach
    	}
    	return (i== nums.length);
    }
    //����˼ά�� beats 24.92%                                                                          
    
    public boolean canJump2(int[] A) {  
        int last = A.length - 1;  
        for (int i = A.length - 2; i >= 0; i--) {  
            if (i + A[i] >= last) {  
                last = i;  
            }  
        }  
        return (last <= 0);  
    }  
	//����˼ά
    
    
    /**45. Jump Game II 
     * @param nums
     * @return ��С��������
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
    //ת�� beats 53.99%
    /*
     * ret:ĿǰΪֹ��jump��
     * curMax:�ܵ�������Χ
     * curReach:��nums[0]����ret�Σ��ﵽ�����Χ
     * ��curReach < i��˵��ret��jump�Ѿ������Ը��ǵ�ǰ��i��Ԫ�أ������Ҫ����һ��jump��ʹ֮�ﵽ��¼��curMax��
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
    //ת
    /*��ǰ���������飬��¼��ǰ���ֹ�����ͼ۸���Ϊ����۸�
     * �������Ե���۸���۵����棬��Ϊ���ܵ�������棬�������������У����ֹ�����������������*/
    
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
    //��ǰ���������飬ֻҪ����ļ۸����ǰһ��ļ۸񣬾��������档
    
    
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
    //���¶̰� beats 51.83%
    
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
    // beats 9.88%, ����һ���ظ��ĵ㿪ʼ���¼���
    
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
    //ת��beats 74.56%����һ�����ڽ����ƶ���runnerһֱ���ң��������ظ���walker����
    
    
    
    
    
}
