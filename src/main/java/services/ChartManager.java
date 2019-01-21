package services;

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

    public final ChartPanel makeXYLineChart(String title, String xTitle, List xVector, String yTitle, List yVector) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        this.jFreeChart = ChartFactory.createXYLineChart(
                title ,
                xTitle, yTitle,
                createXYDataset(xVector, yVector) ,
                PlotOrientation.VERTICAL ,
                SHOW_LEGENDS , SHOW_TOOLTIPS, false);

        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setPreferredSize(new java.awt.Dimension( (int)Math.round(width), (int)Math.round(height)));
        final XYPlot plot = jFreeChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0 , new BasicStroke(1.0f));
        plot.setRenderer(renderer);


        return chartPanel;
    }

    private XYDataset createXYDataset(List xVector, List yVector) {
        final XYSeries xySeries = new XYSeries( "NeedToBeHere" );


        for (int i = 0; i < xVector.size() && i < yVector.size(); i++) {
            if (xVector.get(0) instanceof Integer) {
                if (yVector.get(0) instanceof Integer) {
                    xySeries.add((int)xVector.get(i), (int)yVector.get(i));
                } else if (yVector.get(0) instanceof Double) {
                    xySeries.add((int)xVector.get(i), (double)yVector.get(i));
                }
            } else if (xVector.get(0) instanceof Double) {
                if (yVector.get(0) instanceof Integer) {
                    xySeries.add((double)xVector.get(i), (int)yVector.get(i));
                } else if (yVector.get(0) instanceof Double) {
                    xySeries.add((double)xVector.get(i), (double)yVector.get(i));
                }
            }
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(xySeries);

        final XYSeries min = new XYSeries( "Min" );
        min.add( 0.0 , -10.0 );
        final XYSeries max = new XYSeries( "Max" );
        max.add( 0.0 , 10.0 );

        dataset.addSeries(min);
        dataset.addSeries(max);

        return dataset;
    }

    public static void saveChart(final String fileName, final ChartPanel chartPanel, final JFreeChart jFreeChart) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName + ".png");
            ChartUtilities.writeChartAsPNG(
                    outputStream,
                    jFreeChart,
                    chartPanel.getWidth(),
                    chartPanel.getHeight()
            );
            outputStream.close();
        } catch (IOException e) {
            System.out.println("IOException: " + fileName + " \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void saveMultiple(final JFreeChart jFreeChart, List<ChartPanel> panels) {
        if (instance == null) {
            new ChartManager();
        }
        List<String> filenames = instance.getFilenames();

        System.out.println("save multiple before loop");

        for (int i = 0; i < panels.size() && i < filenames.size(); i++) {
            saveChart(filenames.get(i), panels.get(i), jFreeChart);
            System.out.println("Saving " + filenames.get(i) + " - done.");
        }

        instance.getFilenames().clear();
    }

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
