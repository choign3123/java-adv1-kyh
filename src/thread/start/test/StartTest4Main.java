package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {
        PrintRunnable printARunnable = new PrintRunnable(1000, "A");
        PrintRunnable printBRunnable = new PrintRunnable(500, "B");

        Thread threadA = new Thread(printARunnable, "Thread-A");
        Thread threadB = new Thread(printBRunnable, "Thread-B");
        threadA.start();
        threadB.start();
    }

    static class PrintRunnable implements Runnable {

        private final long interval;
        private final String content;

        public PrintRunnable(long interval, String content) {
            this.interval = interval;
            this.content = content;
        }

        @Override
        public void run() {
            while(true) {
                log(content);

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
