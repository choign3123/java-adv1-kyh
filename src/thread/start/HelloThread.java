package thread.start;

/**
 * 쓰레드를 생성하는 벙법 1
 * - Thread 상속받기
 * - run() 재정의하기
 */
public class HelloThread extends Thread {

    @Override
    public void run() {
        // 현재 쓰레드의 이름을 출력
        System.out.println(Thread.currentThread().getName() + ": run()");
    }
}
