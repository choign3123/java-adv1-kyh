package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class IncrementPerformanceMain {

    private static final long COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        long startMs = System.currentTimeMillis();

        for(int i=0; i<COUNT; i++) {
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();
        log(incrementInteger.getClass().getSimpleName() + " ms: " + (endMs - startMs));
    }
}
