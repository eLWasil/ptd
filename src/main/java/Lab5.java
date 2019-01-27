import commons.VecCommons;

public class Lab5 extends VecCommons {



    public Lab5() {
    }

    @Override
    protected void reCalculate(int indexOutOfBound) {

    }

    public double[] calculateXt(double[] arr_A, double[] arr_B) {
        final int nMax = (arr_A.length <= arr_B.length ? arr_A.length : arr_B.length);
        double[] results = new double[nMax];

        for (int i = 0; i < nMax; i++) {
            results[i] = arr_A[i] * arr_B[i];
            if (i < 10) {
                System.out.println("X(" + i + ") = " + results[i]);
            }
        }

        if (results.length == 0) {
            System.out.println("Lab5, calculate xt returns empty array");
        }

        return results;
    }


}
