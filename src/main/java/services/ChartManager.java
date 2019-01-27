package services;

import commons.VecCommons;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class ChartManager {

    private static final int MAX_Y = 200;
    private static final int MIN_Y = 100;

    public static ChartManager getInstance() {
        if (instance == null) {
            instance = new ChartManager();
        }
        return instance;
    }

    private static ChartManager instance = null;

    ChartManager() {
        if (instance == null) {
            instance = this;
        }
    }

    public static final boolean SHOW_LEGENDS = TRUE;
    public static final boolean SHOW_TOOLTIPS = TRUE;

    private List<String> filenames = new ArrayList<>();
    private JFreeChart jFreeChart = null;

    public final ChartPanel makeXYLineChart(String title, String xTitle, List<Double> xVector, String yTitle, List<Double> yVector) {
        double[] vec1 = new double[xVector.size()];
        double[] vec2 = new double[yVector.size()];
        for (int i = 0; i < xVector.size() && i < yVector.size(); i++) {
            vec1[i] = xVector.get(i);
            vec2[i] = yVector.get(i);
        }
        return makeXYLineChart(title, xTitle, vec1, yTitle, vec2);
    }

    public final ChartPanel makeXYLineChart(String title, String xTitle, double[] xVector, String yTitle, double[] yVector) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        this.jFreeChart = ChartFactory.createXYLineChart(
                title ,
                xTitle, yTitle,
                createXYDataset(xVector, yVector),
                PlotOrientation.VERTICAL ,
                SHOW_LEGENDS , SHOW_TOOLTIPS, false);

        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setPreferredSize(new java.awt.Dimension( (int)Math.round(width), (int)Math.round(height)));
        final XYPlot plot = jFreeChart.getXYPlot();
        jFreeChart.getLegend().setVisible(false);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setBaseShapesFilled(false);
        renderer.setBaseShapesVisible(false);
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0 , new BasicStroke(0.6f));
        plot.setRenderer(renderer);


        return chartPanel;
    }

    private XYDataset createXYDataset(double[] xVector, double[] yVector) {
        final XYSeries xySeries = new XYSeries( "NeedToBeHere" );

        double maxV = VecCommons.findMax(yVector);
        for (int i = 0; i < xVector.length && i < yVector.length; i++) {
            if (Double.isInfinite(yVector[i])) {
                yVector[i] = maxV + 100;
            }
            xySeries.add(xVector[i], yVector[i]);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xySeries);

        final XYSeries min = new XYSeries( "Min" );
        min.add( xVector[0] , -MIN_Y);
        final XYSeries max = new XYSeries( "Max" );
        max.add( xVector[0], + MAX_Y );

        dataset.addSeries(min);
        dataset.addSeries(max);

        return dataset;
    }

    public void saveChart(final String fileName, final ChartPanel chartPanel, final JFreeChart jFreeChart) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName + ".png");
            ChartUtilities.writeChartAsPNG(
                    outputStream,
                    jFreeChart,
                    chartPanel.getWidth(),
                    chartPanel.getHeight()
            );
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("IOException: " + fileName + " \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

//    public static void saveMultiple(final JFreeChart jFreeChart, List<ChartPanel> panels) {
//        if (instance == null) {
//            new ChartManager();
//        }
//        List<String> filenames = instance.getFilenames();
//
//        System.out.println("save multiple before loop");
//
//        for (int i = 0; i < panels.size() && i < filenames.size(); i++) {
//            saveChart(filenames.get(i), panels.get(i), jFreeChart);
//            System.out.println("Saving " + filenames.get(i) + " - done.");
//        }
//
//        instance.getFilenames().clear();
//    }

    public static void prepareFileNames(String ... files) {
        if (instance == null) {
            System.out.println("Its a singleton class");
            return;
        }

        for (int i = 0; i < files.length; i++) {
            instance.addFilename(files[i]);
        }
    }

    public void addFilename(String filename) {
        this.filenames.add(filename);
    }

    public List<String> getFilenames() {
        return this.filenames;
    }

    public final JFreeChart getjFreeChart() {
        return jFreeChart;
    }
}
