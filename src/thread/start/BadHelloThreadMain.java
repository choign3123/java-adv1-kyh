package thread.start;

public class BadHelloThreadMain {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread(); // 현재 쓰레드 반환
        System.out.println(thread.getName() + ": start"); // main 메스드 (X) main 쓰레드 (O)

        HelloThread helloThread = new HelloThread();
        System.out.println(thread.getName() + ": HelloThread의 start() 호출 전");
        helloThread.run(); // run() X -> start()를 호출해야 새로운 쓰레드
        System.out.println(thread.getName() + ": HelloThread의 start() 호출 후");

        System.out.println(thread.getName() + ": end");
    }
}
