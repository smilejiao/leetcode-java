import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import DataStructure.ListNode;
import DataStructure.TreeLinkNode;
import DataStructure.TreeNode;


public class SolutionTree {
	
	
    /**144. Binary Tree Preorder Traversal
     * @param root
     * @return 前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        preorder(root, list);
        return list;
    }
    public void preorder(TreeNode root, List<Integer> list) {
    	if (root != null) {
    		list.add(root.val);
    		preorder(root.left, list);
        	preorder(root.right, list);
    	}
    }
    
    /**94. Binary Tree Inorder Traversal 
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> list = new ArrayList<Integer>();
    	inorder(root, list);
        return list;
    }
    public void inorder(TreeNode root, List<Integer> list) {
    	if (root != null) {  		
    		inorder(root.left, list);
    		list.add(root.val);
    		inorder(root.right, list);
    	}
    }
    
    /**145. Binary Tree Postorder Traversal
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> list = new ArrayList<Integer>();
    	postorder(root, list);
        return list;
    }
    public void postorder(TreeNode root, List<Integer> list) {
    	if (root != null) {  		
    		postorder(root.left, list);   		
    		postorder(root.right, list);
    		list.add(root.val);
    	}
    }
    
    /**102. Binary Tree Level Order Traversal
     * @param root
     * @return
     */
    List<List<Integer>> levelret = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrder(TreeNode root) {
    	if (root == null) {
    		return levelret;
    	}
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.offer(root);
    	while (!q.isEmpty()) {
    		q = level(q);
    	}
    	return levelret;
    }
    public Queue<TreeNode> level(Queue<TreeNode> q) {
    	List<Integer> l1 = new ArrayList<Integer>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    	while (!q.isEmpty()) {
    		TreeNode node = q.poll();	
    		l1.add(node.val);
    		if (node.left != null) {
    			q2.offer(node.left);
    		}
    		if (node.right != null) {
    			q2.offer(node.right);
    		}
    	}
    	levelret.add(l1);
    	return q2;
    }
    //list设置为全局，递归维持队列
    
