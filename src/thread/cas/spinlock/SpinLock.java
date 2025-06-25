package thread.cas.spinlock;


import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;

public class SpinLock {

    private volatile AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득을 시도");

        while(!lock.compareAndSet(false, true)) {
            // 락을 획득할 때까지 스핀대기(바쁜 대기) 한다
            log("락 획득 실패 - 스핀 대기");
        }

        log("락 획득 완료");
    }

    public void unlock() {
        lock.set(false); // 이미 원자적 연산이라서
        log("락 반납 완료");
    }
}
