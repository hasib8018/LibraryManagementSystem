package main;

import models.Book;
import services.LibraryService;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);

        // Adding initial books
        libraryService.addBook(new Book(101, "To Kill a Mockingbird", "Harper Lee"));
        libraryService.addBook(new Book(102, "1984", "George Orwell"));
        libraryService.addBook(new Book(103, "The Great Gatsby", "F. Scott Fitzgerald"));

        boolean exit = false;

        while (!exit) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display All Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    libraryService.addBook(new Book(bookId, title, author));
                    break;

                case 2: // Remove Book
                    System.out.print("Enter the ID of the book to remove: ");
                    int removeId = scanner.nextInt();
                    libraryService.removeBook(removeId);
                    break;

                case 3: // Search Book
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the title of the book to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = libraryService.searchBook(searchTitle);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 4: // Issue Book
                    System.out.print("Enter the ID of the book to issue: ");
                    int issueId = scanner.nextInt();
                    libraryService.issueBook(issueId);
                    break;

                case 5: // Return Book
                    System.out.print("Enter the ID of the book to return: ");
                    int returnId = scanner.nextInt();
                    libraryService.returnBook(returnId);
                    break;

                case 6: // Display All Books
                    libraryService.displayBooks();
                    break;

                case 7: // Exit
                    exit = true;
                    System.out.println("Exiting the library system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
