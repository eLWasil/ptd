import java.awt.*;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        FrameManager frameManager = new FrameManager("Okienko");
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

        GridLayout layout = new GridLayout(2, 2);
        frameManager.setLayout(layout);

        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 1", "N", nVector, "Xs", xVector));
//        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 2a", "N", nVector, "Xs", zVector));
//        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 2b", "N", nVector, "Xs", vVector));
//        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 3", "N", n2Vector, "Xs", uVector));
//        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 4a", "N", gTimeVec, "Xs", gaVector));
//        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 4b", "N", gTimeVec, "Xs", gbVector));
//        frameManager.add(chartManager.makeXYLineChart("Lab 1, zadanie 4c", "N", gTimeVec, "Xs", gcVector));

        Lab2 lab2 = new Lab2();

        lab2.reCalculate(xVector);
        String vecName = "xVector";
        List<Double> mkVec = lab2.getVectorMkDFT();
        List<Double> mdkVec = lab2.getVectorMdkDFT();
        List<Double> fkVec = lab2.getVectorFkDFT();

        List<Double> mkVec2 = lab2.getVectorMkFFT();
        List<Double> mdkVec2 = lab2.getVectorMdkFFT();
        List<Double> fkVec2 = lab2.getVectorFkFFT();

        FileManager.saveVectorToFile(mkVec, "mkVec");
        FileManager.saveVectorToFile(mdkVec, "mdkVec");
        FileManager.saveVectorToFile(fkVec, "fkVec");
        FileManager.saveVectorToFile(xVector, "xVector");
        FileManager.saveVectorToFile(nVector, "nVector");


//        frameManager.add(chartManager.makeXYLineChart("Lab 2 DFT, " + vecName + " Mk", "f(k)", fkVec, "mk", mkVec));
//        frameManager.add(chartManager.makeXYLineChart("Lab 2 DFT, " + vecName + " M'k", "f(k)", fkVec, "mdk", mdkVec));
//
//        frameManager.add(chartManager.makeXYLineChart("Lab 2 FFT, " + vecName + " Mk", "f(k)", fkVec2, "mk", mkVec2));
//        frameManager.add(chartManager.makeXYLineChart("Lab 2 FFT, " + vecName + " M'k", "f(k)", fkVec2, "mdk", mdkVec2));

//        vecName = "uVector";
//        lab2.reCalculate(uVector);
//        mkVec = lab2.getVectorMk();
//        mdkVec = lab2.getVectorMdk();
//        fkVec = lab2.getVectorFk();
//        frameManager.add(chartManager.makeXYLineChart("Lab 2, " + vecName + " Mk", "f(k)", fkVec, "mkVec", mkVec));
//        frameManager.add(chartManager.makeXYLineChart("Lab 2, " + vecName + " M'k", "f(k)", fkVec, "mdkVec", mdkVec));


        //        Lab4 lab4 = new Lab4();
//        List hzVec = lab4.buildHzVec(lab4.vectorZA, 6);

//        frameManager.add(chartManager.makeXYLineChart("Lab 4. ASK", "xTitle", lab4.vectorN, "yTitle", lab4.vectorZA));

        frameManager.build();

    }

}
