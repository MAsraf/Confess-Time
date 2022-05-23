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
        ConfessionPost post1 = new ConfessionPost("UM01","replies-00");
        ConfessionPost post2 = new ConfessionPost("UM02","replies-01");
        ConfessionPost post3 = new ConfessionPost("UM03","replies-01");
        
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
    
    public static TreeNode<ConfessionPost> getSet2(){
        ConfessionPost post0 = new ConfessionPost("UM04","test2");
        ConfessionPost post1 = new ConfessionPost("UM05","replies-04");
        ConfessionPost post2 = new ConfessionPost("UM06","replies-04");
        ConfessionPost post3 = new ConfessionPost("UM07","replies-06");
        
        TreeNode<ConfessionPost> root = new TreeNode<>(post0);
        {
        TreeNode<ConfessionPost> node0 = root.addChild(post1);
        TreeNode<ConfessionPost> node1 = root.addChild(post2);
        {
        TreeNode<ConfessionPost> node2 = node1.addChild(post3);
        }
        }
        return root;
    }
        
    public static void main(String[] args) {
//        TreeNode<ConfessionPost> treeRoot = getSet1();
//        for (TreeNode<ConfessionPost> node : treeRoot){
//            String indent = createIndent(node.getLevel());  //not needed
//            System.out.println(indent + node.data);
//        }
//        ConfessionPost post0 = new ConfessionPost("UM00","test0");
//        ConfessionPost post1 = new ConfessionPost("UM01","test1");
//        ConfessionPost post2 = new ConfessionPost("UM02","test2");
//        ConfessionPost post3 = new ConfessionPost("UM03","test3");
//        
//        PostNode<ConfessionPost> post = new PostNode<>();
//        post.add(post0);
//        System.out.println(post.getSize());
//        post.add(post1);
//        post.add(post2);
//        post.add(post3);
//        post.nextPost();
//        post.nextPost();
//        post.nextPost();
//        post.nextPost();

          PostNode<TreeNode> post = new PostNode<>();
          TreeNode<ConfessionPost> root1 = getSet1();
          post.add(root1);
          TreeNode<ConfessionPost> root2 = getSet2();
          post.add(root2);
//          showAllReplies(root1);
//          post.nextPost();
//          showAllReplies(root2);
          showOnlyRepliesTo(root2,"UM04");  
          
          
    }
    
    private static String createIndent(int depth){          //not needed
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++){
            sb.append(' ');
        }
        return sb.toString();
    }

    private static void showAllReplies(TreeNode<ConfessionPost> root){
        for (TreeNode<ConfessionPost> node : root){
            String indent = createIndent(node.getLevel());  //not needed
            System.out.println(indent + node.data);
            }
    }
    
    private static void showOnlyRepliesTo(TreeNode<ConfessionPost> root, String str){
        int level;
        int tlevel=-1;
        Boolean runOnce = false;    //To make sure only one level below the parent is called.
        for (TreeNode<ConfessionPost> node : root){
            String indent = createIndent(node.getLevel());  //not needed
            level = node.getLevel();
            if(node.data.toString().contains(str)|| level==tlevel){
            System.out.println(indent + node.data);
            if(runOnce==false){
             tlevel = node.getLevel()+1;
             runOnce = true;
            }
             if(level>tlevel){
                 break;
             }
            }
            }
    }
}
