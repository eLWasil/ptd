import java.util.ArrayList;
import java.util.List;

public class Lab4 extends VecCommons
{
    final int TB = 1000; // :/ na liczbe bitów = Tb = T / M

    Lab1 lab1;

    public List vectorN = new ArrayList();
    List vectorZA = new ArrayList();
    List vectorZF = new ArrayList();
    List vectorZP = new ArrayList();

    public Lab4() {
        int N = 200;
        lab1 = new Lab1(N);

        ASKCalculate("Hello World");
        System.out.println(findMinDoubleList(vectorZA) + ", " + findMaxDoubleList(vectorZA));
    }

    public void ASKCalculate(String message) {
        double A1 = 1;
        double A2 = 2;



        String sign = message;
        String signBytes = getBytesMessage(message);

        int Ts = 1; // sekundy
        int Fs = 8000;
        int N = Ts * Fs;
//        TB = Ts / signBytes.length();
//        time = n / Fs;

        System.out.println(message);
        System.out.println(signBytes);
        System.out.println("ASK Calculate, bytes size: " +signBytes.length());

        for (int i = 0; i < signBytes.length(); i++) {
            char mByte = signBytes.charAt(i);
            for (int n = 0; n < N; n++) {
                vectorN.add(i * N + n);
                if (mByte == '0') {
                    moduloForZero(vectorZA);
                } else if (mByte == '1') {
                    moduloForOne(vectorZA);
                }
            }
        }
        // time = N / ilość bitów
    }

    int byteTime = 10;
    double Fn = 2000 * 1 / byteTime;

    private void moduloForOne(List result) {
        double value;
        for (int i = 0; i < byteTime; i++) {
            value = 1.13 * Math.sin((2 * Math.PI * Fn * i) * 57.2957795);
            result.add(value);
        }
    }

    private void moduloForZero(List result) {
        double value;
        for (int i = 0; i < byteTime; i++) {
            value = 0.666 * Math.sin((2 * Math.PI * Fn * i) * 57.2957795);
            result.add(value);
        }
    }

    public List buildHzVec(List calculatesVector, int timeSec) {
        List destinyVector = new ArrayList(calculatesVector.size());
        for (int i = 1; i <= calculatesVector.size(); i++) {
            destinyVector.add((timeSec / i));
        }
        return destinyVector;
    }

    double time = 0;

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

    public String getBytesMessage(String message) {
        byte[] bytesArray = getBytesArray(message);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytesArray.length; i++) {
            builder.append(String.format("%8s", Integer.toBinaryString(bytesArray[i] & 0xFF)).replace(' ', '0'));
        }
        return builder.toString();
    }

    @Override
    protected void reCalculate(int indexOutOfBound) {

    }
}
