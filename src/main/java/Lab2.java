import java.util.ArrayList;
import java.util.List;

public class Lab2 extends VecCommons implements MathHelper {

    private List<Double> vectorX        = new ArrayList<>();

    private List<Double> vectorRe       = new ArrayList();
    private List<Double> vectorIm       = new ArrayList();

    private List<Double> vectorMkDFT    = new ArrayList();
    private List<Double> vectorMdkDFT   = new ArrayList();
    private List<Double> vectorFkDFT    = new ArrayList();

    private List<Double> vectorMkFFT    = new ArrayList();
    private List<Double> vectorMdkFFT   = new ArrayList();
    private List<Double> vectorFkFFT    = new ArrayList();

    private int nMax = 4;

    public void reCalculate(List vector) {
        this.vectorX = vector;
        reCalculate(vector.size());
    }

    @Override
    protected void reCalculate(int nNewMax) {
        clear(
                vectorRe,
                vectorIm,
                vectorMkDFT,
                vectorMdkDFT,
                vectorFkDFT,
                vectorMkFFT,
                vectorMdkFFT,
                vectorFkFFT
        );

        nMax = nNewMax;

        calculateDFT();
        vectorMkDFT = calculateMk();
        vectorMdkDFT = calculateDerivativeMk(vectorMkDFT);
        vectorFkDFT = calculateFk();

        calculateFFT();
        vectorMkFFT = calculateMk();
        vectorMdkFFT = calculateDerivativeMk(vectorMkFFT);
        vectorFkFFT = calculateFk();
    }

    void calculateFFT() {
        System.out.println("4. FFT in");
        Timer timer = new Timer();
        Complex[] complexX = new Complex[vectorX.size()];
        for (int i = 0; i < vectorX.size(); i++) {
            complexX[i] = new Complex(vectorX.get(i), 0);
        }
        Complex[] reimArray = FFT.fft(complexX);

        vectorRe.clear();
        vectorIm.clear();
        for (int i = 0; i < reimArray.length; i++) {
            vectorRe.add(complexX[i].re());
            vectorIm.add(complexX[i].im());
        }
        System.out.println("5. FFT Elapsed time: " + timer + " ms.");

        FileManager.saveVectorToFile(vectorRe, "vectorReFFT", "Vre");
        FileManager.saveVectorToFile(vectorIm, "vectorImF   xFT", "Vim");
    }

    public void calculateDFT() {
        Timer timer = new Timer();
        System.out.println("2. DFT in");
        for (int k = 0; k < nMax; k++) {
            for (int n = 0; n < nMax && n < vectorX.size(); n++) {
                double xn = (double) vectorX.get(n);
                double cosX = Math.cos(-1 * 2 * Math.PI * n * k / nMax);
                double sinX = Math.cos(-1 * 2 * Math.PI * n * k / nMax);

                double Re = xn * cosX;
                double Im = xn * sinX;
                vectorRe.add(Re);
                vectorIm.add(Im);
            }
        }
        System.out.println("3. DFT after Elapsed time : " + timer + " ms.");

        FileManager.saveVectorToFile(vectorRe, "vectorReDFT", "Vre");
        FileManager.saveVectorToFile(vectorIm, "vectorImDFT", "Vim");
//        calculateAlphaZVectors();
    }

//    private void calculateAlphaZVectors() {
//        for (int n = 0; n < vectorRe.size() && n < vectorIm.size(); n++) {
//            double zResult = getZ((double)vectorRe.get(n), (double)vectorIm.get(n));
//            vectorZn.add(zResult);
//            double alphaResult = getAlpha((double)vectorRe.get(n), (double)vectorIm.get(n));
//            vectorAlphan.add(alphaResult);
//        }
//    }

    private List<Double> calculateMk() {
        List<Double> vector = new ArrayList<>();
        for (int k = 0; k < nMax / 2; k++) {
            double re = vectorRe.get(k);
            double im = vectorIm.get(k);
            double re2 = re * re;
            double im2 = im * im;
            double sum = re2 + im2;
            double result = Math.sqrt(sum);
            vector.add(result);
        }
        return vector;
    }

    public List<Double> calculateDerivativeMk(List<Double> mkVec) {
        List<Double> vector = new ArrayList<>();
        for (int k = 0; k < nMax / 2; k++) {
            double result = 10 * log(mkVec.get(k), 10);
            result = (Double.isInfinite(result) ? 0 : result);
            vector.add(result);
        }
        return vector;
    }

    public List<Double> calculateFk() {
        List<Double> vector = new ArrayList<>();
        for (int k = 0; k < nMax / 2; k++) {
            double result = (k * Lab1.Fn) / nMax;
            vector.add(result);
        }
        return vector;
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

    public List<Double> getVectorMkDFT() {
        return vectorMkDFT;
    }

    public List<Double> getVectorMdkDFT() {
        return vectorMdkDFT;
    }

    public List<Double> getVectorFkDFT() {
        return vectorFkDFT;
    }

    public List<Double> getVectorMkFFT() {
        return vectorMkFFT;
    }

    public List<Double> getVectorMdkFFT() {
        return vectorMdkFFT;
    }

    public List<Double> getVectorFkFFT() {
        return vectorFkFFT;
    }

    public List<Double> getVectorRe() {
        return vectorRe;
    }

    public List<Double> getVectorIm() {
        return vectorIm;
    }
}
