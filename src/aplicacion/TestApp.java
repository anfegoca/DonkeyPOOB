package aplicacion;

import junit.framework.TestCase;

public class TestApp  extends TestCase{
	private Escenario escenario;
	
	public void escenario() {
		escenario = new Escenario();
	}
	public void comprobarEscalera() {
		int[] lowerEnd = {0,523};
		int[] higherEnd= {784,513};
		escenario.addPlataforma(lowerEnd,higherEnd);
		
		escenario.addEscalera(0,1,650);
		
		assertTrue(escenario.getEscaleras().size()==0);
	}

}
