import data_structures.*;

public class Tester {
    public static void main(String[] args) {
        final int SIZE = 3;

        Stack objs = new Stack(SIZE);
        objs.push(new String("SDSU"));
        objs.push(new Integer(6));
        objs.push(new Student("Manju", 876543210, 3.98));
        for (int i = 0; i < SIZE; i++)
            System.out.println(objs.pop());
    }
}

class Person {
    String name;
    int redId;

    public Person(String somebody, int id) {
        name = somebody;
        redId = id;
    }
    public String toString() {
        return name + ": " + redId;
    }
}
class Student extends Person {
    double gpa;

    public Student(String somebody, int id, double GPA) {
        super(somebody, id);
        gpa = GPA;
    }
    public String toString() {
        return super.toString() + ", " + gpa + " GPA";
    }
}