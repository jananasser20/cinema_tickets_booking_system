package javaapplication14;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Booking implements Serializable {

    private transient Scanner s = new Scanner("E:\\java\\oop project\\booking.txt");

    private String user_name;
    private String recep_name;
    private String type;
    private String movieName;
    private int bookingNo;
    private int rating;
    private double price;
    private int time;
    // private int hall_id; //hall class org
    //    private int seatNo;
    private HallType halltype;
    private Halls hall;
    static int TotalBookings = 0;
    public static ArrayList<Booking> allbookings = new ArrayList<>();

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setRecep_name(String recep_name) {
        this.recep_name = recep_name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setBookingNo(int bookingNo) {
        this.bookingNo = bookingNo;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setHalltype(HallType halltype) {
        this.halltype = halltype;
    }

    ////org
//    public void setHall_id(int hall_id) {
//        this.hall_id = hall_id;
//    }
//    public void setSeatNo(int seatNo) {
//        this.seatNo = seatNo;
//    }
    public String getUser_name() {
        return user_name;
    }

    public String getRecep_name() {
        return recep_name;
    }

    public String getType() {
        return type;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getBookingNo() {
        return bookingNo;
    }

    public int getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getTime() {
        return time;
    }

//    public int getHall_id() {  //org
//        return hall_id;
//    }
//
//    public int getSeatNo() {  //org
//        return seatNo;
//    }
    public HallType getHalltype() {
        return halltype;
    }

    public static int getTotalBookings() {
        return TotalBookings;
    }

    //Receptionist recep= new Receptionist();   // banady 3ala bcreate booking bta3t el receptionist
    //org
//    public Booking(String user_name, String recep_name, String type, String MovieName, int price, int time) {
//        this.user_name = user_name;
//        this.recep_name = recep_name;
//        this.type = type;
//        this.movieName = MovieName;
//        this.price = price;
//        this.time = time;
//        rating = -1;
//        TotalBookings++;
//        //this.hall_id = hall_id;
//    }
    public Booking(String user_name, String recep_name, String MovieName, int time, String Halltype, double price) {
        this.user_name = user_name;
        this.recep_name = recep_name;
        this.movieName = MovieName;
        this.time = time;
        rating = -1;
        TotalBookings++;
        this.halltype = HallType.valueOf(Halltype.toUpperCase());
        this.hall = hall;
        bookingNo = TotalBookings + 1;
        Booking.allbookings.add(this);
    }

    public Booking() {
        TotalBookings++;
    }

    //org
//    public void view() { //bt3t kol guest   //jana
//        System.out.println("Guest username: " + user_name);
//        System.out.println("Receptionist's Name: " + recep_name);
//        System.out.println("Type: " + type);
//        System.out.println("Name: " + movieName);
//        System.out.println("Price: " + price);
//        System.out.println("Time: " + time);
//         System.out.println(hall_id);
//    }
    public String view() {
        StringBuilder result = new StringBuilder();
        result.append("Guest username: ").append(user_name).append("\n");
        result.append("Receptionist's Name: ").append(recep_name).append("\n");
        result.append("Type: ").append(type).append("\n");
        result.append("Name: ").append(movieName).append("\n");
        result.append("Price: ").append(price).append("\n");
        result.append("Time: ").append(time).append("\n");
        result.append("\n");
        return result.toString();
    }

    //org
//    static public void viewAll() { //kol al booking al at3mlt   //jana
//        System.out.println("Viewing bookings: ");
//
//        for (Booking s : allbookings) {
//            System.out.println(s.bookingNo);
//            System.out.println(s.movieName);
//            System.out.println(s.time);
//        }
//    }
    static public String viewAll() { //kol al booking al at3mlt   //jana
        StringBuilder result = new StringBuilder();
        result.append("Viewing bookings: \n");

        for (Booking s : allbookings) {
            result.append("Booking No: ").append(s.bookingNo).append("\n");
            result.append("Movie Name: ").append(s.movieName).append("\n");
            result.append("Time: ").append(s.time).append("\n\n");
        }

        return result.toString();
    }

   

    public void viewReports() {
        File file = new File("E:\\java\\oop project\\booking.txt");

        // Scanner s = new Scanner(file);
        while (s.hasNextLine()) {
            String read = s.nextLine();
            System.out.println(read);
        }
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
