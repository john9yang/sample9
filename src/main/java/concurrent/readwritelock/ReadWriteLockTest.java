package concurrent.readwritelock;

import static java.lang.Thread.currentThread;

public class ReadWriteLockTest {

    private final static String test = "Thisistheexampleforreadwritelock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(10);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for(int index = 0; index < test.length(); index++){
                    try{
                        char c = test.charAt(index);
                        shareData.write(c);
                        System.out.println(currentThread() + " write " + c);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while(true) {
                    try{
                        System.out.println(currentThread()+" read " +new String(shareData.read()));
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
