package bancoDeDados;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Hello world!
 *
 */
public class ExemploGrafico 
{
    public static void main( String[] args )
    {
    	JFrame jf = new JFrame();
    	jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    	jf.setSize( 500, 500 );
    	
    	DefaultCategoryDataset ds = new DefaultCategoryDataset();
    	ds.addValue( 0, "a", "x" );
    	ds.addValue( 2.3, "a", "y" );
    	ds.addValue( 8, "b", "x" );
    	ds.addValue( 1.5, "b", "y" );
    	ds.addValue( 0.5, "c", "x" );
    	ds.addValue( 7, "c", "y" );
    	JFreeChart chart = ChartFactory.createLineChart(
    	        "Teste",
    	        "Categorias",
    	        "Valores", ds,
    	        PlotOrientation.HORIZONTAL, true, true, true );
    	CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
    	renderer.setSeriesPaint( 0, new Color( 0, 255, 0 ) );
    	renderer.setSeriesPaint( 1, Color.YELLOW );
    	renderer.setSeriesPaint( 2, new Color( 0, 100, 155 ) );
    	ChartPanel panel = new ChartPanel( chart );
    	jf.add( panel, BorderLayout.CENTER );
    	jf.setVisible( true );
    }
}
