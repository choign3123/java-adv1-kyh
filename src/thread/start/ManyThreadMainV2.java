package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV2 {

    public static void main(String[] args) {
        log("main() 메소드 start");

        HelloRunnable runnable = new HelloRunnable();

        for(int i=0; i<100; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
        }

        log("main() 메소드 end");
    }
}
