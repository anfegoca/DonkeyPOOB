package aplicacion;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Esta clase define el barril
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Barril  extends Mario{

	protected int identificador;
	public Barril(Escenario escenario,Plataforma ubicacion,int identificador) {
		super(escenario,ubicacion);
		this.identificador=identificador;
		collider=new BoxCollider(25,25);
		

	}
	/**
	 * Hace que el barril empiece a caer 
	 * @param h altura desde donde va a caer el barril
	 */
	public void iniciar() {
		Timer timer = new Timer();
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {
				if (pausa) {
					timer.cancel();
				}
				if (((Plataforma)ubicacion).getEcu()[0]>=0) {
					moveHorizontal('+');
				}
				else {
					moveHorizontal('-');
				}				
			}
			
		};
		timer.schedule(tarea, 0,100);
	}

public void caer() {
		int [] dim = escenario.getDimension();
		inAir=true;
		Timer timer2 = new Timer();
		TimerTask tarea2 = new TimerTask() {
			@Override
			public void run() {	
				
				y++;
				if (y>=dim[1]+10) { 
					elimineme();
					escenario.setUltimo(identificador);
					timer2.cancel();
					collider.setLocation(x+10,y-20);
				
				}
				else if(pausa){
					timer2.cancel();
				}
				Plataforma temp = escenario.enPlataforma(x, y);
				if(temp!=null) {
					ubicacion=temp;
					timer2.cancel();
					inAir=false;
				}
			}
		};
		timer2.schedule(tarea2, 0, 5);
	}

	public int[] getPosition() {
		int[] pos = {x,y};
		return pos;
	}
	public int getId() {
		return identificador;
	}
	public void elimineme() {
		escenario.eliminarBarril(this);
	}



}
