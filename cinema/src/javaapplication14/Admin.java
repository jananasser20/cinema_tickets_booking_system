package javaapplication14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class Admin extends User implements Serializable { 

    // Users Part
    private transient Scanner s = new Scanner(System.in);
   // File file = new File("C:\\Users\\Meno\\Documents\\NetBeansProjects\\JavaApplication26\\src\\javaapplication26\\mena.txt");
    private transient Scanner f = new Scanner("E:\\mena.txt");
    public Admin() {
    }

    public Admin(String username, String pass) {
        super(username, pass);
        users.add(this);
    }
    
    
    

    void add(User type) {
        String newUsername = type.getUsername();
        if (users.contains(type.getUsername())) {
            System.out.println("Username already exists please enter another username");
        } else {
            users.add(type);
        }
    }

    static boolean isAdmin(String name) {
        for (Object userObject : users) {
            if (userObject instanceof Admin) {
                if (name.equalsIgnoreCase(((Admin) userObject).getUsername())) {
                    return true;
                }
            }

        }
        return false;
    }

//    static void getAdmin(String username){
//        for(User s: User.users){
//            if(s instanceof Admin){
//                if(s.)
//            }
//        }
//    }
    
    public void listUsers() {
        // Scanner s = new Scanner(System.in);
        System.out.println("To List \nAdmins press 1\nReceptionists press 2\nGuests press 3\nFor all users press 4");
        int choice = s.nextInt();

        System.out.println("Listing usernames: ");
        int i = 0;
        for (Object userIT : users) {
            i++;
            User user = (User) userIT;

            switch (choice) {
                case 1:
                    if (user instanceof Admin) {
                        System.out.println(i + "- " + user.getUsername());
                    }
                    break;
                case 2:
                    if (user instanceof Receptionist) {
                        System.out.println(i + "- " + user.getUsername());
                    }
                    break;
                case 3:
                    if (user instanceof Guest) {
                        System.out.println(i + "- " + user.getUsername());
                    }
                    break;
                case 4:
                    System.out.println(i + "- " + user.getUsername());
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    void edit(String userName) { //edits user by username
//            System.out.print("Enter the username of the user you would like to edit: ");
        //  Scanner s = new Scanner(System.in);
//            String userName = s.next();
        boolean userFound = false;
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user.getUsername().equals(userName)) {
                System.out.println("Press 1 to edit" + user.getUsername() + "'s username, 2 to edit password");
            }
            int check = s.nextInt();

            if (check == 1) {
                System.out.print("Enter the new username: ");
                String newUsername = s.next();
                user.setUsername(newUsername);
                System.out.println("Username updated!");
            } else if (check == 2) {
                System.out.print("Enter new password: ");
                String newPass = s.next();
                user.setPass(newPass);
                System.out.println("Password updated!");
            } else {
                System.out.println("Error:\nInvalid choice!");
            }

            userFound = true;
        }
        if (!userFound) {
            System.out.println("Username not found!");
        }
    }

    public void searchUser() {
        System.out.print("Enter the username to search: ");
        String searchUsername = s.next();

        boolean found = false;

        for (Object userIT : users) {
            User user = (User) userIT;

            if (user.getUsername().equals(searchUsername)) {
                System.out.println("User found:");
                System.out.println("Username: " + user.getUsername());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("User: " + searchUsername + " not found!");
        }
    }

    void remove(String userName) { // removes a user by it's username
//            System.out.print("Enter the username of the user you would like to edit: ");
        //  Scanner s = new Scanner(System.in);
//            String userName = s.next();
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user.getUsername().equals(userName)) {
                System.out.println("Are you sure you want to delete " + userName);
                System.out.print("Press 1 to delete, and 2 to return: ");

                int check = s.nextInt();

                if (check == 1) {
                    users.remove(userIT);
                    System.out.println("User deleted!");
                    return;
                } else if (check == 2) {
                    return;
                }
            }
        }
        System.out.println("Username not found!");
    }

    void maxTargetsReceps() {

        String MaxRevRecep = null;
        String MaxBookingRecep = null;
        int MaxBooking = 0;
        int MaxRev = 0;
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user instanceof Receptionist) {
                if (((Receptionist) user).getBooking_counter() > MaxBooking) {
                    MaxBooking = ((Receptionist) user).getBooking_counter();
                    MaxBookingRecep = ((Receptionist) user).getUsername();
                }
                if (((Receptionist) user).getBooking_counter() > MaxRev) {
                    MaxRev = ((Receptionist) user).getBooking_counter();
                    MaxRevRecep = ((Receptionist) user).getUsername();
                }
            }
        }
        System.out.println("Receptionist with max number of bookings: " + MaxBookingRecep);
        System.out.println("Receptionist with max number of Revenue: " + MaxRevRecep);
    }

    void maxTargetsGuests() {

        String MaxRevGuest = null;
        String MaxBookingGuest = null;
        int MaxBooking = 0;
        int MaxRev = 0;
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user instanceof Guest) {
                if (((Guest) user).getBooking_counter() > MaxBooking) {
                    MaxBooking = ((Guest) user).getBooking_counter();
                    MaxBookingGuest = ((Guest) user).getUsername();
                }
                if (((Guest) user).getBooking_counter() > MaxRev) {
                    MaxRev = ((Guest) user).getBooking_counter();
                    MaxRevGuest = ((Guest) user).getUsername();
                }
            }
        }
        System.out.println("Guest with max number of bookings: " + MaxBookingGuest);
        System.out.println("Guest with max number of Revenue: " + MaxRevGuest);
    }

    void view_receptionistBooking() {
        System.out.println("To view passwords encrypted press 1, to view it normally press any key: ");
        int check = s.nextInt();
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user instanceof Receptionist) {
                String encPass = ((Receptionist) user).getPass();
                if (check == 1) {
                    encPass = new String("");
                    for (int i = 0; i < ((Receptionist) user).getPass().length(); i++) {
                        encPass += ((Receptionist) user).getPass().charAt(i) + 54;
                    }
                }
                //if (recep_name.equals(user.username)) {
                System.out.println(((Receptionist) user).getUsername() + "'s Total Bookings: " + ((Receptionist) user).getBooking_counter());
                System.out.println("Receptionist username: " + ((Receptionist) user).getUsername());
                System.out.println("Receptionist Pass: " + encPass);
                // }
            }
        }
    }

    void guestsBookings() {
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user instanceof Guest) {
                System.out.println(user.getUsername() + "'s number of bookings: " + ((Guest) user).getBooking_counter());
            }
        }
    }

    void viewBookingDetails(Booking booking) {
        booking.view();
    }

    //Movies Part
    public void AddMovie(Object ms) {
        if (ms instanceof Movies) {
            Movies.getMovies().add((Movies) ms);
        } else if (Movies.getMovies().contains(ms)) {
            System.out.println("This movie is already exist");
        }
    }

    public void Remove(String name) {
        boolean found = false;
        System.out.println("Are you sure that you want to delete this movie?");
        System.out.println("Press 1 to delete");
        System.out.println("Press 2 to exit");
       // Scanner s = new Scanner(System.in);
        int check = s.nextInt();

        if (check == 1) {
            for (Movies i : Movies.getMovies()) {
                if (i.getMovieName().equals(name)) {
                    System.out.println("movie " + i.getMovieName() + "Deleted");
                    Movies.getMovies().remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Not found");
            }
        } else if (check == 2) {
            return;

        }
    }

    public void Edit(String name) {
        //Scanner s = new Scanner(System.in);
        boolean found = false;
        for (Movies i : Movies.getMovies()) {
            if (i.getMovieName().equals(name)) {
                System.out.println("What field do you want to edit?");
                System.out.println("Press 1 to edit the movie Name");
                System.out.println("Press 2 to edit the movie Price");
                System.out.println("Press 3 to edit the movie Catgory");
                int check = s.nextInt();
                String new_movie_name;
                int new_movie_price;
                String new_movie_catgory;
                if (check == 1) {
                    System.out.println("Please enter the new movie name");
                    new_movie_name = s.nextLine();
                    i.setMovieName(new_movie_name);
                } else if (check == 2) {
                    System.out.println("Please enter the new movie price");
                    new_movie_price = s.nextInt();
                    i.setMoviePrice(new_movie_price);
                } else if (check == 3) {
                    System.out.println("Please enter the new catgory you want to add");
                    new_movie_catgory = s.nextLine();
                    i.setMovieCatgory(new_movie_catgory);
                } else {
                    System.out.println("Please enter a valid number!");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Movie ID not found");
        }
    }

    public void ListMovies() {
        System.out.println("Listing Movies: ");
        for (Movies s : Movies.getMovies()) {
            System.out.println("Movie Name: " + s.getMovieName());
            System.out.println("Movie Category: " + s.getMovieCatgory());
            System.out.println("Movie Price: " + s.getMoviePrice());
            System.out.println("Movie ID: " + s.getMovieId());
        }
    }

    public void viewReports() {
        for (Object userIT : users) {
            User user = (User) userIT;
            if (user instanceof Receptionist) {
                System.out.println(user.getUsername() + " generated: " + ((Receptionist) user).getRevenue_counter());
                System.out.println(user.getUsername() + " generated: " + ((Receptionist) user).getRevenue_counter());
            }
        }
    }

//    public void writeToFile() {
//        Movies m = new Movies();
//
//        try {
//            File file = new File("C:\\Users\\Meno\\Documents\\NetBeansProjects\\JavaApplication26\\src\\javaapplication26\\mena.txt");
//
//            PrintWriter w = new PrintWriter(file);
//
//            for (Movies movie : Movies.getMovies()) {
//                w.println(movie.getName());
//                w.println(movie.getMovieCatgory());
//                w.println(movie.getMovieId());
//                w.println(movie.getMoviePrice());
//            }
//
//            w.close();
//
//        } catch (IOException e) {
//            System.out.println(e);
//
//        }
//    }

    public void readfromFile() throws FileNotFoundException {
       // try {
            

            while (s.hasNextLine()) {
                Movies movie = new Movies();
                movie.setMovieName(s.nextLine());
                movie.setMovieCatgory(s.nextLine());
                movie.setMovieId(Integer.parseInt(s.nextLine()));
                movie.setMoviePrice(Integer.parseInt(s.nextLine()));
                Movies.getMovies().add(movie);
            }

            s.close();
       // } catch (FileNotFoundException e) {
            //System.out.println("File not found: " + e.getMessage());
        }
    

    // Halls Part
    void addHalls(Halls hall) {
        Halls.Halls.add(hall);
    }

    //shows part
    void addShow(Shows show) {
        Shows.allshows.add(show);
        show.hall.shows.add(show); //Bzwd al show dah fe al arraylist al hall
        System.out.println("Show added successfully!");
        if (Shows.allshows.contains(show)) {
            System.out.println("This show is already exist");
        }
    }

    void removeShow(String showid) {
        System.out.println("Are you sure that you want to delete this show?");
        System.out.println("Press 1 to delete");
        System.out.println("Press 2 to exit");
     //   Scanner s = new Scanner(System.in);
        int check = s.nextInt();
        boolean found = false;

        if (check == 1) {
            for (Shows i : Shows.allshows) {
                if (i.getShowID().equals(showid)) {
                    System.out.println("Show " + i.getName() + "Deleted");
                    Shows.allshows.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Not found");
            }
        } else if (check == 2) {
            return;

        }
    }

//    void EditShow(String showId) {
//        Scanner s = new Scanner(System.in);
//
//        for (Shows i : Shows.allshows) {
//            if (i.getShowID().equals(showId)) {  //be search
//                System.out.println("What field do you want to edit?");
//                System.out.println("Press 1 to edit the start time ");
//                System.out.println("Press 2 to edit the end time ");
//                System.out.println("Press 3 to edit the movie");
//                System.out.println("Press 4 to edit the Hall");
//                System.out.println("Press 5 to edit the show ID");
//                int check = s.nextInt();
//                String new_movie_name;
//                double new_starttime;
//                double new_endtime;
//                String showid;
//                Halls hallid;
//                if (check == 1) {
//                    System.out.println("Please enter the new start time");
//                    new_starttime = s.nextDouble();
//                    i.setStartDate(new_starttime);
//                } else if (check == 2) {
//                    System.out.println("Please enter the new end time");
//                    new_endtime = s.nextDouble();
//                    i.setEndDate(new_endtime);
//                } else if (check == 3) {
//                    boolean found = false;
//                    System.out.println("Please enter the new movie name you want to add");
//                    new_movie_name = s.nextLine();
//                    for (Movies m : Movies.getMovies()) { //3ashan t access al arraylist al fe class movie
//                        if (m.getMovieName().equals(new_movie_name)) {
//                            System.out.println("Movie Successfully replaced with " + m.getMovieName());
//                            i.setMovie(m);
//                            found = true;
//                            break;
//                        }
//                    }
//                    if (!found) {
//                        System.out.println("Movie name incorrect, Please try again!");
//                    }
//
//                } else if (check == 4) {
//                    System.out.println("Please enter the new hall you want to add");
//                    boolean found = false;
//                    int new_hall = s.nextInt();
//                    for (Halls m : Halls.Halls) {
//                        if (m.getHallID() == new_hall) {
//                            System.out.println("Hall Successfully replaced with " + m.getHallID());
//                            i.setHallID(m);
//                            found = true;
//                            break;
//                        }
//                    }
//                    if (!found) {
//                        System.out.println("Hall ID incorrect, Please try again!");
//                    }
//                } else if (check == 5) {
//                    System.out.println("Please enter the new show id you want to add");
//                    showid = s.next();
//                    i.setShowID(showid);
//
//                } else {
//                    System.out.println("Please enter a valid number!");
//                }
//
//            }
//        }
//
//    }

    public void ListShows() {
        System.out.println("Listing Show: ");
        for (Shows s : Shows.allshows) {
            System.out.println("Show Name: " + s.getName());
            System.out.println("Show Movie: " + s.getMovie());
            System.out.println("Show Duration: " + s.getDuration());
            System.out.println("Show Price: " + s.getPrice());
            System.out.println("Show ID: " + s.getShowID());
        }
    }

    void Search(String showId) {
        System.out.println("What show you want to search for");

      //  Scanner s = new Scanner(System.in);
        String search = s.next();
        boolean found = false;

        for (Shows i : Shows.allshows) {
            if (search.equals(i.getShowID())) {
                System.out.println("The showid is" + i.getShowID());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Not found");
        }
    }

}
