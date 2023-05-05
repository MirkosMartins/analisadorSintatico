package codigo;

public class AnalisadorSintatico {

	
	//TODAS AS SINTAXES DEVEM SER CARREGADAS
	//ANTES DA EXECUCAO
	public AnalisadorSintatico() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String[] vetorTokens = {"AParentesis",
				"NOME","COMPARACAO","NOME","PV",
				"NOME","COMPARACAO","NOME",};
		AnalisadorSintatico asin = 
				new AnalisadorSintatico();
		AFD afd1 = new AFD("SINTCOMPARACAO",
				"comparacaoConfig.txt",
				vetorTokens);
		System.out.println(afd1.executa(1));
		System.out.println(afd1.executa(5));

	}

}
