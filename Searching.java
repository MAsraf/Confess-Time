/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asraf
 */
public class Searching {
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

		TreeNode<ConfessionPost> treeRoot = Iteration.getSet1();
		TreeNode<ConfessionPost> found = treeRoot.findTreeNode(searchCriteria);

		System.out.println("Found: " + found);
	}
}
