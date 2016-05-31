package bancoDeDados;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class TimerLeituraEGravacao {
		static JFrame jf ;
		static DefaultCategoryDataset ds ;
	    Timer timer;

	    public TimerLeituraEGravacao() {
	        
	        timer = new Timer();
	        timer.schedule(new RemindTask(),
	                       0,        //initial delay
	                       1*1000);  //subsequent rate
	     }

	    class RemindTask extends TimerTask {
	        
	        public void run() {
	              DaoGrafico dao=new DaoGrafico();
	              LerDadosWeb ler=new LerDadosWeb();
	              Grafico ponto=ler.retornaPontoDeLeitura();
	              ponto.setData(new Date());
	              System.out.println(ponto.getX());
	              // verifica qual operacao esta sendo realizada
	             if(ponto.getX()!=0.00){
	            	 
	             
	              dao.escrevePontoLeitura(ponto);
	              ds.addValue(ponto.getX(), "maximo", ponto.getData().toString());
	             }
	             
	             jf.repaint();
	              
	       }
	        
	        public void stopTimer(){
	        timer.cancel();
	        }

	    }
	    
	    public static void main(String args[]) {
	    	TimerLeituraEGravacao t= new TimerLeituraEGravacao();
	    	jf=new JFrame();
	    	jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    	jf.setSize( 500, 500 );

	    	
	    	
	    	ds = new DefaultCategoryDataset();
	    	ArrayList<Grafico> g=(ArrayList<Grafico>) new DaoGrafico().readAll();
	    	
	    	int i=0;
	    	for (int j = g.size()-1; j > 40; j--) {
				Grafico grafico=g.get(j);
				ds.addValue(grafico.getX(), "maximo", grafico.getData().toString());
			}
//	    	for (Grafico grafico : g) {
//	    		i++;
//	    	ds.addValue(grafico.getX(), "maximo", grafico.getData().toString());
//	    	
//			}
//	    	
	    	JFreeChart chart = ChartFactory.createLineChart(
	    	        "Linha de Produto Quimico",				
	    	        "Horario",
	    	        "Valores", ds,
	    	        PlotOrientation.VERTICAL, true, true, true );
	    	chart.setBackgroundPaint(Color.white);
	    	chart.getCategoryPlot().setBackgroundPaint(Color.white);
	    	
	    	CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();
	    	renderer.setSeriesPaint( 0, Color.MAGENTA);
	    	
	    	
//	    	renderer.setSeriesPaint( 1, Color.YELLOW );
//	    	renderer.setSeriesPaint( 2, new Color( 0, 100, 155 ) );
	    	ChartPanel panel = new ChartPanel( chart );
	    	panel.setBackground(Color.white);
	    	jf.add( panel, BorderLayout.CENTER );
	    	jf.setVisible( true );
	    	
	    	
	    	
	    	
	    }
}