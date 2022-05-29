/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author asraf
 */
public class Data {
    
        ConfessionPost post;
        ReplyNode<ConfessionPost> tree = getTree();                             //Sample data using tree data structure
        
        public Data(){
            
        }
        
        /**
         * Inserts new Confession Post to the data.
         * This method add a child to the designated node.
         * @param postID    the Confession Post ID that need to be find in the data   
         * @param newPost   new Confession Post created
         */
        public void insertData(String postID, ConfessionPost newPost){   
        ReplyNode<ConfessionPost> node = searchData(postID);
            if(node != null){
            node.addChild(newPost);
        }
        }
        
        /**
         * Search for Confession Post ID that matches the user input.
         * This method will search for Confession Post that matches the post ID entered by the user.
         * @param input     Confession Post ID that entered by the user
         * @return          a node if found, else return null
         */
        public ReplyNode<ConfessionPost> searchData(String input){   
        for (ReplyNode<ConfessionPost> node : tree){
            if(node.data.toString().contains(input)){
                 return node;
            }
            }
        return null;
        }
        
        /**
         * Returns this tree data structure.
         * This method is just a sample data.
         * @return  a tree data structure with some sample data
         */
        public static ReplyNode<ConfessionPost> getTree(){
            ConfessionPost defaultPost = new ConfessionPost("UM00","Root");
            ConfessionPost post01 = new ConfessionPost("UM01","test1");
            ConfessionPost post02 = new ConfessionPost("UM02","replies-01");
            ConfessionPost post03 = new ConfessionPost("UM03","replies-01");
            ConfessionPost post04 = new ConfessionPost("UM04","replies-03");
            ReplyNode<ConfessionPost> tree = new ReplyNode<>(defaultPost);      //Create a tree structure with default value as the root.
            ReplyNode<ConfessionPost> newReplyPost;                             //Create an object use to assign new reply confession post.
            ReplyNode<ConfessionPost> newPublishedPost;                         //Create an object use to assign new published confession post.
            newPublishedPost = tree.addChild(post01);                           //Add a child object(new published confession post) to the root.
            newPublishedPost.addChild(post02);                                  //Add a child object(new reply confession post) to the first published confession post. 
            newReplyPost = newPublishedPost.addChild(post03);                   //Add a child object(new reply confession post) to the first published confession post. 
            newReplyPost.addChild(post04);                                      //Add a child object(new reply confession post) to the second reply confession post(post03). 
            
            ConfessionPost post05 = new ConfessionPost("UM05","test5");         
            ConfessionPost post06 = new ConfessionPost("UM06","replies-05");
            ConfessionPost post07 = new ConfessionPost("UM07","replies-05");
            ConfessionPost post08 = new ConfessionPost("UM08","replies-06");
            ConfessionPost post09 = new ConfessionPost("UM09","replies-08");
            newPublishedPost = tree.addChild(post05);                           //Add a child object(new published confession post) to the root.
            newReplyPost = newPublishedPost.addChild(post06);                   //Add a child object(new reply confession post) to the second published confession post.
            newPublishedPost.addChild(post07);                                  //Add a child object(new reply confession post) to the second published confession post.
            newReplyPost = newReplyPost.addChild(post08);                       //Add a child object(new reply confession post) to the first reply confession post(post06). 
            newReplyPost.addChild(post09);                                      //Add a child object(new reply confession post) to the reply confession post(post08). 
            return tree;
        }
        
}
