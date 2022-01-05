import data_structures.*;
import java.util.Iterator;
import java.io.*;

public class Driver{
    public static void main(String[] args){
        PhoneBook phonebook = new PhoneBook(20);

        phonebook.load("numbers.txt");
    }
}