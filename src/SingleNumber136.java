import java.util.Arrays;


public class SingleNumber136 {
	public static void main(String [] args) {
		int[] arr= {17,12,5,-6,12,4,17,-5,2,-3,2,4,5,16,-3,-4,15,15,-4,-5,-6};
		System.out.println(singleNumber(arr));
		
	}
	 public static int singleNumber(int[] nums) {
		 int result = 0;
		    for (int i = 0; i < nums.length; i ++) {
		        result = result ^ nums[i];
		    }
		    return result;
//	        Arrays.sort(nums);
//	        if (nums.length == 1) {
//	            return nums[0];
//	        }
//	        for (int i = 1; i < nums.length; i = i +2) {
//	        	if (nums[i] != nums[i-1]) {
//	        		return nums[i-1];
//	        	}
//	        }
//	        return nums[nums.length-1];
	    }
	
}
