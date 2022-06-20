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
public class Tester3 {          //Search Published Confession Posts
    static Load data = new Load();
    public static void main(String[] args) {
        input();
    }
    
    public static void input(){
        Scanner in = new Scanner(System.in);
        System.out.println("===============================================================");
        System.out.println(">> Searching formats and options:");
        System.out.println(">> \"YYYY-MM-DD\" - search by date");
        System.out.println(">> \"YYYY-MM-DD HH:mm am/pm\" - search by date time");
        System.out.println(">> \"#UMXXXXX\" - search by post ID");
        System.out.println(">> <any> - search by keywords");
        System.out.println("---------------------------------------------------------------");
        System.out.print("> ");
        String key = in.next();
        System.out.println("===============================================================");
        formatCheck(key);
    }
    
    public static void formatCheck(String key){
        DateValidator date = new DateValidatorFormat("yyyy-MM-dd");
        if(date.isValid(key)){//search by date method
            searchDate(key);
        }else if(key.startsWith("#UM")){//search by post ID
            searchPostID(key);
        }else{//search by keyword
            searchKeyword(key);
        }   
    }
    
    public static void listingSearch(GenericQueue<ReplyNode> queue){
        DoublyLinkedList<String> pageList = new DoublyLinkedList<>();
        Scanner in = new Scanner(System.in);
        int index = 1;
        int totalCount = 1;
        String input = "";
        String page = "";
        while(!queue.isEmpty()){
            ConfessionPost postData = (ConfessionPost)queue.dequeue().data;
            String content = postData.getContent();
            content = truncateStr(content);
            page = page + index +"  #"+postData.getID()+" \""+content+"\"\n"; 
            if(totalCount % 5 == 0){
                pageList.add(page);
                page = "";
                totalCount =- 5;
            }else if(queue.isEmpty()){
                pageList.add(page);
            }
            index++;
            totalCount++;
            
        }
        String tmp = pageList.returnCurrentPost();
        int RSIndexPage = 1;
        int LSIndexPage = 0;
        int totalIndex = index-1;
        while(!input.equalsIgnoreCase("Q")){
            if(tmp!=null){
                System.out.print(tmp);
                if(RSIndexPage >= pageList.Size()){
                    System.out.print("["+((RSIndexPage-1)*5+1)+"-"+totalIndex+"]");
                }else{
                    System.out.print("["+(1*LSIndexPage+1)+"-"+5*RSIndexPage+" of "+ totalIndex+"]");
                }
                System.out.println("\n---------------------------------------------------------------");
            }
            System.out.println(">> Options:");
            System.out.println(">> \"D\" - view next page");
            System.out.println(">> \"A\" - view previous page");
            System.out.println(">> \"#UMXXXXX\" - view selected confession post");
            System.out.println(">> \"R\" - search again");
            System.out.println(">> \"Q\" - quit searching post");
            input = in.next();
            if(input.equalsIgnoreCase("D")){  //view next page
                pageList.nextPost();
                RSIndexPage++;
                LSIndexPage+=5;
                if(pageList.returnCurrentPost()==null){
                    pageList.firstPost();
                    RSIndexPage = 1;
                    LSIndexPage = 0;
                }
                tmp = pageList.returnCurrentPost();
                System.out.println("---------------------------------------------------------------");
            }else if(input.equalsIgnoreCase("A")){  //view previous page
                pageList.previousPost();
                RSIndexPage--;
                LSIndexPage-=5;
                if(pageList.returnCurrentPost()==null){
                    pageList.lastPost();
                    RSIndexPage = pageList.Size();
                }
                tmp = pageList.returnCurrentPost();
                System.out.println("---------------------------------------------------------------");
            }else if(input.startsWith("#UM")){
                StringBuilder buf = new StringBuilder(input);
                buf.replace(0, 1, "");
                ReplyNode node = data.searchDataByPostID(buf.toString());
                ConfessionPost post = (ConfessionPost) node.data;
                Tester2 view = new Tester2();
                DoublyLinkedList list = new DoublyLinkedList();
                list.add(post);
                view.showPost(list);
            }else if(input.equalsIgnoreCase("R")){
                input = "Q";
                input();
            }else if(input.equalsIgnoreCase("Q")){
                tmp = null;
            }
    }
    }
    
    public static void searchDate(String key){
        GenericQueue<ReplyNode> queue;
        queue = data.searchDataByDate(key);
        System.out.println("===============================================================");
        System.out.println(">> Search results by the date \""+key+"\".");
        listingSearch(queue);
    }
    
    public static void searchPostID(String key){
        StringBuilder buf = new StringBuilder(key);
        buf.replace(0, 1, "");
        ReplyNode node = data.searchDataByPostID(buf.toString());
        ConfessionPost post = (ConfessionPost) node.data;
        System.out.println("===============================================================");
        System.out.println(">> Search results by the post ID \""+key+"\".");
        Tester2 view = new Tester2();
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(post);
        view.showPost(list);
        
    }
    
    public static void searchKeyword(String key){
        GenericQueue<ReplyNode> queue;
        queue = data.searchDataByKeyword(key);
        System.out.println("===============================================================");
        System.out.println(">> Search results by the keyword \""+key+"\".");
        listingSearch(queue);
    }
    
    public static String truncateStr(String content){
        StringBuilder buf = new StringBuilder(content);
            if(content.length()>45){
                buf.replace(45, content.length(), "");
                buf.append("...");
            }
            return buf.toString();
    }
}
