package presentacion;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Esta clase representa el menu de play
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class MenuPlay  extends Menu{
	private JLabel one;
	private JLabel pvp;
	private JLabel pvc;
	private JLabel cvc;
	
	/**
	 * Constructor de la clase
	 * @param padre Objeto que contiene al menu
	 */
	public MenuPlay(DonkeyPoobGUI padre) {
		super(padre);
		one = new JLabel(new ImageIcon("recursos/texturas/1p.png"));
		pvc = new JLabel(new ImageIcon("recursos/texturas/pvc.png"));
		pvp = new JLabel(new ImageIcon("recursos/texturas/pvp.png"));
		cvc = new JLabel(new ImageIcon("recursos/texturas/cvc.png"));
		one.setBounds(200, 250, 400, 50);
	    pvc.setBounds(200, 300, 400, 50);
	    pvp.setBounds(200, 350, 400, 50);
	    cvc.setBounds(200, 400, 400, 50);
		add(one);
		add(pvc);
		add(pvp);
		add(cvc);
	}
	
	/**
	 * prepara las acciones del menu
	 */
	public void prepareAcciones() {
    	back.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			padre.cambiar("principal");
    		}
    	
    	});
    	
    	one.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent e) {
    			padre.cambiar("escenario");
    			
    		}
    	
    	});
    	
		
	}

}
