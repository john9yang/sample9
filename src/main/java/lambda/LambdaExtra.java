package lambda;

import entity.Employee;
import entity.Manager;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExtra {
    public static void main(String[] args) {
        Predicate<Manager> isGood = manager -> false;
        testEmployee(emp -> false);

        testEmployeeGeneric((Manager manager) -> isGood.test(manager));
        testEmployee2((Manager manager) -> isGood.test(manager));
    }

    public static Employee createEmployee(Supplier<? extends Employee> factory){
        return factory.get();
    }

    public static boolean testEmployee(Predicate<Employee> theTest){
        return theTest.test(new Employee("john",2000));
    }

    public static <E extends  Employee> boolean testEmployeeGeneric(Predicate<E> theTest){
        return theTest.test(null);
    }

    public static boolean testEmployee2(Predicate<? extends Employee> theTest){
        return theTest.test(null);
    }
}