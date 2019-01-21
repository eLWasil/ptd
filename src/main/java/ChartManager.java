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
import java.util.List;

import static java.lang.Boolean.TRUE;

public class ChartManager {

    public static final boolean SHOW_LEGENDS = TRUE;
    public static final boolean SHOW_TOOLTIPS = TRUE;

    private JFreeChart jFreeChart = null;

    public ChartPanel makeXYLineChart(String title, String xTitle, List xVector, String yTitle, List yVector) {
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
        renderer.setSeriesStroke(0 , new BasicStroke(2.0f));
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
        } catch (IOException e) {
            System.out.println("IOException: " + fileName + " \n" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public final JFreeChart getjFreeChart() {
        return jFreeChart;
    }
}
