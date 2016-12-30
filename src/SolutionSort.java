import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import DataStructure.ListNode;


public class SolutionSort {

    /**88. Merge Sorted Array
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
    	for (int i = m-1, j = n-1, k = m+n-1; k >= 0; k--) {
    		if (i >= 0 && j >= 0) {
    			if (nums1[i] >= nums2[j]) {
        			nums1[k] = nums1[i];
        			i--;
        		} else {
        			nums1[k] = nums2[j];
        			j--;
        		}
    		} else if (i >= 0) {
    			nums1[k] = nums1[i];
    			i--;
    		} else if ( j >= 0) {
    			nums1[k] = nums2[j];
    			j--;
    		}
    	}
    }
    //从后往前比较，直接定位
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int index = m+n-1;
        while(i >= 0 && j >= 0){
            if(nums1[i] > nums2[j])
                nums1[index--] = nums1[i--];
            else
                nums1[index--] = nums2[j--];
            
        }
        while(i >= 0) nums1[index--] = nums1[i--];
        while(j >= 0) nums1[index--] = nums2[j--];
    }
    
    /**21. Merge Two Sorted Lists 
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(-1);
        ListNode retHead = ret;
        if (l1 == null && l2 == null) {
        	return null;
        }else if (l1 == null) {
        	return l2;
        } else if (l2 == null) {
        	return l1;
        }
        while (l1 != null && l2 != null) {
        	if (l1.val <= l2.val) {
        		ret.next = l1;
        		l1 = l1.next;
        		ret = ret.next;
        	} else {
        		ret.next = l2;
        		l2 = l2.next;
        		ret = ret.next;
        	}
        }
        if (l1 != null) {
        	ret.next = l1;
        }
        if (l2 != null) {
        	ret.next = l2;
        }
        return retHead.next;
    }
   
    /**23. Merge k Sorted Lists
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int listLen = lists.length;
        if (listLen == 0) {
            return null;
        }
        ListNode p = lists[0];
        for (int i = 1; i < listLen; i++) {
        	p = mergeTwoLists(p, lists[i]);
        }
        return p;
    }
    //time limit
    public ListNode mergeKLists1(ListNode[] lists) {  
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode> (10,new Comparator<ListNode>(){  
                @Override  
                public int compare(ListNode n1, ListNode n2)  
                {  
                    return n1.val-n2.val;  
                }  
        });  
        for(int i=0;i<lists.length;i++)  
        {  
            ListNode node = lists[i];   
            if(node!=null)  
            {  
                heap.offer(node);  
            }  
        }  
        ListNode head = null;  
        ListNode pre = head;  
        while(heap.size()>0)  
        {  
            ListNode cur = heap.poll();  
            if(head == null)  
            {  
                head = cur;  
                pre = head;  
            }  
            else  
            {  
                pre.next = cur;  
            }  
            pre = cur;  
            if(cur.next!=null)  
                heap.offer(cur.next);  
        }  
        return head;  
    } 
  /**堆
    维护一个大小为k的堆，每次取堆顶的最小元素放到结果中，然后读取该元素的下一个元素放入堆中，重新维护好。
    因为每个链表是有序的，每次又是去当前k个元素中最小的，所以当所有链表都读完时结束，这个时候所有元素按从小到大放在结果链表中。
    这个算法每个元素要读取一次，即是k*n次，然后每次读取元素要把新元素插入堆中要logk的复杂度，
    所以总时间复杂度是O(nklogk)。空间复杂度是堆的大小，即为O(k)。*/
    
    /**147. Insertion Sort List
     * @param head
     * @return 插入排序
     */
    public ListNode insertionSortList0(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode ret = new ListNode (-1);
    	ret.next = head;
    	ListNode pre = head;
    	ListNode cur = head.next;
    	while (cur != null) {
    		ListNode p = ret.next;
    		ListNode pnext = p.next;
    		if (cur.val < p.val) {//头
    			pre.next = cur.next;
    			ret.next = cur;
    			cur.next = p;
    		} else {
    			while (cur.val >= p.val && cur != p) {
    				if (cur.val < pnext.val) {
    					pre.next =  cur.next;
        				cur.next = pnext;
        				p.next = cur;
        				break;
    				} else {
    					p = pnext;
    					pnext = pnext.next;
    				}
    			}
    		}
    		pre = cur;
    		cur = cur.next;
    	}
    	return ret.next;
    }
    //time exceed
    
    public ListNode insertionSortList(ListNode head) {
    	if (head == null) {
    		return head;
    	}
    	ListNode minHead = new ListNode (Integer.MIN_VALUE);
    	ListNode last = head;
    	while (last != null) {
    		ListNode nextNode = last.next;
    		ListNode insertNode = minHead;
    		while (insertNode.next != null) {
    			if (last.val < insertNode.next.val) {
    				break;
    			}
    			insertNode = insertNode.next;
    		}
    		last.next = insertNode.next;
    		insertNode.next = last;
    		last = nextNode;
    	}
    	return minHead.next;
    }
    
