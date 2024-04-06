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

/**
 *
 * @author
 */
import java.util.ArrayList;
public class Halls implements Serializable { 

    private int hallID;
    private int SeatCapacity = 50;
    private int seatsCounter;
    private int Capacity = 100;
    private int[] seatNos  = new int[12];
    boolean available = checkAvailability();
    ArrayList<Shows> shows = new ArrayList<>(); //kol hall lyha show mo3ym
    static ArrayList<Halls> Halls = new ArrayList<>(); //gwha kol al halls 
    HallType hallType;

    public Halls() {
        Capacity = 100;
        hallType = HallType.Standard;
    }

    public Halls(int hallID, HallType hallType) {
        this.seatsCounter = 0;
        this.hallID = hallID;
        this.hallType = hallType;
        Halls.add(this);
    }
    
    

    void viewOwnShows() { //b view kol al shows al btt3rd fe al hall deh
        for (Shows s : shows) {
            System.out.println("Movie Name: " + s.movie.getMovieName());
            System.out.println("Duration: " + s.getDuration());
            System.out.println("End Time: " + s.getEndTime());
            System.out.println("Show Id: " + s.getShowID());
            System.out.println("Hall Id: " + s.hall.hallID);
        }
    }

    public HallType getHallType() {
        return hallType;
    }
    public static Halls getHallById(int hallNumber) {
        for (Halls hall : Halls) {
            if (hall.getHallID() == hallNumber) {
                return hall;
            }
        }
        return null;
    }
    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public int getHallID() {
        return hallID;
    }

    public void setSeats(int[] arr){
        for(int i = 0; i<50;i++){
            if(arr[i]==1){
                seatNos[i]=1;
            }
        }
    }
    
    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    public int getCapacity() {
        return Capacity;
    }

    Boolean checkAvailability() {
        return seatsCounter < Capacity;
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