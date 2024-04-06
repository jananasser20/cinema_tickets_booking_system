/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
public class Movies implements Serializable { 
    
    private transient Image image;
    private double MoviePrice;
    private String MovieName;
    private String MovieCatgory;
    private int bookings;
    private int revenue;
    private int MovieId;
    static HashMap<String, Movies> movieMap = new HashMap<>();
    private static ArrayList<Movies> Movies = new ArrayList<>();

    public Movies() {
    }

    public static ArrayList<Movies> getMovies() {
        return Movies;
    }

    public static Movies getMoviebyName(String name){
        for(Movies s: Movies){
            if(s.MovieName.equalsIgnoreCase(name)){
                System.out.println(name);
                return s;
            }
        }
        return null;
    }

    public Movies(String MovieName, int MoviePrice,  String MovieCatgory, int MovieId) {
        this.MoviePrice = MoviePrice;
        this.MovieName = MovieName;
        this.MovieCatgory = MovieCatgory;
        this.MovieId = MovieId;
        this.image=new Image("file:E:\\oopcinema\\inception.jpg");
        Movies.add(this);
    }
    
    public Movies( String MovieName,int MoviePrice,  String MovieCatgory, String path) {
        this.MoviePrice = MoviePrice;
        this.MovieName = MovieName;
        this.MovieCatgory = MovieCatgory;
        //this.MovieId = movieID;
        this.bookings = 0;
        this.image = new Image("file:"+path);
        Movies.add(this);
    }

    public Image getImage() {
        return image;
    }

    public String getImagePath() {
        return image.getUrl();
    }

    public void setMoviePrice(int MoviePrice) {
        this.MoviePrice = MoviePrice;
    }

    public void setMovieName(String MovieName) {
        this.MovieName = MovieName;
    }

    public void setMovieCatgory(String MovieCatgory) {
        this.MovieCatgory = MovieCatgory;
    }

    public double getMoviePrice() {
        return MoviePrice;
    }


    public String getMovieName() {
        return MovieName;
    }
    public static boolean isMovie(String name){
        for(Movies s: Movies){
            if(s.getMovieName().equalsIgnoreCase(name))
                System.out.println(s.MovieName);
                return true;
        }
        return false;
    }
    public String getMovieCatgory() {
        return MovieCatgory;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int MovieId) {
        this.MovieId = MovieId;
    }
    
    public int getRevenue() {
        return revenue;
    }

    public String getName() {
        return MovieName;
    }

    public int getBookings() {
        return bookings;
    }

    public void book() {
        revenue = (int)MoviePrice * bookings;
        bookings++;
    }

    public VBox viewReports() {
    TableView<Movies> reportsTable = new TableView<>();
    
    TableColumn<Movies, String> nameColumn = new TableColumn<>("Movie Name");
nameColumn.setCellValueFactory(new PropertyValueFactory<>("MovieName"));

    TableColumn<Movies, String> categoryColumn = new TableColumn<>("Category");
    categoryColumn.setCellValueFactory(new PropertyValueFactory<>("MovieCatgory"));
    TableColumn<Movies, Integer> bookingsColumn = new TableColumn<>("Bookings");
    bookingsColumn.setCellValueFactory(new PropertyValueFactory<>("bookings"));

    reportsTable.getColumns().addAll(nameColumn, categoryColumn, bookingsColumn);

    ObservableList<Movies> data = FXCollections.observableArrayList(Movies);


    reportsTable.setPrefWidth(175); 
    nameColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.30));
    categoryColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.35));
    bookingsColumn.prefWidthProperty().bind(reportsTable.widthProperty().multiply(0.35));

    reportsTable.setRowFactory(tv -> {
        TableRow<Movies> row = new TableRow<>();
        row.prefHeightProperty().bind(reportsTable.heightProperty().divide(10)); 
        return row;
    });
    reportsTable.setItems(data);
    Label mostBookedLabel = new Label("Most booked movie is: " + (mostBooked() != null ? mostBooked().getMovieName() + " (Bookings: " + mostBooked().getBookings() + ", Revenue: " + mostBooked().getRevenue() + ")" : " "));
    Label mostRevenueLabel = new Label("Most revenue movie is: " + (mostRevenue() != null ? mostRevenue().getMovieName() + " (Bookings: " + mostRevenue().getBookings() + ", Revenue: " + mostRevenue().getRevenue() + ")" : " "));
    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14);
    mostBookedLabel.setFont(font);
    mostRevenueLabel.setFont(font);

    VBox container = new VBox();
    container.getChildren().addAll(reportsTable, mostBookedLabel, mostRevenueLabel);
    return container;
    }
    public Movies mostBooked() {
        Movies mostBookedMovie = null;
        int maxNum = 0;

        for (Movies movie : movieMap.values()) {
            if (movie.getBookings() > maxNum) {
                maxNum = movie.getBookings();
                mostBookedMovie = movie;
            }
        }
        return mostBookedMovie;
    }

    public Movies mostRevenue() {
        Movies mostRevMovie = null;
        int maxRevenue = -1;

        for (Movies movie : movieMap.values()) {
            int revenue = (int)movie.getMoviePrice() * movie.getBookings();
            if (revenue > maxRevenue) {
                maxRevenue = revenue;
                mostRevMovie = movie;
            }
        }
        return mostRevMovie;
    }
    
     public static ArrayList<String> getMovieNames() {  //j
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movies movie : Movies) {
            movieNames.add(movie.getMovieName());
        }
        return movieNames;
    }

     
      public static void soutMovies() {  //j
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movies movie : Movies) {
            System.out.println(movie.MovieName);
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