package concurrent.future;

import java.util.concurrent.TimeUnit;

public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();

        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("I am finish done.");
        });

        future.get();

        FutureService<String, Integer> service2 = FutureService.newService();
        Future<Integer> integerFuture = service2.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return input.length();
        }, "YangJiaHong");

        System.out.println(integerFuture.get());
    }
}