    public List<List<Integer>> levelOrder1(TreeNode root) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	levelsearch(root, 1, ret);
    	return ret;
    }
    public void levelsearch (TreeNode root, int level, List<List<Integer>> list) {
    	if (root == null) {
    		return;
    	}
    	if (level > list.size()) {
    		List<Integer> l = new ArrayList<Integer>();
    		list.add(l);
    	}
    	list.get(level-1).add(root.val);
    	levelsearch(root.left, level+1, list);
    	levelsearch(root.right, level+1, list);
    }
    //递归，使用arrayList可随机访问的特性
    //注意list.get(level-1)，因为第一层对应list中的get(0)
    
    public List<List<Integer>> levelOrder2(TreeNode root) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	if (root == null) {
    		return ret;
    	}
    	TreeNode flag = new TreeNode(0);
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.add(root);
    	q.add(flag);
    	List<Integer> temp = new ArrayList<Integer>();
    	while(q.size() > 1) {
    		TreeNode top = q.poll();
    		if (top == flag) {
    			ret.add(temp);
    			temp = new ArrayList<Integer>();
    			q.add(flag);
    		}else {
    			temp.add(top.val);
    			if (top.left != null) {
    				q.add(top.left);
    			}
    			if (top.right != null) {
    				q.add(top.right);
    			}
    		}
    	}
    	ret.add(temp);//最后一层
    	return ret;
    }
    //迭代，设置标记
    
    /**107. Binary Tree Level Order Traversal II 
     * @param root
     * @return
     */
    Stack<List<Integer>> s = new Stack<List<Integer>>();
    List<List<Integer>> ret = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	if (root == null) {
    		return ret;
    	}
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.offer(root);
    	while (!q.isEmpty()) {
    		q = levelBottom(q);
    	}
    	while (!s.isEmpty()) {
    		ret.add(s.pop());
    	}
    	return ret;
    }
    public Queue<TreeNode> levelBottom(Queue<TreeNode> q) {
    	List<Integer> l1 = new ArrayList<Integer>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    	while (!q.isEmpty()) {
    		TreeNode node = q.poll();	
    		l1.add(node.val);
    		if (node.left != null) {
    			q2.offer(node.left);
    		}
    		if (node.right != null) {
    			q2.offer(node.right);
    		}
    	}
    	s.push(l1);
    	return q2;
    }
    //使用上题的思路，得到的list先推入栈，最后出栈存入ret中
    
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
    	LinkedList<List<Integer>> ans = new LinkedList<List<Integer>>();
    	if (root == null) {
    		return ans;
    	}
    	TreeNode last = new TreeNode(-1);
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.add(root);
    	List<Integer> level = new ArrayList<Integer>();	
    	while (q.size() > 1) {
    		TreeNode node = q.poll();
    		if (node != last) {
    			level.add(node.val);
    			if (node.left != null) {
    				q.add(node.left);
    			}
    			if (node.right != null) {
    				q.add(node.right);
    			}
    		} else {
    			ans.addFirst(level);
    			level.clear();
    			q.add(last);
    		}
    	}
    	ans.addFirst(level);
    	return ans;
    }
    //递归，设置哨兵
    //使用linkedList，存储时  addFirst()
    
    /**103. Binary Tree Zigzag Level Order Traversal
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> ans = new LinkedList<List<Integer>>();
    	if (root == null) {
    		return ans;
    	}
    	TreeNode last = new TreeNode(-1);
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.offer(root);
    	q.offer(last);
    	LinkedList<Integer> level = new LinkedList<Integer>();
    	boolean flag = true;
    	while (q.size() > 1) {
    		TreeNode node = q.poll();
    		if (node != last) {
    			if (flag) {
    				level.addLast(node.val);
    			} else {
    				level.addFirst(node.val);
    			}
    			if (node.left != null) {
    				q.offer(node.left);
    			}
    			if (node.right != null) {
    				q.offer(node.right);
    			}
    		} else {
    			ans.add(new ArrayList<Integer>(level));
    			flag = !flag;
    			level.clear();
    			q.offer(last);
    		}
    	}
    	ans.add(level);
    	return ans;
    }
    //设置哨兵，和flag标记奇偶层
    //ans.add(new ArrayList<Integer>(level))
    
    /**99. Recover Binary Search Tree
     * @param root
     * 修复成二叉查找树
     */
    public TreeNode pre, node1, node2;
    public void recoverTree(TreeNode root) {
        /*inorder(root, root, true);*/
    	if (node1 != null && node2 != null) {
    		int temp = node1.val;
    		node1.val = node2.val;
    		node2.val = temp;
    	}
    }
    public void inorder(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	if (root.left != null) {
    		inorder(root.left);
    	}
    	if (pre != null && pre.val > root.val) {
    		if (node1 == null) {
    			node1 = pre;
    			node2 = root;
    		} else {
    			node2 = root;
    		}
    	}
    	pre = root;
    	if (root.right != null) {
    		inorder(root.right);
    	}
    }
