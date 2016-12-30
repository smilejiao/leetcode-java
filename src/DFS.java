import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DFS {
	
	
	
    /**131. Palindrome Partitioning
     * @param s
     * @return ����������
     */
    public List<List<String>> partition(String s) {
    	List<List<String>> ret = new ArrayList<List<String>>();
    	List<String> item = new ArrayList<String>();
    	if (s == null || s.length() == 0)
    		return ret;
    	
    	DFSpartition(s, ret, item, 0);
    	return ret;
    }
	public void DFSpartition(String s, List<List<String>> ret, List<String> item, int start) {
		if (start == s.length()) {
			ret.add(new ArrayList<String>(item));
			return;
		}
		for (int i=start; i<s.length(); i++) {
			String str = s.substring(start, i+1);
			if (isPalindrome(str)) {
				item.add(str);
				DFSpartition(s, ret, item, i+1);
				item.remove(item.size()-1);
			}
		}
		
	}
	public boolean isPalindrome (String str) {
		int s = 0;
		int e = str.length()-1;
		while (s <= e) {
			if (str.charAt(s) != str.charAt(e))
				return false;
			s = s+1;
			e = e-1;
		}
		return true;
	}
	//beats 50.34%
    //��ȱ���������ַ��������еݹ飬���������Ƿ������ַ���β
    
    
    /**132. Palindrome Partitioning II
     * @param s
     * @return
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0)
        	return 0;
        List<List<String>> ret = partition(s);
        int min = s.length()-1;
        for (int i=0; i<ret.size(); i++) {
        	List<String> item = ret.get(i);
        	if (min > item.size()-1)
        		min = item.size()-1;
        }
        return min;
    }
    //Time Limit Exceed
    
    
    /**62. Unique Paths 
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths0(int m, int n) {
        if (m < 1 || n < 1) 
        	return 0; // ��ֹ����
        if (m == 1 && n == 1)
        	return 1; // ��������
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }
    //Time Limit Exceed

    public int uniquePaths(int m, int n) {
    	if (m==0 || n==0)
    		return 0;
    	if (m==1 || n==1)
    		return 1;
    	int[][]dp = new int[m][n];
    	for (int i=0; i<m; i++)//ֻ��һ��
    		dp[i][0] = 1;
    	for (int i=0; i<n; i++)//ֻ��һ��
    		dp[0][i] = 1;
    	for (int i=1; i<m; i++) {
    		for (int j=1; j<n; j++) {
    			dp[i][j] = dp[i-1][j] + dp[i][j-1];
    		}
    	}
    	return dp[m-1][n-1];
    }
    /*dp[i][j]��ʾ�����꣨i,j�����߷���������
    dp[i][j] = dp[i-1][j] + dp[i][j-1]*/
    
    
    /**63. Unique Paths II 
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
        	return 0;
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1)
        	return 0;
        
        int [][]dp = new int[m][n];
        dp[0][0] = 1;
        
        for (int i=1; i<m; i++) {
        	if (obstacleGrid[i][0] == 1)
        		dp[i][0] = 0;
        	else 
        		dp[i][0] = dp[i-1][0];
        }
        for (int i=1; i<n; i++) {
        	if (obstacleGrid[0][i] == 1) 
        		dp[0][i] = 0;
        	else 
        		dp[0][i] = dp[0][i-1];
        }
        
        for (int i=1; i<m; i++) {
        	for (int j=1; j<n; j++) {
        		if (obstacleGrid[i][j] == 1)
        			dp[i][j] = 0;
        		else 
        			dp[i][j] = dp[i-1][j] + dp[i][j-1];
        	}
        }
        return dp[m-1][n-1];
    }
    //��ʼ����ʱ�����һ�������ϰ���ϰ���������и��ӵ��߷�����0��
    
    
    /**51. N-Queens
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<List<String>>();
        if (n <= 0)
        	return ret;
        int[] val = new int[n];
        dfsQ(n, ret, 0, val);
        return ret;
    }
    public void dfsQ(int n, List<List<String>> ret, int row, int[] val) {
    	if (row == n) {
    		List<String> item = new ArrayList<String>();
    		for (int i=0; i<n; i++) {
    			int pos = val[i];
    			String s = "";
    			for (int j=0; j<n; j++) {
    				if (j == pos)
    					s += "Q";
    				else
    					s += ".";
    			}
    			item.add(s);
    		}
    		ret.add(item);
    		
    	} else {
    		for (int i=0; i<n; i++) {
    			val[row] = i;
    			if (isValid(val, row)) {
    				dfsQ(n, ret, row+1, val);
    			}
    		}
    	}
    }
    public boolean isValid (int[]val, int row) {
    	for (int i=0; i<row; i++) {
    		if (val[row] == val[i] || Math.abs(val[row] - val[i]) == row-i) //��ͬ�У��Ҳ��Խ�
    			return false;
    	}
    	return true;
    }
    
    /*
	 һά������±��ʾ�����꣨��һ�У����������ֵ��ʾ�����꣨��һ�У�
        ��һ��ѭ���ݹ鴦�������⡣
        ��������У���ÿһ��ݹ麯���У�������һ��ѭ����һ���ʺ������Ӧ�е�ĳһ���У�
        �����ǰ���̺Ϸ������Ǿ͵ݹ鴦����һ�У��ҵ���ȷ���������Ǿʹ洢����������档
     
        ������Ŀ����ʹ�������·��������һ��ѭ��ȥö�ٵ�ǰ���������Ȼ���Ԫ�ؼ��룬�ݹ飬�ٰ�Ԫ���Ƴ���
        �����Ŀ�в����Ƴ���ԭ����������һ��һά����ȥ��ʺ��ڶ�Ӧ�е���һ�У���Ϊһ��ֻ����һ���ʺ�
        �����ά���飬��ô����Ҫ����һ����һ���ڵݹ���������û�лʺ����Ե�����һ���ġ�
     * */
    
    
    /**52. N-Queens II
     * @param n
     * @return
     */
    int count = 0;
    public int totalNQueens(int n) {
        if (n <= 0)
        	return 0;
        int[] val = new int[n];
        bfsQs(n, 0, val);
        return count;
    }
    public void bfsQs(int n, int row, int[]val) {
    	if (n == row)
    		count += 1;
    	else {
    		for (int i=row; i<n; i++) {
    			val[row] = i;
    			if (isValid(val, row)) {
    				bfsQs(n, row+1, val);
    			}
    		}
    	}
    }
    //������һ������Ҫȫ�ִ洢��Ϣ������ʹ������洢Ҳ����
    
    /**93. Restore IP Addresses
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
    	List<String> ret = new ArrayList<String>();
        if (s == null || s.length() == 0)
        	return ret;
        String item = "";
        dfsIp(s, ret, item, 0);
        return ret;
    }
    public boolean validIp (String s) {
    	if (s.length() > 3)
    		return false;
    	if (s.startsWith("0"))
    		return s.equals("0");
    	int val = Integer.valueOf(s);
    	if (val >=0 && val <= 255) 
    		return true;
    	else
    		return false;
    }
    public void dfsIp(String s, List<String> ret, String item, int n) {
    	if (n == 3) {
    		String[] arr = s.split("\\.");
    		if (validIp(arr[3])) {
    			ret.add(new String(s));
    		}
    	} else {
    		String cur;
    		
    		if (n > 0) {
    			String[] arr = s.split("\\.");
    			s = "";
        		int count = arr.length-1;
        		cur = arr[count];
        		
        		for (int j=0; j<count; j++) {
    				s = s + arr[j] + ".";
    			}
    		} else {
    			cur = s;
    			s = "";
    		}
    		String tmp = s;
    		for (int i=1; i<cur.length(); i++) {
    			String pre = cur.substring(0, i);
    			String next = cur.substring(i, cur.length()); 
    			if (validIp(pre)) {
    				s = tmp + pre + "." + next;;
    				dfsIp(s, ret, item, n+1);
    				s = tmp + pre + next;
    			} else {
    				break;
    			}
    		}
    	}
    }
    //����AC�ˣ� beats 5.30%�� dfs���Ǻܲ�͸����
    //0.01.1.1�� ע���0���ж�
    
    public List<String> restoreIpAddresses0(String s) {
    	List<String> ret = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12)
        	return ret;
        String item = new String();
        dfsIP(s, 0, item, ret);
        return ret;
    }
    public void dfsIP(String s, int start, String item, List<String> ret) {
    	if (start == 3 && isValid(s)) {
    		ret.add(item + s);
    		return;
    	}
    	for (int i=1; i<=3 && i<s.length(); i++) {//���3λ����1λ��������ÿһ�����ѭ��3�Σ����������
    		String substr = s.substring(0, i);
    		if (isValid(substr)) {
    			dfsIP(s.substring(i, s.length()), start+1, item+substr+".", ret);
    			//������Ҫ����Ĳ��֣� ����+1�� ��ǰ�������ɵģ� ret���
    		}
    	}
    }
    public boolean isValid(String s){  
    	if (s.startsWith("0")) {
    		return s.equals("0");
    	}
    	int val = Integer.valueOf(s);
    	if (val >= 0 && val <= 255)
    		return true;
    	return false;
    } 
    //ת��������������
    
    
    /**39. Combination Sum
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0)
        	return ret;
    	List<Integer> item = new ArrayList<Integer>();
    	Arrays.sort(candidates);
    	dfsSum(candidates, target, ret, item, 0, 0);
    	return ret;
    }
    public void dfsSum(int[] candidates, int target, List<List<Integer>> ret, List<Integer> item, int sum, int start) {
    	for (int i=start; i<candidates.length; i++) {
    		int cur = candidates[i];
    		if (cur + sum == target) {
    		    item.add(cur);
    		    if (!ret.contains(item)) {
    		        ret.add(new ArrayList<Integer>(item));
        			item.remove(item.size()-1);
        			return;
    		    }
    			
    		} else if (cur + sum < target) {
    		    item.add(cur);
    			dfsSum(candidates, target, ret, item, cur+sum, i);
    			item.remove(item.size()-1);
    		} 
    	}
    }
    // beats 45.76%
    //ע������ظ�ʹ�ã�����setû��˳��4��2��8��
    
    
    /**40. Combination Sum II 
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	List<Integer> item = new ArrayList<Integer>();
        if (candidates == null || candidates.length == 0)
        	return ret;
        Arrays.sort(candidates);
        dfsSum2(candidates, target, ret, item, 0);
        return ret;
    }
    public void dfsSum2(int[] candidates, int target, List<List<Integer>> ret, List<Integer> item, int start) {
    	if (target == 0) {
    		List<Integer> list = new ArrayList<Integer>(item);
    		Collections.sort(list);
    		if (!ret.contains(list)) {
    			ret.add(list);
    		}
    		return;
    	}
    	for (int i=start; i<candidates.length; i++) {
    		if (target - candidates[i] >= 0) {
    			item.add(candidates[i]);
    			dfsSum2(candidates, target-candidates[i], ret, item, i+1);
    			item.remove(item.size()-1);
    		} else {
    			break;
    		}
    		
    	}
    }
    // 14.71%
    
    public List<List<Integer>> combinationSum2_1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();  
        List<Integer> item = new ArrayList<Integer>();
        if(candidates == null || candidates.length==0)  
            return res; 
            
        Arrays.sort(candidates);  
        boolean [] visited = new boolean[candidates.length];
        helper(candidates,target, 0, item ,res, visited);  
        return res;  
    }  
    
    private static void helper(int[] candidates, int target, int start, List<Integer> item,   
    List<List<Integer>> res, boolean[] visited){  
        if(target<0)  
            return;  
        if(target==0){  
            res.add(new ArrayList<Integer>(item));  
            return;  
        }
        
        for(int i=start;i<candidates.length;i++){
            if(!visited[i]){
                if(i>0 && candidates[i] == candidates[i-1] && visited[i-1]==false)//deal with dupicate
                    continue;  
                item.add(candidates[i]);
                visited[i]=true;
                int newtarget = target - candidates[i];
                helper(candidates,newtarget,i+1,item,res,visited);  
                visited[i]=false;
                item.remove(item.size()-1);  
            }
        }  
    }
    //ת����visit���б�ǣ�i==i-1�ж��ظ�
    
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        String str = "";
        backTracking(n, n, list, "");
        return list;
    }
    
    
    /**22. Generate Parentheses
     * @param left
     * @param right
     * @param list
     * @param str
     */
    public void backTracking (int left, int right, List<String> list, String str) {
        if (left > right) {
            return;
        }
    	if (left == 0 && right == 0) {
    		list.add(str);
    	}
    	if (left > 0) {
    		backTracking (left-1, right, list, str+"(");
    	}
    	if (right > 0) {
    		backTracking (left, right-1, list, str+")");
    	}
    }
    
    
    /**37. Sudoku Solver
     * @param board
     */
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0)
        	return;
        dfsSolve(board); 
    }
    public boolean dfsSolve(char[][] board) {
    	for (int i=0; i<board.length; i++) {
    		for (int j=0; j<board[0].length; j++) {
    			if (board[i][j] == '.') {
    				for (char c='1'; c<='9'; c++) {//����
    					
    					if (isValidNum(board, i, j, c)) {
    						board[i][j] = c;
    						if (dfsSolve(board)) {
    							return true;
    						}else {
            					board[i][j] = '.';//����
        					}
    					} 
    				}
    				return false;
    			}
    		}
    	}
    	return true;
    	
    }
    public boolean isValidNum(char[][] board, int x, int y, char c) {
    	for (int i=0; i<9; i++) {
    		if (board[x][i] == c)
    			return false;
    	}
    	for (int j=0; j<9; j++) {
    		if (board[j][y] == c)
    			return false;
    	}
    	for (int i=x/3*3; i<x/3*3+3; i++) {
    		for (int j=y/3*3; j<y/3*3+3; j++) {
    			if (board[i][j]==c)
    				return false;
    		}
    	}
    	
    	return true;
    }
    // beats 63.82%, ����С�Ź���x/3*3
    
    
    /**79. Word Search
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0)
        	return false;
        int x = board.length;
        int y = board[0].length;
        boolean[][] visit = new boolean[x][y];
        for (int i=0; i<x; i++) {
        	for (int j=0; j<y; j++) {
        		if (board[i][j] == word.charAt(0)) {
        			visit[i][j] = true;
        			if(dfsWord(board, word, visit, 1, i, j)) 
        				return true;
        			else 
        				visit[i][j] = false;
        		}
        	}
        }
        return false;
    }
    public boolean dfsWord(char[][]board, String word, boolean[][] visit, int start, int x, int y) {
    	if (start == word.length())
    		return true;
    	int xlen = board.length;
    	int ylen = board[0].length;
    	char ch = word.charAt(start);
    	
    	if (x-1>=0 && !visit[x-1][y] && board[x-1][y] == ch) {
    		visit[x-1][y] = true;
			if(dfsWord(board, word, visit, start+1, x-1, y)) 
				return true;
			else 
				visit[x-1][y] = false;
    	}
    	
    	if (x+1<xlen && !visit[x+1][y] && board[x+1][y] == ch) {
    		visit[x+1][y] = true;
			if(dfsWord(board, word, visit, start+1, x+1, y)) 
				return true;
			else 
				visit[x+1][y] = false;
    	}
    	
    	if (y-1>=0 && !visit[x][y-1] && board[x][y-1] == ch) {
    		visit[x][y-1] = true;
			if(dfsWord(board, word, visit, start+1, x, y-1)) 
				return true;
			else 
				visit[x][y-1] = false;
    	}
    	
    	if (y+1<ylen && !visit[x][y+1] && board[x][y+1] == ch) {
    		visit[x][y+1] = true;
			if(dfsWord(board, word, visit, start+1, x, y+1)) 
				return true;
			else 
				visit[x][y+1] = false;
    	}
    	return false;
    	
    }
    // beats 67.38%,yeah~!
    
    
    /**212. Word Search II
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
    	List<String> ret = new ArrayList<String>();
        if (words == null || words.length == 0)
        	return ret;
        for (int i=0; i<words.length; i++) {	
        	if (exist(board, words[i])) {
        		if (!ret.contains(words[i]))
        			ret.add(words[i]);
        	}
        }	
        return ret;	
    }
    //Time Limit Exceed
    
    
}
