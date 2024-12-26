
import java.util.Date;
import java.text.SimpleDateFormat;

public class LibraryTransaction {
    private Patron patron;
    private Book book;
    private Date checkoutDate;
    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        this.patron = patron;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        calculateFine();
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void calculateFine() {
        // Date currentDate = new Date();
        if (returnDate.after(dueDate)) {
            long daysOverdue = (returnDate.getTime() - dueDate.getTime()) / (24 * 60 * 60 * 1000);
            if (daysOverdue >= 1 && daysOverdue <= 7) {
                fineAmount = daysOverdue * 1.00;
            } else if (daysOverdue >= 8 && daysOverdue <= 14) {
                fineAmount = daysOverdue * 2.00;
            } else if (daysOverdue > 14) {
                fineAmount = daysOverdue * 3.00;
            }
        } else {
            fineAmount = 0.00;
        }
    }

    public String getDescription() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d yyyy");
        if (returnDate == null) {
            return "Transaction Details: "+"\n"
                    + "\tPatron ID: " + this.patron.getPatronID()+"\n"
                    + "\tBook ISBN: "+this.book.getISBN()+"\n"
                    + "\tCheckout Date: "+dateFormat.format(this.checkoutDate)+"\n"
                    + "\tDue Date: "+dateFormat.format(this.dueDate)+"\n"
                    + "\tHaven't returned yet, "+"\n"
                    + "\tFine Amount: $"+this.getFineAmount()
                    + System.lineSeparator();
        } else {
            return "Transaction Details: "+"\n"
                    + "\tPatron ID: " + this.patron.getPatronID()+"\n"
                    + "\tBook ISBN: "+this.book.getISBN()+"\n"
                    + "\tCheckout Date: "+dateFormat.format(this.checkoutDate) +"\n"
                    + "\tDue Date: "+dateFormat.format(this.dueDate) +"\n"
                    + "\tReturn Date: "+ dateFormat.format(this.returnDate) + ", "+"\n"
                    + "\tFine Amount: $"+this.getFineAmount()
                    + System.lineSeparator();
        }

    }

    public Patron getPatron() {
        return this.patron;
    }

    public Book getBook() {
        return this.book;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public Date getDueDate() {
        return this.dueDate;
    }
}