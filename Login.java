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
public class Login {
    public Boolean menu(){
        Scanner in = new Scanner(System.in);
        System.out.println("===============================================================");
        System.out.println("Login..");
        System.out.print("User name: ");
        String username = in.next();
        System.out.print("Password: ");
        String password = in.next();
        Boolean Authenticate = checkLogin(username,password);
        if(Authenticate){
            System.out.println("You're logged in");
            return true;
        }else{
            System.out.println("Username or password is wrong");
            return false;
        }
    }
    
    public Boolean checkLogin(String username, String password){
        RegisterAccount list = new RegisterAccount();
        Account current = list.AccountList.returnCurrentPost();
        while(current != null){
            if(current.getUserName().equals(username) && current.getPassword().equals(password)){
                return true;
            }
            list.AccountList.nextPost();
            current = list.AccountList.returnCurrentPost();
        }
        return false;
    }
}
