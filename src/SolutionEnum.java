import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SolutionEnum {

	
    /**78. Subsets
     * @param nums
     * @return 子集
     */
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	Arrays.sort(nums);
    	List<Integer> p = new ArrayList<Integer>();
    	generateSub(nums, list, p, 0);
    	return list;
    }
    public void generateSub(int[] nums, List<List<Integer>> list, List<Integer> p, int n) {
    	if (n == nums.length){
    		List<Integer> l = new ArrayList<Integer>(p);
    		list.add(l);
    		return;
    	}
    	generateSub(nums, list, p, n+1);
    	p.add(nums[n]);
    	generateSub(nums, list, p, n+1);
    	p.remove(p.size()-1);
    }
    //运用dfs的思想，注意remove
    
    public List<List<Integer>> subsets1(int[] S) {
		int all = 1 << S.length;
		Arrays.sort(S);
		List<List<Integer>> subsetsList = new ArrayList();
		for (int i = 0; i < all; i++) {
			int b = 1;
			List<Integer> tmpList = new ArrayList();
			for (int j = 0; j < S.length; j++) {
				if ((i & b) != 0) {
					tmpList.add(S[j]);
				}
				b <<= 1;
			}
			subsetsList.add(tmpList);
		}
		return subsetsList;
	}
    //转，二进制法，也可以看做是位向量法
    
    
    
    /**90. Subsets II
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	Arrays.sort(nums);// 输出要求有序
    	List<Integer> p = new ArrayList<Integer>();
    	generateSub2(nums, list, p, 0);
    	return list;
    }
    public void generateSub2(int[] nums, List<List<Integer>> list, List<Integer> p, int n) {
    	if (n == nums.length){
    		List<Integer> l = new ArrayList<Integer>(p);
    		if (!list.contains(l)) {
    			list.add(l);
    		}
    		return;
    	}
    	generateSub2(nums, list, p, n+1);
    	p.add(nums[n]);
    	generateSub2(nums, list, p, n+1);
    	p.remove(p.size()-1);
    }
    //list.add前判断去重
    
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        res.add(new ArrayList<Integer>());
        for(int i = 0; i<nums.length; i++){
            int size = res.size();
            for(int j = 0; j<size; j++){
                ArrayList<Integer> elem = new ArrayList<Integer>(res.get(j));
                elem.add(nums[i]);
                if(!res.contains(elem)){
                    res.add(elem);
                }
            }
        }
        return res;
    }
    //转
    
    /**46. Permutations
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	List<Integer> p = new ArrayList<Integer>();
        if (nums.length == 0) {
        	return list;
        }
        creatPermute(nums, list, p, 0);
        return list;
    }
    public void creatPermute(int[] nums, List<List<Integer>> list, List<Integer> p, int n) {
    	if (n == nums.length) {
    		List l = new ArrayList<Integer>(p);
    		list.add(l);
    		return;
    	} 
    	for (int i = 0; i < nums.length; i++) {
    		if (!p.contains(nums[i])) {
    			p.add(nums[i]);
    			creatPermute(nums, list, p, n+1);
    			p.remove(p.size() - 1);//注意！！
    		}
    	}
    }
    // beats 44.38%
    
    /**47. Permutations II
     * @param nums
     * @return 数组中的元素可能重复
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	List<Integer> p = new ArrayList<Integer>();
        if (nums.length == 0) {
        	return list;
        }
        Arrays.sort(nums);
        int[] visit = new int[nums.length];
        creatPermute1(nums, list, p, 0, visit);
        return list;
    }
    public void creatPermute1(int[] nums, List<List<Integer>> list, List<Integer> p, int n, int[] visit) {
    	if (n == nums.length) {
    		list.add(new ArrayList<Integer>(p));	
    		return;
    	} 
    	for (int i = 0; i < nums.length; i++) {
    		if (visit[i] == 1 || i != 0 && nums[i] == nums[i-1] && visit[i-1] == 0) {
    			continue;
    		}
    		visit[i] = 1;
    		p.add(nums[i]);
			creatPermute1(nums, list, p, n+1, visit);
			p.remove(p.size()-1);
			visit[i] = 0;
    	}
    }
    //转，排序，利用visit判断元素是否要添加
    
    
    /**77. Combinations 
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	List<Integer> p = new ArrayList<Integer>();
    	if (n == 0 || k == 0 || n < k) {
    		return list;
    	}
    	creatCombine(n, k, list, p);
    	return list;
    }
    public void creatCombine(int n, int k, List<List<Integer>> list, List<Integer> p){
    	if (p.size() == k) {
    		list.add(new ArrayList<Integer>(p));
    		return;
    	}
    	for (int i = 1; i <= n; i++) {
    		if (p.size() == 0 || p.size() >=1 && p.get(p.size()-1) < i) {//加入队列的条件找好，
    			p.add(i);
        		creatCombine(n, k, list, p);
        		p.remove(p.size()-1);
    		}
    	}
    }
    //哭 beats 7.85%
    
    public List<List<Integer>> combine1(int n, int k) {
        List<Integer> temp = new ArrayList<Integer>(k);
        List<List<Integer>> result = new ArrayList();
        combineRecur(n, 0, k, temp, result);
        return result;
    }

    public void combineRecur(int n,int s, int k,List<Integer> temp,List<List<Integer>> result){
        if(temp.size() == k ){
        	result.add(new ArrayList<Integer>(temp));
            return;
        }
        if(s + k - temp.size() > n) return;
        temp.add(s+1);
        combineRecur(n, s+1, k, temp,result);
        temp.remove(temp.size()-1);
        combineRecur(n, s+1, k, temp,result);
    }
    //转， beats 90.95%
    
    /**17. Letter Combinations of a Phone Number
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
    	List<String> list = new ArrayList<String>();
    	if (digits == null || digits.length() == 0)
    		return list;
    	HashMap<Character,String> map = new HashMap<Character,String>();
    	map.put('1', "");
    	map.put('2', "abc");
    	map.put('3', "def");
    	map.put('4', "ghi");
    	map.put('5',"jkl");
    	map.put('6',"mno");
    	map.put('7', "pqrs");
    	map.put('8', "tuv");
    	map.put('9', "wxyz");
    	map.put('0', "");
    	creatLetter(digits, map, list, "", 0);
    	return list;
    }
    public void creatLetter(String digits, HashMap<Character,String> map, List<String> list, String s, int n) {
    	if (n == digits.length()){
    		list.add(new String(s));
    		return;
    	}
    	String val = map.get(digits.charAt(n));
    	if (val.equals("")) {
    		creatLetter(digits, map, list, s, n+1);
    	}else {
    		for (int i = 0; i < val.length(); i++) {
    			s += String.valueOf(val.charAt(i));
    			creatLetter(digits, map, list, s, n+1);
    			s = s.substring(0, s.length()-1);
    		}
    	}	
    }
    //beats 18.57%
    //注意递归后要移除本次添加
    //substring（0, s.length）是保留整个字符串
    /**
    for (int i = 0; i < val.length(); i++) {
    	String str = s + String.valueOf(val.charAt(i));
    	creatLetter(digits, map, list, str, n+1);
    }
    beats 39.35%
     * */
}
