import java.util.ArrayList;
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

    public static List makeStronger(List vector, int power) {
        List vectorToReturn = new ArrayList();
        for (int i = 0; i < vector.size(); i++) {
            vectorToReturn.add((double)vector.get(i) * power);
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

}
