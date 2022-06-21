package concurrent.future;

import java.util.concurrent.TimeUnit;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        FutureService<String, Integer> service2 = FutureService.newService();
        Future<Integer> integerFuture = service2.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return input.length();
        }, "YangJiaHong",System.out::println);

        System.out.println(integerFuture.get());
    }
}
