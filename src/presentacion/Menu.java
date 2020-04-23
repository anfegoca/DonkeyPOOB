package presentacion;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Esta clase representa un menu
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public abstract class Menu extends JPanel{

	protected JLabel logo;
	protected JLabel back;
	protected DonkeyPoobGUI padre;
	
	/**
	 * Constructor de la clase
	 * @param padre Objeto que contiene el menu
	 */
	public Menu(DonkeyPoobGUI padre) {
		
		super();
		this.padre=padre;
		logo = new JLabel(new ImageIcon("recursos/texturas/logo.png"));
		back = new JLabel(new ImageIcon("recursos/texturas/back.png"));
		
		logo.setBounds(200, -100, 400, 400);
		back.setBounds(-100, 500, 400, 50);
		
		add(logo);
		add(back);
		setLayout(null);
	}
	/**
	 * Cambia el color del fondo del menu
	 * @param color
	 */
	public void changeColor(Color color) {
		this.setBackground(color);
	}
	/**
	 * Metodo que prepara las acciones del menu
	 */
	public abstract void prepareAcciones();
}
