import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {

        Runnable runnable = () -> {
            long id = Thread.currentThread().getId();
            System.out.printf("%d Попытка занять слот\n", id);
            boolean result = semaphore.tryAcquire();
            System.out.printf("%d Доступ получен? " + result + "\n", id);

            if (!result) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.printf("%d Выполняется код в резервированном пространстве\n", id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%d Освобождаем слотa\n", id);
            semaphore.release();

            System.out.printf("%d Конец\n", id);
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
