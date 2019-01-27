import commons.MathHelper;
import commons.VecCommons;

import java.util.ArrayList;
import java.util.List;

public class Lab1 extends VecCommons implements MathHelper {

    static final double Fn = 100; // Hz
    static final double Time = 1; // s
    private final double F = 4; // Hz
    private final double PHI = (7 * Math.PI) / 9;
    private double nMax = 128;

    public List moduloTest = new ArrayList();
    private List vectorN = new ArrayList();
    private List vectorX = new ArrayList();
    private List vectorY = new ArrayList();
    private List vectorZ = new ArrayList();
    private List vectorV = new ArrayList();
    private List vectorU = new ArrayList();
    private List vectorGa = new ArrayList();
    private List vectorGb = new ArrayList();
    private List vectorGc = new ArrayList();
    private List vectorGTime = new ArrayList();

    public final double getFs() {
        return Fn;
    }

    public Lab1(int nMax) {
        reCalculate(nMax);
    }

    @Override
    public void reCalculate(int nNewMax) {
        nMax = nNewMax;

        clear(
                vectorN,
                vectorX,
                vectorY,
                vectorZ,
                vectorV,
                vectorU,
                vectorGa,
                vectorGb,
                vectorGc,
                vectorGTime
        );

        fillNVec();
        calculateXn();
        calculateYn();
        calculateZn();
        calculateVn();
        calculateUn(1, convHzToSeconds(1024));
        calculateGTime(1, convHzToSeconds(1024));

//        moduloTest = makeStronger(vectorX, 2);
    }

    public void fillNVec() {
        for (int i = 0; i < nMax; i++) {
            vectorN.add(i);
        }
    }

    private void calculateXn() {
        for (int n = 0; n < nMax; n++) {
            double result = 0.7 * Math.sin(2 * Math.PI * F * (n / Fn) + PHI) * n;
            vectorX.add(result);
        }
    }

    private void calculateYn() {
        for (int n = 0; n < nMax; n++) {
            double result = (getX(n) + 1) / (getX(n) + 10);
            vectorY.add(result);
        }
    }

    private void calculateZn() {
        for (int n = 0; n < nMax; n++) {
            double result = getY(n) * Math.pow(Math.abs(n), 0.333);
            vectorZ.add(result);
        }
    }

    private void calculateVn() {
        for (int n = 0; n < nMax; n++) {
            double result = 3 * getX(n) + (getY(n) * (Math.abs(getX(n)) + 1.78));
            vectorV.add(result);
        }
    }

    private void calculateUn(int time, double interval) {
        for (double n = 0; n < time; n += interval) {
            double result = unTimeCases(n);
            vectorU.add(result);
        }
    }

    private double unTimeCases(double time) {
        if (time <= 0.2) {
            return 0.8 * Math.sin(20 * Math.PI * time);
        } else if (time <= 0.4) {
            return Math.exp(time - 0.2) * 0.8 * Math.sin(20 * Math.PI * time);
        } else if (time <= 0.6) {
            return 0.6 * Math.sin(10 * Math.PI * time);
        } else if (time <= 0.8) {
            return Math.exp(time - 0.6) * 0.6 * Math.sin(10 * Math.PI * time);
        } else {
            return log(0.7 * time, 2) * 0.5 * Math.sin(40 * Math.PI * time);
        }
    }

    private void calculateGTime(int time, double interval) {
        for (double n = 0; n < time; n += interval) {
            double pre = (9 / Math.pow(Math.PI, 2));
            vectorGTime.add(n);
            vectorGa.add(pre * sigma(2, n));
            vectorGb.add(pre * sigma(5, n));
            vectorGc.add(pre * sigma(50, n));
        }
    }

    private double sigma(int H, double currentTime) {
        double result = 0;
        for (int n = 0; n <= H; n++) {
            result += Math.sin((n * currentTime * Math.PI) / 2) * Math.sin(20 * n * Math.PI * currentTime);
        }
        return result;
    }

    public double getX(int index) {
        return checkVec(index, vectorX);
    }

    public double getY(int index) {
        return checkVec(index, vectorY);
    }

    public double getZ(int index) {
        return checkVec(index, vectorZ);
    }

    public double getnMax() {
        return nMax;
    }

    public List getVectorN() {
        return vectorN;
    }

    public List getVectorX() {
        return vectorX;
    }

    public List getVectorY() {
        return vectorY;
    }

    public List getVectorZ() {
        return vectorZ;
    }

    public List getVectorV() {
        return vectorV;
    }

    public List getVectorU() {
        return vectorU;
    }

    public List getVectorGTime() {
        return vectorGTime;
    }

    public List getVectorGa() {
        return vectorGa;
    }

    public List getVectorGb() {
        return vectorGb;
    }

    public List getVectorGc() {
        return vectorGc;
    }
}
