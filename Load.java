/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author asraf
 */
public class Load{
    static String postID = null;
    static String content = null;
    static String postTime = null;
    static String parentID = null;
    ReplyNode<ConfessionPost> tree = read();
    
    public static void main(String[] args) {
    }
    
    public ReplyNode<ConfessionPost> read(){
        try {
        File fileName = new File("Data.txt");
        Scanner Reader = new Scanner(fileName);
        while (Reader.hasNextLine()) {
        String data = Reader.nextLine();
        getDataOnly(data);
        if(postID!=null && content!=null && postTime!=null && parentID!=null){
            ConfessionPost post = new ConfessionPost(postID,content,postTime);
            if(postID.equals("UM00")){
                tree = new ReplyNode<>(post);
            }else{
                insertData(parentID, post);
            }
            postID = null;
            content = null;
            postTime = null;
            parentID = null;
        }
      }
      Reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
        return tree;
    }
    
    public static void getDataOnly(String data){
        if(data.startsWith("postID=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 7, "");
            postID = buf.toString();
        }else if(data.startsWith("content=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 8, "");
            content = buf.toString();
        }else if(data.startsWith("postTime=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 9, "");
            postTime = buf.toString();
        }else if(data.startsWith("Parent ID=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 10, "");
            parentID = buf.toString();
        }
    }
    
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
        
    public  GenericQueue<ReplyNode> searchDataByDate(String input){   
        GenericQueue<ReplyNode> queue = new GenericQueue<>();

        for (ReplyNode<ConfessionPost> node : tree){
            if(node.data.getTime().contains(input)){
                queue.enqueue(node);
            }
        }
        return queue;
    }
        
        public  ReplyNode searchDataByPostID(String input){  
            for (ReplyNode<ConfessionPost> node : tree){
            if(node.data.getID().contains(input)){
                return node;
            }
            }
        return null;
        }
        
        public  GenericQueue<ReplyNode> searchDataByKeyword(String input){   
            GenericQueue<ReplyNode> queue = new GenericQueue<>();

            for (ReplyNode<ConfessionPost> node : tree){
            if(node.data.getContent().contains(input)){
                queue.enqueue(node);
            }
            }
        return queue;
        }    
}
