/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author asraf
 */
public class RegisterAccount {
    DoublyLinkedList<Account> AccountList = read();
    public static void main(String[] args) {
        
    }
    
    
    public void registerMenu(){
        Scanner in = new Scanner(System.in);
        System.out.println("register");
        System.out.print("User name: ");
        String username = in.next();
        System.out.print("Password: ");
        String password = in.next();
        register(username,password);
    }
    
    public void register(String username, String password){
        Account acc = new Account(username,password);
        Account accountInfo = (Account)AccountList.returnCurrentPost();
        Boolean userDup = false;
        while(accountInfo != null){
            if(accountInfo.getUserName().equals(username)){
                System.out.println("Username already existed. Try again!");
                userDup = true;
            }
            AccountList.nextPost();
            accountInfo = AccountList.returnCurrentPost();
        }
        if(!userDup){
            append(acc);
        }
    }
    
    public void append(Account acc){
        try{
        // Creates a FileWriter
        FileWriter fileName = new FileWriter("AccountList.txt",true);
        // Creates a BufferedWriter
        BufferedWriter  bw = new BufferedWriter(fileName);
                bw.append("\n"+acc.toString());
        bw.close();
    }catch(Exception e){
        System.out.print("An error occured try again ");
    }
    }
    
    public String searchID(String userID){
        if(AccountList != null){
        Account accountInfo = (Account)AccountList.returnCurrentPost();
        while(accountInfo != null){
            if(accountInfo.getUserID().equals(userID)){
                return userID;
            }
            AccountList.nextPost();
            accountInfo = AccountList.returnCurrentPost();
        }
        }return null;
    }
    
    public DoublyLinkedList read(){
        String userID = null;
        String userName = null;
        String password = null;
        DoublyLinkedList<Account> List = new DoublyLinkedList<>();
        try {
        File fileName = new File("AccountList.txt");
        Scanner Reader = new Scanner(fileName);
        while (Reader.hasNextLine()) {
        String data = Reader.nextLine();
        if(data.startsWith("userID=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 7, "");
            userID = buf.toString();
        }else if(data.startsWith("userName=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 9, "");
            userName = buf.toString();
        }else if(data.startsWith("password=")){
            StringBuilder buf = new StringBuilder(data);
            buf.replace(0, 9, "");
            password = buf.toString();
        }
        if(userID!=null && userName!=null && password!=null){
            Account acc = new Account(userID,userName,password);
            
            List.add(acc);
            userID = null;
            userName = null;
            password = null;
        }
      }
      Reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
        return List;
    }
    
    
}
