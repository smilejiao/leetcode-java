package again;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lenovo
 *数组类
 */
public class Solution_Array {
    /** 26. Remove Duplicates from Sorted Array 
     * @param nums
     * @return 有序数组中删除重复数字
     */
    public int removeDuplicates(int[] nums) {
    	int count = 0;
    	int len = len = nums.length;
    	for (int i=1; i<len; i++) {
    		if (nums[i] == nums[i-1]) {
    			count++;
    		} else {
    			nums[i-count] = nums[i]; 
    		}
    	}
    	return len-count;
    }
    
    
    
    /**80. Remove Duplicates from Sorted Array II 
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
    	Queue<Integer> q = new LinkedList<Integer>();
    	int count = 0;
    	int len = nums.length;
    	for ()
    }
}
