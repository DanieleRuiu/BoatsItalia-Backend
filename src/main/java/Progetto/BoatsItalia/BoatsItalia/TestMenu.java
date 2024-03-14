package Progetto.BoatsItalia.BoatsItalia;

import Progetto.BoatsItalia.BoatsItalia.service.AuthService;

import java.util.Scanner;

public class TestMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Test BoatsItaliaApplication");
            System.out.println("2. Test AuthService");
            System.out.println("3. Test CategoryService");
            System.out.println("4. Test CountryService");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:



                    break;
                case 2:


                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);

        scanner.close();
    }
}