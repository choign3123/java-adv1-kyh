package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount {

    private int balance; // volatile 붙여도 문제 발생. 근본적인 문제는 다른 원인이기 때문.

    public BankAccountV3(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        // ==임계 영역 시작==
        synchronized (this) {
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if(balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);
                return false;
            }
            // 잔고가 출금액보다 많으면, 진행
            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);

            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance = balance - amount;
            log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
        }
        // ==임계 영역 종료==

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() { // 계산이 모두 끝나고 읽게하기 위해
        return balance;
    }
}
