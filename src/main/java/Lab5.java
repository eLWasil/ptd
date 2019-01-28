import commons.VecCommons;

public class Lab5 extends VecCommons {

    private Lab4 lab4;
    private double[]
            arr_Time,
            arr_XtASK, arr_PtASK, arr_MtASK,
            arr_XtPSK, arr_PtPSK, arr_MtPSK
    ;

    public Lab5() {
        lab4 = new Lab4();

        arr_Time = lab4.a_Time;
        for (int i = 0; i < 10; i++) {
            System.out.println("Lab5, time[" +i+ "] = " + arr_Time[i]);
        }

        arr_XtASK = calculateXt(lab4.getA_ASK(), lab4.getSant());
        arr_PtASK = calculatePt(arr_XtASK);
        arr_MtASK = calcuateMt(arr_PtASK, 300000);

        arr_XtPSK = calculateXt(lab4.getA_PSK(), lab4.getSpnt());
        arr_PtPSK = calculatePt(arr_XtPSK);
        arr_MtPSK = calcuateMt(arr_PtPSK, 0);

        System.out.println("Fs = " + lab4.fs + " Arr = " + arr_XtASK.length);
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
        }

        if (results.length == 0) {
            System.out.println("Lab5, calculate xt returns empty array");
        }

        return results;
    }

    public double[] calculatePt(double[] arr_Y) {
        double[] results = new double[arr_Y.length];
        int nMax = arr_Y.length;

        final int f_SquareCount = (int)Math.floor(arr_Y.length / lab4.getBytesCount());

        double h = f_SquareCount / f_SquareCount;
        System.out.println("calculatePt(), h = "  + h);

        double PSum = 0;
        for (int i = 0; i < lab4.getBytesCount(); i++) {
            PSum = 0;
            for (int j = 0; j < f_SquareCount; j++) {
                try {
                    double p_result = arr_Y[(int)Math.floor((i * f_SquareCount) + (j * h))] * h;
                    PSum += p_result;
                    results[i * f_SquareCount + j] = PSum;
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Index: " + i * f_SquareCount + j + " out of bound. Arr size = " + arr_Y.length + ", result = " + PSum);
                }
            }
        }

        return results;
    }

    public double[] calcuateMt(double[] arr_Y, double H) {
        double[] results = new double[arr_Y.length];
        for(int i = 0; i < arr_Y.length; i++) {
            if (arr_Y[i] < H) {
                results[i] = 0;
            } else {
                results[i] = 1;
            }
        }
        return results;
    }

    public double[] getArr_Time() {
        return arr_Time;
    }

    public double[] getArr_XtASK() {
        return arr_XtASK;
    }

    public double[] getArr_PtASK() {
        return arr_PtASK;
    }

    public double[] getArr_MtASK() {
        return arr_MtASK;
    }

    public double[] getArr_XtPSK() {
        return arr_XtPSK;
    }

    public double[] getArr_PtPSK() {
        return arr_PtPSK;
    }

    public double[] getArr_MtPSK() {
        return arr_MtPSK;
    }
}
