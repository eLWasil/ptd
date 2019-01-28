import commons.Complex;
import commons.VecCommons;
import services.FFT;

public class Lab4 extends VecCommons
{
    public static final int POWER = 15;
    public final int fs = 512;
    public final int N = 2;
    public final double totalTime = 1; // sec
    double timeByte;
    private double[] a_ASK;
    private double[] a_FSK;
    private double[] a_PSK;

    private double[] sant, sn1t, sn2t, spnt;

    private final double nMax = totalTime * fs;

    private double[][] a_ZaSpectrum;
    private double[][] a_ZfSpectrum;
    private double[][] a_ZpSpectrum;

    public double[] a_Time;

    public static String message = "L";
    private char[] signBytes;
    short[] bytes;
    private int bytesCount;


    public Lab4() {
        reCalculate(999);
    }

    public Lab4(String message) {
        this.message = message;
        reCalculate(123);
    }

    @Override
    protected void reCalculate(int indexOutOfBound) {

        String signBytes = getBytesMessage(message);
        this.timeByte = totalTime / signBytes.length();
        int fnByte = (int)Math.ceil(nMax / signBytes.length());
        bytesCount = signBytes.length();

        a_Time = new double[(int)nMax];
        bytes = new short[(int)nMax];
        for (int i = 0; i < nMax; i++) {
            int idx = (int)Math.floor(i / fnByte);

            a_Time[i] = (double)i / fs;
            if (signBytes.charAt(idx) == '0') {
                bytes[i] = 0;
            } else {
                bytes[i] = 1;
            }
        }

        a_ASK = ASKCalculate(16, 32);
        a_FSK = FSKCalculate();
        a_PSK = PSKCalculate();

        a_ZaSpectrum = calculateFFT(a_ASK);
        a_ZfSpectrum = calculateFFT(a_FSK);
        a_ZpSpectrum = calculateFFT(a_PSK);
    }

    public void setMessage(String message) {
        Lab4.message = message;
        reCalculate(0);
    }

    public double[] ASKCalculate(double A1, double A2) {
        double fn = N * 1 / timeByte;
        double[] results = new double[(int)Math.ceil(nMax)];
        sant = new double[(int)Math.ceil(nMax)];

        for (int n = 0; n < nMax; n++) {
            double result = A2 * Math.sin(2 * Math.PI * fn * (n + 1) / fs);
            sant[n] = result;

            if (bytes[n] == 0) {
                result = A1 * Math.sin(2 * Math.PI * fn * (n + 1) / fs);
            }
            results[n] = result * POWER;
        }
        return results;
    }

    public double[] FSKCalculate() {
        double results[] = new double[(int)Math.ceil(nMax)];
        double fn1 = (N + 1) / timeByte;
        double fn2 = (N + 2) / timeByte;

        sn1t = new double[(int)Math.ceil(nMax)];
        sn2t = new double[(int)Math.ceil(nMax)];
        for (int n = 0; n < nMax; n++) {
            double result;
            sn1t[n] = Math.sin(2 * Math.PI * fn1 * (n + 1) / fs);
            sn2t[n] = Math.sin(2 * Math.PI * fn2 * (n + 1) / fs);

            if (bytes[n] == 0) {
                result = sn1t[n];
            } else {
                result = sn2t[n];
            }
            results[n] = result * POWER;
        }
        return results;
    }


    public double[] PSKCalculate() {
        double results[] = new double[(int)Math.ceil(nMax)];
        double fn = N * 1 / timeByte;

        spnt = new double[(int)Math.ceil(nMax)];
        for (int n = 0; n < nMax; n++) {
            double result = Math.sin(2 * Math.PI * fn * (n + 1) / fs);
            spnt[n] = result;

            if (bytes[n] == 1) {
                result = Math.sin(2 * Math.PI * fn * (n + 1) / fs + Math.PI);
            }
            results[n] = result * POWER;
        }
        for (int w = 0; w < 10; w++) {

//            System.out.println("result = " + results[w]);
        }
        return results;
    }

    public double[][] calculateFFT(double[] vector) {
        commons.Complex[] arr_FFT = new commons.Complex[vector.length];
        for (int i = 0; i < vector.length; i++) {
            arr_FFT[i] = new Complex(vector[i], 0);
        }
        commons.Complex[] fftComplex = FFT.fft(arr_FFT);

        double[][] results = new double[3][fftComplex.length];
        for (int i = 0; i < fftComplex.length; i++) {
            double Re = fftComplex[i].re();
            double Im = fftComplex[i].im();

            double z = Math.sqrt(Re * Re + Im * Im);
            double logZ = 10 * Math.log(z);

//            if (i < 3) {
//                System.out.println(i + ". = " + z + " " + logZ + " " + Re + " " + Im);
//            }

            results[0][i] = Double.valueOf(i * fs / fftComplex.length);
            results[1][i] = z;
            results[2][i] = logZ;
        }
        return results;
    }

    public byte[] getBytesArray(String word) {
        return word.getBytes();
    }

    public String getBytesMessage(String message) {
        byte[] bytesArray = getBytesArray(message);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytesArray.length; i++) {
            builder.append(String.format("%8s", Integer.toBinaryString(bytesArray[i] & 0xFF)).replace(' ', '0'));
        }
        return builder.toString();
    }

    public double[][] getA_ZaSpectrum() {
        return a_ZaSpectrum;
    }

    public double[] getA_ASK() {
        return a_ASK;
    }

    public double[] getA_FSK() {
        return a_FSK;
    }

    public double[] getA_PSK() {
        return a_PSK;
    }

    public double[][] getA_ZfSpectrum() {
        return a_ZfSpectrum;
    }

    public double[][] getA_ZpSpectrum() {
        return a_ZpSpectrum;
    }

    public double[] getSant() {
        return sant;
    }

    public double[] getSn1t() {
        return sn1t;
    }

    public double[] getSn2t() {
        return sn2t;
    }

    public double[] getSpnt() {
        return spnt;
    }

    public int getBytesCount() {
        return bytesCount;
    }
}
