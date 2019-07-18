import entity.Employee;
import org.junit.Test;

import java.util.Arrays;

public class EmployeeGenerateTest {

    @Test
    public void generateEmployees(){
        Employee[] employees = Employee.randomlyGenerate(50);
        Arrays.stream(employees).forEach(System.out::println);
    }
}