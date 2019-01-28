import commons.VecCommons;
import org.jfree.chart.ChartPanel;
import services.ChartManager;
import services.FrameManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainClass {

    public static final boolean SAVE_ALL = true;

    private static List<Double> nVector = null;
    private static List<Double> xVector = null;
    private static List<Double> zVector = null;
    private static List<Double> vVector = null;
    private static List<Double> uVector = null;

    private static List<Double> gTimeVec = null;
    private static List<Double> gaVector = null;
    private static List<Double> gbVector = null;
    private static List<Double> gcVector = null;

    private static FrameManager frameManager = new FrameManager("PTD");
    private static ChartManager chartManager = ChartManager.getInstance();

    private static String vecName = "xVector";
    private static String fileName = vecName + "nVector";

    static List<ChartPanel> panelList = new ArrayList<>(1);
    static int currentIndex = 0;

    public static void Lab1() {
        Lab1 think = new Lab1(128);
        List nVector = think.getVectorN();
        List xVector = think.getVectorX();
        List zVector = think.getVectorZ();
        List vVector = think.getVectorV(); // ok
        List uVector = think.getVectorU(); // ok

        List gTimeVec = think.getVectorGTime();
        List gaVector = think.getVectorGa();
        List gbVector = think.getVectorGb();
        List gcVector = think.getVectorGc();

//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 1", "Vector N", nVector, "Vector X", xVector);

//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 2a", "N", nVector, "Xs", zVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 2b", "N", nVector, "Xs", vVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 3", "N", nVector, "Xs", uVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 4a", "N", gTimeVec, "Xs", gaVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 4b", "N", gTimeVec, "Xs", gbVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector);


    }

    public static void Lab2() {
        Lab2 lab2 = new Lab2();

        lab2.reCalculate(gcVector);
        String vecName = "gcVector";
        List<Double> mkVec = lab2.getVectorMkDFT();
        List<Double> mdkVec = lab2.getVectorMdkDFT();
        List<Double> fkVec = lab2.getVectorFkDFT();

        List<Double> mkVec2 = lab2.getVectorMkFFT();
        List<Double> mdkVec2 = lab2.getVectorMdkFFT();
        List<Double> fkVec2 = lab2.getVectorFkFFT();

//        services.FileManager.saveVectorToFile(mkVec, "mkVec");
//        services.FileManager.saveVectorToFile(mdkVec, "mdkVec");
//        services.FileManager.saveVectorToFile(fkVec, "fkVec");
//        services.FileManager.saveVectorToFile(xVector, "xVector");
//        services.FileManager.saveVectorToFile(nVector, "nVector");

//        ChartPanel panel = chartManager.makeXYLineChart("DFT M(k)" + vecName, "f(k)", fkVec, "M(k)", mkVec);
//        ChartPanel panel = chartManager.makeXYLineChart("DFT M'(k)" + vecName, "f(k)", fkVec, "M'(k)", mdkVec);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 2 services.FFT, " + vecName, "f(k)", fkVec2, "M(k)", mkVec2);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 2 services.FFT, " + vecName, "f(k)", fkVec2, "M'(k)", mdkVec2);
//
//
    }


    public static void Lab3() {
        double ka1 = 0.67;
        double ka2 = 7.03;
        double ka3 = 21.12;
        double kp1 = 1.12;
        double kp2 = Math.PI / 13;
        double kp3 = 44.666;
        Lab3 lab3 = new Lab3(ka1, kp1);
        int zIdx = 1;

        double[] xVec = lab3.getArr_Fn();
        double[] yVec1 = lab3.getArr_ZaLog();
        double[] yVec2 = lab3.getArr_ZpLog();


        panelList.add(chartManager.makeXYLineChart("Lab3 Modulacja amplitudy dla ka = " + lab3.ka, "Time", xVec, "Za(t)", yVec1));
//        panelList.add(chartManager.makeXYLineChart("Lab3 Modulacja kąta dla kp = " + lab3.kp, "Time", xVec, "Za(t)", yVec2));
//
//        panelList.add(chartManager.makeXYLineChart("Lab3 Mod. amplitudy - vectorZa1", "Time", fnVector, "Za(t)", getVectorZa1));
//        panelList.add(chartManager.makeXYLineChart("Lab3 Mod. amplitudy - vectorZa2", "Time", fnVector, "Za(t)", getVectorZa2));
//        panelList.add(chartManager.makeXYLineChart("Lab3 Mod. amplitudy - vectorZa3", "Time", fnVector, "Za(t)", getVectorZa3));
//
//        panelList.add(chartManager.makeXYLineChart("Lab3 Mod. kąta - vectorZp1" + vecName, "Time", fnVector, "Za(t)", getVectorZp1));
//        panelList.add(chartManager.makeXYLineChart("Lab3 Mod. kąta - vectorZp2" + vecName, "Time", fnVector, "Za(t)", getVectorZp2));
//        panelList.add(chartManager.makeXYLineChart("Lab3 Mod. kąta - vectorZp3" + vecName, "Time", fnVector, "Za(t)", getVectorZp3));

        fileName = (currentIndex == 0 ? "Za" : "Zp") + zIdx;
    }

    public static void Lab4() {
        Lab4 lab4 = new Lab4();

        double[] ask = lab4.getA_ASK();
        double[][] za = lab4.getA_ZaSpectrum();
        double zaPasmo = VecCommons.szerokoscPasma(za[2]);
        System.out.println("Szerokość pasma sygnału zmodulowanego ASK = " + (zaPasmo));

        double[] fsk = lab4.getA_FSK();
        double[][] zf = lab4.getA_ZfSpectrum();
        double zfPasmo = VecCommons.szerokoscPasma(zf[2]);
        System.out.println("Szerokość pasma sygnału zmodulowanego FSK = " + (zfPasmo));

        double[] psk = lab4.getA_PSK();
        double[][] zp = lab4.getA_ZpSpectrum();
        double zpPasmo = VecCommons.szerokoscPasma(zp[2]);
        System.out.println("Szerokość pasma sygnału zmodulowanego PSK = " + (zpPasmo));

        String time = "Time " + lab4.totalTime + "s";
//        panelList.add(chartManager.makeXYLineChart("Lab4 ASK", time, za[0], "Za(t) fs: " + lab4.fs, ask));
//        panelList.add(chartManager.makeXYLineChart("Lab4 FSK", time, zf[0], "Zf(t) fs: " + lab4.fs, fsk));
//        panelList.add(chartManager.makeXYLineChart("Lab4 PSK", time, zp[0], "Zp(t) fs: " + lab4.fs, psk));
//        panelList.add(chartManager.makeXYLineChart("Lab4 Widmo ASK", time, za[0], "Za(t) fs = " + lab4.fs, za[1]));
//        panelList.add(chartManager.makeXYLineChart("Lab4 Widmo FSK", time, zf[0], "Zf(t) fs = " + lab4.fs, zf[1]));
//        panelList.add(chartManager.makeXYLineChart("Lab4 Widmo PSK", time, zp[0], "Zp(t) fs = " + lab4.fs, zp[1]));

    }

    public static void Lab5() {
        Lab5 lab5 = new Lab5();

//        panelList.add(chartManager.makeXYLineChart("Lab5 ASK Xt", "Time", lab5.getArr_Time(), "X(t)", lab5.getArr_XtASK()));
//        panelList.add(chartManager.makeXYLineChart("Lab5 ASK Pt", "Time", lab5.getArr_Time(), "P(t)", lab5.getArr_PtASK()));
//        chartManager.setMinMax(-1, 2);
//        panelList.add(chartManager.makeXYLineChart("Lab5 ASK Mt", "Time", lab5.getArr_Time(), "M(t)", lab5.getArr_MtASK()));

        chartManager.setMinMax(-1, 2);
//        panelList.add(chartManager.makeXYLineChart("Lab5 PSK Xt", "Time", lab5.getArr_Time(), "X(t)", lab5.getArr_XtPSK()));
//        panelList.add(chartManager.makeXYLineChart("Lab5 PSK Pt", "Time", lab5.getArr_Time(), "P(t)", lab5.getArr_PtPSK()));
        panelList.add(chartManager.makeXYLineChart("Lab5 PSK Mt", "Time", lab5.getArr_Time(), "M(t)", lab5.getArr_MtPSK()));


    }


    public static void main(String[] args) {
        GridLayout layout = new GridLayout(1, 1);
        frameManager.setLayout(layout);

//        Lab3();
//        Lab4();
        Lab5();

        if (panelList != null && !panelList.isEmpty()) {
            for (int i = 0; i < panelList.size(); i++) {
                frameManager.add(panelList.get(i));
            }
            frameManager.build();
            frameManager = null;

            String uuid = UUID.randomUUID().toString();

//            System.out.println("Filename:  " + fileName);
            chartManager.saveChart(fileName + uuid, panelList.get(currentIndex), chartManager.getjFreeChart());

        }

    }

}
