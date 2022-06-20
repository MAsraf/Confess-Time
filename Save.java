/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author asraf
 */
public class Save {
    
    public static void main(String[] args) {
    }
    
    public static void write(Load data){
    try{
        // Creates a FileWriter
        FileWriter fileName = new FileWriter("Data.txt");

        // Creates a BufferedWriter
        BufferedWriter  bw = new BufferedWriter(fileName);
        for (ReplyNode<ConfessionPost> node : data.tree){
                bw.write(node.data.toString());
                if(node.parent != null){
                    bw.write("\nParent ID="+node.parent.data.getID()+"\n");
                }else{
                    bw.write("\nParent ID=None\n");
                }
            }
        bw.close();
    }catch(Exception e){
        System.out.print("An error occured try again ");
    }
    }
}
