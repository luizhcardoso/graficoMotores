package bancoDeDados;

public class teste {
public static void main(String[] args) {
	LerDadosWeb ler=new LerDadosWeb();
	Grafico grafico=new Grafico();
	grafico.setY(Double.parseDouble(ler.retornaDadosLeituraWeb().get(0)));
	grafico.setX(1);
	DaoGrafico dao=new DaoGrafico();
	dao.escrevePontoLeitura(grafico);
//	for (Grafico g : ) {
		System.out.println(dao.lerUltimaLinha().getX());
//	}
}
}
