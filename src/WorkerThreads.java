import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// TODO solve this on paper first
// TODO use a cyclic barrier to synchronize the threads and wait for array to be normalized

public class WorkerThreads extends hwk45 {
    public static Integer[][] B;
    public static Boolean maxFound = false;

    static Integer maxB = Integer.MIN_VALUE;
    private CyclicBarrier barrier1 = new CyclicBarrier(1);
    private CyclicBarrier barrier2 = new CyclicBarrier(1);
    private int row;

    public WorkerThreads(int row, Integer[][] B) {
        this.row = row;
        WorkerThreads.B = B;
    }

    public void findMax() {

        int regional_maxValue = Integer.MIN_VALUE;

        for (int j = 0; j < B[row].length; j++) {
            regional_maxValue = Math.max(regional_maxValue, B[row][j]);
        }

        System.out.println("The max value in row " + row + " is " + regional_maxValue);

        maxB = Math.max(maxB, regional_maxValue);
    }

    public void normalize() {
        Bn = new Double[B.length][B.length];

        for (int i = row, j = 0; j < Bn[row].length; j++) {
            Bn[i][j] = (Double) ((1.0 * B[i][j]) / maxB);
        }
    }

    @Override
    public void run() {
        try {

            findMax();

            barrier1.await();

            normalize();

            barrier2.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}