package br.com.game.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.game.cm.execao.ExplosaoException;

 
class CampoTeste {
	
	Campo campo ;
@BeforeEach
void  iniciarCampo() {

	campo = new Campo(3,3);
	
}
	@Test
	void testeVizinhoRealDistanciaE() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}	@Test
	void testeVizinhoRealDistanciaD() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistanciaTopo() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeVizinhoRealDistanciaBaixo() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	@Test
	void testeNaoVizinhoDistancia1() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinhoDistancia2() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeNaoVizinhoDistancia3() {
		Campo vizinho = new Campo(8,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	 void  testeValorPadraoMarcado() {
		assertFalse(campo.isMarcado());
		
	}
	@Test
	 void testeAternarMarcado() {
		campo.alternarMarcado();
		assertTrue(campo.isMarcado());
		
	}
	
	@Test
	 void testeAternarDuasChamadasMarcado() {
		campo.alternarMarcado();
		campo.alternarMarcado();
		assertFalse(campo.isMarcado());
		
	}
	@Test
	 void testeAbrirNaoMinadoNaoMacado() {	 
		assertTrue(campo.abrir());		
	}
	@Test
	 void testeAbrirNaoMinadoMacado() {
		campo.alternarMarcado();
		assertFalse(campo.abrir());		
	}
	
	@Test
	 void testeAbrirMinadoMacado() {
		campo.alternarMarcado();
		campo.minar();
		assertFalse(campo.abrir());		
	}
	
	@Test
	 void testeAbrirMinadoNaoMacado() {		 
		campo.minar();
		assertThrows(ExplosaoException.class,
				()-> campo.abrir());
			
	}
	
	@Test
	 void testeAbrirComVizinho() {
		Campo campo11  = new Campo(1,1);		
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);		 
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());		
	}
	
	@Test
	 void testeAbrirComVizinhoMinado() {
		Campo campo11  = new Campo(1,1);
		Campo campo12  = new Campo(1,1);
		campo12.minar();
		
		Campo  campo22 = new Campo(2,2);
		campo22.adicionarVizinho(campo11);				
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);		 
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());		
	}
	
	@Test
	void testeobjetivoAlcançado() {
		
		Campo campo11  = new Campo(1,1);
		Campo campo12  = new Campo(1,1);
		Campo campo13  = new Campo(1,1);
		Campo campo14  = new Campo(1,1);
		
		campo11.minar();
		campo.alternarMarcado();
		campo12.isMarcado();
		
		campo.objetivoAlcançado();
		System.out.println("Teste:"+campo11+" "+ campo12);
		
	}
	
    @Test
    public void testObjetivoAlcancadoDesvendado() {
        // Cenário: Campo não minado e aberto
        Campo campo = new Campo(1, 1);
        campo.abrir();
        assertFalse(campo.isMarcado()); // Certificando-se de que não está marcado
        assertFalse(campo.isFechado()); // Certificando-se de que está aberto
        assertFalse(campo.minasNaVizinhanca() > 0); // Certificando-se de que não há minas na vizinhança
        
        assertTrue(campo.objetivoAlcançado());
    }

    @Test
    public void testObjetivoAlcancadoProtegido() {
        // Cenário: Campo minado e marcado
        Campo campo = new Campo(2, 2);
        campo.minar();
        campo.alternarMarcado();
        
        assertFalse(campo.isAberto()); // Certificando-se de que está fechado
        assertTrue(campo.isMarcado()); // Certificando-se de que está marcado
        assertTrue(campo.minasNaVizinhanca() == 0); // Certificando-se de que não há minas na vizinhança
        
        assertTrue(campo.objetivoAlcançado());
    }

    @Test
    public void testObjetivoNaoAlcancado() {
        // Cenário: Campo minado e não marcado
        Campo campo = new Campo(3, 3);
        campo.minar();
        
        assertFalse(campo.isAberto()); // Certificando-se de que está fechado
        assertFalse(campo.isMarcado()); // Certificando-se de que não está marcado
        assertTrue(campo.minasNaVizinhanca() == 0); // Certificando-se de que não há minas na vizinhança
        
        assertFalse(campo.objetivoAlcançado());
    }
}
