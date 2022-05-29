package Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asraf
 */
public class Searching {
    public static ReplyNode<ConfessionPost> getSet2(){
        ConfessionPost post0 = new ConfessionPost("UM09","test9");
        ConfessionPost post1 = new ConfessionPost("UM10","replies-09");
        ConfessionPost post2 = new ConfessionPost("UM11","replies-09");
        ConfessionPost post3 = new ConfessionPost("UM12","replies-11");
        ConfessionPost post4 = new ConfessionPost("UM13","replies-12");
        ConfessionPost post5 = new ConfessionPost("UM14","replies-12");
        ConfessionPost post6 = new ConfessionPost("UM15","replies-12");
        ConfessionPost post7 = new ConfessionPost("UM16","replies-09");
        
        ReplyNode<ConfessionPost> root = new ReplyNode<>(post0);
            {
            ReplyNode<ConfessionPost> node0 = root.addChild(post1);
            ReplyNode<ConfessionPost> node1 = root.addChild(post2);
                {
                ReplyNode<ConfessionPost> node2 = node1.addChild(post3);
                    {
                    ReplyNode<ConfessionPost> node3 = node2.addChild(post4);  
                    ReplyNode<ConfessionPost> node4 = node2.addChild(post5);
                    ReplyNode<ConfessionPost> node5 = node2.addChild(post6);
                    }
                }
            ReplyNode<ConfessionPost> node6 = root.addChild(post7);    
            }
        return root;
    }
    public static void main(String[] args) {
		
		Comparable<ConfessionPost> searchCriteria = new Comparable<>() {
			@Override
			public int compareTo(ConfessionPost treeData) {
				if (treeData == null)
					return 1;
				boolean nodeOk = treeData.hasID("UM01",treeData);
				return nodeOk ? 0 : 1;
			}

		};

		ReplyNode<ConfessionPost> treeRoot = getSet2();
		ReplyNode<ConfessionPost> found = treeRoot.findTreeNode(searchCriteria);

		System.out.println("Found: " + found);
	}
}
