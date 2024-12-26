
import common.PatronType;

public class Patron {
    private static int idCounter = 1;
    private int bookCheckout = 0;
    private String patronID;
    private String name;
    private String dateOfBirth;
    private String email;
    private Integer phoneNumber;
    public PatronType patronType;
    public Patron(String name, String dateOfBirth, String email, Integer phoneNumber, PatronType patronType){
        generatePatronID();
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
    }
    public void generatePatronID(){
        if(1<=idCounter && idCounter<=9){
            this.patronID = "P00" + idCounter;
        }else if (10<=idCounter && idCounter<=99){
            this.patronID = "P0" + idCounter;
        }else if(idCounter == 100){
            this.patronID = "P100";
        }
        idCounter +=1;
    }
    @Override
    public String toString(){
        return "PatronID:"+patronID+", "
        +"Patron Name:"+ name+", "
        +"Date of birth:"+dateOfBirth+", "
        +"Email:"+email+", "
        +"Phone number:"+phoneNumber+", "
        +"Patron Type:"+ patronType;
    }
    public String getPatronID(){
        return this.patronID;
    }
    public PatronType getPatronType(){
        return this.patronType;
    }
    public Integer getNumBookCheckout(){
        return this.bookCheckout;
    }
    public void checkoutBook(){
        bookCheckout+=1;
    }
    public boolean canCheckout(){
        if(patronType == PatronType.PREMIUM){
            if(bookCheckout<=4){
                return true;
            } else { 
                return false;
            }
        } else if(patronType == PatronType.REGULAR){
            if(bookCheckout<=2){
                return true;
            } else { 
                return false;
            }
        }return true;
    }
}
