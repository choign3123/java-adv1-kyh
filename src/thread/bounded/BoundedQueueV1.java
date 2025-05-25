package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue{

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        if(queue.size() == max) {
            log("[put] 큐가 가득 참, 버림: " + data);
            return;
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if(queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    // 원칙적으로는 `synchronized`를 적용해야 한다.
    // 그러나 예제가 꼬이기 때문에 여기서는 임시로 뺐다.
    @Override
    public String toString() {
        return queue.toString();
    }
}
