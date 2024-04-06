package javaapplication14;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.NullPointerException;
import java.util.Scanner;
public class Guest extends User implements Serializable{
private transient Scanner s = new Scanner(System.in);

    private int booking_counter = 0;
    private int revenue_counter = 0;
    Booking booking;
    public static ArrayList<Guest> guestss = new ArrayList<>();
    public ArrayList<Booking> bookings = new ArrayList<>();

    public Guest() {
    }
    
    
    
    public Guest(String Gusername, String GPass) {
        super(Gusername, GPass);
        User.users.add(this);
        guestss.add(this);
    }


    static boolean isClient(String name){
         
        for(Guest s: guestss){
            if(s.getUsername().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    public static Guest getGuest(String name){
        
        Guest g = null;
        if(!isClient(name)){
            System.out.println("Not found");
            return null;
        }
        else{
            for(Guest s:guestss){
            if(s.getUsername().equalsIgnoreCase(name))
                g = s;
        }
        }
        return g;
    }
    public void view() {
        try {
            System.out.println("Viewing " + getUsername() + "'s booking details: ");
            booking.view();
        }catch(NullPointerException e){
            System.out.println("No bookings to view");
        }
    }
    public Guest findGuestByUsername(String username) {
        for (Object userObject : User.users) {
            if (userObject instanceof Guest) {
                Guest guest = (Guest) userObject;
                if (username.equals(guest.getUsername())) {
                    return guest;
                }
            }
        }
        return null; // Guest not found
    }
    public void create_booking(String guestName, String MovieName, int time, String hallType, double price) {
        Guest client = null;
        client = Guest.getGuest(guestName);
        if (Guest.isClient(guestName)) {
            client.booking = new Booking(client.getUsername(), getUsername(), MovieName, time, hallType, price);//be yaccess al booking bt3 kol guest
            // guest.booking.setHall_id(hall.getHallID()); //bhot al id bt3 al hall
            Booking.allbookings.add(client.booking); // h add fe al array list bt3t al booking
            client.bookings.add(client.booking);
            client.incBookingCounter();
            booking_counter++; //bt_track kol r 3aml kam booking
        } else {
            // hena ba2a el code law howa guest msh 3amel log in
        }
        //System.out.println("Booking created, your total is: " + (price * hall.getHallType().getPriceMultiplier()));
        //hall.setSeats(hall.getSeats()+1);  //no gui
        //guest.booking.setSeatNo(hall.getSeats()+1);  //no gui
        Movies movie = new Movies();
        movie.book();
    }
    public void viewAll() {
        System.out.println("Viewing " + getUsername() + "'s bookings: ");
        for (Booking s : bookings) {
            System.out.println(s.getBookingNo());
            System.out.println(s.getMovieName());
            System.out.println(s.getTime());
        }
    }

    public void Gview_movies(){
     view_allmovies();
    }
    
    
    public int getBooking_counter() {
        return booking_counter;
    }

    public int getRevenue_counter() {
        return revenue_counter;
    }

    public void incBookingCounter() {
        booking_counter++;
    }

    public void setBooking_counter(int booking_counter) {
        this.booking_counter = booking_counter;
    }

    public void setRevenue_counter(int revenue_counter) {
        this.revenue_counter = revenue_counter;
    }

    void rateBooking() {
        System.out.println("Please enter your rating on a range from 1-5");
        int rating = s.nextInt();
        while (rating < 0 && rating > 5) {
            System.out.println("sorry, your rating is out of range, can you please re enter it from 1-5");
            rating = s.nextInt();
        }
        System.out.println("your rating has been saved succefully");
        booking.setRating(rating);

    }
    
   
   
   static void viewallguests(){
       for(Guest s: guestss){
           System.out.println(s.getUsername());
       }
   }
}