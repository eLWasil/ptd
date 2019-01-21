import org.jfree.chart.ChartPanel;
import services.ChartManager;
import services.FileManager;
import services.FrameManager;

import java.awt.*;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        FrameManager frameManager = new FrameManager("PTD");
        ChartManager chartManager = new ChartManager();

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

        GridLayout layout = new GridLayout(1, 1);
        frameManager.setLayout(layout);

//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 1", "Vector N", nVector, "Vector X", xVector);

//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 2a", "N", nVector, "Xs", zVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 2b", "N", nVector, "Xs", vVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 3", "N", nVector, "Xs", uVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 4a", "N", gTimeVec, "Xs", gaVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 4b", "N", gTimeVec, "Xs", gbVector);
//        ChartPanel panel = chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector);

        Lab2 lab2 = new Lab2();

        lab2.reCalculate(gcVector);
        String vecName = "gcVector";
        List<Double> mkVec = lab2.getVectorMkDFT();
        List<Double> mdkVec = lab2.getVectorMdkDFT();
        List<Double> fkVec = lab2.getVectorFkDFT();
        System.out.println(mkVec.size() + " " + mdkVec.size() + " " + fkVec.size());

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
        ChartPanel panel = chartManager.makeXYLineChart("Lab 2 services.FFT, " + vecName, "f(k)", fkVec2, "M'(k)", mdkVec2);
        String fileName = vecName + "_fft_mdk";
        frameManager.add(panel);


//        Lab4 lab4 = new Lab4();
//        List hzVec = lab4.buildHzVec(lab4.vectorZA, 6);

//        frameManager.add(chartManager.makeXYLineChart("Lab 4. ASK", "xTitle", lab4.vectorN, "yTitle", lab4.vectorZA));
        frameManager.build();
        ChartManager.saveChart(fileName, panel, chartManager.getjFreeChart());


    }

}
