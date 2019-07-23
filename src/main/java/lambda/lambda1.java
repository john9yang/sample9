package lambda;

import entity.Employee;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.function.Consumer;

public class lambda1 {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Comparator<Employee> byName = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        //our first lambda expression
        Comparator<Employee> byNameLambda1 =
                (Employee a,Employee b) -> { return a.getName().compareTo(b.getName());};

        //removing parameter types
        Comparator<Employee> byNameLambda2 =
                (a,b) -> {return a.getName().compareTo(b.getName());};

        //removing braces and 'return'
        Comparator<Employee> byNameLambda3 =
                (a,b) -> a.getName().compareTo(b.getName());

        //Expression with no parameters
        Runnable r = () -> {
            System.out.println("A compact Runnable");
        };
        Thread t1 = new Thread(r);

        //No need to even mention Runnable
        Thread t2 = new Thread(() -> {
            System.out.println("An implicit Runnable!");

        //no need for braces here
        Thread t3 = new Thread(() -> System.out.println("An implicit Runnable!"));

        //expression with one parameter
            Consumer<String> lengthPrinter =
                    s -> System.out.println(s.length());
        });
    }
}
