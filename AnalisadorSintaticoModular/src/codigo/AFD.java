package codigo;

public class AFD {
	String nomeSintaxe;
	String arquivoConfig;
	String tabSimbolos;
	int input;//ID do token onde o AFD começa o reconhecimento.
	boolean output;//RECONHECE OU NAO A SINTAXE
	String mensagem="";//Mensagem de erro (se houver)
	
	
	/**
	 * Construtor da classe
	 * @param nome - obrigatorio [nome da sintaxe]
	 * @param nomeArq - arquivo de configuracao
	 * @param nomeTabSimbolos - arquivo tab simbolos
	 */
	public AFD(String nome,String arqConfig, 
			String nomeTabSimbolos) {
		nomeSintaxe=nome;
		arquivoConfig=arqConfig;
		tabSimbolos=nomeTabSimbolos;
		le_config(arquivoConfig);
	}
	
	public void executa(int id_inicio) {
		//existe a possibilidade de um AFD chamar um AFD
		//COMO FAZER ISSO?
	}
	public void le_config(String arquivoConfig) {}
	public void valida_termo() {}
	public void escreve_mensagem() {}

}
