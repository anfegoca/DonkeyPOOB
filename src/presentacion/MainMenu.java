package presentacion;

import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Esta clase es el menu principal del juego
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class MainMenu extends JPanel{
	private JLabel logo;
	private JLabel play;
	private JLabel load;
	private JLabel options;
	private JLabel exit;
	private DonkeyPoobGUI padre;



	/**
	 * Constructor de la clase
	 * @param padre objeto que contiene al menu principal
	 */
	public MainMenu(DonkeyPoobGUI padre) {

		super();
		this.padre = padre;
		logo = new JLabel(new ImageIcon("recursos/texturas/logo.png"));
		play = new JLabel(new ImageIcon("recursos/texturas/play.png"));
		load = new JLabel(new ImageIcon("recursos/texturas/load.png"));
		options = new JLabel(new ImageIcon("recursos/texturas/options.png"));
		exit = new JLabel(new ImageIcon("recursos/texturas/exit.png"));
		logo.setBounds(200, -100, 400, 400);
		play.setBounds(200, 250, 400, 50);
		load.setBounds(200, 300, 400, 50);
		options.setBounds(200, 350, 400, 50);
		exit.setBounds(200, 450, 400, 50);
		logo.setBounds(200, -100, 400, 400);
		play.setBounds(200, 250, 400, 50);
		load.setBounds(200, 300, 400, 50);
		options.setBounds(200, 350, 400, 50);
		exit.setBounds(200, 450, 400, 50);


		setLayout(null);

		add(logo);
		add(play);

		add(load);
		add(options);
		add(exit);	


	}
	/**
	 * Metodo que captura los eventos de los botones
	 */
	public void prepareAcciones() {
		play.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				padre.cambiar("play");

			}
		});

		load.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				System.out.print("l3ad");

			}
		});
		options.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				padre.cambiar("opciones");

			}
		});
		exit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				padre.salga();
			}

		});
	}
	/**
	 * Cambia el fondo del menu
	 * @param color color con el cual se va a reemplazar el color actual
	 */
	public void changeColor(Color color) {
		this.setBackground(color);
	}

}
