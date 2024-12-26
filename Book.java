
import  common.Genre;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private Genre genre;
    private Integer publicationYear;
    private Integer numsOfCopyAvailable;

    public Book(String title, String author, Genre genre, Integer publicationYear, Integer numsOfCopyAvailable) {
        generateISBN(author,genre,publicationYear);
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numsOfCopyAvailable = numsOfCopyAvailable;
    }

    public void generateISBN(String author,Genre genre, Integer publicationYear){
        String[] words = author.split(" ");
        String authorInitial = "";
        for (String word : words) {
            authorInitial += word.charAt(0);
        }
        if (genre.ordinal()<10){
            this.ISBN = authorInitial+"-" +"0"+ this.getGenrePosition(genre)+"-" + publicationYear;
        } else if (genre.ordinal()>=10){
            this.ISBN = authorInitial+"-" + this.getGenrePosition(genre)+"-" + publicationYear;
        }
    }
    public String getGenrePosition(Genre genre){
        Integer a = genre.ordinal()+1;
        return Integer.toString(a);
    } 
    @Override
    public String toString(){
        return "ISBN: "+ISBN+", "
        +"Title: "+title+", "
        +"Author: "+author+", "
        +"Genre: "+genre+", "
        +"Publication Year: "+publicationYear+", "
        +"Number of Copies Available: "+numsOfCopyAvailable;
    }
    public String getISBN() {
        return this.ISBN;
    }

    public Integer getNumCopiesAvailable() {
        return this.numsOfCopyAvailable;
    }

    public void copiesAfterCheckout() {
        this.numsOfCopyAvailable += 1;
    }
}
