package bancoDeDados;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;




public class LerDadosWeb {
	final String endereco="http://192.168.1.177/";
	final String enderecoLocalTeste="file:///C:/Users/LUIZAO/workspace/MonitoraMotores/resources/dadosWeb.html";
	
	public String LerDadosWeb() {

		final StringBuffer buf = new StringBuffer(1000);
		try {
			HTMLDocument doc = new HTMLDocument() {
				public HTMLEditorKit.ParserCallback getReader(int pos) {
					return new HTMLEditorKit.ParserCallback() {
						public void handleText(char[] data, int pos) {
							buf.append(data);
							buf.append('\n');
						}
					};
				}
			};
			URL url = new URI(endereco).toURL();
			URLConnection conn = url.openConnection();
			Reader rd = new InputStreamReader(conn.getInputStream());
			EditorKit kit = new HTMLEditorKit();
			kit.read(rd, doc, 0);
		} catch (MalformedURLException e) {
		} catch (URISyntaxException e) {
		} catch (BadLocationException e) {
		} catch (IOException e) {
		}
		// Retorna todo o texto encontrado
		String a = "";
		for (int i = 0; i < (buf.length()); i++) {
			a += buf.charAt(i);
//			System.out.println(a);
		}
		
		 StringTokenizer st = new StringTokenizer(";",a);
		
		 while(st.hasMoreTokens()){
			 System.out.println(st.nextToken());
		 }
		 
		return a;
	}
	
	public ArrayList<String> retornaDadosLeituraWeb(){
		
		StringTokenizer st = new StringTokenizer(LerDadosWeb(),";");
		ArrayList<String> array=new ArrayList<String>();
		 while(st.hasMoreTokens()){
			 array.add(st.nextToken());
			 System.out.println(st.nextToken());
		 }
		 ArrayList<String> leituraDados= new ArrayList<String>();
		 
		 leituraDados.add(array.get(0));
//		 leituraDados.add(array.get(3));
//		 leituraDados.add(array.get(5));
//		 leituraDados.add(array.get(7));
//		 leituraDados.add(array.get(9));
//		 leituraDados.add(array.get(11));
//		 leituraDados.add(array.get(13));
		return leituraDados;
	}
	
public Grafico retornaPontoDeLeitura(){
		
		StringTokenizer st = new StringTokenizer(LerDadosWeb(),";");
		List<String> array=new ArrayList<String>();
		 while(st.hasMoreTokens()){
			 array.add(st.nextToken());
		 }
		 Grafico ponto=new Grafico();
		 ponto.setX(Double.parseDouble(array.get(0)));
		
//				 new Date(), 
//				 converteBoolean(Integer.parseInt(array.get(1))), 
//				 converteBoolean(Integer.parseInt(array.get(3))), 
//				 converteBoolean(Integer.parseInt(array.get(5))), 
//				 converteBoolean(Integer.parseInt(array.get(7))), 
//				 converteBoolean(Integer.parseInt(array.get(9))), 
//				 converteBoolean(Integer.parseInt(array.get(11))));
//		 	     ponto.setStatus(new controlaUsinada().getOperacao(ponto));
		 return ponto;
	}

	
	
	
}
