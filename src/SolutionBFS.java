import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import DataStructure.ArrayNode;

/**
 * @author lenovo
 *2016-11-24
 */
public class SolutionBFS {
//BFS，层序遍历
	
    /**127. Word Ladder
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return 从start变换到end，途中只能经过字典中的单词，每次只允许差一个字母
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> q = new LinkedList<String>();
        q.offer(beginWord);
        q.offer("");
        int res = 1;
        while (!q.isEmpty()) {
        	String str = q.poll();
        	if (!str.equals("")) {
        		int strlen = str.length();
        		for (int i=0; i<strlen; i++) {
        			char[] s = str.toCharArray();
        			char tmp = s[i];
        			for (char c='a'; c<='z'; c++) {
        				if (c == tmp)
        					continue;
        				s[i] = c;
        				String ss = String.valueOf(s);
        				if (ss.equals(endWord)) {
        					return res+1;
        				}
        				if (wordList.contains(ss)){
        					q.offer(ss);
        					wordList.remove(ss);
        				}
        			}
        			s[i] = tmp;
        		}
        	} else if (!q.isEmpty()) {
        		res++;
        		q.offer("");
        	}
        }
        return 0;       
    }
    //beats54.46%
    //Queue：peek()返回队头元素； poll()移除并返回队头元素; offer()添加至队尾
    //wrong-case:
    /*"a", "c", ["a","b","c"]
     a->c,
     length = 2
    */
    
    public int ladderLength1(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }
        