    /**148. Sort List 
     * @param head
     * @return 归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode pslow = null;
        while (fast != null && fast.next != null) {
        	fast = fast.next.next;
        	pslow = slow;
        	slow = slow.next;
        }
        ListNode right = slow;
        pslow.next = null;
        ListNode left = head;
        left = sortList (left);
        right = sortList (right);
        return mergeList (left, right);
    }
    public ListNode mergeList (ListNode left, ListNode right) {
    	if (left == null) {
    		return right;
    	} 
    	if (right == null) {
    		return left;
    	}
    	ListNode head = null;
    	ListNode p = null;
    	while (left != null && right != null) {
    		if (left.val <= right.val) {
    			if (p == null) {
    				p = left;
    				head = left;
    			} else {
    				p.next = left;
    				p = p.next;
    			}
    			left = left.next;
    		} else {
    			if (p == null) {
    				p = right;
    				head = right;
    			} else {
    				p.next = right;
    				p = p.next;
    			}
    			right = right.next;
    		}
    	}
    	if (left != null) {
    		p.next = left;
    	}
    	if (right != null) {
    		p.next = right;
    	}
    	return head;
    }
    //能够有O(n lgn)时间复杂度的算法为，快速排序，堆排序，归并排序
    
    /**75. Sort Colors
     * @param nums
     */
    public void sortColors0(int[] nums) {
        int red = -1, white = -1, blue = -1;
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == 0) {
        		red++;
        	} else if (nums[i] == 1) {
        		white++;
        	} else if (nums[i] == 2) {
        		blue++;
        	}
        }
        for (int i = 0; i < nums.length; i++) {
            if (red >= 0) {
                nums[i] = 0;
                red--;
            } else if (white >= 0) {
                nums[i] = 1;
                white--;
            } else if (blue >= 0) {
                nums[i] = 2;
                blue--;
            }
        }
    }
    
    public void sortColors(int[] nums) {
    	int red = 0;
    	int blue = nums.length-1;
    	for (int i = 0; i < blue+1; ) {//循环条件i<blue+1
    		if (nums[i] == 0) {
    			int temp = nums[red];
    			nums[red] = nums[i];
    			nums[i] = temp;
    			++red;
    			++i;
    		} else if (nums[i] == 2) {
    			int temp = nums[blue];
    			nums[blue] = nums[i];
    			nums[i] = temp;
    			--blue;//注意i不变，交换后继续处理nums[i]
    			
    		} else {
    			++i;
    		}
    	}
    }
    //java传值无法改变值内容
    
    
    /**41. First Missing Positive
     * @param nums
     * @return 第一个正数
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        if (len <= 0) {
        	return 1;
        }
        int i = 0;
        while (i < len) {
        	if (nums[i] > 0 && nums[i] != i+1 && nums[i]-1 < len && nums[i] != nums[nums[i]-1]) {
        		int temp = nums[i];
        		nums[i] = nums[nums[i]-1];
        		nums[nums[i]-1] = temp;
        	} else {
        		++i;
        	}
        }
        for (i = 0; i < len; i++) {
        	if (nums[i] != i+1) {
        		return i+1;
        	}
        }
        return len+1;
    }
    //[1, 2, 3, -1, 0, 1]， 桶排序
    
    public int firstMissingPositive1(int[] nums) {
    	Arrays.sort(nums);
    	int len = nums.length;
    	boolean positive = false;
    	int count = 1;
    	for (int i = 0; i < len; i++) {
    		if (nums[i] > 0) {
    			positive = true;
    		}
    		if (i > 0 && nums[i] == nums[i-1]) {
    			continue;
    		}
    		if (positive && nums[i] != count) {
    			return count;
    		} else if (positive && nums[i] == count) {
    			++count;
    		}
    	}
    	return count;
    }
    //continue的作用[0, 2, 2, 1, 1]
    //用Arrays.sort竟然也过了
    
    public int firstMissingPositive2(int[] nums) {
    	int len = nums.length;
    	for (int i = 0; i < len; i++) {
    		while (nums[i] != i+1) {
    			if (nums[i] <= 0 || nums[i] > len || nums[i] == nums[nums[i]-1]) 
    				break;
    			int temp = nums[i];
        		nums[i] = nums[nums[i]-1];
        		nums[nums[i]-1] = temp;
    		}
    	}
    	 for (int i = 0; i < len; i++) {
         	if (nums[i] != i+1) {
         		return i+1;
         	}
         }
         return len+1;
    }
}
