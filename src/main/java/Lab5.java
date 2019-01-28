import commons.VecCommons;

public class Lab5 extends VecCommons {

    private Lab4 lab4;
    private double[]
            arr_Time,
            arr_zASK, arr_XtASK, arr_PtASK, arr_MtASK,
            arr_zPSK, arr_XtPSK, arr_PtPSK, arr_MtPSK,
            arr_zFSK, arr_Xt1FSK, arr_Xt2FSK, arr_Pt1FSK, arr_Pt2FSK, arr_PtFSK, arr_MtFSK
    ;

    public Lab5() {
        lab4 = new Lab4();

        arr_Time = lab4.a_Time;

        arr_zASK = lab4.getA_ASK();
        arr_XtASK = calculateXt(arr_zASK, lab4.getSant());
        arr_PtASK = calculatePt(arr_XtASK);
        arr_MtASK = calculateMt(arr_PtASK, 300000);

        arr_zPSK = lab4.getA_PSK();
        arr_XtPSK = calculateXt(arr_zPSK, lab4.getSpnt());
        arr_PtPSK = calculatePt(arr_XtPSK);
        arr_MtPSK = calculateMt(arr_PtPSK, 0);

        arr_zFSK = lab4.getA_FSK();
        arr_Xt1FSK = calculateXt(arr_zFSK, lab4.getSn1t());
        arr_Pt1FSK = calculatePt(arr_Xt1FSK);

        arr_Xt2FSK = calculateXt(arr_zFSK, lab4.getSn2t());
        arr_Pt2FSK = calculatePt(arr_Xt2FSK);

        arr_PtFSK = calculatePt_FSKSum(arr_Pt1FSK, arr_Pt2FSK);
        arr_MtFSK = calculateMt(arr_PtFSK, 1);

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
        double PSum;
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

    public double[] calculateMt(double[] arr_Y, double H) {
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

    public double[] calculatePt_FSKSum(double[] a, double[] b) {
        double[] results = new double[a.length <= b.length ? a.length : b.length];
        for (int i = 0; i < a.length && i < b.length; i++) {
            results[i] = a[i] - b[i];
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

    public double[] getArr_Xt1FSK() {
        return arr_Xt1FSK;
    }

    public double[] getArr_Xt2FSK() {
        return arr_Xt2FSK;
    }

    public double[] getArr_Pt1FSK() {
        return arr_Pt1FSK;
    }

    public double[] getArr_Pt2FSK() {
        return arr_Pt2FSK;
    }

    public double[] getArr_MtFSK() {
        return arr_MtFSK;
    }

    public double[] getArr_PtFSK() {
        return arr_PtFSK;
    }

    public double[] getArr_zASK() {
        return arr_zASK;
    }

    public double[] getArr_zPSK() {
        return arr_zPSK;
    }

    public double[] getArr_zFSK() {
        return arr_zFSK;
    }
}
