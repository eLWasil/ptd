import java.util.ArrayList;
import java.util.List;

public class Lab2 extends VecCommons implements MathHelper {

    private List vectorX;

    private List vectorRe = new ArrayList();
    private List vectorIm = new ArrayList();
    private List vectorZn = new ArrayList();
    private List vectorAlphan = new ArrayList();
    private List vectorMk = new ArrayList();
    private List vectorDeriMk = new ArrayList();

    private int nMax = 4;

    public Lab2(List vectorX) {
        this.vectorX = vectorX;
        reCalculate(vectorX.size());
    }

    public void reCalculate(List vector) {
        vectorX = vector;
        reCalculate(vector.size());
    }

    @Override
    protected void reCalculate(int nNewMax) {
        clear(
                vectorRe,
                vectorIm,
                vectorMk,
                vectorDeriMk
        );

        nMax = nNewMax;

        calculateDFT();
        calculateMk();
        calculateDerivativeMk();
    }

    public void calculateDFT() {
        for (int k = 0; k < nMax; k++) {
            calculateReImVectors(k);
        }
        calculateAlphaZVectors();
    }

    private void calculateReImVectors(int k) {
        for (int n = 0; n < nMax && n < vectorX.size(); n++) {
            double phi = calcPhi(n, k);
            double xn = (double) vectorX.get(n);

            double Re = getRe(phi) * xn;
            double Im = getIm(phi) * xn / 2;
            vectorRe.add(Re);
            vectorIm.add(Im);
        }
    }

    private void calculateAlphaZVectors() {
        for (int n = 0; n < vectorRe.size() && n < vectorIm.size(); n++) {
            vectorZn.add(getZ((double)vectorRe.get(n), (double)vectorIm.get(n)));
            vectorAlphan.add(getAlpha((double)vectorRe.get(n), (double)vectorIm.get(n)));
        }
    }

    public void calculateMk() {
        for (int k = 0; k < nMax / 2; k++) {
            double result = Math.sqrt(Math.pow(getRe(getX(k)), 2) + Math.pow(getIm(getX(k)), 2));
            vectorMk.add(result);
        }
    }

    public void calculateDerivativeMk() {
        for (int k = 0; k < nMax / 2; k++) {
            double result = 10 * log(getMk(k), 10);
            vectorDeriMk.add(result);
        }
    }

    private double calcPhi(int n, int k) {
        int kn = k * n;
        return (kn / nMax) * 2 * Math.PI;
    }


    private double getRe(double phi) {
        return Math.cos(phi);
    }

    private double getIm(double phi) {
        return Math.sin(phi);
    }

    private double getX(int k) {
        return checkVec(k, vectorRe);
    }

    private double getZ(double aRe, double bIm) {
        return Math.sqrt(Math.pow(aRe, 2) + Math.pow(bIm, 2));
    }

    private double getAlpha(double re, double im) {
        double tgAlpha = im / re;
        double alpha = Math.atan(tgAlpha);
        return alpha;
    }

    private double getMk(int k) {
        return checkVec(k, vectorMk);
    }

    public List getVectorZn() {
        return vectorZn;
    }

    public List getVectorAlphan() {
        return vectorAlphan;
    }

    public List getVectorMk() {
        return vectorMk;
    }

    public List getVectorDeriMk() {
        return vectorDeriMk;
    }
}
