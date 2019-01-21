import commons.VecCommons;

import java.util.ArrayList;
import java.util.List;

public class Lab3 extends VecCommons {

    private List<Double> vectorMt = new ArrayList<>();
    private List<Double> vectorZa1 = new ArrayList<>();
    private List<Double> vectorZa2 = new ArrayList<>();
    private List<Double> vectorZa3 = new ArrayList<>();
    private List<Double> vectorZp1 = new ArrayList<>();
    private List<Double> vectorZp2 = new ArrayList<>();
    private List<Double> vectorZp3 = new ArrayList<>();
    private List<Double> vectorfn = new ArrayList<>();

    private double Fm = 1 / 10;
    private double Time = 1;
    double Am = 2;

    private final double fn = Fm * 150;

    public Lab3() {
        reCalculate(666);
    }

    @Override
    protected void reCalculate(int indexOutOfBound) {
        Fm = (Fm == 0 ? 100 : Fm);
        calculateZa();
        calculateZp();
        calculateFn();
    }

    private final Double calculateMt(double time) {
        double _x = 2 * Math.PI * Fm * time;
        int x = (int)Math.floor(_x);
        double result = Am * Math.sin(x);
        return result;
    }

    private void calculateZa() {
        double ka1 = 0.69;
        double ka2 = 7.03;
        double ka3 = 21.112;


        for (double t = 0; t < Time; t += 1/Fm) {
            final double mt = calculateMt(t);
//            System.out.println("mt2 = " + mt);

            double result1 = (ka1 * mt + 1) * Math.cos(2 * Math.PI * fn * t);
            double result2 = (ka2 * mt + 1) * Math.cos(2 * Math.PI * fn * t);
            double result3 = (ka3 * mt + 1) * Math.cos(2 * Math.PI * fn * t);

            System.out.println("2 * Math.PI * fn * t = " + (2 * Math.PI * fn * t) + " Math.cos(2 * Math.PI * fn * t) = " + Math.cos(2 * Math.PI * fn * t));

            vectorMt.add(calculateMt(t));

            vectorZa1.add(result1);
            vectorZa2.add(result2);
            vectorZa3.add(result3);

        }
    }

    private void calculateZp() {
        double kp1 = 1.3;
        double kp2 = Math.PI / 7;
        double kp3 = 42.000001;

        for (double t = 0; t < Time; t+= 1/Fm) {
            double result1 = Math.cos(2 * Math.PI * fn * t + (kp1 * calculateMt(t)));
            double result2 = Math.cos(2 * Math.PI * fn * t + (kp2 * calculateMt(t)));
            double result3 = Math.cos(2 * Math.PI * fn * t + (kp3 * calculateMt(t)));

            vectorZp1.add(result1);
            vectorZp2.add(result2);
            vectorZp3.add(result3);
        }
    }

    private void calculateFn() {
        for (double t = 0; t < Time; t+= 1/Fm) {
            vectorfn.add(t);
        }
    }


    public List<Double> getVectorZa1() {
        return vectorZa1;
    }

    public List<Double> getVectorZa2() {
        return vectorZa2;
    }

    public List<Double> getVectorZa3() {
        return vectorZa3;
    }

    public List<Double> getVectorZp1() {
        return vectorZp1;
    }

    public List<Double> getVectorZp2() {
        return vectorZp2;
    }

    public List<Double> getVectorZp3() {
        return vectorZp3;
    }

    public List<Double> getVectorfn() {
        return vectorfn;
    }

    public List<Double> getVectorMt() {
        return vectorMt;
    }
}
