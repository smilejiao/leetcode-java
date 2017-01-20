import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import DataStructure.UndirectedGraphNode;


/**ͼ
 * @author lenovo
 *
 */
public class Graph {

	
    /**133. Clone Graph
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if (node == null) {
    		return null;
    	}
    	UndirectedGraphNode ret = new UndirectedGraphNode(node.label);	
    	
    	HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    	Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
    	map.put(node, ret);
    	q.add(node);
    	
    	while (!q.isEmpty()) {
    		UndirectedGraphNode cur = q.poll();
    		for (UndirectedGraphNode n: cur.neighbors) {
    			if (!map.containsKey(n)) {//
    				q.add(n);
    				UndirectedGraphNode neighbor = new UndirectedGraphNode(n.label);
    				map.put(n, neighbor);//ԭʼ�ڵ�->��Ӧ�½ڵ�neighbor
    			}
    			map.get(cur).neighbors.add(map.get(n));
    		}
    	}
    	
        return ret;
    }
    //key��ԭʼֵ��value��copy��ֵ
    //ԭʼ�ڵ����ν�����У�������cur������
    //�����ھӡ����map��û�У�������У��½�node����map��������cur���ھ�
    
    
    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
    	if (node == null) {
    		return null;
    	}
    	UndirectedGraphNode ret = new UndirectedGraphNode(node.label);
    	HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    	
    	dfs(ret, node, map);
    	return ret;
    }
    public void dfs (UndirectedGraphNode ret, UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
    	map.put(node, ret);
    	for (UndirectedGraphNode n: node.neighbors) {
    		if (!map.containsKey(n)) {
    			UndirectedGraphNode neighbor = new UndirectedGraphNode(n.label);
    			dfs(neighbor, n, map);
    		}
    		ret.neighbors.add(map.get(n));
    	}
    }
    //beats 78.76%
    //��Ҫ����ѭ���и�ret���neighbor
    
    
    
}
