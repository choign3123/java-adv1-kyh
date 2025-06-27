package thread.collection.java;

import java.util.HashMap;
import java.util.Map;

public class MapUnsafeExample {
    static Map<Integer, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread writer1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                map.put(i, "Thread1-" + i);
            }
        });

        Thread writer2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                map.put(i, "Thread2-" + i);
            }
        });

        writer1.start();
        writer2.start();

        writer1.join();
        writer2.join();

        System.out.println("Map size: " + map.size());  // 항상 200이 아님
        System.out.println(map);
    }
}