import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import DataStructure.ListNode;
import DataStructure.RandomListNode;

/**链表
 * @author：JJL
 * @date: 2016年10月23日
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
    //if-else判断
    //因为循环多一个节点，返回时往后一个
    
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
    //小贱人，哼
    
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
    //小贱人，花费我好长时间
    //1.思路上，指定两个指针分别指向两个链表，大的放到l后，小的放到s后
    //2.细节上，l.next = null,去截断后面的链接
    //3.遍历：不需要进行元素的交换，可以通过创建两个新的头结点指针，来分别指向小于x的结点和大于等于x的结点，遍历结束之后，再将两个新的链表重新连接起来
    
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
    //经过上道题，这题easy，不过注意边界head=null的情况
    
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
    //利用三个指针
    
    /**61. Rotate List
     * @param head
     * @param k
     * @return 成环后右移k位
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
    //计算真个链表长度，之后成环，计算后退的个数
    //把环断开！
    
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
    //真不敢相信这么一道easy题我竟然一直没有ac，怀疑智商
    //一直在i和n的取值上有错，注意两个指针相隔的节点
    //注意一个元素时特殊处理
    
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
    //san，忘记ret.next = head了
    
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
    //目前为止比较快ac的一道hard
    //注意开头return条件，k=1,head=null,head.next=null
    //往后数k个
    //反思：可以分为两个函数，一个负责数k个，一个负责逆序
    
    /**138. Copy List with Random Pointer
     * @param head
     * @return 深度复制一个链表，使用重新分配内存保存新的
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
    /*HashMap的key存原始pointer，value存新的pointer。
	第一遍，先不copy random的值，只copy数值建立好新的链表。并把新旧pointer存在HashMap中。
	第二遍，遍历旧表，复制random的值，因为第一遍已经把链表复制好了并且也存在HashMap里了，
	所以只需从HashMap中，把当前旧的node.random作为key值，得到新的value的值，并把其赋给新node.random就好。
     * */
    public RandomListNode copyRandomList1(RandomListNode head) {  
        
        if (head == null)  
            return head;  
        /*第一步:在OldList的每个节点后面都插入一个copyNode(拷贝链表的结点)*/  
        RandomListNode nowNode = head;  
        while (nowNode != null){  
            RandomListNode copyNode = new RandomListNode(nowNode.label);  
            copyNode.random = nowNode.random;  
            copyNode.next = nowNode.next;  
            nowNode.next = copyNode;  
            nowNode = nowNode.next.next;  
        }  
          
        /*第二步:确定NewList的每个节点,真正关联到的Random结点是哪个， 
         *      因为第一步已经把所有NewList上的结点都建立了*/  
        nowNode = head;  
        while (nowNode != null){  
            if (nowNode.random != null){  
                nowNode.next.random = nowNode.random.next;  
            }  
            nowNode = nowNode.next.next;  
        }  
          
        /*第三步：还原OldList的next为一开始的next结点 
         *      并拼接NewList的next到它真正所应该关联的next结点 
         *      即：保持老链表OldList不变，拼接新链表NewList！ 
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
    //使用快慢指针，fast走两步，slow走一步
    //注意循环条件
    
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
    //让我们画个圈来解释这个问题，fast走的是a+b+c+b,
    //						   slow走的是a+b, 所以a=c，
    //相遇后一个从起点，一个从相遇点同时走，再次相遇，刚好走了a
    
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
    //两倍指针的使用
    //先找到后一半，然后逆序，最后把前后合并到一起
    //计算好slow和fast的位置，slow个数>fast个数
    
    /**146. LRU Cache
     * @author：JJL
     * @date: 2016年10月27日
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
     * @return 插入排序
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
    		ListNode pre = p.next;//p的后一个节点
    		while (ret != null) {
    			if (rpre == rhead && p.val <= ret.val) {//头
    				p.next = ret;
    				rpre.next = p;
    				break;
    			} else if (ret.next == null && p.val > ret.val) {//尾
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
    //妈个叽，leetcode开始为什么说我time exceed
    
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
}
