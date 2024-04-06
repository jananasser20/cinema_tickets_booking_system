package javaapplication14;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class AdminGUI extends Application implements Serializable {

    static int rowN = 0;
    static int colN = 0;

    static int rowS = 0;
    static int colS = 0;

    static int rowU = 0;
    static int colU = 0;
    int UsersNodeCounter = 0;
    //int NodeCounter = 0;
    GridPane usersStack;
    GridPane moviesStack = createMovieStack();
    Stage primaryStage = new Stage();
    GridPane showstack = createShowsStack();
    //GridPane adminStack = createAdminStack("Bakr");
    Scene scene;
    Admin bakr = new Admin("Bakr", "0000");

    @Override
    public void start(Stage primaryStage) {
        login();
    }

    Scene admin(String username) {
        BorderPane root = new BorderPane();
        //moviesStack = createMovieStack();
        ScrollPane ScrollMovie = new ScrollPane(moviesStack);
        moviesStack.setPadding(new Insets(60));
        moviesStack.setHgap(30);
        moviesStack.setVgap(20);

        GridPane adminStack = createAdminStack(username);
        root.setCenter(adminStack);
        adminStack.setPadding(new Insets(35));
        adminStack.setHgap(30);
        adminStack.setVgap(20);
        Button MovRep = new Button("View Movies Reports");
        Button ShowRep = new Button("View Shows Reports");
        MovRep.setOnAction(e -> showMoviesReports());
        ShowRep.setOnAction(e -> showShowsReports());
        adminStack.add(MovRep, 3, 0);
        adminStack.add(ShowRep, 3, 1);

        usersStack = createUsersStack();
        usersStack.setPadding(new Insets(60));
        usersStack.setHgap(30);
        usersStack.setVgap(20);

        showstack.setPadding(new Insets(40));
        showstack.setHgap(30);
        showstack.setVgap(20);

        // Create header
        HBox headerPane = new HBox();
        headerPane.setBackground(new Background(new BackgroundFill(Color.web("014421"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label label = new Label("     Cinema");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        headerPane.getChildren().add(label);
        HBox headerPane2 = new HBox();
        headerPane2.setBackground(new Background(new BackgroundFill(Color.web("014421"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label label2 = new Label("\t\t\t\t\t\t\t\t\t Movies");
        headerPane2.getChildren().add(label2);
        label.setTextFill(Color.BLACK);
        label2.setFont(Font.font("Roboto", FontWeight.BOLD, 14));
        headerPane2.setMinWidth(1450);
        headerPane.setMinWidth(172);
        headerPane.setMinHeight(40);
        GridPane hs = new GridPane();
        hs.add(headerPane, 0, 0);
        hs.add(headerPane2, 1, 0);
        root.setTop(hs);

        GridPane rightPanel = new GridPane();
        ScrollPane rightWheel = new ScrollPane(rightPanel);

        rightPanel.setMinWidth(115.5);
        //rightPanel.setBackground(new Background(new BackgroundFill(Color.web("#191c1f"), CornerRadii.EMPTY, Insets.EMPTY)));

        root.setLeft(rightPanel);
        rightPanel.setStyle("-fx-background-color: #014421; -fx-padding: 10px;");

        Button Administration = new Button("Administration");
        Administration.setBackground(Background.EMPTY);
        Administration.setTextFill(Color.WHITE);
        /* Set font color to blue */
        Administration.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        Administration.setOnAction(e -> {
            root.setCenter(adminStack);

        });
        Administration.setOnMouseEntered(e -> {
            Administration.setTextFill(Color.web("#db4e58"));
        });
        Administration.setOnMouseExited(e -> {
            Administration.setTextFill(Color.WHITE);
        });

        Button Moviesb = new Button("Movies");
        Moviesb.setBackground(Background.EMPTY);
        Moviesb.setTextFill(Color.WHITE);
        /* Set font color to blue */
        Moviesb.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        Moviesb.setOnAction(e -> {
            root.setCenter(ScrollMovie);
        });
        Moviesb.setOnMouseEntered(e -> {
            Moviesb.setTextFill(Color.web("#db4e58"));
        });
        Moviesb.setOnMouseExited(e -> {
            Moviesb.setTextFill(Color.WHITE);
        });

        Button Usersb = new Button("Users");
        Usersb.setTextFill(Color.WHITE);
        /* Set font color to blue */
        Usersb.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        Usersb.setBackground(Background.EMPTY);
        /* Set transparent background */
        Usersb.setOnAction(e -> {
            root.setCenter(usersStack);

        });
        Usersb.setOnMouseEntered(e -> {
            Usersb.setTextFill(Color.web("#db4e58"));
        });
        Usersb.setOnMouseExited(e -> {
            Usersb.setTextFill(Color.WHITE);
        });

        ScrollPane Scrollshow = new ScrollPane(showstack);
        Button showsB = new Button("Shows");

        showsB.setTextFill(Color.WHITE);
        /* Set font color to blue */
        showsB.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        showsB.setBackground(Background.EMPTY);
        showsB.setOnAction(e -> {
            root.setCenter(Scrollshow);

        });
        showsB.setOnMouseEntered(e -> {
            showsB.setTextFill(Color.BLACK);
        });
        showsB.setOnMouseExited(e -> {
            showsB.setTextFill(Color.WHITE);
        });

        Button Login = new Button("Login");
        Login.setTextFill(Color.WHITE);
        Login.setFont(Font.font("Montserrat", FontWeight.BOLD, 18));
        Login.setBackground(Background.EMPTY);
        /* Set transparent background */
        Login.setOnAction(e -> {
            login();

        });
        Login.setOnMouseEntered(e -> {
            Login.setTextFill(Color.web("#db4e58"));
        });
        Login.setOnMouseExited(e -> {
            Login.setTextFill(Color.WHITE);
        });

        rightPanel.add(Administration, 0, 0);
        rightPanel.add(Moviesb, 0, 2);
        rightPanel.add(Usersb, 0, 3);
        rightPanel.add(showsB, 0, 4);
        rightPanel.add(Login, 0, 17);
        rightPanel.setVgap(20);

        // Button Moviesb = new Button("Movies");
        //   Button Moviesb = new Button("Movies");
//        ComboBox<String> menu1 = new ComboBox<>();
//        ObservableList<String> items = observableArrayList("Option 1", "Option 2", "Option 3");
//        menu1.setItems(items);
//        ComboBox<String> menu2 = new ComboBox<>();
//        ObservableList<String> items2 = observableArrayList("Option 1", "Option 2", "Option 3");
//        menu2.setItems(items2);
//        rightPanel.add(menu1, 0, 0);
//        rightPanel.add(menu2, 0, 1);
//        rightPanel.setVgap(10);
//        rightPanel.setPadding(new Insets(10));
//        // VBox left = new VBox();
//        Pane topPanel = new Pane();
//        topPanel.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #a0a0a0; -fx-padding: 10px;");
//        topPanel.setMinHeight(90);
//        root.setTop(topPanel);
        scene = new Scene(root, 800, 600);
        return scene;
//        primaryStage.setScene(scene);
//
//        primaryStage.setTitle("Admin Page ");
//        primaryStage.show();

    }

    void Guest(String username) {
        GUI gu = new GUI(username);
        gu.start(primaryStage);
    }

    GridPane createMovieStack() {
        GridPane moviesStack = new GridPane();
        moviesStack.setBackground(new Background(new BackgroundFill(Color.web("BCB88A"), CornerRadii.EMPTY, Insets.EMPTY)));

        for (Movies mov : Movies.getMovies()) {
            VBox movp = moviePane(mov.getImagePath(), mov.getMovieName(), mov.getMovieCatgory());
            addMovie(movp, moviesStack);
        }

        // TODO: Replace with your actual logic to retrieve movie data
        ArrayList<Movies> movies = Movies.getMovies();  // Assuming you have a method to fetch movie data
//        VBox inceptionPane = moviePane(new Image("file:E:\\oopcinema\\inception.jpg"), "Inception", "SciFi");
//        addMovie(inceptionPane, moviesStack);
//
//        VBox egPane = moviePane(new Image("file:E:\\oopcinema\\endgame.jpg"), "Endgame", "Action");
//        addMovie(egPane, moviesStack);
//
//        VBox barbiePane = moviePane(new Image("file:E:\\oopcinema\\barbie.jpg"), "Barbie", "Drama, Romance");
//        addMovie(barbiePane, moviesStack);
//
//        VBox blueePane = moviePane(new Image("file:E:\\oopcinema\\blue elephant.jpg"), "Blue Elephant", "Action, Horror");
//        addMovie(blueePane, moviesStack);
//
//        VBox gnPane = moviePane(new Image("file:E:\\oopcinema\\gn.jpg"), "Goodnight", "Drama");
//        addMovie(gnPane, moviesStack);

        // Add other elements to moviesStack (e.g., button to trigger adding more movies)
        moviesStack.setMinWidth(900);
        moviesStack.setMinHeight(1200);
        return moviesStack;
    }

    GridPane createShowsStack() {
        GridPane showsStack = new GridPane();
        showsStack.setBackground(new Background(new BackgroundFill(Color.web("BCB88A"), CornerRadii.EMPTY, Insets.EMPTY)));

//        VBox barbiePane = showPane(new Image("file:E:\\oopcinema\\barbie.jpg"), "Barbie", 1, "Drama");
//        addShow(barbiePane, showsStack);
        showsStack.setMinWidth(1400);
        showsStack.setMinHeight(1000);
        return showsStack;
    }

    GridPane createAdminStack(String username) {
        GridPane adminStack = new GridPane();
        adminStack.setBackground(new Background(new BackgroundFill(Color.web("BCB88A"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Image and Admin Name
        Image userPhoto = new Image("file:E:\\oopcinema\\user.png");
        ImageView movieView = new ImageView(userPhoto);
        movieView.setFitHeight(120);
        movieView.setFitWidth(120);
        adminStack.add(movieView, 0, 0);

        Label adminName = new Label("Welcome, " + username);  // Add the admin name
        adminName.setFont(Font.font("Arial", FontWeight.BOLD, 34));
        adminName.setTextFill(Color.WHITE);  // Set the label color to blue
        adminStack.add(adminName, 1, 0);

        Dialog<ButtonType> addUserDialog = new Dialog<>();
        addUserDialog.setTitle("Add User");

        VBox dialogContent = new VBox();
        TextField userNameField = new TextField();
        TextField passField = new TextField("Pass");
        ChoiceBox<String> userTitleChoiceBox = new ChoiceBox<>(observableArrayList("Owner", "Admin", "Receptionist", "Guest"));
        Button confirmButton = new Button("Add");
        dialogContent.getChildren().addAll(userNameField, passField, userTitleChoiceBox, confirmButton);
        addUserDialog.getDialogPane().setContent(dialogContent);

        confirmButton.setOnAction(e -> {
            addUserDialog.setResult(ButtonType.OK);  // Indicate success
            addUserDialog.close();
        });

        Button addUserButton = new Button("Add User");
        addUserButton.setOnAction(e -> {
            Optional<ButtonType> result = addUserDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String name = userNameField.getText();
                String title = userTitleChoiceBox.getValue();
                String pass = passField.getText();

                VBox userPane = userPane(name, pass, title);
                addUser(userPane, usersStack);

                if ("Admin".equals(title)) {
                    // Create and add an Admin user
                    Admin admin = new Admin(name, pass);
                    VBox adminPane = userPane(name, pass, "Admin");
                    addUser(adminPane, usersStack);
                    // Add the Admin user to the User.users list
                    User.users.add(admin);
                } else if ("Receptionist".equals(title)) {
                    // Create and add a Receptionist user
                    Receptionist receptionist = new Receptionist(name, pass);
                    VBox receptionistPane = userPane(name, pass, "Receptionist");
                    addUser(receptionistPane, usersStack);
                    // Add the Receptionist user to the User.users list
                    User.users.add(receptionist);
                } else if ("Guest".equals(title)) {
                    // Create and add a Guest user
                    Guest guest = new Guest(name, pass);
                    VBox guestPane = userPane(name, pass, "Guest");
                    addUser(guestPane, usersStack);
                    // Add the Guest user to the User.users list
                    User.users.add(guest);
                }

            }
        });
        adminStack.add(addUserButton, 1, 4);

        // da el Add Movie Button 
        Button addMovieButton = new Button("Add Movie");
        adminStack.add(addMovieButton, 2, 4);

        Dialog<ButtonType> addMovieDialog = new Dialog<>();
        addMovieDialog.setTitle("Add Movie");

        VBox MovieContent = new VBox();
        TextField titleField = new TextField("Name");
        TextField categoryField = new TextField("Category");
        TextField MovieIDField = new TextField("ID");
        TextField MoviePriceField = new TextField("Price");
        MovieContent.getChildren().addAll(titleField, categoryField, MovieIDField, MoviePriceField);
        Button choosePosterButton = new Button("Browse...");
        TextField posterPathField = new TextField();
        posterPathField.setEditable(false);
        choosePosterButton.setOnAction(e1 -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                posterPathField.setText(selectedFile.getAbsolutePath());
            }
        });
        MovieContent.getChildren().addAll(choosePosterButton, posterPathField);
        addMovieDialog.getDialogPane().setContent(MovieContent);

        addMovieButton.setOnAction(e -> {
            Optional<ButtonType> Movieresult = addMovieDialog.showAndWait();
            if (Movieresult.isPresent() && Movieresult.get() == ButtonType.OK) {

                String name = titleField.getText();  // Re-access name and title
                String cat = categoryField.getText();
                int price = Integer.parseInt(MoviePriceField.getText());
                Movies mov = new Movies(name, price, cat, posterPathField.getText());
                // Create the user pane using the retrieved name and title
                Image s = new Image("file:" + posterPathField.getText());
                VBox moviePane = moviePane(posterPathField.getText(), name, cat);
                addMovie(moviePane, moviesStack);
            }
            // Poster image selection
        });

        // Confirm button with action
        Button confirmMovieButton = new Button("Add");
        confirmMovieButton.setOnAction(e2 -> {
            // Close the dialog
            addMovieDialog.setResult(ButtonType.OK);  // Set the result to OK
            addMovieDialog.close();
        });
        MovieContent.getChildren().add(confirmMovieButton);
        // Set the dialog content and show it
        addMovieDialog.getDialogPane().setContent(MovieContent);

        // button el add show
        Button addShowButton = new Button("Add Show");
        adminStack.add(addShowButton, 3, 4);

        Dialog<ButtonType> addShowDialog = new Dialog<>();
        addMovieDialog.setTitle("Add Movie");

        VBox ShowContent = new VBox();
        TextField showTitle = new TextField("Name");
        TextField showStart = new TextField("Start time");
        TextField showDuration = new TextField("Duration");
        TextField showHall = new TextField("Hall ID");
        ShowContent.getChildren().addAll(showTitle, showStart, showDuration, showHall);
        Button showPoster = new Button("Browse...");
        TextField posterPath = new TextField();
        posterPath.setEditable(false);
        showPoster.setOnAction(e1 -> {
            FileChooser iChooser = new FileChooser();
            File selectedi = iChooser.showOpenDialog(null);
            if (selectedi != null) {
                posterPath.setText(selectedi.getAbsolutePath());
            }
        });

        ShowContent.getChildren().addAll(showPoster, posterPath);
        addShowDialog.getDialogPane().setContent(ShowContent);

        addShowButton.setOnAction(e -> {
            Optional<ButtonType> Showresult = addShowDialog.showAndWait();
            if (Showresult.isPresent() && Showresult.get() == ButtonType.OK) {
                String name = showTitle.getText();  // Re-access name and title
                String startt = showStart.getText();
                double duration = Double.parseDouble(showDuration.getText());
                int hallID = Integer.parseInt(showHall.getText());
                String cat = categoryField.getText();
                System.out.println(name);
                boolean isMovie = Movies.isMovie(name);

                if (isMovie) {
                    Movies newMov = Movies.getMoviebyName(name);
                    System.out.println(newMov.getMoviePrice());
                    bakr.addShow(new Shows(name, startt, duration, newMov, hallID));
                    Image s = new Image("file:" + posterPathField.getText());
                    VBox showpane = showPane(s, name, hallID, cat);
                    addShow(showpane, showstack);
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Movie Unavailable");
                    alert.setContentText("Sorry, the movie you selected is not currently available.");
                    alert.showAndWait();

                }

                // Create the user pane using the retrieved name and title
            }
            // Poster image selection
        });

        // Confirm button with action
        Button confirmShowButton = new Button("Add");
        confirmShowButton.setOnAction(e2 -> {
            // Close the dialog
            addShowDialog.setResult(ButtonType.OK);  // Set the result to OK
            addShowDialog.close();
        });
        ShowContent.getChildren().add(confirmShowButton);
        // Set the dialog content and show it
        addShowDialog.getDialogPane().setContent(ShowContent);

        return adminStack;
    }

    GridPane createUsersStack() {
        GridPane usersStack = new GridPane();
        usersStack.setBackground(new Background(new BackgroundFill(Color.web("#222b31"), CornerRadii.EMPTY, Insets.EMPTY)));
        for (User user : User.users) {
            VBox userPane = userPane(user.getUsername(), user.getPass(), user.getClass().getName().substring(18));
            addUser(userPane, usersStack);
        }

        return usersStack;
    }

    // ll tarekh de 
    private VBox moviePane(String photopath, String name, String Category) {
        VBox pane = new VBox();
        Image photo = new Image("file:" + photopath);
        ImageView movieView = new ImageView(photo);
        movieView.setFitHeight(142);
        movieView.setFitWidth(100);
        Label nameL = new Label(name);
        nameL.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        nameL.setTextFill(Color.WHITE);

        Label catL = new Label(Category);
        catL.setTextFill(Color.GREY);
        pane.getChildren().addAll(movieView, nameL, catL);

        return pane;
    }

    private VBox showPane(Image photo, String name, int HallID, String cat) {
        VBox pane = new VBox();
        ImageView movieView = new ImageView(photo);
        movieView.setFitHeight(142);
        movieView.setFitWidth(100);
        Label nameL = new Label(name);
        nameL.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        nameL.setTextFill(Color.WHITE);

        Label catL = new Label(cat);
        catL.setTextFill(Color.GREY);
        Label HallL = new Label(Integer.toString(HallID));
        TextArea slots = new TextArea("Time:12:00 PM\nTime:3:00 AM\nTime:6:00 AM\nTime:9:00 AM\nTime:12:00 PM\n");
        slots.setEditable(false);
        slots.setMaxWidth(100);
        slots.setMaxHeight(90);
        slots.setStyle("-fx-control-inner-background: #BCB88A;");

        pane.getChildren().addAll(movieView, nameL, catL, HallL, slots);

        return pane;
    }

    private VBox userPane(String name, String Pass, String Title) {
        VBox pane = new VBox();
        Image userphoto = new Image("file:E:\\oopcinema\\user.png");
        ImageView movieView = new ImageView(userphoto);
        movieView.setFitHeight(120);
        movieView.setFitWidth(120);
        Label nameL = new Label(name);
        nameL.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        nameL.setTextFill(Color.WHITE);

        Label catL = new Label(Title);
        catL.setTextFill(Color.GREY);
        pane.getChildren().addAll(movieView, nameL, catL);

        return pane;
    }

    void addMovie(VBox moviePane, GridPane movieStack) {

        if (colN == 3) {
            colN = 0;
        }
        int size = movieStack.getChildren().size();
        if (size % 3 == 0) {
            rowN++;
        }
        movieStack.add(moviePane, colN++, rowN);
    }

    void addShow(VBox showPane, GridPane showStack) {
        if (colS == 3) {
            colS = 0;
        }
        int size = showStack.getChildren().size();
        if (size % 3 == 0) {
            rowS++;
        }
        showStack.add(showPane, colS++, rowS);
    }

    void addUser(VBox userPane, GridPane usersStack) {
        if (colU == 3) {
            colU = 0;
        }
        int size = usersStack.getChildren().size();
        if (size % 3 == 0) {
            rowU++;
        }
        usersStack.add(userPane, colS++, rowS);
        // nodeCounter++;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        ReadFromFile();
        Movies.soutMovies();
        Halls hall1 = new Halls(1, HallType.bakr);
        Halls hall2 = new Halls(2, HallType.ali);
        Halls hall3 = new Halls(3, HallType.Standard);

        launch(args);
        writeToFile();
    }

    public void login() {
        BorderPane header = new BorderPane();
        Label title = new Label("Cinema Booking System");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.setCenter(title);

        GridPane loginForm = new GridPane();
        loginForm.setHgap(10);
        loginForm.setVgap(10);
        loginForm.setPadding(new Insets(20));
        loginForm.setBackground(new Background(new BackgroundFill(Color.web("BCB88A"), CornerRadii.EMPTY, Insets.EMPTY)));

        Label usernameL = new Label("Username:");
        TextField usernameF = new TextField("bakr");
        Label passwordL = new Label("Password:");
        PasswordField passwordF = new PasswordField();
        ChoiceBox<String> userTypeChoice = new ChoiceBox<>();
        userTypeChoice.getItems().addAll("Admin", "Receptionist", "Guest");
        userTypeChoice.setValue("Admin");
        Button loginButton = new Button("Login");
        Button guestLoginButton = new Button("Continue as Guest");

        guestLoginButton.setOnAction(e -> {
            Guest("Guest");
        });

        loginForm.add(usernameL, 0, 0);
        loginForm.add(usernameF, 1, 0);
        loginForm.add(passwordL, 0, 1);
        loginForm.add(passwordF, 1, 1);
        loginForm.add(userTypeChoice, 1, 2);
        loginForm.add(loginButton, 1, 3);
        loginForm.add(guestLoginButton, 1, 4);

        HBox options = new HBox();
        options.setSpacing(10);
        options.setAlignment(Pos.CENTER_RIGHT);
        Button forgotPasswordLink = new Button("Forgot Password?");
        Button createAccountLink = new Button("Create New Account");
        forgotPasswordLink.setTextFill(Color.BLUE);
        createAccountLink.setTextFill(Color.BLUE);
        forgotPasswordLink.setBackground(Background.EMPTY);
        createAccountLink.setBackground(Background.EMPTY);
        options.getChildren().addAll(forgotPasswordLink, createAccountLink);

        forgotPasswordLink.setOnAction(e -> {
            TextInputDialog xy = new TextInputDialog();
            xy.setContentText(null);
            xy.setHeaderText("Please enter your phone number to send you the code");
            xy.show();
        });

        createAccountLink.setOnAction(e -> {
            Dialog<ButtonType> createAccountDialog = new Dialog<>();
            createAccountDialog.setTitle("Create Account");

            ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
            createAccountDialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

//            TextField usernameF = new TextField();
            //          PasswordField passwordF = new PasswordField();
            grid.add(new Label("Username:"), 0, 0);
            grid.add(usernameF, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(passwordF, 1, 1);

            // Add the combo box for selecting the user type
            ComboBox<String> userType = new ComboBox<>();
            userType.getItems().addAll("Admin", "Receptionist", "Guest");
            userType.setValue("Guest");
            grid.add(new Label("User Type:"), 0, 2);
            grid.add(userType, 1, 2);

            createAccountDialog.getDialogPane().setContent(grid);

            Optional<ButtonType> res = createAccountDialog.showAndWait();
            if (res.isPresent() && res.get() == ButtonType.OK) {
                String un = usernameF.getText();
                String ps = passwordF.getText();
                String Type = userType.getValue();

                createAccountDialog.close();

                if ("Admin".equals(Type)) {
                    new Admin(un, ps);
                } else if ("Receptionist".equals(Type)) {
                    new Receptionist(un, ps);
                } else if ("Guest".equals(Type)) {
                    Guest.guestss.add(new Guest(un, ps));
                }
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Account Created");
            alert.setHeaderText(null);
            alert.setContentText("Account Created Successfully");
            alert.showAndWait();
            Guest.viewallguests();
        });

        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(header, loginForm, options);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.web("BCB88A"), CornerRadii.EMPTY, Insets.EMPTY)));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);

        Scene login = new Scene(mainLayout, 500, 400);
        primaryStage.setScene(login);
        primaryStage.setTitle("Cinema Booking System - Login");
        primaryStage.show();

        loginButton.setOnAction(e -> {
            String username = usernameF.getText();
            String password = passwordF.getText();
            String userType = userTypeChoice.getValue();

            if (userType.equals("Admin")) {
                if (Admin.isAdmin(username)) {
                    if (User.login(username, password)) {
                        primaryStage.setScene(admin(username));
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null); // Remove default header text
                        alert.setContentText("Wrong password");
                        alert.showAndWait();
                    }
                }
            } else if (userType.equals("Receptionist")) {
                if (Receptionist.isRecep(username)) {
                    if (User.login(username, password)) {
                        Guest(username);
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null); // Remove default header text
                        alert.setContentText("Wrong password");
                        alert.showAndWait();
                    }
                }
            } else if (userType.equals("Guest")) {
                if (Guest.isClient(username)) {
                    if (User.login(username, password)) {
                        Guest(username);
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null); // Remove default header text
                        alert.setContentText("Wrong password");
                        alert.showAndWait();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid username or password");
                alert.setContentText("Please check your credentials and try again.");
                alert.showAndWait();
            }
        });

    }

    private BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Movie Analysis");
        xAxis.setLabel("Movies");
        yAxis.setLabel("Value");
        XYChart.Series<String, Number> bookingsSeries = new XYChart.Series<>();
        bookingsSeries.setName("Bookings");
        XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
        revenueSeries.setName("Revenue");
        for (Movies movie : Movies.movieMap.values()) {
            bookingsSeries.getData().add(new XYChart.Data<>(movie.getMovieName(), movie.getBookings()));
            revenueSeries.getData().add(new XYChart.Data<>(movie.getMovieName(), movie.getRevenue()));
        }
        barChart.getData().addAll(bookingsSeries, revenueSeries);
        barChart.setBarGap(10);
        barChart.setCategoryGap(10);
        return barChart;
    }

    

    private BarChart<String, Number> createShowsBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Shows Analysis");
        xAxis.setLabel("Shows");
        yAxis.setLabel("Value");

        XYChart.Series<String, Number> bookingsSeries = new XYChart.Series<>();
        bookingsSeries.setName("Bookings");

        XYChart.Series<String, Number> revenueSeries = new XYChart.Series<>();
        revenueSeries.setName("Revenue");

        for (Shows show : Shows.allshows) {
            bookingsSeries.getData().add(new XYChart.Data<>(show.getName(), show.getShowsBookings()));
            revenueSeries.getData().add(new XYChart.Data<>(show.getName(), show.getPrice() * show.getShowsBookings()));
        }

        barChart.getData().addAll(bookingsSeries, revenueSeries);
        barChart.setBarGap(10);
        barChart.setCategoryGap(50);

        return barChart;
    }

    private void showMoviesReports() {
        //System.out.println("Showing Movie Reports");
        Stage moviesReportsStage = new Stage();
        VBox reportsContainer = new VBox();
        VBox reportsTable = new Movies().viewReports();
        BarChart<String, Number> barChart = createBarChart();
        reportsContainer.getChildren().addAll(reportsTable, barChart);
        Scene moviesReportsScene = new Scene(reportsContainer, 800, 600);
        moviesReportsStage.setScene(moviesReportsScene);
        moviesReportsStage.setTitle("Movies Reports");
        moviesReportsStage.show();
    }

    private void showShowsReports() {
        Stage showsReportsStage = new Stage();
        VBox reportsContainer = new VBox();
        VBox reportsTable = new Shows().viewReports();
        BarChart<String, Number> showBarChart = createShowsBarChart();
        reportsContainer.getChildren().addAll(reportsTable, showBarChart);
        Scene showsReportsScene = new Scene(reportsContainer, 800, 600);
        showsReportsStage.setScene(showsReportsScene);
        showsReportsStage.setTitle("Shows Reports");
        showsReportsStage.show();
    }

// Add these lines to set the action event handlers
    public static void ReadFromFile() throws ClassNotFoundException, IOException {
//        FileReaderMethods.readUsersFromFile();
//        FileReaderMethods.readBookingsFromFile();
//        FileReaderMethods.readHallsFromFile();
//          FileReaderMethods.readMoviesFromFile();
//                FileReaderMethods.readShowsFromFile();
        File file = new File("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\users.dat");
        if (file.length() == 0) {
            System.out.println(file.getName() + " is empty");
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            try {
                while (true) {
                    Object obj = objectInputStream.readObject();

                    if (obj instanceof User) {
                        User.users.add((User) obj);
                    } else if (obj instanceof Movies) {
                        Movies.getMovies().add((Movies) obj);
                    } else if (obj instanceof Shows) {
                        Shows.allshows.add((Shows) obj);
                    } else if (obj instanceof Halls) {
                        Halls.Halls.add((Halls) obj);
                    } else if (obj instanceof Booking) {
                        Booking.allbookings.add((Booking) obj);
                    } else {
                        System.out.println("Unexpected object type in " + file.getName());
                    }
                }
            } catch (EOFException ex) {
                System.out.println("Finished reading " + file.getName());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void writeToFile() {
        User.writeToFile();
        Movies.writeToFile();
        Shows.writeToFile();
        Booking.writeToFile();
        Halls.writeToFile();
    }
}
