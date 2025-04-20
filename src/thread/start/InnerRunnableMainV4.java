package thread.start;

import static util.MyLogger.log;

// 람다
public class InnerRunnableMainV4 {

    public static void main(String[] args) {
        log("main() 메소드 start");

        Thread thread = new Thread(() -> log("run() 메소드"));
        thread.start();

        log("main() 메소드 end");
    }
}
