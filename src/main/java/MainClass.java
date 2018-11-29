import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        FrameManager frameManager = new FrameManager("Okienko");
        ChartManager chartManager = new ChartManager();

        Lab1 think = new Lab1(100);
        List nVector = think.getVectorN();
        List xVector = think.getVectorX();
        List zVector = think.getVectorZ();
        List vVector = think.getVectorV();
        List uVector = think.getVectorU();

        List gTimeVec = think.getVectorGTime();
        List gaVector = think.getVectorGa();
        List gbVector = think.getVectorGb();
        List gcVector = think.getVectorGc();

        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 1", "N", nVector, "Xs", xVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 2a", "N", nVector, "Xs", zVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 2b", "N", nVector, "Xs", vVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 3", "N", nVector, "Xs", uVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 4a", "N", gTimeVec, "Xs", gaVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 4b", "N", gTimeVec, "Xs", gbVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector);

        Lab2 lab2 = new Lab2(xVector);
        List alphaVec = lab2.getVectorAlphan();
        List zVec = lab2.getVectorZn();
        List mkVec = lab2.getVectorMk();
        List deriMkVec = lab2.getVectorDeriMk();

//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 2, zadanie 1", "N", zVec, "Xs", alphaVec);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector);
//        ChartPanel chartPanel = chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector);

        frameManager.add(chartPanel);
        frameManager.add(chartPanel);
        frameManager.build();

    }

}
