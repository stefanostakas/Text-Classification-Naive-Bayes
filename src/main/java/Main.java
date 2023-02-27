import com.stefanostakas.NaiveBayes;
import com.stefanostakas.TrainingData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Display app menu
        int choice = Displaymenu();
    }

    public static int Displaymenu() {

        Scanner scanner = new Scanner(System.in);

        int choice=-1;

        NaiveBayes NB = new NaiveBayes();
        TrainingData TD = null;
        System.out.println("Welcome to Naive Bayes Text Classification!");

        while (choice != 0) {

        System.out.println("----------------------------");
        System.out.println("Please choose a mode:");
        System.out.println("1. Training mode");
        System.out.println("2. Testing mode");
        System.out.println("0. Exit");
        System.out.println("----------------------------");

            choice = scanner.nextInt();

            // Training mode
            if (choice == 1) {

                System.out.println("Training mode selected.");

                trainForm();

                 TD = NB.TrainingMode();


            } else if (choice == 2) {

                System.out.println("Testing mode selected.");

                // If you have trained the algorithm
                if (TD != null) {

                    // Testing call based on the training TD
                    NB.TestingMode(NB.likelihood(TD), NB.getPosProb(TD), NB.getNegProb(TD));
                }else{

                    System.out.println("First you have to train the algorithm!");
                }

            } else if (choice == 0) {
                System.out.println("Exiting program.");
            } else {
                System.out.println("Invalid choice. Please choose again.");
            }

            choice=-1;
        }

        scanner.close();


        return choice;
    }

    public static void trainForm() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Record Type: (5 words or less)");
        String recordType = scanner.nextLine();

        System.out.println("Category Names: (separated by comma)");
        String categoryNames = scanner.nextLine();

        System.out.println("Program Capabilities: (brief description)");
        String programCapabilities = scanner.nextLine();

        System.out.println("----------------------------");

        System.out.println("Training information collected:");
        System.out.println("Record Type: " + recordType);
        System.out.println("Category Names: " + categoryNames);
        System.out.println("Program Capabilities: " + programCapabilities);

        System.out.println("----------------------------");

    }




}
