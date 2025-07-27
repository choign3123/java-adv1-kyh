package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class RejectMainV3 {

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        es.submit(new RunnableTask("task1"));
        es.submit(new RunnableTask("task2"));
        log("main 스레드가 task2를 수행하느라 그동안 task 3,4를 생산을 못함..!");
        es.submit(new RunnableTask("task3"));
        es.submit(new RunnableTask("task4"));

        es.close();
    }
}
