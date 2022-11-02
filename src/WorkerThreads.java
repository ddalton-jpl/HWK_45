import java.util.concurrent.Semaphore;

public class WorkerThreads extends Thread {
    public static Integer[][] B;
    public static Boolean maxFound = false;
    public static Double[][] Bn;
    static Integer maxB = Integer.MIN_VALUE;
    private Semaphore lock;
    private int row;

    public WorkerThreads(Semaphore lock, int row, Integer[][] B) {
        this.lock = lock;
        this.row = row;
        WorkerThreads.B = B;
    }

    public void findMax() {
        int regional_maxValue = Integer.MIN_VALUE;

        for (int j = 0; j < B[row].length; j++) {
            regional_maxValue = Math.max(regional_maxValue, B[row][j]);
        }

        System.out.println("The max value in row " + row + " is " + regional_maxValue);

        try {
            lock.acquire();
            maxB = Math.max(maxB, regional_maxValue);
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
        lock.release();
    }

    public synchronized void normalize() {
        try {
            lock.acquire();
            Bn = new Double[B.length][B.length];

            for (int i = row, j = 0; j < Bn[row].length; j++) {
                Bn[i][j] = (Double) ((1.0 * B[i][j]) / maxB);
                if (j == 9) {
                    for (int k = 0; k < Bn[row].length; k++) {
                        System.out.print(String.format("%.6f", Bn[i][k]) + " ");
                    }
                    System.out.println();
                }
            }
        } catch (

        InterruptedException e) {
            e.printStackTrace();
        }
        lock.release();

    }

    @Override
    public synchronized void run() {
        findMax();

        normalize();

    }
}