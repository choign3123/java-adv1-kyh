package thread.start;

import static util.MyLogger.log;

// 중첩 클래스
public class InnerRunnableMainV1 {

    public static void main(String[] args) {
        log("main() 메소드 start");

        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        log("main() 메소드 end");
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log("run() 메소드");
        }
    }
}
