package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시 runFlag = false"); // 그렇지만 sleep(3초) 때문에 task 스레드가 바로 중단되지 않음
        task.runFlag = false;
    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            while(runFlag) {
                log("작업 중");
                sleep(3000);
            }

            log("자원 정리");
            log("작업 종료");
        }
    }
}
