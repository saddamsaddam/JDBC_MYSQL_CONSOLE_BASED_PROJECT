package org.example;
import java.util.List;
import java.util.Scanner;

public class UserMenu {
    public static void main(String[] args) {
        DataLoader.loadData("funn.txt");
        showMenu();
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. List all findings");
            System.out.println("2. List all persons");
            System.out.println("3. List all museums");
            System.out.println("4. List find by year");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listFindings();
                    break;
                case 2:
                    listPersons();
                    break;
                case 3:
                    listMuseums();
                    break;
                case 4:
                    System.out.println("Enter Year:");
                    int year=scanner.nextInt();
                    listFindByYear(year);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private static void listFindByYear(int year){
        DataLoader.listFindByYear(year);
    }
    private static void listFindings() {
        DataLoader.listFindings();
    }


    private static void listPersons() {
        List<Person> dataList=DataLoader.personList();
        dataList.forEach(e->{
            System.out.println("Id: " + e.getId());
            System.out.println("Name: " + e.getNavn());
            System.out.println("Phone: " +e.getTlf());
            System.out.println("Email: " + e.getEPost());
            System.out.println("----------------------------------");
        });
    }

    private static void listMuseums() {
        List<Museum> dataList=DataLoader.museumList();
        dataList.forEach(e->{
            System.out.println("Id: " + e.getId());
            System.out.println("Name: " + e.getNavn());
            System.out.println("Location: " +e.getSted());
            System.out.println("----------------------------------");
        });

    }
}
