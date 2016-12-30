import DataStructure.TreeNode;


public class isBalanced110 {
	int val;
	isBalanced110 left;
	isBalanced110 right;
	isBalanced110(int x) { val = x; }
	public static void main (String [] args) {
		TreeNode tree=new TreeNode(0);
		tree.left = new TreeNode(0);
		tree.right = new TreeNode(0);
		tree.left.left = new TreeNode(0);
		tree.left.right = new TreeNode(0);
		
		Solution s=new Solution();
		System.out.println(s.maxDepth(tree));
//		System.out.println(isBalanced(tree));
//		char[] array={'a','b','c'};
//		String s=String.valueOf(array);
	}
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
        
    }
	public static int depth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(depth(root.left), depth(root.right));
	}
}
