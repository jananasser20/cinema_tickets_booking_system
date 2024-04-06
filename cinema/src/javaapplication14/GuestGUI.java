package javaapplication14;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GuestGUI extends Application {

    private static final int COLUMN_COUNT = 4;
    private static int row = 0;
    private static int column = 0;

    private Stage primaryStage;
    private Scene guestScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        BorderPane root = new BorderPane();
        GridPane showsStack = createShowsStack(); // Implement this method to populate shows
        ScrollPane scrollPane = new ScrollPane(showsStack);
        showsStack.setPadding(new Insets(60));
        showsStack.setHgap(30);
        showsStack.setVgap(20);

        HBox headerPane = createHeader();
        root.setTop(headerPane);
        root.setCenter(scrollPane);

        Scene guestScene = new Scene(root, 800, 600);
        primaryStage.setScene(guestScene);
        primaryStage.setTitle("Guest Preview");
        primaryStage.show();
    }

    HBox createHeader() {
        HBox headerPane = new HBox();
        headerPane.setBackground(new Background(new BackgroundFill(Color.web("#f83745"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label label = new Label("     Cinema");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        headerPane.getChildren().add(label);

        return headerPane;
    }

     GridPane createShowsStack() {
        GridPane showsStack = new GridPane();
        showsStack.setBackground(new Background(new BackgroundFill(Color.web("#222b31"), CornerRadii.EMPTY, Insets.EMPTY)));

        for (int i = 1; i <= 8; i++) {
            VBox showPane = showPane(new Image("file:path/to/show" + i + ".jpg"), "Show " + i, "Time", "Hall " + i);
            showsStack.add(showPane, column, row);
            column++;
            if (column == COLUMN_COUNT) {
                row++;
                column = 0;
            }
        }

        return showsStack;
    }

   VBox showPane(Image photo, String name, String time, String hall) {
    VBox pane = new VBox();
    ImageView showView = new ImageView(photo);
    showView.setFitHeight(142);
    showView.setFitWidth(100);
    Label nameL = new Label(name);
    nameL.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
    nameL.setTextFill(Color.WHITE);

    Label timeLabel = new Label("Time: " + time);
    timeLabel.setTextFill(Color.GREY);

    Label hallLabel = new Label("Hall: " + hall);
    hallLabel.setTextFill(Color.GREY);

    Button detailsButton = new Button("Show Details");
    detailsButton.setOnAction(e -> previewShowDetails(name));

    pane.getChildren().addAll(showView, nameL, timeLabel, hallLabel, detailsButton);

    return pane;
}
   
  void previewShowDetails(String showName) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Show Details");
    dialog.setHeaderText("Details for Show: " + showName);

    ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
    ButtonType nadaButtonType = new ButtonType("Book from here!!", ButtonData.APPLY);

    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, nadaButtonType);

    Button okButton = (Button) dialog.getDialogPane().lookupButton(okButtonType);
    Button nadaButton = (Button) dialog.getDialogPane().lookupButton(nadaButtonType);



    okButton.setOnAction(e -> {
        dialog.close();
    });

    nadaButton.setOnAction(e -> {
        
        
        
        System.out.println("Booking from here!!");
    });

    Label detailsLabel = new Label("Write any details you want here.");

    dialog.getDialogPane().setContent(detailsLabel);

    Optional<String> result = dialog.showAndWait();
}

    
//    private void performBooking(Shows selectedShow) {
//    // Use the details of the selected show to create a new booking
//    Booking newBooking=new Booking();
//      newBooking.setMovieName(selectedShow.getName());
//    newBooking.setShowTiming(selectedShow.date());
//    newBooking.setHallNumber(selectedShow.getHallNumber());
//    newBooking.setPrice(selectedShow.getPrice());
//    // Set other booking details as needed
//    // Add the new booking to the list of all bookings
//    Booking.allbookings.add(newBooking);
//
//    // Print a message or perform additional actions as needed
//    System.out.println("Booking successful for show: " + selectedShow.getName());
//}
    
    void previewAllShows() {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("All Shows");
    dialog.setHeaderText("Details for all Shows");

    GridPane allShowsGrid = createShowsStack();
    dialog.getDialogPane().setContent(allShowsGrid);

    ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().add(okButtonType);

    Button okButton = (Button) dialog.getDialogPane().lookupButton(okButtonType);
    okButton.setOnAction(e -> dialog.close());

    dialog.showAndWait();
}
    
    
    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
}