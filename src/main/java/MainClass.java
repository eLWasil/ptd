import org.jfree.chart.ChartPanel;
import services.ChartManager;
import services.FrameManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private static List<ChartPanel> panelList = null;

    private static String vecName = "xVector";
    private static String fileName = vecName + "nVector";

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

    static int currentIndex = 0;

    public static void Lab3() {
        panelList = new ArrayList<>(1);

        double ka1 = 0.67;
        double ka2 = 7.03;
        double ka3 = 21.12;
        double kp1 = 1.12;
        double kp2 = Math.PI / 13;
        double kp3 = 44.666;
        Lab3 lab3 = new Lab3(ka1, kp1);
        currentIndex = 0;
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


    public static void main(String[] args) {
        GridLayout layout = new GridLayout(1, 1);
        frameManager.setLayout(layout);

        Lab3();


        if (panelList != null && !panelList.isEmpty()) {
            frameManager.add(panelList.get(currentIndex));
            frameManager.build();

            System.out.println("Filename:  " + fileName);
            chartManager.saveChart(fileName, panelList.get(currentIndex), chartManager.getjFreeChart());

        }

    }

}
