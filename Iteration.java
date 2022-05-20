/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asraf
 */
public class Iteration {
    
    public static TreeNode<ConfessionPost> getSet1(){
        ConfessionPost post0 = new ConfessionPost("UM00","test0");
        ConfessionPost post1 = new ConfessionPost("UM01","test1");
        ConfessionPost post2 = new ConfessionPost("UM02","test2");
        ConfessionPost post3 = new ConfessionPost("UM03","test3");
        
        TreeNode<ConfessionPost> root = new TreeNode<>(post0);
        {
        TreeNode<ConfessionPost> node0 = root.addChild(post1);
        {
        TreeNode<ConfessionPost> node1 = node0.addChild(post2);
        TreeNode<ConfessionPost> node2 = node0.addChild(post3);
        }
        }
        return root;
    }
        
    public static void main(String[] args) {
        TreeNode<ConfessionPost> treeRoot = getSet1();
        for (TreeNode<ConfessionPost> node : treeRoot){
            String indent = createIndent(node.getLevel());
            System.out.println(indent + node.data);
        }
    }
    
    private static String createIndent(int depth){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++){
            sb.append(' ');
        }
        return sb.toString();
    }
}
