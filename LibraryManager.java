

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import common.DateUtils;

public class LibraryManager {
    private List<Book> books;
    private List<LibraryTransaction> transactions;
    private DateUtils dateUtils; 
    public LibraryManager() {
        dateUtils = new DateUtils();
        books = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedoutBooks = new ArrayList<>();
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getPatron().equals(patron) && transaction.getReturnDate() == null) {
                checkedoutBooks.add(transaction);
                System.out.println(transaction.getBook().getISBN());
            }
        }
        return checkedoutBooks;
    }

    public LibraryTransaction checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        if (patron.canCheckout()) {
            if (book.getNumCopiesAvailable() > 0) {
                LibraryTransaction transaction = new LibraryTransaction(patron, book, checkoutDate, dueDate);
                transactions.add(transaction);
                book.copiesAfterCheckout();
                patron.checkoutBook();
                System.out.println(patron.getPatronID() + " has checkout successful the book " + book.getISBN());
                return transaction;
            } else {
                System.out.println("Book " + book.getISBN() + " checkout of " + patron.getPatronID() + " No copies of the book are currently available.");
                return null;
            }
        } else {
            System.out.println(patron.getPatronID() + " has exceeded their checkout limit.");
            return null;
        }
    }

    public void returnBook(LibraryTransaction transaction, Date returnDate) {
        transaction.setReturnDate(returnDate);
        Book book = transaction.getBook();
        Patron patron = transaction.getPatron();
        book.copiesAfterCheckout();
        System.out.println("Book " + book.getISBN()+ " successfully returned by "+patron.getPatronID());
    }

    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        Date currentDate = dateUtils.getCurrentDate(); 
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getReturnDate() == null && currentDate.after(transaction.getDueDate())) {
                overdueBooks.add(transaction);
            }
        }
        return overdueBooks;
    }

    public void sort() {
        transactions.sort(Comparator.comparing(transaction -> transaction.getPatron().getPatronID()));
        for(LibraryTransaction transaction : transactions){
            System.out.println(transaction.getDescription());
        }
    }
}