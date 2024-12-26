
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.Genre;
import common.PatronType;

public class LibraryManProg {
    private static Date[] checkoutDate = new Date[] {
            new Date(2023 - 1900, Calendar.MARCH, 25),
            new Date(2023 - 1900, Calendar.MAY, 8),
            new Date(2023 - 1900, Calendar.JUNE, 1),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.AUGUST, 10)
    };

    private static Date[] dueDate = new Date[] {
            new Date(2023 - 1900, Calendar.APRIL, 25),
            new Date(2023 - 1900, Calendar.MAY, 10),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.JULY, 25),
            new Date(2023 - 1900, Calendar.SEPTEMBER, 20)
    };
    private static Date[] returnDate = new Date[] {
            new Date(2023 - 1900, Calendar.OCTOBER, 1),
            new Date(2023 - 1900, Calendar.OCTOBER, 2),
            new Date(2023 - 1900, Calendar.OCTOBER, 3),
            new Date(2023 - 1900, Calendar.OCTOBER, 4),
            new Date(2023 - 1900, Calendar.OCTOBER, 8)
    };

    public static void main(String[] args) {

        LibraryManager manager1 = new LibraryManager();
        // List<LibraryTransaction> transactions = new ArrayList<>();
        // Initialize at least 10 books in the library collection.
        Book book1 = new Book("The Lord of the Rings", "John Ronald Reuel Tolkien", Genre.ADVENTURE, 1954, 10);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", Genre.FICTION, 1960, 8);
        Book book3 = new Book("1984", "George Orwell", Genre.FICTION, 1949, 6);
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", Genre.ROMANCE, 1813, 4);
        Book book5 = new Book("The Great Gatsby", "Francis Scott Key Fitzgerald", Genre.HISTORY, 1925, 9);
        Book book6 = new Book("To the Lighthouse", "Virginia Woolf", Genre.FICTION, 1927, 5);
        Book book7 = new Book("The Catcher in the Rye", "Jerome David Salinger", Genre.FICTION, 1951, 3);
        Book book8 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", Genre.NON_FICTION, 2011, 8);
        Book book9 = new Book("The Alchemist", "Paulo Coelho", Genre.FANTASY, 1988, 7);
        Book book10 = new Book("Harry Potter", "Joanne Kathleen Rowling", Genre.FICTION, 1997, 6);
        manager1.addBook(book1);
        manager1.addBook(book2);
        manager1.addBook(book3);
        manager1.addBook(book4);
        manager1.addBook(book5);
        manager1.addBook(book6);
        manager1.addBook(book7);
        manager1.addBook(book8);
        manager1.addBook(book9);
        manager1.addBook(book10);
        // System.out.println(book2);
        // System.out.println(book3);
        // System.out.println(book4);
        // System.out.println(book5);
        // System.out.println(book6);

        // Initialize at least 3 patrons involving both regular and premium patrons.
        Patron patron1 = new Patron("John", "10/3/2000", "email1@gmail.com", 000000001, PatronType.PREMIUM);
        Patron patron2 = new Patron("Alys", "31/2/2004", "email2@gmail.com", 000000002, PatronType.REGULAR);
        Patron patron3 = new Patron("Jayce", "8/9/1999", "email3@gmail.com", 000000003, PatronType.REGULAR);
        // System.out.println(patron1);

        // Initialize and use to create 5 book transactions
        LibraryTransaction transaction1 = manager1.checkoutBook(patron1, book10, checkoutDate[0], dueDate[0]);
        LibraryTransaction transaction2 = manager1.checkoutBook(patron2, book5, checkoutDate[1], dueDate[1]);
        LibraryTransaction transaction3 = manager1.checkoutBook(patron2, book6, checkoutDate[2], dueDate[2]);
        LibraryTransaction transaction4 = manager1.checkoutBook(patron2, book7, checkoutDate[3], dueDate[3]);
        LibraryTransaction transaction5 = manager1.checkoutBook(patron3, book8, checkoutDate[4], dueDate[4]);

        // Print currently checked-out books
        System.out.println("Book that have been checked out:");
        manager1.getCheckedOutBooks(patron1);
        manager1.getCheckedOutBooks(patron2);
        manager1.getCheckedOutBooks(patron3);

        // Print list of the overdue books that are not returned yet
        System.out.println("\nList of overdue books that have not been returned:");
        List<LibraryTransaction> overdueBooks = manager1.getOverdueBooks();
        for (LibraryTransaction transaction : overdueBooks) {
            System.out.println(transaction.getBook().getISBN());
        }
        // Patron returns the book
        System.out.println("\nReturn book: ");
        manager1.returnBook(transaction5, returnDate[0]);
        manager1.returnBook(transaction3, returnDate[1]);
        manager1.returnBook(transaction2, returnDate[2]);
        manager1.returnBook(transaction4, returnDate[3]);
        manager1.returnBook(transaction1, returnDate[4]);

        // Sort transactions by patron ID
        System.out.println("\nSorted transactions: ");
        manager1.sort();

        System.out.println("Program completed successfully.");
        System.exit(0);
    }
}
