package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.CardLayout;


/**
 * Esta es la clase principal del juego, desde la que se hace la interfaz grafica
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class DonkeyPoobGUI extends JFrame{

	public static final int ANCHO = 800;
	public static final int ALTO = 600;
	private MainMenu menuprincipal;
	private JPanel prueba;
	private MenuPlay menuplay;
	private CardLayout c;
	private MenuOptions menuopciones;
	private EscenarioP escenario;
	private static DonkeyPoobGUI donkeyPoob;

	/**
	 * Constructor de la clase
	 */
	private DonkeyPoobGUI(){
		super("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prepareElementos();
		prepareAcciones();
	}
	public static DonkeyPoobGUI getGUI() {
		if(donkeyPoob == null) {
			donkeyPoob = new DonkeyPoobGUI();
		}
		return donkeyPoob;
	}
	/**
	 * Metodo que prepara los elementos
	 */
	public void prepareElementos(){
		setSize( ANCHO,ALTO);
		setLocationRelativeTo(null);
		prueba= new JPanel();
		prueba.setBackground(Color.cyan);
		c= new CardLayout();
		prueba.setLayout(c);
		add(prueba); 
		prepareElementosMenu();
		prepareElementosOptions();
		prepareElementosPlay();
		prepareElementosEscenario();


	}
	/**
	 * Prepara los elementos del menu de play
	 */
	private void prepareElementosPlay() 
	{	
		menuplay = new MenuPlay(this);
		menuplay.changeColor(Color.black);
		prueba.add(menuplay,"play");		
	}
	/**
	 * Prepara los elementos del menu de opciones
	 */
	private void prepareElementosOptions() 
	{	
		menuopciones = new MenuOptions(this);
		menuopciones.changeColor(Color.black);
		prueba.add(menuopciones,"opciones");						
	}
	/**
	 * Prepara los elementos del escenario
	 */
	public void prepareElementosEscenario() {
		escenario = new EscenarioP(this);
		prueba.add(escenario,"escenario");
	}
	/**
	 * Prepara los elementos del menu principal
	 */
	public void prepareElementosMenu() {

		menuprincipal = new MainMenu(this);
		menuprincipal.changeColor(Color.black);
		prueba.add(menuprincipal,"principal");
	}
	/**
	 * Termina la ejecucion del juego
	 */
	public void salga() {
		setVisible(false);
		System.exit(0);
	}
	/**
	 * Prepara las acciones del juego
	 */
	public void prepareAcciones() {
		menuprincipal.prepareAcciones();
		menuplay.prepareAcciones();
		menuopciones.prepareAcciones();
	}

	/**
	 * Metodo que cambia las diferentes pantallas
	 * @param nombre nombre de la pantalla que quiere hacer visible
	 */
	public  void cambiar(String nombre) {
		c.show(prueba,nombre);
		if(nombre=="escenario") {
			escenario.estadoJuego(true);
		}
		
	}
	/**
	 * Metodos main
	 * @param args
	 */
	public static void main(String[] args){
		DonkeyPoobGUI interfaz = getGUI();
		interfaz.setVisible(true);     

	}
}
