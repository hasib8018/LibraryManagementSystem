package services;

import models.Book;
import java.util.ArrayList;

public class LibraryService {
    private ArrayList<Book> books;

    // Constructor
    public LibraryService() {
        books = new ArrayList<>();
    }

    // Add a new book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // Display all books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Search for a book by title
    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Issue (Lend) a book
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

    // Return a book
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

    // Remove a book by ID
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
