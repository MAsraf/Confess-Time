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
public class Tester2 {      //View Published Confession Posts
    static Data data = new Data();
    static DoublyLinkedList<ConfessionPost> list = assignList(data.tree);
    static GenericStack<DoublyLinkedList> stackList = new GenericStack<>();
    static GenericStack<String> stackCurrent = new GenericStack<>();
    public static void main(String[] args) {
        showPost(list);
    }
    
    public static void showPost(DoublyLinkedList<ConfessionPost> list){
        Scanner in = new Scanner(System.in);
        ConfessionPost tmp = list.returnCurrentPost();
        String key="";
        while(!key.equalsIgnoreCase("Q")){
            String id="";
            if(tmp!=null){
            System.out.println("===============================================================");
            System.out.println("#"+tmp.getID());
            System.out.println(tmp.getContent());
            System.out.println(tmp.getTime());
            id = tmp.getID();
            }else{
                System.out.println("no replies here");
            }
            getOption();
            key = in.next();
            if(key.equalsIgnoreCase("D")){  //view next post
                list.nextPost();
                if(list.returnCurrentPost()==null){
                    list.firstPost();
                }
                tmp = list.returnCurrentPost();
            }else if(key.equalsIgnoreCase("A")){  //view previous post
                list.previousPost();
                if(list.returnCurrentPost()==null){
                    list.lastPost();
                }
                tmp = list.returnCurrentPost();
            }else if(key.equalsIgnoreCase("W")){
                key="Q";
                DoublyLinkedList<ConfessionPost> replyList;
                replyList = stackList.pop();
                String current = stackCurrent.pop();
                replyList = sort(replyList,current);
                showPost(replyList);
            }else if(key.equalsIgnoreCase("S")){                                      //view posts that are replying to this post
                key="Q";
                stackList.push(list);
                stackCurrent.push(list.returnCurrentPost().getID());
                DoublyLinkedList<ConfessionPost> replyList = new DoublyLinkedList<>();
                searchBelowPost(replyList, id);
                showPost(replyList);
            }else if(key.equalsIgnoreCase("Q")){
                tmp = null;
            }
        }
    }
    
    public static DoublyLinkedList<ConfessionPost> sort(DoublyLinkedList<ConfessionPost> replyList, String current){
        DoublyLinkedList<ConfessionPost> newList = new DoublyLinkedList<>();
        GenericQueue<ConfessionPost> queue = new GenericQueue<>();
        replyList.firstPost();
        ConfessionPost tmp = replyList.returnCurrentPost();
        Boolean found = false;
        while(tmp!=null){
            if(tmp.getID().equals(current)||found){
                newList.add(tmp);
                found = true;
                replyList.nextPost();
                tmp = replyList.returnCurrentPost();
                continue;
            }
            queue.enqueue(tmp);
            replyList.nextPost();
            tmp = replyList.returnCurrentPost();
            replyList.removeFirst();
        }
        for(int i = 0; i<queue.getSize(); i++){
            newList.add(queue.dequeue());
        }
        return newList;
    }
    
    public static void getOption(){
        System.out.println("===============================================================");
        System.out.println(">> Options:");
        System.out.println(">> \"D\" - view next post");
        System.out.println(">> \"A\" - view previous post");
        System.out.println(">> \"W\" - view #UM03291");  //why want to view the post when the program is showing it???
        System.out.println(">> \"S\" - view posts that are replying to this post");
        System.out.println(">> \"Q\" - quit viewing post");
        System.out.println("---------------------------------------------------------------");
    }
    
    
    /*
    Press "W" to view posts that are replied by the current post.(Move up one level)
    */
    public static String searchAbovePost(DoublyLinkedList<ConfessionPost>replyList, String id){
        String str="";
        for (ReplyNode<ConfessionPost> node : data.tree){
            if(node.data.toString().contains(id)){
            replyList.add(node.parent.data);
            str =node.parent.data.getID();
            break;
            }
        }
        return str;
    }
    
    /*
    Press "S" to view posts that are replying to current post.(Move down one level)
    */
    public static void searchBelowPost(DoublyLinkedList<ConfessionPost> replyList, String id){
        int targetLevel = -1;                                                   //Value that help locate the children of a node and break the loop once iterate above the level.
        Boolean foundReply = false;   
        for (ReplyNode<ConfessionPost> node : data.tree){
            int level = node.getLevel();
            if(node.data.toString().contains(id)|| level==targetLevel){
            
            if(foundReply==false){
             targetLevel = node.getLevel()+1;
             foundReply = true;
             continue;                                                          //Skip all statements below this for the first matched post ID it found.
            }
            replyList.add(node.data);
            }
            if(level<targetLevel && foundReply){
                break;
            }
            }
    
    }
    
    /*
    To assign all Published Confession Post to a a Doubly Linked List.
    So that we can iterate forward and backward.
    */
    public static DoublyLinkedList<ConfessionPost> assignList(ReplyNode<ConfessionPost> tree){    
        DoublyLinkedList<ConfessionPost> postList = new DoublyLinkedList<>();
        for(ReplyNode<ConfessionPost> node : tree){
            int PostLevel = node.getLevel();                                    //assign each node their current level in the tree structure. 
            if(PostLevel == 1){                                                 //To view only the published post as they are at level 1.
                postList.add(node.data);
            }  
        }
        return postList;
    }
    
}
