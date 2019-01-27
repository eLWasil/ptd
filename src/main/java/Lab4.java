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
    double[] a_ASK;
    double[] a_FSK;
    double[] a_PSK;

    final double nMax = totalTime * fs;

    double[][] a_ZaSpectrum;
    double[][] a_ZfSpectrum;
    double[][] a_ZpSpectrum;


    public static String message = "L";
    private char[] signBytes;
    short[] bytes;

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

        bytes = new short[(int)nMax];
        for (int i = 0; i < nMax; i++) {
            int idx = (int)Math.floor(i / fnByte);

            if (signBytes.charAt(idx) == '0') {
                bytes[i] = 0;
            } else {
                bytes[i] = 1;
            }
        }

        a_ASK = ASKCalculate(16, 32);
        a_FSK = FSKCalculate();
        a_PSK = PSKCalculate();

        for (int i = 0; i < 200; i+=10) {
//            System.out.println("FSK = " + a_FSK[i] + " " + " PSK = " + a_PSK[i]);
        }

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

        for (int n = 0; n < nMax; n++) {
            double result;
            if (bytes[n] == 0) {
                result = A1 * Math.sin(2 * Math.PI * fn * (n + 1) / fs);
            } else {
                result = A2 * Math.sin(2 * Math.PI * fn * (n + 1) / fs);
            }
            results[n] = result * POWER;
        }
        return results;
    }

    public double[] FSKCalculate() {
        double results[] = new double[(int)Math.ceil(nMax)];
        double fn1 = (N + 1) / timeByte;
        double fn2 = (N + 2) / timeByte;


        for (int n = 0; n < nMax; n++) {
            double result;
            if (bytes[n] == 0) {
                result = Math.sin(2 * Math.PI * fn1 * (n + 1) / fs);
            } else {
                result = Math.sin(2 * Math.PI * fn2 * (n + 1) / fs);
            }
            results[n] = result * POWER;
        }
        return results;
    }


    public double[] PSKCalculate() {
        double results[] = new double[(int)Math.ceil(nMax)];
        double fn = N * 1 / timeByte;

        int i = 0, j = 0;
        for (int n = 0; n < nMax; n++) {

            double result;
            if (bytes[n] == 0) {
                result = Math.sin(2 * Math.PI * fn * (n + 1) / fs);
            } else {
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
}
