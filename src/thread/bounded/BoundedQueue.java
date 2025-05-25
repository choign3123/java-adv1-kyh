package thread.bounded;

/**
 * 버퍼 역할을 하는 큐의 인터페이스
 */
public interface BoundedQueue {

    void put(String data);

    String take();
}
