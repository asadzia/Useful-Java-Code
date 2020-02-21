import java.util.*;
import java.io.*;

// The employee class with a hashcode and equals implementation
// which allows hashmap values to be extracted by ID only

public class Employee {
    
    private int id;
    private String name;
    
    public Employee(String name, int id) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int x) {
        this.id = x;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName (String newName) {
        this.name = newName;
    }
    
    @Override
    public int hashCode() {
        final int primeNumber = 31;
        
        int hash = primeNumber + this.id;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        Employee e = (Employee)obj;
        
        if (e.id != this.id) {
            return false;
        }
        
        return true;
    }
    
    public static void main (String args[]) {
        
        Employee e1 = new Employee("Ben", 123);
        Employee e2 = new Employee("John", 132);
        Employee e3 = new Employee("Matt", 123);
        
        HashMap<Employee, String> mp = new HashMap<Employee, String>();
        
        mp.put(e1, "Ben");
        mp.put(e2, "John");
        
        e1.setName("BenNameChange");
        e2.setName("lala");
        
        System.out.println(mp.get(e1));
        System.out.println(mp.get(e2));
        System.out.println(mp.get(e3));
    }
    
}
