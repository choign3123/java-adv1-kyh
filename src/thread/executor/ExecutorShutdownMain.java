package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("longTask", 100_000)); // 100초 대기

        es.execute(new Runnable() { // 인터럽트를 받을 수 없는 스레드
            @Override
            public void run() {
                log("무한 반복 시작 - 인터럽트를 받을 수 없는 스레드");
                while(true) {
                    // 무한 반복
                }
            }
        });

        es.execute(new Runnable() { // 인터럽트를 받을 수 있는 스레드
            @Override
            public void run() {
                log("무한 반복 시작 - 인터럽트를 받을 수 있는 스레드");
                while(true) {
                    if(Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    // 무한 반복
                }
                log("무한 반복 종료 - 인터럽트를 받을 수 있는 스레드");
            }
        });

        printState(es);

        log("== shutdown 시작 == ");
        shutdownAndAwaitTermination(es);
        log("== shutdown 완료 == ");
        printState(es);
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {
        es.shutdown(); // non-blocking. 새로운 작업을 받지 않는다. 처리 중이거나, 큐에 이미 대기중인 작업은 처리한다. 이후에 풀의 스레드를 종료한다.

        try {
            if(!es.awaitTermination(10, TimeUnit.SECONDS)) { // blocking. 이미 대기중인 작업들을 모두 완료할 때까지 10초 기다린다
                // 정상 종료가 너무 오래 걸리면...
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow();

                // 작업이 취소될 때까지 대기한다
                if(!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination() 을 호출하여 대기중인 현재 스레드(=여긴선 main 스레드)가 외부(=다른 스레드)로부터 인터럽트를 받은 경우
            log("InterruptedException 발생!");
            es.shutdownNow(); // awaitTermination() 를 더 기다리지 않고 지금 남아있는 작업이라도 강제 취소하자는 의미
        }
    }
}
