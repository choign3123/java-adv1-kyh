package thread.collection.simple;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

public class SyncList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 5;

    private Object[] elementData;
    private int size = 0;

    public SyncList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public synchronized void add(Object e) {
        elementData[size] = e;
        sleep(100); // 멀티스레드 문제를 쉽게 확인할 수 있도록 삽입. 스레드1이 elementData에 데이터를 넣고 size를 1 증가시키기까지 시간이 걸리게 만듦
        size++; // 원자적 연산이 아님 & 공유자원
    }

    @Override
    public synchronized Object get(int index) {
        return elementData[index];
    }

    @Override
    public synchronized String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size))
                + " size = " + size
                + ", capasity = " + DEFAULT_CAPACITY;
    }
}
