

package models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false; 
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
               ", Status: " + (isIssued ? "Issued" : "Available");
    }
}

package services;

import models.Book;
import java.util.ArrayList;

public class LibraryService {
    private ArrayList<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean issueBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (!book.isIssued()) {
                    book.setIssued(true);
                    System.out.println("Book issued successfully: " + book.getTitle());
                    return true;
                } else {
                    System.out.println("Book is already issued: " + book.getTitle());
                    return false;
                }
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
        return false;
    }

    public boolean returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (book.isIssued()) {
                    book.setIssued(false);
                    System.out.println("Book returned successfully: " + book.getTitle());
                    return true;
                } else {
                    System.out.println("Book was not issued: " + book.getTitle());
                    return false;
                }
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
        return false;
    }

    public boolean removeBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                System.out.println("Book removed successfully: " + book.getTitle());
                return true;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
        return false;
    }
}

package main;

import models.Book;
import services.LibraryService;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);

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
                case 1: 
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    libraryService.addBook(new Book(bookId, title, author));
                    break;

                case 2: 
                    System.out.print("Enter the ID of the book to remove: ");
                    int removeId = scanner.nextInt();
                    libraryService.removeBook(removeId);
                    break;

                case 3: 
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

                case 4: 
                    System.out.print("Enter the ID of the book to issue: ");
                    int issueId = scanner.nextInt();
                    libraryService.issueBook(issueId);
                    break;

                case 5: 
                    System.out.print("Enter the ID of the book to return: ");
                    int returnId = scanner.nextInt();
                    libraryService.returnBook(returnId);
                    break;

                case 6: 
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
