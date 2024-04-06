/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication14;

import java.util.ArrayList;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class GUI extends Application {
    String username;
    static int bc =0;
    public GUI() {
        this.username = "Guest";
    }

    public GUI(String username) {
        this.username = username;
    }
    
    
    public static BorderPane createMoviePanel(){
    BorderPane root = new BorderPane();
        GridPane moviesStack = new GridPane();
        root.setCenter(moviesStack);
        moviesStack.setPadding(new Insets(60));
        moviesStack.setHgap(30);
        moviesStack.setVgap(20);

        moviesStack.setBackground(new Background(new BackgroundFill(Color.web("#222b31"), CornerRadii.EMPTY, Insets.EMPTY)));
        VBox inceptionPane = moviePane(new Image("file:E:\\oopcinema\\inception.jpg"), "Inception", "Action");
        moviesStack.add(inceptionPane,0,0);
        
        VBox egPane = moviePane(new Image("file:E:\\oopcinema\\endgame.jpg"), "Endgame", "Action");
        moviesStack.add(egPane,1,0);
        
        VBox barbiePane = moviePane(new Image("file:E:\\oopcinema\\barbie.jpg"), "Barbie", "Drama, Romance");
        moviesStack.add(barbiePane,2,0);
        
        VBox blueePane = moviePane(new Image("file:E:\\oopcinema\\blue elephant.jpg"), "Blue Elephant", "Action, Horror");
        moviesStack.add(blueePane,3,0);
        
        VBox gnPane = moviePane(new Image("file:E:\\oopcinema\\gn.jpg"), "Goodnight", "Drama");
        moviesStack.add(gnPane,0,1);
        // Create header
        HBox headerPane = new HBox();
        headerPane.setBackground(new Background(new BackgroundFill(Color.web("#f83745"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label label = new Label("     Cinema");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        headerPane.getChildren().add(label);
        HBox headerPane2 = new HBox();
        headerPane2.setBackground(new Background(new BackgroundFill(Color.web("#ff424f"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label label2 = new Label("\t\t\t\t\t\t\t\t\t Movies");
        headerPane2.getChildren().add(label2);
        label.setTextFill(Color.BLACK);        
        label2.setFont(Font.font("Roboto", FontWeight.BOLD, 14));        
        headerPane2.setMinWidth(750);
        headerPane.setMinWidth(115.5);
        headerPane.setMinHeight(40);
        GridPane hs = new GridPane();
        hs.add(headerPane, 0, 0);
        hs.add(headerPane2, 1, 0);
        root.setTop(hs);

        GridPane rightPanel = new GridPane();
        rightPanel.setMinWidth(115.5);
        //rightPanel.setBackground(new Background(new BackgroundFill(Color.web("#191c1f"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        root.setLeft(rightPanel);
        rightPanel.setStyle("-fx-background-color: #191c1f; -fx-padding: 10px;");
        Button Moviesb = new Button("Movies");
        Moviesb.setBackground(Background.EMPTY);
        Moviesb.setTextFill(Color.WHITE);  /* Set font color to blue */
        Moviesb.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        Moviesb.setOnAction(e->{
            moviesStack.setVisible(true);
        
        });
        Moviesb.setOnMouseEntered(e->{Moviesb.setTextFill(Color.web("#db4e58"));});
        Moviesb.setOnMouseExited(e->{Moviesb.setTextFill(Color.WHITE);});  
        
        Button Usersb = new Button("Users");
        Usersb.setTextFill(Color.WHITE);  /* Set font color to blue */
        Usersb.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        Usersb.setBackground(Background.EMPTY); /* Set transparent background */
        Usersb.setOnAction(e->{
            moviesStack.setVisible(false);
        });
        Usersb.setOnMouseEntered(e->{Usersb.setTextFill(Color.web("#db4e58"));});
        Usersb.setOnMouseExited(e->{Usersb.setTextFill(Color.WHITE);});   
        
        rightPanel.add(Moviesb, 0, 0);
        rightPanel.add(Usersb, 0, 1);
        rightPanel.setVgap(20);
        
        return root;
    }
    public static VBox moviePane(Image photo, String name, String Category){
     VBox pane = new VBox();
     ImageView movieView = new ImageView(photo);
     movieView.setFitHeight(142);
     movieView.setFitWidth(100);
     Label nameL = new Label (name);
     nameL.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
     nameL.setTextFill(Color.WHITE);

     Label catL = new Label (Category);
     catL.setTextFill(Color.GREY);
     pane.getChildren().addAll(movieView, nameL, catL);
     
        
     return pane;   
    }
    
    public Stage mystage = new Stage();
    //Movies m=new Movies();
    Booking b = new Booking();
    Receptionist r = new Receptionist();
    HallType h;

    @Override
    public void start(Stage primarystage) {
        mystage = which_page(primarystage);
    }

    public static void main(String[] args) {
        
        Movies temp = new Movies("Inf", 50, "Action", 1);
        Movies.soutMovies();
        launch(args);
    }

    private void resetBookingPage(Stage stage, ComboBox cbo2, RadioButton standardRadio, RadioButton vipRadio,
            RadioButton bakrRadio, RadioButton alidRadio, RadioButton childRadio, Button[] buttons, TextField... textFields) {
        standardRadio.setSelected(false);
        vipRadio.setSelected(false);
        bakrRadio.setSelected(false);
        alidRadio.setSelected(false);
        childRadio.setSelected(false);

        for (Button button : buttons) {
            button.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        }
        for (TextField textField : textFields) {
            textField.clear();
        }

        cbo2.getSelectionModel().clearSelection();

        stage.show();
    }

    public Stage which_page(Stage stage) {
        HBox[] h = new HBox[3];
        for (int i = 0; i < 3; i++) {
            h[i] = new HBox(15);
        }
        Text welcome = new Text("Welcome back " + username);
        h[0].getChildren().add(welcome);
        h[0].setAlignment(Pos.TOP_LEFT);

        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        welcome.setFill(Color.TURQUOISE);

        Text t1 = new Text("For a new booking        :");
        Text t2 = new Text("For viewing some data  :");
        Button bt1 = new Button("      click        ");
        Button bt2 = new Button("      click        ");
        Button bt3 = new Button("Login");
        bt3.setOnAction(e->{
            new AdminGUI().login();
        });
        
        t1.setFont(Font.font("Georgia", FontWeight.NORMAL, 15));
        t2.setFont(Font.font("Georgia", FontWeight.NORMAL, 15));

        bt1.setStyle("-fx-padding: 5;");
        bt2.setStyle("-fx-padding: 5;");
        bt3.setStyle("-fx-padding: 15;");
        bt1.setOnAction(e -> {
            mystage = gbooking(stage);
        });
        bt2.setOnAction(e -> {
            mystage = view_details(stage);
        });

        h[1].getChildren().addAll(t1, bt1);
        h[2].getChildren().addAll(t2, bt2);
        VBox v = new VBox(20);
        //v.getChildren().addAll(h[0],new Text(),h[1],h[2]);
        v.setPadding(new Insets(20));
        v.getChildren().addAll(h[0]);
        VBox.setMargin(h[1], new Insets(10, 0, 0, 0));
        v.getChildren().addAll(h[1], h[2], bt3);

        Scene scene = new Scene(v, 430, 220);
        stage.setScene(scene);
        stage.setTitle("Receptionist page");
        stage.show();
        return stage;
    }

    public Stage gbooking(Stage stage) {
        HBox[] h = new HBox[10];
        for (int i = 0; i < 10; i++) {
            h[i] = new HBox(15);
        }
        Text welcomeText = new Text("Welcome to OOP Cinema!");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        welcomeText.setFill(Color.DARKGREEN);
        h[0].getChildren().add(welcomeText);
        h[0].setAlignment(Pos.TOP_CENTER);

        Label l1 = new Label("Client's username : ");
        Label l3 = new Label("Movie time            : ");
        TextField tf1 = new TextField("Name");

        Label l9 = new Label("Movie name            : ");
        TextField tf2 = new TextField("type");
        ArrayList movieNames = Movies.getMovieNames();
        ComboBox<String> movieComboBox = new ComboBox<>(FXCollections.observableArrayList(movieNames));


        ComboBox<String> cbo2 = new ComboBox<>(FXCollections.observableArrayList("12:00 pm", "3:00 pm",
                "6:00 pm", "9:00 pm", "12:00 am"));

        Label hallLabel = new Label("Select Hall Type   :");
        ToggleGroup hallGroup = new ToggleGroup();
        RadioButton standardRadio = new RadioButton("Standard");
        standardRadio.setToggleGroup(hallGroup);
        RadioButton vipRadio = new RadioButton("VIP");
        RadioButton bakrRadio = new RadioButton("Bakr");
        bakrRadio.setToggleGroup(hallGroup);
        RadioButton alidRadio = new RadioButton("Ali");
        alidRadio.setToggleGroup(hallGroup);
        RadioButton childRadio = new RadioButton("Children");
        childRadio.setToggleGroup(hallGroup);
        vipRadio.setToggleGroup(hallGroup);

        Label l4 = new Label("Select seats         : ");
        Button b1 = new Button("A1");
        Button b2 = new Button("A2");
        Button b3 = new Button("A3");
        Button b4 = new Button("A4");
        Button b5 = new Button("B1");
        Button b6 = new Button("B2");
        Button b7 = new Button("B3");
        Button b8 = new Button("B4");
        Button b9 = new Button("C1");
        Button b10 = new Button("C2");
        Button b11 = new Button("C3");
        Button b12 = new Button("C4");

        Button[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};

        for (Button button : buttons) {
            button.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        }

        for (Button button : buttons) {
            button.setOnAction(e -> {
                if(bc%2==0){
                button.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
                bc++;
                }
                else{
                 button.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
                 bc++;
                }
            });

        }

        l1.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        l9.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        l3.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        l4.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        hallLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 12));

        l1.setTextFill(Color.DARKSLATEGRAY);
        l9.setTextFill(Color.DARKSLATEGRAY);
        l3.setTextFill(Color.DARKSLATEGRAY);
        l4.setTextFill(Color.DARKSLATEGRAY);
        hallLabel.setTextFill(Color.DARKSLATEGRAY);

        Button bk = new Button("confirm booking");

        bk.setOnAction(e -> {

            // Check if the movie in movie arraylist    bs lazmm ela dmin y add  movie el aw
            String userName = tf1.getText();
            String movieName = movieComboBox.getValue();
//            boolean movieExists = Movies.isMovie(movieName);
//            if (!movieExists) {
//                resetBookingPage(stage, cbo2, standardRadio, vipRadio, bakrRadio, alidRadio, childRadio, buttons, tf1, tf2);
//                mview_alert();
//            }

            String selectedTime = cbo2.getSelectionModel().getSelectedItem();
            RadioButton selectedRadioButton = (RadioButton) hallGroup.getSelectedToggle();
            String hallTypeName = selectedRadioButton.getText();
            HallType selectedHallType = HallType.valueOf(hallTypeName);
            double priceMultiplier = selectedHallType.getPriceMultiplier();
            int selectedHour = Integer.parseInt(selectedTime.split(":")[0]);
            Movies mov = Movies.getMoviebyName(movieName);
            
            // IMPO
            // guestName takhod el result bta3 el name 
             r.create_booking(userName, movieName, selectedHour, hallTypeName, mov.getMoviePrice()*priceMultiplier);

        });

        h[5].setPadding(new Insets(0, 0, 0, 128));
        h[7].setPadding(new Insets(0, 0, 0, 122));
        h[8].setPadding(new Insets(0, 0, 0, 122));

        h[1].getChildren().addAll(l1, tf1);
        h[2].getChildren().addAll(l9, movieComboBox
        
        );
        h[3].getChildren().addAll(l3, cbo2);
        h[4].getChildren().addAll(hallLabel, standardRadio, vipRadio, childRadio);
        h[5].getChildren().addAll(bakrRadio, alidRadio);
        h[6].getChildren().addAll(l4, b1, b2, b3, b4);
        h[7].getChildren().addAll(b5, b6, b7, b8);
        h[8].getChildren().addAll(b9, b10, b11, b12);
        h[9].getChildren().add(bk);

        VBox v = new VBox(28);
        v.getChildren().addAll(h[0], h[1], h[2], h[3], h[4], h[5], h[6], h[7], h[8], h[9]);
        v.setPadding(new Insets(20));
        Scene scene = new Scene(v, 510, 550);
        stage.setScene(scene);
        stage.setTitle("Booking page");
        stage.show();
        return stage;
    }

    public Stage view_details(Stage stage) {

        HBox[] h = new HBox[5];
        for (int i = 0; i < 5; i++) {
            h[i] = new HBox(15);
        }

        Text welcomeText = new Text("Happy to share some info ;)");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        welcomeText.setFill(Color.PURPLE);
        h[0].getChildren().add(welcomeText);
        h[0].setAlignment(Pos.TOP_CENTER);

        Label l1 = new Label(" your total bookings are   : " + Integer.toString(r.getBooking_counter()));
        Label l2 = new Label(" Total revenue     : " + Integer.toString(r.getRevenue_counter()));
        Label l3 = new Label(" View bookings    : ");
        Button b1 = new Button("Per client");
        Button b2 = new Button("All Bookings");

        b1.setOnAction(e -> {
            pview_alert();
        });

        b2.setOnAction(e -> {
            aview_alert();
        });

        l1.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #333333;");
        l2.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #333333;");
        l3.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #333333;");

        b1.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        b2.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        h[1].getChildren().add(l1);
        h[2].getChildren().add(l2);
        h[3].getChildren().addAll(l3, b1, b2);
        VBox v = new VBox(15);
//        v.getChildren().addAll(h[0],h[1],h[2],h[3]);
        v.setPadding(new Insets(20));

        v.getChildren().addAll(h[0]);

        VBox.setMargin(h[1], new Insets(20, 0, 0, 0));

        v.getChildren().addAll(h[1], h[2], h[3]);
        Scene scene = new Scene(v, 500, 250);
        stage.setScene(scene);
        stage.setTitle("Info page");
        stage.show();
        return stage;
    }

    public void pview_alert() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(b.view());
        a.getButtonTypes().clear();
        a.getButtonTypes().add(ButtonType.OK);
        a.setHeaderText(null);
        a.setTitle("Bookings per client");
        a.show();

    }

    public void aview_alert() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(Booking.viewAll());
        a.getButtonTypes().clear();
        a.getButtonTypes().add(ButtonType.OK);
        a.setHeaderText(null);
        a.setTitle("All Bookings booked");
        a.show();

    }

    public void mview_alert() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText("Unfortunatly, your chosen movie is not available");
        a.getButtonTypes().clear();
        a.getButtonTypes().add(ButtonType.OK);
        a.setHeaderText(null);
        a.setTitle("All Bookings booked");
        a.show();

    }
}
