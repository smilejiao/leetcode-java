import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import DataStructure.ListNode;
import DataStructure.RandomListNode;

/**����
 * @author��JJL
 * @date: 2016��10��23��
 * @description:
 */
public class Solution2 {
	public Solution2 () {
		
	}
    /**92. Reverse Linked List II
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	int sum = 0;
    	ListNode l3 = new ListNode(0);
    	ListNode l = l3;
    	while (l1 != null || l2 != null || sum != 0) {
    		if (l1 != null && l2 != null) {
    			sum = l1.val + l2.val + sum;
    			l1 = l1.next;
    			l2 = l2.next;
    		} else if (l2 != null) {
    			sum = l2.val + sum;
    			l2 = l2.next;
    		} else if (l1 != null) {
    			sum = l1.val + sum;
    			l1 = l1.next;
    		}
    		int val;
    		if (sum > 9) {
    			val = sum % 10;
    			sum = sum/10;
    		} else {
    			val = sum;
    			sum = 0;
    		}
    		l3.next = new ListNode (val);
    		l3 = l3.next;
    	}
        return l.next;
    }
    //if-else�ж�
    //��Ϊѭ����һ���ڵ㣬����ʱ����һ��
    
    /**92. Reverse Linked List II
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	int i = 1;
    	ListNode ret = new ListNode(-1);
    	ListNode pre = ret;
    	ret.next = head;
    	if (m == n) {
    		return ret.next;
    	}
    	while (head != null) {
    		
    		if (i < m) {
    			pre = pre.next;  			
    		} else if (i == m) {
    			while (i < n) {
    				ListNode preNext = pre.next;
        			pre.next = head.next;
        			head.next = head.next.next;
        			pre.next.next = preNext;
        			i++;
        		}
    			return ret.next;
    		}
    		i += 1;
    		head = head.next;	
    	}
        return ret.next;
    }
    //С���ˣ���
    
    /**86. Partition List
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode l = new ListNode(-1);
    	ListNode s = new ListNode(-1);
    	ListNode lhead = l;
    	ListNode shead = s;
    	if (head == null) {
    	    return head;
    	}
    	while (head != null) {
    		if (head.val >= x) {
    			l.next = head;
    			l = l.next;
    		} else {
    			s.next = head;
    			s = s.next;
    		}
    		head = head.next;
    	}
    	s.next = lhead.next;
    	l.next = null;
        return shead.next;
    }
    //С���ˣ������Һó�ʱ��
    //1.˼·�ϣ�ָ������ָ��ֱ�ָ������������ķŵ�l��С�ķŵ�s��
    //2.ϸ���ϣ�l.next = null,ȥ�ضϺ��������
    //3.����������Ҫ����Ԫ�صĽ���������ͨ�����������µ�ͷ���ָ�룬���ֱ�ָ��С��x�Ľ��ʹ��ڵ���x�Ľ�㣬��������֮���ٽ������µ�����������������
    
    /**83. Remove Duplicates from Sorted List 
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode ret = head;
        ListNode rHead = ret;
        if (head == null) {
            return head;
        }
    	while (head != null) {
    		if (head.val != ret.val) {
    			ret.next = head;
    			ret = ret.next;
    		}
    		head = head.next;
    	}
    	ret.next = null;
    	return rHead;
    }
    //�����ϵ��⣬����easy������ע��߽�head=null�����
    
    /**82. Remove Duplicates from Sorted List II
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }
        ListNode r = new ListNode(-1);
        ListNode rHead = r;
        ListNode pre = null;
        ListNode next = head.next;
        while (head != null) {
        	boolean flag = false;
        	if (pre != null && head.val == pre.val) {
        		flag = true;
        	}
        	if (next != null && head.val == next.val) {
        		flag = true;
        	} 
        	if (flag == false) {
        		r.next = head;
        		r = r.next;
        	}
        	pre = head;
        	head = next;
        	if (next != null) {
        		next = next.next;
        	}	
        }
        r.next = null;
        return rHead.next;
    }
    //��������ָ��
    
    /**61. Rotate List
     * @param head
     * @param k
     * @return �ɻ�������kλ
     */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode ret = head;
        int len = 1;
        if (head == null || head.next == null) {
        	return head;
        }
        while (head.next != null) {
        	head = head.next;
        	len += 1;
        }
        head.next = ret;
        k = k%len;
        k = len - k + 1;
        while (k > 0) {
        	if (k == 1) {
        		ret = head.next;
        		head.next = null;
        	}
        	head = head.next;
        	k -= 1;
        }
        return ret;
    }
    //������������ȣ�֮��ɻ���������˵ĸ���
    //�ѻ��Ͽ���
    
    /**19. Remove Nth Node From End of List
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ret = new ListNode (-1);
        ret.next = head;
        ListNode pre = ret;
        int i = 0;
        if (head.next == null && n == 1) {
        	return head.next;
        }
        while (head != null) { 
        	i += 1;
        	if ( i >= n) {
        		pre = pre.next;
        	}
        	head = head.next;       	
        	
        	if (head !=null && head.next == null) {
        		pre.next = pre.next.next;    		
        	}
        }      
        return ret.next;
    }
    //�治��������ôһ��easy���Ҿ�Ȼһֱû��ac����������
    //һֱ��i��n��ȡֵ���д�ע������ָ������Ľڵ�
    //ע��һ��Ԫ��ʱ���⴦��
    
    /**24. Swap Nodes in Pairs 
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
    	ListNode ret = new ListNode(-1);
    	ret.next = head;
    	while(head != null){
    		int temp = head.val;
    		if (head.next != null) {
    			head.val = head.next.val;
    			head.next.val = temp;  
    			head = head.next.next;
    		} else {
    			head = head.next;
    		}   		
    	}
        return ret.next;
    }
    //san������ret.next = head��
    
    /**25. Reverse Nodes in k-Group
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
    	ListNode ret = new ListNode(-1);
    	ret.next = head;
    	ListNode pre = ret;
    	ListNode preNext;
    	if (k == 1 || head.next == null) {
    		return head;
    	}
    	int i = 1;
    	while (head != null) {
    		boolean flag = false;
    		ListNode temp = head;
    		while (temp != null) {
    			temp = temp.next;
    			i++;
    			if (i > k) {
    				flag = true;
    				break;
    			}
    		}
    		if (flag == true) {
    			i = 1;
    			while (i < k) {
        			preNext = pre.next;
        			pre.next = head.next;
        			head.next = head.next.next;
        			pre.next.next = preNext;
        			i++;
        		}
    		}
    		i = 1;
    		pre = head;
    		head = head.next;
    	}
        return ret.next;
    }
    //ĿǰΪֹ�ȽϿ�ac��һ��hard
    //ע�⿪ͷreturn������k=1,head=null,head.next=null
    //������k��
    //��˼�����Է�Ϊ����������һ��������k����һ����������
    
    /**138. Copy List with Random Pointer
     * @param head
     * @return ��ȸ���һ������ʹ�����·����ڴ汣���µ�
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
    	RandomListNode ret = new RandomListNode(head.label);
    	map.put(head, ret);
    	RandomListNode rhead = ret;
    	RandomListNode phead = head;
    	while (head != null) {
    		RandomListNode node = new RandomListNode(head.label);
    		map.put(head, node);
    		ret.next = node;
    		ret = ret.next;
    		head = head.next;
    	}
    	ret = rhead;
    	head = phead;
    	while(head != null) {
    		ret.random = map.get(head.random);
    		ret = ret.next;
    		head = head.next;
    	}
        return rhead;  
    }
    /*HashMap��key��ԭʼpointer��value���µ�pointer��
	��һ�飬�Ȳ�copy random��ֵ��ֻcopy��ֵ�������µ����������¾�pointer����HashMap�С�
	�ڶ��飬�����ɱ�����random��ֵ����Ϊ��һ���Ѿ��������ƺ��˲���Ҳ����HashMap���ˣ�
	����ֻ���HashMap�У��ѵ�ǰ�ɵ�node.random��Ϊkeyֵ���õ��µ�value��ֵ�������丳����node.random�ͺá�
     * */
    public RandomListNode copyRandomList1(RandomListNode head) {  
        
        if (head == null)  
            return head;  
        /*��һ��:��OldList��ÿ���ڵ���涼����һ��copyNode(��������Ľ��)*/  
        RandomListNode nowNode = head;  
        while (nowNode != null){  
            RandomListNode copyNode = new RandomListNode(nowNode.label);  
            copyNode.random = nowNode.random;  
            copyNode.next = nowNode.next;  
            nowNode.next = copyNode;  
            nowNode = nowNode.next.next;  
        }  
          
        /*�ڶ���:ȷ��NewList��ÿ���ڵ�,������������Random������ĸ��� 
         *      ��Ϊ��һ���Ѿ�������NewList�ϵĽ�㶼������*/  
        nowNode = head;  
        while (nowNode != null){  
            if (nowNode.random != null){  
                nowNode.next.random = nowNode.random.next;  
            }  
            nowNode = nowNode.next.next;  
        }  
          
        /*����������ԭOldList��nextΪһ��ʼ��next��� 
         *      ��ƴ��NewList��next����������Ӧ�ù�����next��� 
         *      ��������������OldList���䣬ƴ��������NewList�� 
         * */  
        RandomListNode pHead = new RandomListNode(0);  
        pHead.next = head;  
        RandomListNode newlist = pHead;  
          
        nowNode = head;  
        while (nowNode != null){  
            pHead.next = nowNode.next;  
            nowNode.next = pHead.next.next;  
            pHead = pHead.next;  
            nowNode = nowNode.next;  
        }  
        return newlist.next;  
    } 
    
    /**141. Linked List Cycle 
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
        	p1 = p1.next;
        	if (p2.next != null) {
        		p2 = p2.next.next;
        	}
        	if (p1 == p2) {
        		return true;
        	}
        }
        return false;
    }
    //ʹ�ÿ���ָ�룬fast��������slow��һ��
    //ע��ѭ������
    
    /**142. Linked List Cycle II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
        	fast = fast.next.next;
        	slow = slow.next;
        	if (fast == slow) {
        		slow = head;
        		while(fast != slow) {
        			fast = fast.next;
        			slow = slow.next;
        		}
        		return fast;
        	}
        }
        return null;
    }
    //�����ǻ���Ȧ������������⣬fast�ߵ���a+b+c+b,
    //						   slow�ߵ���a+b, ����a=c��
    //������һ������㣬һ����������ͬʱ�ߣ��ٴ��������պ�����a
    
    /**143. Reorder List 
     * @param head
     */
    public void reorderList(ListNode head) {
        if (!(head == null || head.next == null)) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
            	fast = fast.next.next;
            	slow = slow.next;
            }
            fast = slow.next;
            slow.next = null;
            if (fast != null && fast.next != null) {
                fast = reverse(fast);
            }
            slow = head;
            while (fast != null) {
            	ListNode tempS = slow.next;
            	ListNode tempF = fast.next;
            	slow.next = fast;
            	fast.next = tempS;
            	fast = tempF;
            	slow = tempS;
            }
        }
    }   
    public ListNode reverse(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode p = new ListNode (-1) ;
    	ListNode phead = p;
    	p.next = head;
    	while (head.next != null) {
    		ListNode temp = p.next;
    		p.next = head.next;
    		head.next = head.next.next;
    		p.next.next = temp;
    	}
    	return p.next;
    }
    //����ָ���ʹ��
    //���ҵ���һ�룬Ȼ����������ǰ��ϲ���һ��
    //�����slow��fast��λ�ã�slow����>fast����
    
    /**146. LRU Cache
     * @author��JJL
     * @date: 2016��10��27��
     * @description: http://blog.csdn.net/sbitswc/article/details/35899935
     */
    public class LRUCache {
        
        public LRUCache(int capacity) {
            
        }
        
        public int get(int key) {
            return 0;
        }
        
        public void set(int key, int value) {
            
        }
    }
    
    /**147. Insertion Sort List
     * @param head
     * @return ��������
     */
    public ListNode insertionSortList(ListNode head) {
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode p = head.next;
    	
    	ListNode rhead = new ListNode(-1);
    	ListNode ret = head;
    	rhead.next = ret;
    	ret.next = null;
    	
    	while (p != null) {
    		ret = rhead.next;
    		ListNode rpre = rhead;
    		ListNode pre = p.next;//p�ĺ�һ���ڵ�
    		while (ret != null) {
    			if (rpre == rhead && p.val <= ret.val) {//ͷ
    				p.next = ret;
    				rpre.next = p;
    				break;
    			} else if (ret.next == null && p.val > ret.val) {//β
    				ret.next = p;
    				p.next = null;
    				break;
    			} else if (p.val > rpre.val && p.val <= ret.val){
    				p.next = ret;
    				rpre.next = p;
    				break;
    			} else {
    				rpre = ret;
    				ret = ret.next;
    			}
    		}
    		p = pre;
    	}
        return rhead.next;
    }
    //���ߴ��leetcode��ʼΪʲô˵��time exceed
    
    /**148. Sort List 
     * @param head
     * @return �鲢����
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
    //�ܹ���O(n lgn)ʱ�临�Ӷȵ��㷨Ϊ���������򣬶����򣬹鲢����
}
