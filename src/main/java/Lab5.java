import commons.VecCommons;

public class Lab5 extends VecCommons {

    private Lab4 lab4;
    private double[] arr_Time, arr_Xt;

    public Lab5() {
        lab4 = new Lab4();

        arr_Time = lab4.a_Time;
        for (int i = 0; i < 10; i++) {
            System.out.println("Lab5, time[" +i+ "] = " + arr_Time[i]);
        }

        arr_Xt = calculateXt(lab4.getA_ASK(), lab4.getSant());
    }

    @Override
    protected void reCalculate(int indexOutOfBound) {
        lab4.reCalculate(666);
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

    public double[] calculatePt(double[] arr_Y) {

        final int f_SquareCount = (int)Math.floor(lab4.fs * lab4.timeByte);
        int nMax = arr_Y.length;

        double[] arr_Pi = new double[f_SquareCount];
        double h = (lab4.timeByte * nMax) / f_SquareCount;
        System.out.println("calculatePt(), h = "  + h);

        for (int i = 1; i < nMax; i++) {
            double v_Pi = ((arr_Y[i-1] + arr_Y[i]) / 2) * h;
        }

    }



    public double[] getArr_Xt() {
        return arr_Xt;
    }

    public double[] getArr_Time() {
        return arr_Time;
    }
}
