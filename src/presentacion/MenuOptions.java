package presentacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Esta clase representa el menu de opciones
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class MenuOptions extends Menu {
	
	private JLabel comming;
	private JLabel back;
	
	/**
	 * Constructor de la clase
	 * @param padre Objeto que contiene al menu de opciones
	 */
	public MenuOptions(DonkeyPoobGUI padre) {
		super(padre);
		
		comming= new JLabel(new ImageIcon("recursos/texturas/comming.png"));
		back = new JLabel(new ImageIcon("recursos/texturas/back.png"));
		
		comming.setBounds(200, 400, 400, 50);
		back.setBounds(-100, 500, 400, 50);
		
		
		add(comming);
		add(back);
	}
	/**
	 * Prepara las acciones del menu de opciones
	 */
	public void prepareAcciones() {
    	back.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			padre.cambiar("principal");
    		}
    	
    	});
    	
		
	}
}
