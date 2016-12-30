
public class SumofTwoIntegers371 {
	public static void main(String [] args) {
		Solution s=new Solution();
		//System.out.println(s.getSum(3, 2));
		//System.out.println(s.findTheDifference("abcd", "abcde"));
//		int [] nums = {0,1 ,2 , 0, 23};
//		int [] n = s.moveZeroes(nums);
//		for (int i = 0; i < n.length; i ++) {
//			System.out.println(n[i]);
//		}
		StringBuffer a = new StringBuffer("a");
		StringBuffer b = new StringBuffer("b");
		change (a,b);
		
		System.out.println(a);
		System.out.println(b);
		
	}
	public static void change(StringBuffer a, StringBuffer b) {
		a.append(b);
		b= a;
	}
	
}
