import javafx.application.Platform;

public class GeometryThreadsExample {
    static int[] arrayList = new int[10];
    static {
        for(int i = 0; i < arrayList.length; i++){
            arrayList[i] = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < arrayList.length; i++){
            final int index = 0;
            new Thread(() -> {
                while(true) {
                    final int newValue = arrayList[index] + 1;

                    // ЭТО СТРОКА РАБОТАЕТ ИСКЛЮЧИТЕЛЬНО В JAVAFX ПРИЛОЖЕНИИ!!!
                    Platform.runLater(() -> {
                        arrayList[index] = newValue;
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        Thread.sleep(3000);

        for(int i = 0; i < arrayList.length; i++){
            System.out.printf("array[%d] = %d\n", i, arrayList[i]);
        }
    }
}
