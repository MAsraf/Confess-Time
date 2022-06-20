/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author asraf
 */
public class Account {
    private String userID;
    private String userName;
    private String password;
    private static int ID=0;
    
    public Account(){
        userID = null;
        userName = null;
        password = null;
    }
    
    public Account(String userName, String password){
        this.userID = generateID();
        this.userName = userName;
        this.password = password;
    }

    public Account(String userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    
    /**
     * Creates a unique Confession Post ID.
     * This method generate an ID by checking the data class.
     * If a post ID already taken, it will increase counter by 1 until
     * it found an available post ID.
     * @return      the generated post ID of the new post
     */
    public String generateID(){
        userID = "UID0"+ID;
        ID++;
        if(!checkAvailableID(userID)){
            generateID();
        }
        return userID;
    }
    /**
     * Returns true if newly generated post ID is not found in the data class.
     * @param postID    newly generated post ID from generateID()
     * @return          true, if newly generated post ID is not found in the data class
     */
    public Boolean checkAvailableID(String userID){
        RegisterAccount data = new RegisterAccount();
        return data.searchID(userID)==null;
    }
    
    @Override
    public String toString() {
        return "userID=" + userID + "\nuserName="+userName + "\npassword=" + password;
    }
    
    
}
