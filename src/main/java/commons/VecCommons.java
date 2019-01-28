package commons;

import java.util.List;

public abstract class VecCommons {

    protected abstract void reCalculate(int indexOutOfBound);

    protected double checkVec(int index, List vector) {
        if (vector == null || vector.isEmpty()
                || index >= vector.size()) {
            reCalculate(index);
        }
        return (double) vector.get(index);
    }

    protected void clear( List ... vectorsToClear ) {
        for (int i = 0; i < vectorsToClear.length; i++) {
            vectorsToClear[i].clear();
        }
    }

    public static void showResults( List ... vectorsToShow ) {
        System.out.println("==========================================================");
        for (List vec :
                vectorsToShow) {
            System.out.println(vec);
        }
        System.out.println("==========================================================");
    }

    public static double findMin(double[] arr) {
        double min = (arr.length == 0 ? 0 : arr[0]);

        for (int i = 1; i < arr.length; i++) {
            min = (min < arr[i] ? min : arr[i]);
        }

        return min;
    }

    public static double findMax(double[] arr) {
        double max = (arr.length == 0 ? 0 : arr[0]);

        for (int i = 1; i < arr.length; i++) {
            max = (max > arr[i] ? max : arr[i]);
        }

        return max;
    }

    public static double findMinDoubleList(List vector) {
        if (vector.size() >= 0) {
            double min = (double)vector.get(0);
            for (int i = 1; i < vector.size(); i++) {
                if ((double)vector.get(i) < min) {
                    min = (double)vector.get(i);
                }
            }
            return min;
        }
        return 0;
    }

    public static double findMaxDoubleList(List vector) {
        if (vector.size() >= 0) {
            double max = (double)vector.get(0);
            for (int i = 1; i < vector.size(); i++) {
                if ((double)vector.get(i) > max) {
                    max = (double)vector.get(i);
                }
            }
            return max;
        }
        return 0;
    }

    public static double[] makeStronger(double[] vector, int power) {
        double[] vectorToReturn = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            vectorToReturn[i] = (vector[i] * power);
        }
        return vectorToReturn;
    }

    public static void printVec(String vecName, List vector) {
        System.out.println(vecName + " = [ ");
        for (int i = 0; i < vector.size() && i < 50; i++) {
            System.out.print("["+vector.get(i)+"]");
        }
        System.out.println(" ]");
    }

    public static String stringOf(List<Double> vec, String title, final int buffSize) {
        StringBuilder builder = new StringBuilder(title + " = [\n");
        for (int i = 0; i < vec.size() && i < buffSize; i++) {
            builder.append(String.valueOf(vec.get(i)) + ", ");
        }
        builder.append("\n]");
        return builder.toString();
    }

    public static double szerokoscPasma(double[] arr_dB, double[] arr_Hz, int minusDb) {
        double max = findMax(arr_dB);
        double minDb = max - minusDb;

        int idxFirst = -1;
        int idxLast = -1;
        for (int i = 0; i < arr_dB.length; i++) {
            if (arr_dB[i] >= minDb && idxFirst == -1) {
                idxFirst = i;
            }
            else if (arr_dB[i] >= minDb && idxFirst != -1) {
                idxLast = i;
            }
        }

        if (idxFirst == -1 || idxLast == -1) {
            System.out.println("Nie mozna obliczyc pasma poniewaz zadne wartosci nie wykraczaja poza " + minDb + " dB.");
            return 0;
        }
        if (idxFirst >= arr_Hz.length || idxLast >= arr_Hz.length) {
            System.out.println("Nie mozna obliczyc pasma. Index poza zakresem Hz array.");
            return 0;
        }
        else {
            return (arr_Hz[idxLast] - arr_Hz[idxFirst]);
        }
    }

}
