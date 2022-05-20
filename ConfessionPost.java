/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Date;

/**
 *
 * @author asraf
 */
public class ConfessionPost {
    private String postID;
    private String content;
    private Date postTime;
    
    public ConfessionPost(){
        this.postID = "UM0";
        this.content = "Test";
        this.postTime = new Date();
    }

    public ConfessionPost(String postID, String content) {
        this.postID = postID;
        this.content = content;
        this.postTime = new Date();
    }

    @Override
    public String toString() {
        return "\npostID=" + postID + "\ncontent=" + content + "\npostTime=" + postTime;
    }

    boolean hasID(String id,ConfessionPost data) {
        return data.postID.equals(id);
    }
    
    
    
}