/*    public void inorder (TreeNode node, TreeNode pre, boolean left) {
    	if (node == null) {
    		return;
    	}
    	inorder(node.left, node, true);
    	if (left && node.val > pre.val || !left && pre.val > node.val) {
    		int temp = node.val;
    		node.val = pre.val;
    		pre.val = temp;
    	}
    	inorder(node.right, node, false);
    }
*///忽略左右子节点互换的case，pre节点应该为中序遍历的前一个节点

    /**100. Same Tree
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null & q == null) {
			return true;
		}
		if (p != null && q != null && p.val == q.val) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
        return false;
    }
    
    /** 101. Symmetric Tree 
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
        	return true;
        }
        List<Integer> left = levelorder(root.left, true);
        List<Integer> right = levelorder(root.right, true);
        if (left.size() != right.size()) {
        	return false;
        }
        for (int i=0; i < left.size(); i++) {
        	if (left.get(i) != right.get(i)) {
        		return false;
        	}
        }
        return true;
    }
    public List<Integer> levelorder(TreeNode root, boolean left) {
    	List<Integer> list = new ArrayList<Integer>();
    	LinkedList<TreeNode> q = new LinkedList<TreeNode>();
    	if (root != null) {
    		q.offer(root);
    	}    	
    	while (!q.isEmpty()) {
    		TreeNode node = q.poll();
    		if (left) {
    			if (node.left != null) {
        			q.offer(node.left);
        		}
        		if (node.right != null) {
        			q.offer(node.right);
        		}
    		} else {
        		if (node.right != null) {
        			q.offer(node.right);
        		}
    			if (node.left != null) {
        			q.offer(node.left);
        		}
    		}
    	}
    	return list;
    }
    //使用层次遍历判断不可以，每层只有一个无法检测
    
    public boolean isSymmetric1(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> leftStack = new Stack<TreeNode>();
        Stack<TreeNode> rightStack = new Stack<TreeNode>();
        leftStack.push(root.left);
        rightStack.push(root.right);
        while (leftStack.size() > 0 && rightStack.size() > 0){
            TreeNode left = leftStack.pop();
            TreeNode right = rightStack.pop();
            if(left == null && right == null) 
            	continue;
            if(left == null || right == null) 
            	return false;
            if(left.val == right.val){
                leftStack.push(left.right);
                leftStack.push(left.left);
                rightStack.push(right.left);
                rightStack.push(right.right);
            }else return false;
        }
        return true;
    }
    //转
    public boolean isSymmetric2(TreeNode root) { 
    	if (root == null) {
    		return true;
    	}
    	return isTwoSymmetric (root.left, root.right);
    }
    public boolean isTwoSymmetric (TreeNode left, TreeNode right) {
    	if (left == null && right == null) {
    		return true;
    	}
    	if (left != null && right != null) {
    		if (left.val == right.val) {
    			return isTwoSymmetric (left.left, right.right) && isTwoSymmetric (left.right, right.left);
    		}
    	}
    	return false;
    }
    //递归，确定终止条件，以及每次递归的内容
    
    /**110. Balanced Binary Tree
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return balanceHeight(root) >= 0;   
    }
    public int balanceHeight (TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int left = balanceHeight (root.left);
    	int right = balanceHeight (root.right);
    	if (left < 0 || right < 0 || Math.abs(left-right) > 1) {
    		return -1;
    	}
    	return Math.max(left, right)+1;
    }
    //递归计算每个节点是否平衡
    
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
        	return true;
        }
        if (root.left == null && root.right == null) {
        	return true;
        }
        if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
        	return false;
        }
        return isBalanced1(root.left) && isBalanced1(root.right);
    }
    public int depth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	return 1+Math.max(depth(root.left), depth(root.right));
    }
    //递归，分别判断左右子树是否为平衡二叉树
    
    
    /**114. Flatten Binary Tree to Linked List 
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
        	return;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;
        root = pre;
        while (!s.isEmpty()) {
        	TreeNode node = s.pop();  	
        	if (node.right != null) {
        		s.push(node.right);
        	}
        	if (node.left != null) {
        		s.push(node.left);
        	}
        	if (pre != null) {
        		pre.left = null;
        		pre.right = node;
        	}
        	pre = node;
        }
    }
    //利用栈实现前序遍历，注意 左子树=null
    
    public void flatten1(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	if (root.left != null) {
    		TreeNode left = root.left;
    		TreeNode right = root.right;
    		root.left = null;
    		root.right = left;
    		TreeNode p = left;
    		while(p.right != null) {//找到左孩子的最右边节点,将right挂在最有节点上
    			p = p.right;
    		}
    		p.right = right;
    	}
    	flatten1(root.right);
    }
    //递归，每次处理左节点，递归右节点
    
    /** 116. Populating Next Right Pointers in Each Node
     * @param root、
     * 完全二叉树
     */
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        TreeLinkNode last = new TreeLinkNode(-1);
        if (root == null) {
        	return;
        }
        q.offer(root);
        q.offer(last);
        TreeLinkNode node1 = q.poll();
        while (q.size() >= 1) {
        	TreeLinkNode node2 = q.poll();
        	if (node1 != last) {
        		if (node2 != last) {
        			node1.next = node2;
        		} else {
        			node1.next = null;
        		}
        		
        		if (node1.left != null) {
        			q.offer(node1.left);
        		}
        		if (node1.right != null) {
        			q.offer(node1.right);
        		}
        	} else {
        		q.offer(last);
        	}
        	node1 = node2;
        	if (q.size() == 1 && node1 == last) {
        		break;
        	}
        } 
    }
    //尴尬。。beat 7.11%，利用层序遍历来做
    
    public void connect1(TreeLinkNode root) { 
    	if (root == null || root.left == null) {
    		return;
    	}
    	while (root != null) {
    		if (root.left == null) {
    			return;
    		}
    		TreeLinkNode p = root;
    		while (p != null) {
    			p.left.next = p.right;
    			if (p.next != null) {
    				p.right.next = p.next.left;
    			}
    			p = p.next;
    		}
    		root = root.left;
    	}
    }
    //转，beat 75.73%。找规律，利用上层来串联下层
    
    /**117. Populating Next Right Pointers in Each Node II 
     * @param root
     * 二叉树
     */
    public void connect2(TreeLinkNode root) {
    	if (root == null || root.left== null && root.right == null) {
    		return;
    	}
    	while (root != null) {
    		if (root.left == null && root.right == null) {
    			return;
    		}
    		TreeLinkNode p = root;
    		while (p != null) {
    			if (p.left != null) {
    				//找到右边的节点
    				TreeLinkNode next = null;
    				if (p.right != null) {
    					next = p.right;
    				} else {
    					TreeLinkNode pnext = p.next;
    					while (pnext != null) {
        					if (pnext.left != null)  {
        						next = pnext.left;
        						break;
        					} else if (pnext.right != null) {
        						next = pnext.right;
        						break;
        					}
        					pnext = pnext.next;
        				}
    				}
        			p.left.next = next;
        		}
    			if (p.right != null) {
    				TreeLinkNode next = null;
    				TreeLinkNode pnext = p.next;
					while (pnext != null) {
    					if (pnext.left != null)  {
    						next = pnext.left;
    						break;
    					} else if (pnext.right != null) {
    						next = pnext.right;
    						break;
    					}
    					pnext = pnext.next;
    				}
    				TreeLinkNode right = p.right;
    				p.right.next = next;
    			}
    			p = p.next;
    		}
    		if (root.left != null) {
    			root = root.left;
    		} else {
    			root = root.right;
    		}
    	}
    }
    
    public void connect3 (TreeLinkNode root) {
    	TreeLinkNode levelHead = root, nextLevelHead = null;
        while (levelHead != null) {
            TreeLinkNode node = levelHead, tail = null;
            while (node != null) {
                if (node.left != null && node.right != null) {
                    node.left.next = node.right;
                }
                TreeLinkNode sub;
                if (node.left != null)
                    sub = node.left;
                else if (node.right != null)
                    sub = node.right;
                else
                    sub = null;
                if (sub != null) {
                    if (nextLevelHead == null) {
                        nextLevelHead = sub;
                        tail = sub;
                    } else {
                        tail.next = sub;
                    }
                    while (tail.next != null)
                        tail = tail.next;
                }
                node = node.next;
            }
            levelHead = nextLevelHead;
            nextLevelHead = null;
        }
    }
    //http://www.acmerblog.com/leetcode-solution-populating-next-right-pointers-in-each-node-ii-6256.html
    
    
    /**105. Construct Binary Tree from Preorder and Inorder Traversal 
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree0(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    public TreeNode build(int[] preorder, int[] inorder, int ps, int pe, int is, int ie) {
    	if (ps > pe) {
    		return null;
    	}
    	int pivot = preorder[ps];
    	int i = is;
    	for (; i < ie; i++) {
    		if (pivot == inorder[i]) {
    			break;
    		}
    	}
    	TreeNode node = new TreeNode (pivot);
    	int lenleft = i - is;
    	node.left = build (preorder, inorder, ps+1, ps+lenleft, is, i-1);
    	node.right = build (preorder, inorder, ps+lenleft+1, pe, i+1, ie);
    	return node;
    }
    //前序+中序生成，计算用lenleft
    
    /**106. Construct Binary Tree from Inorder and Postorder Traversal 
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build2(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }
    
    public TreeNode build2 (int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
    	if (ps > pe) {
    		return null;
    	}
    	int pivot = postorder[pe];
    	int i = is;
    	for (; i <= ie; i++) {//	=!!!
    		if (pivot == inorder[i]) {
    			break;
    		}
    	}
    	TreeNode node = new TreeNode(pivot);
    	int lenright = ie - i;
    	node.left = build2(inorder, postorder, is, i-1, ps, pe-lenright-1);
    	node.right = build2(inorder, postorder, i+1, ie, pe-lenright, pe-1);
    	return node;
    }
    //中序+后序生成，计算lenright
    
    /** 96. Unique Binary Search Trees 
     * @param n
     * @return 二叉搜索树 
     */
    public int numTrees(int n) {
    	if (n == 0) {
    		return 0;
    	}
        if (n == 1) {
        	return 1;
        }
        if (n == 2) {
        	return 2;
        }
        int[] record = new int[n+1];
        record[0] = 1;
        record[1] = 1;
        record[2] = 2;
        for (int i = 3; i <= n; i++) {
        	int tmp = 0;
        	for (int k = 0; k < i; k++) {
        		tmp += record[k] * record[i-k-1];//递推公式
        	}
        	record[i] = tmp;
        }
        return record[n];
    }

    /**95. Unique Binary Search Trees II 
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return tree(1, n);
    }
    public List<TreeNode> tree(int start, int n) {
    	List<TreeNode> list = new ArrayList<TreeNode>();
        if (start > n) {
        	list.add(null);
        	return list;
        }
        for(int i=start; i<=n; i++) {
        	List<TreeNode> leftlist = tree(start, i-1);
        	List<TreeNode> rightlist = tree(i+1, n);
        	for (TreeNode left: leftlist) {
        		for (TreeNode right: rightlist) {
        			TreeNode root = new TreeNode (i);
        			root.left = left;
        			root.right = right;
        			list.add(root);
        		}
        	}
        }
        return list;
    }
    //当根节点为i时的个数，等于：左子树的个数 * 右子树的个数
    //						  (1，i-1)     (i+1, n)
    
    
    /**98. Validate Binary Search Tree
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
    	if (root == null || (root != null && root.left == null && root.right == null)) {
    		return true;
    	}
        List<Integer> list = new ArrayList<Integer>();
        inorderBST(root, list);
        for (int i=1; i<list.size(); i++) {
        	if (list.get(i-1) >= list.get(i)) {
        		return false;
        	}
        }
        return  true;
    }
    public void inorderBST(TreeNode root, List<Integer> list){
    	if (root != null) {
    		if (root.left != null) {
    			inorderBST(root.left, list);
    		}
    		list.add(root.val);
    		if (root.right != null) {
    			inorderBST(root.right, list);
    		}
    	}
    }
    //中序遍历到列表后，判断是否有序
    
    public boolean isValidBST1(TreeNode root) {
    	return valid1(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean valid1(TreeNode root, int left, int right) {
    	if (root == null) {
    		return true;
    	}
    	return root.val > left && root.val < right 
    			&& valid1(root.left, left, root.val) && valid1 (root.right, root.val, right);
    }
    //直接递归判断root.val是否符合要求,zhuan
    //思路是对的，但是运用integer.min+max,没过测试用例
    /**X*/
    public boolean isValidBST2(TreeNode root) {
    	if (root == null || (root != null && root.left == null && root.right == null)) {
    		return true;
    	}
    	return valid2(root, root.left, root.right);
    }
    public boolean valid2(TreeNode root, TreeNode left, TreeNode right) {
    	if (root == null) {
    		return true;
    	} else if (left != null && right != null) {
    		return root.val > left.val && root.val < right.val && valid2(left, left.left, left.right) && valid2(right, right.left, right.right);
    	} else if (left != null) {
    		return root.val > left.val && root.val < right.val && valid2(left, left.left, left.right);
    	} else if (right != null) {
    		return root.val > left.val && root.val < right.val && valid2(right, right.left, right.right);
    	} else {
    		return true;
    	}
    }
    //左子树的所有节点都要比根节点小，而非只是其左孩子比其小,
    /**X*/
    
    TreeNode preBST = null;  
    public boolean isValidBST3(TreeNode root) {  
        // Traverse the tree in inorder.  
        if (root != null) {    
            if (!isValidBST(root.left)) return false;  
            if (preBST != null && root.val <= preBST.val) return false;   
            preBST = root; 
            return isValidBST(root.right);  
        }   
        return true;  
     } 
     //中序遍历,compare
    
    /**
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        if (len == 0) {
        	return null;
        } 
        return creatBST(nums, (len-1)/2, 0, len-1);
    }
    public TreeNode creatBST(int[] nums, int p, int s, int e) {
    	if (p < s || p > e || s > e) {
    		return null;
    	}
    	TreeNode node = new TreeNode(nums[p]);
    	node.left = creatBST(nums, (p+s)/2, s, p-1);
    	node.right = creatBST(nums, (p+e+1)/2, p+1, e);//计算好中间点
    	return node;
    }
    
    /** 109. Convert Sorted List to Binary Search Tree
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
        	return null;
        } else if (head.next == null) {
        	return new TreeNode(head.val);
        }
        return creatBST1(head, null);
        
    }
    public TreeNode creatBST1(ListNode s, ListNode e) {
    	ListNode pre = findMiddle(s, e);
    	if (pre == null && s != null) {
    		return new TreeNode (s.val);
    	}
    	if (pre != null && pre.next != null) {
    		ListNode p = pre.next;
    		TreeNode node = new TreeNode (p.val);
    		pre.next = null;//截断
    		node.left = creatBST1(s, null);
    		node.right = creatBST1(p.next, e);
    		return node;
    	}
    	return null;
    }
    public ListNode findMiddle(ListNode s, ListNode e) {
    	ListNode fast = s;
    	ListNode slow = s;
    	ListNode slowpre = null;
    	while (fast != e && fast.next != e) {
    		fast = fast.next.next;
    		slowpre = slow;
    		slow = slow.next;
    	}
    	return slowpre;
    }
    //同上题思路，采用fast和slow两个指针找出中间值,注意截断
    
    /**111. Minimum Depth of Binary Tree
     * @param root
     * @return 最小深度
     */
    public int minDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	} else if (root.left == null && root.right == null) {
    		return 1;
    	} else if (root.left == null){
    		return minDepth(root.right)+1;
    	} else if (root.right == null) {
    		return minDepth(root.left)+1;
    	} else {
    		return Math.min(minDepth(root.left)+1, minDepth(root.right)+1);
    	}
    }

    /** 104. Maximum Depth of Binary Tree
     * @param root
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
			return 0;
		} else {
			int l = maxDepth(root.left);
			int r = maxDepth(root.right);
			return l > r ? l+1 : r+1;
		}
    }
    
    /**112. Path Sum
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
        	return false;
        } else if (root.val == sum && root.left == null && root.right == null) {
        	return true;
        }
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
    //判断是否是叶子节点
    
    public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null) 
            return false;
        
        if(root.left == null && root.right == null)
            return (root.val == sum);
            
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
    
    /**
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	List<Integer>path = new ArrayList<Integer>();
    	if (root == null) {
    		return list;
    	} 	
    	creatpath(root, sum, list, path);
    	return list;
    }
    public void creatpath(TreeNode root, int sum, List<List<Integer>> list, List<Integer> path) {
    	if (root == null) {
    		return;
    	}
    	path.add(root.val);
    	if (root.val == sum && root.left == null && root.right == null) {
    		list.add(path);
    	}
    	List<Integer> path1 = new ArrayList<Integer>();
    	path1.addAll(path);
    	creatpath(root.left, sum-root.val, list, path1);
    	
    	List<Integer> path2 = new ArrayList<Integer>();
    	path2.addAll(path);
    	creatpath(root.right, sum-root.val, list, path2);
    }
    //加入val，新建两个list分别递归左右子树
    
    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList<Integer>();
        pathSumRecur(root,sum,lists,list);
        return lists;
    }
    public void pathSumRecur(TreeNode root, int sum,List<List<Integer>> lists, List<Integer> list) {
        if(root == null) return;
        list.add(root.val);
        if(root.left == null && root.right == null && root.val == sum){
        	List<Integer> newList = new ArrayList<Integer>(list);//不可以直接add
        	lists.add(newList);
        }
        pathSumRecur(root.left,sum-root.val,lists,list);
        pathSumRecur(root.right,sum-root.val,lists,list);
        list.remove(list.size()-1);//移除
    }
    //不能直接将path存到result中，否则后面remove也会将其删掉
    
    /**437. Path Sum III
     * @param root
     * @param sum
     * @return
     */
    public int pathSum3(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return dfs(root, sum)+pathSum3(root.left, sum)+pathSum3(root.right, sum);
    }
    private int dfs(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res+=dfs(root.left,sum - root.val);
        res+=dfs(root.right,sum - root.val);
        return res;
    }
    //树的深度优先搜索
    //将每个节点看作新树进行搜索
    
    /**124. Binary Tree Maximum Path Sum 
     * @param root
     * @return
     */
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
    	return dfs1(root);
    }
    public int dfs1(TreeNode root) {
    	if (root == null) {
    		return 0;
    	} else {
    		int sum = root.val;
    		int left = dfs1(root.left);
    		int right = dfs1(root.right);
    		if (left > 0)
    			sum += left;
    		if (right > 0) 
    			sum += right;
    		max = Math.max(sum, max);
    		if (Math.max(left, right) > 0) {
    			return root.val + Math.max(left, right);
    		} else {
    			return root.val;
    		}
    	}	
    }
    //当前加左，当前加右，当前自己，当前加左、右子树的return值 
/**    
    private int max = Integer.MIN_VALUE;  
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    public int helper(TreeNode root) {
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        //连接父节点的最大路径是一、二、四这三种情况的最大值
        int currSum = Math.max(Math.max(left + root.val, right + root.val), root.val);
        //当前节点的最大路径是一、二、三、四这四种情况的最大值
        int currMax = Math.max(currSum, left + right + root.val);
        //用当前最大来更新全局最大
        max = Math.max(currMax, max);
        return currSum;
    }
*/
        
    /**129. Sum Root to Leaf Numbers
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfs2(root, 0);
    }
    public int dfs2(TreeNode root, int sum) {
    	if (root == null) {
    		return sum;
    	}
    	int s = sum*10 + root.val;
    	if (root.left != null && root.right != null) {
    		return dfs2(root.left, s) + dfs(root.right, s);
    	} else if (root.left != null) {
    		return dfs2(root.left, s);
    	} else {
    		return dfs2(root.right, s);
    	}	
    }
    //yeah!~
    
    
}
