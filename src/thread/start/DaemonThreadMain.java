package thread.start;

public class DaemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() 메소드 start");

        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // 데몬 쓰레드 여부
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + ": main() 메소드 end");
    }

    static class DaemonThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run() 메소드 start");

            try {
                Thread.sleep(10000); // 10초간 실행된다고 가정
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": run() 메소드 end");
        }
    }
}
