package thread.start;

import static util.MyLogger.log;

// 익명 클래스
public class InnerRunnableMainV2 {

    public static void main(String[] args) {
        log("main() 메소드 start");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log("run() 메소드");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        log("main() 메소드 end");
    }
}
