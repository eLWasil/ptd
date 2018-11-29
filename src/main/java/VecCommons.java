import java.util.ArrayList;
import java.util.Collections;
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

    public void showResults( List ... vectorsToShow ) {
        System.out.println("==========================================================");
        for (List vec :
                vectorsToShow) {
            System.out.println(vec);
        }
        System.out.println("==========================================================");
    }
}
