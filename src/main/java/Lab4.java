import java.util.ArrayList;
import java.util.List;

public class Lab3
{
    final int TB = 1000; // :/ na liczbe bitów = Tb = T / M
    final int N = 200;
    double time = 0;

    Lab1 lab1 = new Lab1(N);
    List vectorX = new ArrayList();

    double Fn = N * (1 / TB);
    double Fn1 = (N + 1) / TB;
    double Fn2 = (N * 2) / TB;

    List vectorZA = new ArrayList();
    List vectorZF = new ArrayList();
    List vectorZP = new ArrayList();

    public Lab3() {
        vectorX = lab1.getVectorX();
        time = vectorX.size();
    }

    public void ASKCalculate(byte mByte, int numerProbki) {
        double A1 = 1;
        double A2 = 2;
        double result = 0;

        int Fs = 8000;
                // 1 ... N-1     // 100 (Hz)
        time = numerProbki / Fs;



        if (mByte == 0) {
            result = A1 * Math.sin(2 * Math.PI * Fn * time);
        } else if (mByte == 1) {
            result = A2 * Math.sin(2 * Math.PI * Fn * time);
        }

        vectorZA.add(result);
        // time = N / ilość bitów
    }


    public void FSKCalculate(byte mByte) {
        double result = 0;

        if (mByte == 0) {
            result = Math.sin(2 * Math.PI * Fn * time);
        } else if (mByte == 1) {
            result = Math.sin(2 * Math.PI * Fn * time);
        }

        vectorZF.add(result);
        // time = N / ilość bitów
    }


    public void PSKCalculate(byte mByte) {
        double result = 0;

        if (mByte == 0) {
            result = Math.sin(2 * Math.PI * Fn * time);
        } else if (mByte == 1) {
            result = Math.sin((2 * Math.PI * Fn * time) + Math.PI);
        }

        vectorZP.add(result);
        // time = N / ilość bitów
    }

    // 4 sekundy / liczba bitów
    // Tb (sec) * Tb = tb w próbkach

    public byte[] getBytesArray(String word) {
        return word.getBytes();
    }

    public double getTime()

}
