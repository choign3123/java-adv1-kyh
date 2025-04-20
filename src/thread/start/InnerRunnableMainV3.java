package thread.start;

import static util.MyLogger.log;

// 익명 클래스
public class InnerRunnableMainV3 {

    public static void main(String[] args) {
        log("main() 메소드 start");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log("run() 메소드");
            }
        });
        thread.start();

        log("main() 메소드 end");
    }
}
