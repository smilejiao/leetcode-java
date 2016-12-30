
public class SolutionSearch {
	
	
    /**34. Search for a Range
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
    	int[] arr = new int[2];
    	arr[0] = -1;
    	arr[1] = -1;
        int len = nums.length;
        if (len == 0) {
        	return arr;
        }
        half(nums, target, arr, 0, len);
        return arr;
    }
    public void half (int[] nums, int target, int[] arr, int s, int e) {
    	if (s > e) {
    		return;
    	}
    	int m = (s+e)/2;
    	if (nums[m] > target) {
    		half(nums, target, arr, s, m-1);
    	} else if (nums[m] < target) {
    		half(nums, target, arr, m+1, e);
    	} else {
    		while( m >= 0 ) {//start
    			if (nums[m] != target)
    				break;
    			--m;
    		}    		
    		m += 1;
    		arr[0] = m;
    		while (m <= e) {//end
    			if (nums[m] != target)
    				break;
    			++m;
    		}
    		arr[1] = m-1;
    	}
    }
    // beats 65.12% 
	
    /**35. Search Insert Position 
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
    	int i = 0;
        for (; i < nums.length; i++) {
        	if (nums[i] >= target) {//>=
        		return i;
        	}
        }
        return i;
    }
    //half search
    
    /**74. Search a 2D Matrix
     * @param matrix
     * @param target
     * @return 每行第一个比上行最后要一个大
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
        	return false;
        }
        int ce = matrix.length;//竖
        int re = matrix[0].length;//横
        int col = 0;
        int row = re-1;
        while (row>=0 && col<ce) {
        	if (matrix[col][row] > target) 
        		--row;
        	else if (matrix[col][row] < target)
        		++col;
        	else
        		return true;
        }
        return false;
    }
    //从右上角开始遍历
    
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
        	return false;
        }
        int s = 0;
        int e = matrix.length-1;
        while (s<=e) {
        	int m = (s+e)/2;
        	if (matrix[m][0] > target)
        		e = m-1;
        	else if (matrix[m][0] < target)
        		s = m+1;
        	else 
        		return true;
        }
        int col = e;
        if (col < 0) 
        	return false;
        s = 0;
        e = matrix[0].length-1;
        while(s<=e) {
        	int m = (s+e)/2;
        	if (matrix[col][m] > target)
        		e = m-1;
        	else if (matrix[col][m] < target)
        		s = m+1;
        	else 
        		return true;
        }
        return false;
    }
    //二分法，先确定竖行：col，在确定横行
    //注意：确定col后判断col>=0
    
    /**240. Search a 2D Matrix II
     * @param matrix
     * @param target
     * @return 从上到下，从左到右，依次增大
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
        	return false;
        }
        int ce = matrix.length;//竖
        int re = matrix[0].length;//横
        int col = 0;
        int row = re-1;
        while (row>=0 && col<ce) {
        	if (matrix[col][row] > target) 
        		--row;
        	else if (matrix[col][row] < target)
        		++col;
        	else
        		return true;
        }
        return false;
    }
}
