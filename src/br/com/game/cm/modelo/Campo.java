package br.com.game.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean marcado = false;	
	private boolean minado = false;
	
	private List<Campo> vizihos = new ArrayList<>();
	

	public Campo(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}
	
	
	
	
	
	

}
