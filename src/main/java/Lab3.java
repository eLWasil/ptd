import commons.VecCommons;
import commons.Complex;
import services.FFT;

public class Lab3 extends VecCommons {

    double[] arr_Fn;
    double[] arr_Mt;
    double[] arr_Za;
    double[][] arr_ZaFFT;
    double[] arr_Zp;
    double[][] arr_ZpFFT;

    private int nMax;
    private final double fs = 512;
    private final double fn = 64;
    private double Fm = 4;
    private double Time = 1;
    double Am = 2;

    double ka, kp;

    public Lab3(double ka, double kp) {
        this.ka = ka;
        this.kp = kp;
        nMax = (int)Math.round(Time * fs);

        arr_Mt = new double[nMax];
        arr_Za = new double[nMax];
        arr_ZaFFT = new double[2][nMax];

        arr_Zp = new double[nMax];
        arr_ZpFFT = new double[2][nMax];
        arr_Fn = new double[nMax];

        reCalculate(666);
    }

    @Override
    protected void reCalculate(int indexOutOfBound) {
        Fm = (Fm == 0 ? 100 : Fm);
        calculateMt();
        calculateZa(ka);
        calculateZp(kp);
        arr_ZaFFT = calculateFFT(arr_Za);
        arr_ZpFFT = calculateFFT(arr_Zp);
    }

    private final void calculateMt() {
        for (int i = 0; i < nMax; i++) {
            double time = (i / fs);
            double _x = 2 * Math.PI * Fm * time;
            double result = Am * Math.sin(_x);
            arr_Mt[i] = result;
        }
    }

    private void calculateZa(double ka) {
        for (int i = 0; i < nMax; i++) {
            double time = i / fs;
            arr_Za[i] = (ka * arr_Mt[i] + 1) * Math.cos(2 * Math.PI * fn * time);
        }
    }

    private void calculateZp(double kp) {
        for (int i = 0; i < nMax; i++) {
            double time = i / fs;
            arr_Zp[i] = Math.cos(2 * Math.PI * fn * time + (kp * arr_Mt[i]));
        }
    }

    private double[][] calculateFFT(double[] xVector) {
        commons.Complex[] arr_FFT = new commons.Complex[xVector.length];
        for (int i = 0; i < xVector.length; i++) {
            arr_FFT[i] = new Complex(xVector[i], 0);
        }
        commons.Complex[] fftComplex = FFT.fft(arr_FFT);

        double[][] results = new double[2][fftComplex.length];
        for (int i = 0; i < fftComplex.length; i++) {
            double Re = fftComplex[i].re();
            double Im = fftComplex[i].im();

            double z = Math.sqrt(Re * Re + Im * Im);
            double log = 10 * Math.log(Math.abs(z));
            results[0][i] = z;
            results[1][i] = log;
            arr_Fn[i] = i * (fs / fftComplex.length);
        }
        return results;
    }

    public double[] getArr_Mt() {
        return arr_Mt;
    }

    public double[] getArr_Za() {
        return arr_Za;
    }

    public double[] getArr_Zp() {
        return arr_Zp;
    }

    public double[] getArr_Fn() {
        return arr_Fn;
    }

    public double[][] getArr_ZaFFT() {
        return arr_ZaFFT;
    }

    public double[][] getArr_ZpFFT() {
        return arr_ZpFFT;
    }

    public double getKa() {
        return ka;
    }

    public double getKp() {
        return kp;
    }
}
