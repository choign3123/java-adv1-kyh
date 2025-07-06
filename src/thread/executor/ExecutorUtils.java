package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize(); // pool에 있는 스레드의 개수
            int active = poolExecutor.getActiveCount(); // 작업을 수행중인 스레드의 개수
            int queued = poolExecutor.getQueue().size(); // 큐에서 대기중인 작업의 개수
            long completedTask = poolExecutor.getCompletedTaskCount(); // 완료된 작업의 개수

            log("[pool = " + pool + ", active = " + active + ", queuedTasks = " + queued +
                    ", completedTask = " + completedTask + "]");
        } else {
            log(executorService);
        }
    }
}
