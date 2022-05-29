/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import java.util.Scanner;
/**
 *
 * @author asraf
 */
public class Tester {   //Submit Connfession Post
   static Data data = new Data();
    public static void main(String[] args) {
        intro();
    }
    
    public static void intro(){
        Scanner in = new Scanner(System.in);
        System.out.println("===============================================================");
        System.out.println(">> Please enter the confession poast ID you want to reply.");
        System.out.println(">> Leave it blank if you don't want to reply a confession post.");
        System.out.println("---------------------------------------------------------------");
        System.out.print("Reply confession post ID: ");
        String input = in.nextLine();
//        String str = "UM04";
        System.out.println("---------------------------------------------------------------");
        searchPublishedPost(data.tree,input);
        String newID = preEnterConfession(input);
        postDetail(data.tree, newID);
        showAllReplies(data.tree);
        
    
    }
    public static void searchPublishedPost(ReplyNode<ConfessionPost> tree, String input){
        Boolean postFound = false;
        for (ReplyNode<ConfessionPost> node : tree){
            if(node.data.toString().contains(input)){
            System.out.println(">> Confession post ID exists!");
            System.out.println("===============================================================");
                postFound = true;
            }
            }
        if(!postFound){
            System.out.println(">> Confession post ID doesn't exists!");
            System.out.println("===============================================================");
        }
    }
    
    public static String enterConfession(String input){
        Scanner in = new Scanner(System.in);
        ConfessionPost newPost = new ConfessionPost();
        System.out.println("===============================================================");
        System.out.println(">> Please enter your confession content.");
        System.out.println(">> Insert \"-1\" to submit your confession.");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Confession content:");
        String str="", content="";
        while(!str.equals("-1")){
            content += str;
            str = in.nextLine();
        }
        String newID = newPost.addPost(content);
        
        data.insertData(input,newPost);
        System.out.println("===============================================================");
        return newID;
    }
    
    public static String preEnterConfession(String input){
        String newID;
                newID = enterConfession(input);
            return newID;
    }
    
    public static void postDetail(ReplyNode<ConfessionPost> tree,String id){
        System.out.println("===============================================================");
        System.out.println(">> Submitted at " + data.searchData(id).data.getTime());
        System.out.println(">> Confession post ID " + data.searchData(id).data.getID());
        System.out.println(">> Your confession will be published soon.");
        System.out.println("===============================================================");
}
    
    public static void showAllReplies(ReplyNode<ConfessionPost> tree){
            for (ReplyNode<ConfessionPost> node : tree){
            System.out.println(node.data);
                System.out.println(node.getLevel());
            }
        }
        
}
