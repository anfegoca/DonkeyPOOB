package aplicacion;

import java.util.Timer;
import java.util.TimerTask;

public class Rojo extends Barril{
	
	public Rojo(Escenario escenario,Plataforma ubicacion,int identificador) {
		super(escenario,ubicacion,identificador);
	}
	public void iniciar() {
		caer();
		
		
	}
	public void caer() {
		int [] dim = escenario.getDimension();
		inAir=true;
		Timer timer2 = new Timer();
		TimerTask tarea2 = new TimerTask() {
			@Override
			public void run() {		
				if (pausa) {
					timer2.cancel();
				}
				y++;
				collider.setLocation(x+10,y-20);
				if (y==dim[1]+10) { 
					elimineme();
					escenario.setUltimo(identificador);
					timer2.cancel();
				}

			}
		};
		timer2.schedule(tarea2, 0, 5);
	}
	
}
