package presentacion;


import javax.swing.JPanel;
/**
 * Crea la representacion grafica de una plataforma
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class PlataformaP {
	
	private JPanel padre;
	
	/**
	 * 
	 * @param x1 extremo izquierdo de la plataforma
	 * @param x2 extremo derecho de la plataforma
	 * @param m pendiente de la plataforma
	 * @param b punto b de la ecuacion de la recta correspondiente a la plataforma
	 * @param padre Objeto que contiene a la plataforma
	 */
	public PlataformaP(int x1,int x2,double m,double b,JPanel padre) {
		for(int i=x1; i<=x2;i+=25) {
			double y = m * i + b;
            Suelo piso = new Suelo();
            piso.move(i,(int)y);
            padre.add(piso); 
		}
		/*
		double y = m * x2 + b;
        Suelo piso = new Suelo();
        piso.move(x2,(int)y);
        padre.add(piso);
		*/
	}

}
