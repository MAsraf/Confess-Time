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
public class Main {
        static Login login = new Login();
        static RegisterAccount register = new RegisterAccount();
    public static void main(String[] args) {
        System.out.println("Login \"L\" / Register \"R\"");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        if(input.equalsIgnoreCase("R")){
            register.registerMenu();
        }else if(input.equalsIgnoreCase("L")){
            Boolean logIn = login.menu();
            while(logIn){
                System.out.println("1. submit");
                System.out.println("2. view");
                System.out.println("3. search");
                System.out.println("Q - quit");
                input = in.next();
                if(input.equalsIgnoreCase("1")){
                    Tester4.main(args);
                }else if(input.equalsIgnoreCase("2")){
                    Tester2.main(args);
                }else if(input.equalsIgnoreCase("3")){
                    Tester3.main(args);
                }else if(input.equalsIgnoreCase("Q")){
                    logIn = false;
                }
                
                
            }
        }
    }
}
