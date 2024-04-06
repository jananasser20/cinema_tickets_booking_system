package javaapplication14;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public abstract class User implements Serializable { 

    private String username;
    private String pass;

    public User() {
    }
    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    
    public static boolean login(String username, String password) {
        for (Object userObject : users) {
            if (userObject instanceof User) {
                User user = (User) userObject;
                if (user.getUsername().equalsIgnoreCase(username) && user.getPass().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    //static ArrayList users = new ArrayList<User>();
    public static ArrayList<User> users = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList users) {
        this.users = users;
    }
    
    public void view_allmovies(){
       for(int i=0;i<Movies.getMovies().size();i++){
           System.out.println(i);
       }
    }

    static boolean isUser(String name){
        for(User s: users){
            if(s.username.equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    public static void writeToFile() {
        try {
            File f = new File("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\users.dat");
            FileOutputStream s = new FileOutputStream(f);
            ObjectOutputStream o = new ObjectOutputStream(s);
            for (User g : User.users) {
                o.writeObject(g);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}