        return 0;
    }
    //转， beats 88.36%
    
    
    /**126. Word Ladder II 
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return 给出单词转换的序列
     */
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    	List<List<String>> ret = new ArrayList<List<String>>();
        List<String> p = new ArrayList<String>();
        Queue<String> q = new LinkedList<String>();
        
        q.offer(beginWord);
        q.offer("");
        generateLaddders(endWord, wordList, ret, p, q, false); 
        
        return ret;
    }
    public void generateLaddders(String endWord, Set<String> wordList, List<List<String>> ret, List<String> p, Queue<String> q, boolean flag) {
    	if (!q.isEmpty()) {
    		String str = q.poll();
    		if (!str.equals("")) {
    			List<String> pp = new ArrayList<String>(p);
    			pp.add(str);
    			
    			int strlen = str.length();
		    	for (int i=0; i<strlen; i++) {
					char[] s = str.toCharArray();
					char tmp = s[i];
					for (char c='a'; c<='z'; c++) {
						if (c == tmp)
							continue;
						s[i] = c;
						String ss = String.valueOf(s);
						if (ss.equals(endWord)) {
							pp.add(ss);
							ret.add(pp);
							flag = true;
						}
						if (wordList.contains(ss)){
							q.offer(ss);
							wordList.remove(ss);
							generateLaddders(endWord, wordList, ret, pp, q, flag);
						}
					}
					s[i] = tmp;
		    	}
    		} else {
    			q.offer("");
    			if (flag) {
    				return;
    			}else {
    				generateLaddders(endWord, wordList, ret, p, q, flag);
    			}
    		}
    	}
    }
    //runtime error，估计内存爆了。。
    
    public List<List<String>> findLadders0(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> ret = new ArrayList<List<String>>();
        List<String> p = new ArrayList<String>();
        if (wordList.isEmpty()) {
        	return ret;
        }
        p.add(beginWord);
        generateLaddders0(beginWord, endWord, wordList, ret, p);
        return ret;
    }
    public void generateLaddders0(String beginWord, String endWord, Set<String> wordList, List<List<String>> ret, List<String> p) {
    	String str = p.get(p.size()-1);
    	if (str.endsWith(endWord)) {
    		List pp = new ArrayList<String>(p);
    		if (ret.isEmpty()) {
    			ret.add(pp);
    		} else {
    			List l = ret.get(0);
    			if (pp.size() < l.size()) {
    				ret.clear();
    				ret.add(pp);
    			} else if (pp.size() == l.size()) {
    				ret.add(pp);
    			}
    		}
    		return;
    	}
    	int strlen = str.length();
    	for (int i=0; i<strlen; i++) {
			char[] s = str.toCharArray();
			char tmp = s[i];
			for (char c='a'; c<='z'; c++) {
				if (c == tmp)
					continue;
				s[i] = c;
				String ss = String.valueOf(s);
				if (wordList.contains(ss)){
					p.add(ss);
					wordList.remove(ss);
					generateLaddders0(beginWord, endWord, wordList, ret, p);
					p.remove(p.size()-1);
					wordList.add(ss);
				}
			}
			s[i] = tmp;
    	}
    	
    }
    //time exceed，应该用递归导致深度搜索超时，应该使用BFS广度搜索
    
    HashMap<String,Integer> path = new HashMap<String,Integer>();  
    //bfs生成path  
    void bfs(String start, String end, HashSet<String> dict) {  
        Queue queue = new LinkedList<String>();  
        queue.add(start);  
        path.put(start,0);  
        String current;  
        while(!queue.isEmpty()) {  
            current = (String)queue.poll();  
            if(current==end) {  
                continue;  
            }
            for(int i=0; i<current.length(); i++) {
                char[] strCharArr = current.toCharArray();  
                for(char ch='a'; ch<='z'; ch++) {  
                    if(strCharArr[i] == ch) {  
                        continue;  
                    }
                    strCharArr[i] = ch;
                    String newWord = new String(strCharArr);  
                    if(newWord.equals(end)==true||dict.contains(newWord)) {  
                        //每个单词在path中只能出现一次，也就是每个单词只能出现在一层中，这样就很巧妙的解决了环的问题。  
                    	if(path.get(newWord)==null) {
                            int depth = (int)path.get(current);  
                            path.put(newWord,depth + 1);//将下一层加入到map中
                            queue.add(newWord);
                        }  
                    }  
                }  
            }  
        }  
    }  
    //从目标单词往回找开始单词，记录所有路径
    void dfs(String start, String end, HashSet<String> dict, List<String> pathArray,List<List<String>> result) {  
        //找到了，需要reverse加入的所有单词  
    	if(start.equals(end)==true) {  
            pathArray.add(start);
            Collections.reverse(pathArray);  
            result.add(pathArray);  
            return;
        }  
        if(path.get(start)==null) {  
            return;  
        }
        pathArray.add(start);
        int nextDepth = (int)path.get(start) - 1;  
        for(int i=0;i<start.length();i++) {  
            char[] strCharArr = start.toCharArray();  
            for(char ch='a';ch<='z';ch++) {  
                if(strCharArr[i]==ch) {  
                    continue;  
                }  
                strCharArr[i] = ch;  
                String newWord = new String(strCharArr);  
                //只相差一个字母，同时这个单词所在的层数也是当前单词的上一层
                if(path.get(newWord)!=null&&(path.get(newWord)==nextDepth)) {  
                    List<String> newPathArray = new ArrayList<String>(pathArray);  
                    dfs(newWord,end,dict,newPathArray,result);  
                }  
            }  
        }  
    }  
      
    public List<List<String>> findLadders(String start, String end, HashSet<String> wordList) {  
        List<List<String>> result = new ArrayList<List<String>>();  
        List<String> path = new ArrayList<String>();  
        if(start==null||end==null||start.length()!=end.length()) {  
            return result;  
        }  
        bfs(start, end, wordList);  
        dfs(end,start, wordList, path, result);  
        return result;  
    }  
    //转，beats 81.50% of java submissions.
 
    /**130. Surrounded Regions
     * @param board
     */
    public void solve(char[][] board) {
    	int x = board.length;
    	if (x < 2)
    		return;
    	int y = board[0].length;
    	if (y < 2)
    		return;
    	for (int i=0; i<y-1; i++) {
    		bfs(board, 0, i);//上
    		bfs(board, x-1, i+1);//下
    	}
    	for (int i=0; i<x-1; i++) {
    		bfs(board, i+1, 0);//左
    		bfs(board, i, y-1);
    	}
    	for(int i=0; i<x; i++) {
    		for (int j=0; j<y; j++) {
    			if (board[i][j] == 'O')
    				board[i][j] = 'X';
    			else if (board[i][j] == '+')
    				board[i][j] = 'O';
    		}
    	}
    }
    public void bfs(char[][] board, int x, int y) {
    	if(board[x][y]!='O')  
            return; 
    	
    	int ylen = board[0].length;
    	int xlen = board.length;
    	
    	char ch = board[x][y];
    	if (ch == 'O') {
    		ArrayNode node = new ArrayNode(ch, x, y);
    		Queue<ArrayNode> q = new LinkedList<ArrayNode>();
    		q.offer(node);
    		while(!q.isEmpty()) {
    			ArrayNode cur = q.poll();
    			int i = cur.x;
    			int j = cur.y;
    			board[i][j] = '+';
    			if (j-1>=0 && board[i][j-1]=='O')//左
    				q.offer(new ArrayNode(board[i][j-1], i, j-1));
    			if (j+1<ylen && board[i][j+1]=='O')//右
    				q.offer(new ArrayNode(board[i][j+1], i, j+1));
    			if (i-1>=0 && board[i-1][j] == 'O') //上
    				q.offer(new ArrayNode(board[i-1][j], i-1, j));
    			if (i+1<xlen && board[i+1][j] == 'O')//下
    				q.offer(new ArrayNode(board[i+1][j], i+1, j));
    		}
    		
    	}
    	
    }
    //Time Limit Exceeded;
    
    public void solve1(char[][] board) {
    	int x = board.length;
    	if (x < 2)
    		return;
    	int y = board[0].length;
    	if (y < 2)
    		return;
    	for (int i=0; i<y-1; i++) {
    		fill(board, 0, i);//上
    		fill(board, x-1, i+1);//下
    	}
    	for (int i=0; i<x-1; i++) {
    		fill(board, i+1, 0);//左
    		fill(board, i, y-1);
    	}
    	for(int i=0; i<x; i++) {
    		for (int j=0; j<y; j++) {
    			if (board[i][j] == 'O')
    				board[i][j] = 'X';
    			else if (board[i][j] == '#')
    				board[i][j] = 'O';
    		}
    	}
    }
    public void fill(char[][] board, int i, int j)  
    {  
        if(board[i][j]!='O')  
            return;  
        board[i][j] = '#';  
        LinkedList<Integer> queue = new LinkedList<Integer>();  
        int code = i*board[0].length+j;
        queue.offer(code);  
        while(!queue.isEmpty())  
        {  
            code = queue.poll();  
            int row = code/board[0].length;  
            int col = code%board[0].length;  
            if(row>0 && board[row-1][col]=='O')  
            {  
                queue.offer((row-1)*board[0].length+col);  
                board[row-1][col]='#';  
            }  
            if(row<board.length-1 && board[row+1][col]=='O')  
            {  
                queue.offer((row+1)*board[0].length+col);  
                board[row+1][col]='#';  
            }  
            if(col>0 && board[row][col-1]=='O')  
            {  
                queue.offer(row*board[0].length+col-1);  
                board[row][col-1]='#';  
            }  
            if(col<board[0].length-1 && board[row][col+1]=='O')  
            {  
                queue.offer(row*board[0].length+col+1);  
                board[row][col+1]='#';  
            }              
        }  
    } 
    //beats 38.59%,转
    
    public void solve0(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) 
        	return;
        for (int i=0; i<board.length; i++) {
        	for (int j=0; j<board[i].length; j++) {
        		if (board[i][j] == 'O' && solvebfs(board, i, j)) {
        			board[i][j] = 'X';
        		}
        	}
        }
    }
    public boolean solvebfs (char[][] board, int i , int j) {
    	int ilen = board.length-1;
    	int jlen = board[0].length-1;
    	if (i == 0 || i == ilen || j == 0 || j == jlen) {
    		return false;
    	}
    	boolean up = false;
    	for (int a = i-1; a >=0; a--) {
    		if (board[a][j] == 'X') {
    			up = true;
    			break;
    		}		
    	}
    	boolean down = false;
    	for (int a = i+1; a <= ilen; a++) {
    		if (board[a][j] == 'X') {
    			down = true;
    			break;
    		}
    			
    	}
    	boolean left = false;
    	for (int a = j-1; a >= 0; a--) {
    		if (board[i][a] == 'X') {
    			left = true;
    			break;
    		}
    			
    	}
    	boolean right = false;
    	for (int a = j+1; a <= jlen; a++) {
    		if (board[i][a] == 'X') {
    			right = true;
    			break;
    		}
    			
    	}
    	return up && down && left && right; 
    }
    //wrong, 如果某个O在边（角）上，则所有与这个O相连的O都不能变成X。
    //从上下左右四个边界往里走，凡是能碰到的{‘O’}，都是跟边界接壤的，应该保留
    
    
}
