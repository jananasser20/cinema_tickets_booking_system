package javaapplication14;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileReaderMethods {

    private static <T> void readFromFile(String filePath, ArrayList<T> list) throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (file.length() == 0) {
            System.out.println(file.getName() + " is empty");
            return;
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        try {
            while (true) {
                Object obj = objectInputStream.readObject();

if (list != null && obj != null && list.getClass().getComponentType().isInstance(obj)) {
                    list.add((T) obj);
                } else {
                    System.out.println("Unexpected object type in " + file.getName());
                }
            }
        } catch (EOFException ex) {
            System.out.println("Finished reading " + file.getName());
        }

        objectInputStream.close();
        fileInputStream.close();
    }

    public static void readUsersFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        readFromFile("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\users.dat", User.users);
    }

    public static void readBookingsFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        readFromFile("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\Booking.dat", Booking.allbookings);
    }

    public static void readMoviesFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        readFromFile("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\movies.dat", Movies.getMovies());
    }

    public static void readHallsFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        readFromFile("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\halls.dat", Halls.Halls);
    }

    public static void readShowsFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        readFromFile("E:\\Documents\\NetBeansProjects\\JavaApplication14\\src\\javaapplication14\\shows.dat", Shows.allshows);
    }
}
