import data_structures.*;
import java.util.Iterator;
import java.io.*;
/*  Arthur Vo
    cssc4015
*/
@SuppressWarnings("unchecked")

public class PhoneBook {
    Hashtable<PhoneNumber, String> dictionary;

    // Constructor.  There is no argument-less constructor, or default size
    public PhoneBook(int maxSize){
        dictionary = new Hashtable<PhoneNumber, String>(maxSize);
    }

    // Reads PhoneBook data from a text file and loads the data into
    // the PhoneBook.  Data is in the form "key=value" where a phoneNumber
    // is the key and a name in the format "Last, First" is the value.
    public void load(String filename){
        try{
            File file = new File(filename);
            BufferedReader input = new BufferedReader(new FileReader(file));

            String line = input.readLine();
            while(line != null){
                //reads each line and splits into key, value
                String[] in = line.split("=");
                addEntry(new PhoneNumber(in[0]), in[1]);
                line = input.readLine();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found exception");
        }
        catch(Exception e){
            System.out.println("Exception");
        }
    }

    // Returns the name associated with the given PhoneNumber, if it is
    // in the PhoneBook, null if it is not.
    public String numberLookup(PhoneNumber number){
        return dictionary.getValue(number);
    }

    // Returns the PhoneNumber associated with the given name value.
    // There may be duplicate values, return the first one found.
    // Return null if the name is not in the PhoneBook.
    public PhoneNumber nameLookup(String name){
        return dictionary.getKey(name);
    }

    // Adds a new PhoneNumber = name pair to the PhoneBook.  All
    // names should be in the form "Last, First".
    // Duplicate entries are *not* allowed.  Return true if the
    // insertion succeeds otherwise false (PhoneBook is full or
    // the new record is a duplicate).  Does not change the datafile on disk.
    public boolean addEntry(PhoneNumber number, String name){
        return dictionary.add(number, name);
    }

    // Deletes the record associated with the PhoneNumber if it is
    // in the PhoneBook.  Returns true if the number was found and
    // its record deleted, otherwise false.  Does not change the datafile on disk.
    public boolean deleteEntry(PhoneNumber number){
        return dictionary.delete(number);
    }

    // Prints a directory of all PhoneNumbers with their associated
    // names, in sorted order (ordered by PhoneNumber).
    public void printAll(){
        Iterator<PhoneNumber> iterator = dictionary.keys();
        while (iterator.hasNext()) {
            PhoneNumber number = iterator.next();
            System.out.println(number + "/t" + dictionary.getValue(number));
        }

    }

    // Prints all records with the given Area Code in ordered
    // sorted by PhoneNumber.
    public void printByAreaCode(String code){
        Iterator<PhoneNumber> iterator = dictionary.keys();
        while(iterator.hasNext()){
            PhoneNumber number = iterator.next();
            //print records with given area code
            if(number.getAreaCode().equals(code))
                System.out.println(number + "/t" + dictionary.getValue(number));
        }
    }

    // Prints all of the names in the directory, in sorted order (by name,
    // not by number).  There may be duplicates as these are the values.
    public void printNames(){
        Iterator<String> iterator = dictionary.values();
        String[] names = new String[dictionary.size()];
        int i = 0;
        //fill array with names
        while(iterator.hasNext())
            names[i++] = iterator.next();
        //sort array of names
        names = insertionSort(names);

        //print names
        for(i = 0; i < names.length; i++){
            System.out.println(names[i]);
        }
    }

    //insertion sorting algorithm
    private String[] insertionSort(String[] array){
        String[] on = array;
        int in, out;
        String temp;

        for(out = 1; out < on.length; out++){
            temp = on[out];
            in = out;

            //shifts elements greater than temp to the right
            while(in > 0 && (on[in-1].compareTo(temp) > 0)){
                on[in] = on[in-1];
                in--;
            }
            on[in] = temp;
        }

        return array;
    }
}