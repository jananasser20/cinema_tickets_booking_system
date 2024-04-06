package javaapplication14;

import java.io.Serializable;
import java.util.Scanner;

public class Receptionist extends User implements Serializable { 

    static int TotalReceptionists = 0;
    private int booking_counter = 0;
    private int revenue_counter = 0;
    static int seats_counter = 0;
    int price = 1;
    int r_price = 0;
    
    private transient Scanner s = new Scanner(System.in);
    
    public Receptionist() {
    }

    public Receptionist(String recep_username, String recep_pass) { // 3ashan add r
        setUsername(recep_username);
        setPass(recep_pass);
        User.users.add(this);
        TotalReceptionists++;
    }
    static boolean isRecep(String name) {
        for (Object userObject : users) {
            if (userObject instanceof Receptionist) {
                if (name.equalsIgnoreCase(((Receptionist) userObject).getUsername())) {
                    return true;
                }
            }

        }
        return false;
    }
//    public void create_booking(Guest guest, String category, String MovieName, int price, int time, Halls hall) {
//        guest.booking = new Booking(guest.getUsername(), getUsername(), category, MovieName, price, time);//be yaccess al booking bt3 kol guest
//        guest.booking.setHall_id(hall.getHallID()); //bhot al id bt3 al hall
//        Booking.allbookings.add(guest.booking); // h add fe al array list bt3t al booking
//        guest.bookings.add(guest.booking);
//        guest.incBookingCounter();
//        revenue_counter += price;
//        booking_counter++; //bt_track kol r 3aml kam booking
//        System.out.println("Booking created, your total is: " + (price * hall.getHallType().getPriceMultiplier()));
//        hall.setSeats(hall.getSeats()+1);  //no gui
//        guest.booking.setSeatNo(hall.getSeats()+1);  //no gui
//        Movies movie=new Movies();
//        movie.book();
//    }
    public void create_booking(String guestName, String MovieName, int time, String hallType, double price) {
        Guest client = null;
        client = Guest.getGuest(guestName);
        if (Guest.isClient(guestName)) {
            client.booking = new Booking(client.getUsername(), getUsername(), MovieName, time, hallType, price);//be yaccess al booking bt3 kol guest
            // guest.booking.setHall_id(hall.getHallID()); //bhot al id bt3 al hall
            Booking.allbookings.add(client.booking); // h add fe al array list bt3t al booking
            client.bookings.add(client.booking);
            client.incBookingCounter();
            revenue_counter += r_price;
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

    public void cancel_booking(Guest guest) {

        System.out.print("Please enter the number of the booking that you want to cancel: ");
        boolean flag = false;
        
        int bookingnum = s.nextInt();
        for (int i = 0; i < Booking.allbookings.size(); i++) {
            if (Booking.allbookings.get(i).getBookingNo() == bookingnum) { //bdwr 3la al booking fe al arraylist
                Booking.allbookings.remove(i);
                booking_counter--;
                revenue_counter -= guest.booking.getPrice();
                flag = true;
                break;
            }
        }
        if (flag == false) {
            System.out.println("there is no such booking already");
        }
    }

    public void Rview_movies() {

        view_allmovies();
    }

    public int getBooking_counter() {  //jana
        return booking_counter;
    }

    public int getRevenue_counter() {   //jana
        return revenue_counter;
    }
}
