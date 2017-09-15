import java.util.Random;

public class ThreadDirection {
    static Random random = new Random();

    public static void main(String[] args) {

        new Thread(() -> {
            int direction = random.nextInt(3);

            int x = 0, y = 0;

            while(true) {
                if (direction == 0) { y--; x--; }
                if (direction == 1) { y--; x++; }
                if (direction == 2) { y++; x++; }
                if (direction == 3) { y++; x--; }
            }
        }).start();
    }
}
