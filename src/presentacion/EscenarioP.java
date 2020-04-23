package presentacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.Barril;
import aplicacion.BoxCollider;
import aplicacion.Escenario;
import aplicacion.Plataforma;
/**
 * Esta clase es la representacion grafica del escenario del juego
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class EscenarioP extends JPanel implements Runnable {


	private DonkeyPoobGUI padre;
	private MarioP mario;
	private PlataformaP plata;
	private Escenario escenario;
	private boolean ejecutando;
	private Thread hilo;
	private boolean enFuncionamiento;
	private HashSet<BarrilP> barriles = new HashSet<BarrilP>();
	private BarrilP barril;
	int ide=0;
	int cont=0;

	/**
	 * Constructor de la clase
	 * @param padre Objeto que contiene al escenario
	 */
	public EscenarioP(DonkeyPoobGUI padre) {
		super();
		hilo = new Thread(this);
		this.padre = padre;
		escenario = new Escenario();
		dibujarPlataformas();
		dibujarEscaleras();
		mario = new MarioP();
		setLayout(null);
		ejecutando = true;
		add(mario);
		enFuncionamiento = false;
		prepareAcciones();
		setBackground(Color.black);
		
		
	}
	public void estadoJuego(boolean x) {
		if(x) {
			enFuncionamiento=true;
			hilo.start();
		}else {
			enFuncionamiento=false;
		}
		
	}
	/**
	 * dibuja las plataformas
	 */
	public void dibujarPlataformas() {
		ArrayList plataformas = escenario.getPlataformas();
		for(int i = 0; i<plataformas.size();i+=4) {
			PlataformaP plat = new PlataformaP((int)plataformas.get(i)+35,(int)plataformas.get(i+1),(double)plataformas.get(i+2),(double)plataformas.get(i+3),this);
		}		
	}
	/**
	 * Dibuja las escaleras
	 */
	public void dibujarEscaleras() {
		ArrayList escaleras= escenario.getEscaleras();

		for(int i=0;i<escaleras.size();i+=3) {
			EscaleraP escalera = new EscaleraP((int)escaleras.get(i),(int)escaleras.get(i+1),(int)escaleras.get(i+2),this);
		}

	}

	public void actualizarBarriles() {

		int  pos = escenario.getNumBarriles();
		if (barriles.size()>pos) {
			
			eliminarBarriles();
		}
		if (pos>barriles.size()) {
			
			BarrilP barril = new BarrilP(ide);
			add(barril);
			barriles.add(barril);
			//System.out.println(ide+" pre");
			ide+=1;
		}
	
		for (BarrilP b : barriles) {
			int [] posi=escenario.consultarPosBarril(b.getId());
			b.move(posi[0],posi[1]-25);
			b.next();
			
		}
		

	}
	private void eliminarBarriles() {
		
		for (BarrilP b: barriles) {
			if (b.getId()==escenario.getUltimo()) {

				remove(b);
				barriles.remove(b);
				break;
			}
		}

		
			
	}
	/**
	 * Actualiza la posición de mario
	 */
	public void actualizarMario() {
		int[] pos=escenario.getPositionMario();
		mario.move(pos[0],pos[1]-30);
		

	}
	public void actualizarEscenario() {
		
		if (escenario.estado()==true) {
			for (BarrilP b: barriles) {
				remove(b);
			}
			escenario.generarBarriles(escenario);
			ide=0;
			barriles.clear();
			escenario.reiniciar();
		}
	}
	
	private void restart() {
		
		estadoJuego(true);
	}
	/**
	 * Captura los eventos del teclado
	 */
	public void prepareAcciones() {
		padre.addKeyListener(new KeyListener(){
			private final Set<Integer> pressed = new HashSet<Integer>();
			long startTime;
			long endTime;						
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == 'x' && pressed.size()<2) 
				{
					if ((escenario.saltoVerticalMario())) {
						mario.next("salto");
						escenario.saltoVerticalMario();
						mario.aterrizar("vertical");
					}
				}

			}

			@Override
			public synchronized void  keyPressed(KeyEvent e) {
				pressed.add(e.getKeyCode());
				if (pressed.size() == 1) {
					startTime = System.currentTimeMillis();
				}

				if (pressed.size() > 1) {
					// More than one key is currently pressed.
					// Iterate over pressed to get the keys.
					endTime = System.currentTimeMillis();
					if (endTime-startTime<60) {

						if (pressed.contains(39) && pressed.contains(88)) {
							if ((escenario.saltoParabolicoMario('R'))) {

								mario.setDir('R');
								mario.next("salto");
								mario.aterrizar("parabolicoR");
							}

						}
						else if (pressed.contains(37) && pressed.contains(88)) {
							if ((escenario.saltoParabolicoMario('L'))) {

								mario.setDir('L');
								mario.next("salto");

								mario.aterrizar("parabolicoL");
							}

						}
					}

					pressed.clear();


				}

				if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) 
				{  	

					if (escenario.moveHorizontalMario('+')) {
						mario.next("right");




					}
				}


				if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) 
				{ 	

					if (escenario.moveHorizontalMario('-')) {
						mario.next("left");



					}

				}
				if (e.getExtendedKeyCode() == KeyEvent.VK_UP) 
				{
					if(escenario.moveVerticalMario('+')) {
						mario.next("Up");
					}



				}
				if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) 
				{
					if(escenario.moveVerticalMario('-')) {
						mario.next("Down");
					}

				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}



		});

	}


	@Override
	/**
	 * Hilo principal
	 */
	public void  run() {
		escenario.generarBarriles(escenario);
		escenario.choco();
		while (enFuncionamiento) {
			actualizarMario();
			actualizarBarriles();
			actualizarEscenario();
			
		}
		
		

	}
	

		
		
	



	
	
	
}
