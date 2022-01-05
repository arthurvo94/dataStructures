import java.util.Iterator;
import data_structures.*;
/*  Arthur Vo
    cssc4015
*/

public class PhoneNumber implements Comparable<PhoneNumber> {
    String areaCode, prefix, number;
    String phoneNumber;

    // Constructor.  Creates a new PhoneNumber instance.  The parameter
    // is a phone number in the form xxx-xxx-xxxx, which is area code -
    // prefix - number.  The phone number must be validated, and an
    // IllegalArgumentException thrown if it is invalid.
    public PhoneNumber(String n){
        verify(n);

        phoneNumber = n;
        //splits phone number into area code, prefix, number
        areaCode = n.substring(0,3);
        prefix = n.substring(4,7);
        number = n.substring(8);
    }

    // Follows the specifications of the Comparable Interface.
    public int compareTo(PhoneNumber n){
        return phoneNumber.compareTo(n.phoneNumber);
    }

    // Returns an int representing the hashCode of the PhoneNumber.
    public int hashCode(){
        //uses string hash code function
        return phoneNumber.hashCode();
    }

    // Private method to validate the Phone Number.  Should be called
    // from the constructor.
    private void verify(String n){
        //verifies that fourth and eighth characters are dashes
        //following xxx-xxx-xxxx
        if(n == null || n.charAt(3) != '-' || n.charAt(7) != '-')
            throw new IllegalArgumentException();
    }

    // Returns the area code of the Phone Number.
    public String getAreaCode(){
        return areaCode;
    }

    // Returns the prefix of the Phone Number.
    public String getPrefix(){
        return prefix;
    }

    // Returns the last four digits of the number.
    public String getNumber(){
        return number;
    }

    // Returns the Phone Number.
    public String toString(){
        return phoneNumber;
    }
}