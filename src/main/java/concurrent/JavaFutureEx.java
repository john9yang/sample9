package concurrent;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class JavaFutureEx {

    public static void main(String[] args) {
        var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Map<Integer, Future<BigInteger>>> resultList = new ArrayList<>();

        var random = new Random();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(100) + 10;
            var factorialCalculator = new FactorialCalculator(number);
            Map<Integer,Future<BigInteger>> result = new HashMap<>();
            result.put(number,executor.submit(factorialCalculator));
            resultList.add(result);
        }

        for (Map<Integer, Future<BigInteger>> pair : resultList) {
            var optional = pair.keySet().stream().findFirst();

            if (!optional.isPresent()) {
                return;
            }

            var key = optional.get();
            System.out.printf("Value is: %d%n", key);

            var future = pair.get(key);
            BigInteger result = null;
            try {
                result = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }catch(ArithmeticException e){
                System.out.println("here");
                e.printStackTrace();
            }
            var isDone = future.isDone();
            System.out.printf("Result is %d%n", result);
            System.out.printf("Task done: %b%n", isDone);
            System.out.println("--------------------");
        }

        executor.shutdown();
    }
}
