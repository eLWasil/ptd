import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class ChartManager {

    public static final boolean SHOW_LEGENDS = TRUE;
    public static final boolean SHOW_TOOLTIPS = TRUE;
    // http://www.java2s.com/Code/Java/Chart/JFreeChartCombinedXYPlotDemo1.htm
    // Złącz w jedno będzie git

    public ChartPanel makeXYLineChart(String title, String xTitle, List xVector, String yTitle, List yVector) {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                title ,
                xTitle, yTitle,
                createXYDataset(xVector, yVector) ,
                PlotOrientation.VERTICAL ,
                SHOW_LEGENDS , SHOW_TOOLTIPS, false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint(0, Color.RED );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        plot.setRenderer( renderer );
        return chartPanel;
    }

    private XYDataset createXYDataset(List xVector, List yVector) {
        final XYSeries xySeries = new XYSeries( "Test" );
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

}
