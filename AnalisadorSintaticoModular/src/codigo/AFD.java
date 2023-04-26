package codigo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class AFD {
	String nomeSintaxe;
	String arquivoConfig;
	String tabSimbolos;
	int input;//ID do token onde o AFD começa o reconhecimento.
	boolean output;//RECONHECE OU NAO A SINTAXE
	String mensagem="";//Mensagem de erro (se houver)
	
	String estadoInicial,eInicial;
	String estadoAtual;
	LinkedList<String> estados = new LinkedList<String>();
	LinkedList<EstadoFinal> estadosFinais = new LinkedList<EstadoFinal>();
	LinkedList<RegraTransicao> regrastransicao = new LinkedList<RegraTransicao>();
	
	
	/**
	 * Construtor da classe
	 * @param nome - obrigatorio [nome da sintaxe]
	 * @param nomeArq - arquivo de configuracao
	 * @param nomeTabSimbolos - arquivo tab simbolos
	 */
	public AFD(String nome,String arqConfig, 
			String nomeTabSimbolos, int linha) {
		nomeSintaxe=nome;
		arquivoConfig=arqConfig;
		tabSimbolos=nomeTabSimbolos;
		input = linha;
		le_config(arquivoConfig);
		
	}
	
	public void executa(int id_inicio) {
		//existe a possibilidade de um AFD chamar um AFD
		//COMO FAZER ISSO?
	}
	public void le_config(String arquivoConfig) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(arquivoConfig));
			String linha = br.readLine();
			int index=0;
			while(linha!=null) {//percorre as linhas
				linha = linha.trim();
				if(index==0) {//primeira linha
					//estou lendo a linha dos nomes dos estados
					String nomesE[] = linha.split(",");
					for(int i=0;i<nomesE.length;i++)
						estados.add(nomesE[i]);//adiciono os nomes no linkedList estados				
				}
				if(index==1) {//segunda linha
					//estou lendo o estado inicial
					estadoInicial = linha;
					estadoAtual = estadoInicial;
					eInicial = estadoInicial;
				}
				if(index==2) {//terceira linha
					//estados finais
					String ef[] = linha.split(",");
					for(int i=0;i<ef.length;i++) {
						//System.out.println(ef[i]);
						String efinal[] = ef[i].split(":");
						EstadoFinal estadoFinal = 
								new EstadoFinal(efinal[0],efinal[1]);
						estadosFinais.add(estadoFinal);
					}
						
				}
				if(index>=3) {//quarta linha em diante
					//lendo as regras de transicao
					String rt[] = linha.split(":");
					RegraTransicao regra = 
							new RegraTransicao(rt[0],rt[1],rt[2]);
					regrastransicao.add(regra);
				}
				linha = br.readLine();
				index++;
			}
			br.close();//fecha o arquivo de conf.
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado.");
		} catch (IOException e) {
			System.out.println("Nao foi possivel abrir o arquivo.");
		}
	}
	
	public int valida_termo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(tabSimbolos));
			int idx=1;
			String linha = br.readLine();
			while(idx != input) {
				linha = br.readLine();
				idx++;
			}
			while((linha = br.readLine())!=null) {
				String colunas[]=linha.split(",");
				System.out.println(colunas[4]);
				String eretorno = percorreAFD(colunas[4]);
				String ef = buscaEFinais(eretorno);
				if(!ef.isEmpty()) {
					System.out.println(ef);
					estadoInicial = eInicial;
				}			
				idx++;
			}
			br.close();
			return idx;
		} catch (FileNotFoundException e) {
			System.out.println("Nao foi possivel encontrar o arquivo");
		} catch (IOException e) {
			System.out.println("Nao foi possivel ler o arquivo");
		}
		return input;
	}
	
	public String percorreAFD(String simbolo) {
		for(int i=0;i<regrastransicao.size();i++) {
			RegraTransicao regra = regrastransicao.get(i);
			if(regra.estadoinicial.equals(estadoAtual)
					&&regra.simbolos.contains(simbolo)) {
				estadoAtual = regra.estadofinal;
				return estadoAtual;
			}
		}
		return "";
	}
	
	private String buscaEFinais(String estado) {
		String mensagem="";
		for(int j=0;j<estadosFinais.size();j++) {
			EstadoFinal ef = estadosFinais.get(j);
			//System.out.println(ef.nomeestado+"contains"+estadoAtual);
			if(ef.nomeestado.equals(estado)) {
				mensagem = ef.tipo;
				break;
			}
		}
		return mensagem;
	}
	public void escreve_mensagem() {
		
	}

}
