package br.com.game.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo>campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associaVizinhos();
		sortearMinas();
	}



	private void gerarCampos() {
		
		for (int i = 0; i < linhas; i++) {
			 for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i,j));
			}			
		}		
	}
	
	private void associaVizinhos() {	
		
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
			
		}
	}
	
	private void sortearMinas() {
		long  minasAmadas = 0;
		
		Predicate<Campo> minado = c -> c.isMinado();
		do {			
			minasAmadas = campos.stream().filter(minado).count();
			int aleatorio =(int) Math.random() * campos.size();
			campos.get(aleatorio).minar();
		}while(minasAmadas < minas);		
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c-> c.objetivoAlcan�ado());
	}
	
	public void reininciar() {		
		campos.stream().forEach(c-> c.reiniciar());
		sortearMinas();
	}

	public String toString() {
		return "";
	}
	
}
