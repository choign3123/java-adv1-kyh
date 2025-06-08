package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // java.lang.IllegalStateException: Queue full 예외 터짐
    }

    @Override
    public String take() {
        return queue.remove(); // java.util.NoSuchElementException 터짐
    }

    // 원칙적으로는 `synchronized`를 적용해야 한다.
    // 그러나 예제가 꼬이기 때문에 여기서는 임시로 뺐다.
    @Override
    public String toString() {
        return queue.toString();
    }
}
