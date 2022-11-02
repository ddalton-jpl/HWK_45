import java.util.Arrays;

class ThreadsThatNormalizeArray extends Thread {
    public static Double[][] Bn;
    public Integer[][] B;
    public Integer maxB = 0;
    private int row;

    public ThreadsThatNormalizeArray(int row, Integer[][] B, int maxB) {
        this.row = row;
        this.maxB = maxB;
        this.B = B;
    }

    public void printBn() {
        // for (int i = 0; i < B.length; i++) {
        //     for (int j = 0; j < B.length; j++) {
        //         System.out.print(Bn[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }

    @Override
    public synchronized void run() {


    }
}