package codigo;

public class AnalisadorSintatico {

	
	//TODAS AS SINTAXES DEVEM SER CARREGADAS
	//ANTES DA EXECUCAO
	public AnalisadorSintatico() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		AnalisadorSintatico asin = 
				new AnalisadorSintatico();
		AFD afd = new AFD("SINTCOMPARACAO", "comparacaoConfig.txt", "saida2.csv", 1);
		System.out.println("parou na linha: "+afd.valida_termo());
	}

}
