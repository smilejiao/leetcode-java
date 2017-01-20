import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DataStructure.Interval;
import DataStructure.ListNode;
import DataStructure.TreeLinkNode;
import DataStructure.TreeNode;

/**
 * @author£ºJJL
 * @date: 2016Äê9ÔÂ28ÈÕ
 * @description:
 */
public class Run {
	public static void wang() {
		double s = 2, k = 1;
		while (k < 2016) {
			s = 1/(1-s);
			k = k+1;
		} 
		System.out.println(s);
	}
	public static void main(String [] args) {

		
		SolutionTree s = new SolutionTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right = new TreeNode(3);
		int[] n = new int[]{-1,0,1,2};
//		root.right.left = new TreeNode(15);
//		root.right.right = new TreeNode(7);

		System.out.println();
		
		SolutionTree ss = new SolutionTree();
		int[] n1 = new int[]{2,1};
		int[] n2 = new int[]{1,2,3};
//		ss.firstMissingPositive(n1);
//		Solution2 s = new Solution2();
		ListNode l1 = new ListNode(-1);
		l1.next = new ListNode(0);
		l1.next.next = new ListNode(1);
		l1.next.next.next = new ListNode(2);


//		ListNode n = l1;
//		System.out.println();
		
		SolutionBFS sb = new SolutionBFS();
		String s1 = "hot";
		String s2 = "dog";
		Set<String> wordList = new HashSet<String>();
		wordList.add("hot");
		wordList.add("dog");
		wordList.add("dot");
//		wordList.add("cog");
//		wordList.add("dot");
//		
//		wordList.add("hit");
//		wordList.add("lot");
//		wordList.add("log");
		//List<List<String>> ret = sb.findLadders(s1, s2, wordList);
		//i = sb.ladderLength(s1, s2, wordList);
		//System.out.println(ret);
		
		DFS dfs = new DFS();
		int[]  sip = {1, 2};
//		List<List<Integer>> string = dfs.combinationSum2(sip, 4);
//		System.out.println(string);
		
		
		Greedy g = new Greedy();
		int [] A = {1,2};
		//g.jump(A);
		
		
		Simulation d = new Simulation();
		//System.out.println(d.multiply("9", "9"));
		System.out.println(d.divide(-2147483648,-1));
	}

}
