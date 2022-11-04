
class hwk45 extends Thread {
    public static Double[][] Bn;
    public static WorkerThreads workerThreads;
    public static ThreadsThatNormalizeArray threadNormalize;

    public static void main(String[] args) {
        // TODO have this read in by the command line
        // Note there are 10 rows so there should be 10 threads created
        Integer[][] B = {
                { 92, 99, 1, 8, 15, 67, 74, 51, 58, 40 },
                { 98, 80, 7, 14, 16, 73, 55, 57, 64, 41 },
                { 4, 81, 88, 20, 22, 54, 56, 63, 70, 47 },
                { 85, 87, 19, 21, 3, 60, 62, 69, 71, 28 },
                { 86, 93, 25, 2, 9, 61, 68, 75, 52, 34 },
                { 17, 24, 76, 83, 90, 42, 49, 26, 33, 65 },
                { 23, 5, 82, 89, 91, 48, 30, 32, 39, 66 },
                { 79, 6, 13, 95, 97, 29, 31, 38, 45, 72 },
                { 10, 12, 94, 96, 78, 35, 37, 44, 46, 53 },
                { 11, 18, 100, 77, 84, 36, 43, 50, 27, 59 },
        };

        createThreads(B);

        printArray();

    }

    public static void createThreads(Integer[][] B) {
        // Create a n number of threads
        int numberOfRows = B.length;

        // TODO find a way to wait until threads find max have finished
        for (int i = 0; i < numberOfRows; i++) {
            workerThreads = new WorkerThreads(i, B);
            workerThreads.start();
        }
    }

    public static void printArray() {
        if (!workerThreads.isAlive()) {
            for (int i = 0; i < Bn.length; i++) {
                for (int j = 0; j < Bn.length; j++) {
                    System.out.print(String.format("%.6f", Bn[i][j]) + " ");
                }
                System.out.println();
            }
        }
    }
}