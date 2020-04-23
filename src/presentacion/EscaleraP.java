package presentacion;

import javax.swing.JPanel;
/**
 * Esta clase es la representacion grafica de la escalera
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class EscaleraP {
	
	private JPanel padre;
	
	/**
	 * Constructor de la clase
	 * @param x punto en x donde se posicionará la escalera
	 * @param y1 punto inicial de la escalera
	 * @param y2 punto final de la escalera
	 * @param padre panel que contiene a la escalera
	 */
	public EscaleraP (int x,int y1,int y2, JPanel padre) {
		this.padre=padre;
		Parte parte = new Parte("inicial");
		parte.move(x, y1);
		padre.add(parte);
		
		for (int i=y1+15;i<=y2-15;i+=15) {
			parte = new Parte("medio");
            parte.move(x,i);
            padre.add(parte);
		}
		parte = new Parte("final");
		parte.move(x, y2);
		padre.add(parte);
	}
}
