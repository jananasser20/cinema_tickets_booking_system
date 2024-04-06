/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Shows implements Serializable { 
    
    static ArrayList<Shows> allshows = new ArrayList<>();
    private int showsBookings;
    private String name;
    private double duration ,EndTime;
    private String startDate ;
    private int hallNumber;
    private double price;
    private String showID;
    Movies movie; //aggregation kol show has a movie
    Halls hall; //aggregation kol show has a specific hall

    public Shows(String name, String startDate, double duration, Movies movie, int hallNumber) { //haya
        this.startDate = startDate;
        this.EndTime = EndTime;
        this.name = name;
        this.movie = movie;
        this.showsBookings = 0;
        this.price = movie.getMoviePrice();
        this.showID = showID;
        this.hall=Halls.getHallById(hallNumber);
        allshows.add(this);

    }
    public void setEndDate(double EndTime) {
        this.EndTime = EndTime;
    }
    public void setHallID(Halls hall) {
        this.hall = hall;
    }
    public Shows() {
        this.showsBookings = 0;
    }
    public void setMovie(Movies movie) {
        this.movie = movie;
    }
    public void setStartDate(String startTime) {
        this.startDate = startTime;
    }
    public String getShowID() {
        return showID;
    }
    public void setEndTime(double EndTime) {
        this.EndTime = EndTime;
    }
    public double getEndTime() {
        return EndTime;
    }
    public void setShowID(String showID) {
        this.showID = showID;
    }
    public Movies getMovie() {
        return movie;
    }
    public double getDuration() {
        return duration;
    }

    public int getShowsBookings() {
        return showsBookings;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void book() {
        showsBookings++;
        movie.book();
    }

//    public String date() {
//        //duration in minutes
//        double dateDuration=duration/60;
//        int startHours = (int) startDate ;
//        int startMinutes = (int) ((startDate - startHours) * 60);
//        //double endDate = (startDate + duration);
//        int endHours = (int) (startDate + dateDuration) ;
//        int endMinutes=(int) (((startDate + dateDuration)-endHours)*60);
//        startHours = (startHours % 12 == 0) ? 12 : startHours % 12;
//        endHours = (endHours % 12 == 0) ? 12 : endHours % 12;
//        String formattedStartMinutes = String.format("%02d", startMinutes);
//        String formattedEndMinutes = String.format("%02d", endMinutes);
//        String start = (startHours < 12) ? "AM" : "PM";
//        String end = (endHours < 12) ? "AM" : "PM";
//        return(startHours+":"+formattedStartMinutes+" "+start+" to "+endHours+":"+formattedEndMinutes+" "+end);
//    }
    public static Shows mostBookedShow() {
        Shows mostBooked = null;
        int maxNum = -1;
        for (Shows show : allshows) {
            if (show.getShowsBookings() > maxNum) {
                maxNum = show.getShowsBookings();
                mostBooked = show;
            }
        }
        return mostBooked;
    }

    public static Shows mostRevenue() {
        Shows mostRevShow = null;
        int maxRevenue = -1;

        for (Shows show : allshows) {
            int revenue = (int) show.getPrice() * show.getShowsBookings();
            if (revenue > maxRevenue) {
                maxRevenue = revenue;
                mostRevShow = show;
            }
        }
        return mostRevShow;
    }

    public VBox viewReports() {
    TableView<Shows> reportsTable = new TableView<>();
    
    TableColumn<Shows, String> nameColumn = new TableColumn<>("Show Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    
    TableColumn<Shows, String> hallColumn = new TableColumn<>("Hall Number");
    hallColumn.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
    
    TableColumn<Shows, Integer> bookingsColumn = new TableColumn<>("Bookings");
    bookingsColumn.setCellValueFactory(new PropertyValueFactory<>("showsBookings"));
    
    TableColumn<Shows, String> slotsColumn = new TableColumn<>("Slots");
    slotsColumn.setCellValueFactory(new PropertyValueFactory<>("slot"));

    reportsTable.getColumns().addAll(nameColumn, hallColumn, bookingsColumn, slotsColumn);

    ObservableList<Shows> data = FXCollections.observableArrayList(allshows);

    reportsTable.setPrefWidth(150);
    nameColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.25));
    hallColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.25));
    bookingsColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.25));
    slotsColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.25));

    reportsTable.setRowFactory(tv -> {
        TableRow<Shows> row = new TableRow<>();
        row.prefHeightProperty().bind(reportsTable.heightProperty().divide(10));
        return row;
    });

    reportsTable.setItems(data);

    Label mostBookedLabel = new Label("Most booked show is: " + (mostBookedShow() != null ?
            mostBookedShow().getName() + " (Bookings: " + mostBookedShow().getShowsBookings() + ")" : " "));

    Label mostRevenueLabel = new Label("Most revenue show is: " + (mostRevenue() != null ?
            mostRevenue().getName() + " (Bookings: " + mostRevenue().getShowsBookings() + ")" : " "));

    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14);
    mostRevenueLabel.setFont(font);
    mostBookedLabel.setFont(font);
    VBox container = new VBox();
    container.getChildren().addAll(reportsTable, mostBookedLabel, mostRevenueLabel);
    return container;
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