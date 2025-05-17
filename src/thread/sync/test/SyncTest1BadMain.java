package thread.sync.test;

public class SyncTest1BadMain {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread thread1 = new Thread(task); // 10,000번 호출
        Thread thread2 = new Thread(task); // 10,000번 호출
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("결과: " + counter.getCount());
    }

    static class Counter {

        private int count = 0;

        public void increment() {
            count = count + 1;
            // += 연산자를 사용하면 언제나 올바른 결과값이 나옴.
            // count++; 이건 count = count + 1;과 똑같음.
        }

        public int getCount() {
            return count;
        }
    }
}
