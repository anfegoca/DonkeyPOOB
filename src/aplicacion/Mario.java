package aplicacion;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Esta clase define a mario
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Mario extends Personaje {
	protected BoxCollider collider;
	protected boolean inPlat;
	protected int speed;
	protected int lives;
	protected boolean inAir;
	protected boolean inEscalera;
	protected Escenario escenario;
	private int cont=0;
	protected boolean vivo;
	protected Elemento ubicacion;
	protected static boolean pausa;
	Plataforma ubicacionini;

	/**
	 * Constructor de la clase
	 * @param escenario Escenario del juego
	 */
	public  Mario(Escenario escenario,Plataforma ubicacion) {
		
		super(ubicacion.getPuntoInicial()[0]+30,ubicacion.getPuntoInicial()[1]);
		this.escenario=escenario;
		this.ubicacion=ubicacion;
		ubicacionini=ubicacion;
		speed = 10;
		lives = 3;
		inAir=false;
		vivo=true;
		collider = new BoxCollider(30,50);
		collider.setLocation(x+10, y-20);
		pausa=false;
		

	}
	/**
	 * Mueve horizontalmente a mario
	 * @param direction dirección en la cual queremos mover a mario '-' izquierda o '+' derecha
	 * @return true si la accion se completó
	 */
	public boolean moveHorizontal(char direction){
		boolean res=false;
		if (ubicacion.getTipo()=="Escalera" && (ubicacion.getY2()==y || ubicacion.getY1()==y)) {
				
				if (ubicacion.getY2()==y) {
					ubicacion= ((Escalera)ubicacion).getP2();
				}
				else {
					ubicacion= ((Escalera)ubicacion).getP1();
				}

		}
		if(inAir!=true && ubicacion.getTipo()=="Plataforma") {
			res = true;
			int [] posicion = ubicacion.next(direction, x,y,speed);
			if (posicion[0]==0 && posicion[1]==0) {
				caer();
			}
			else {
				
				x=posicion[0];
				y=posicion[1];
			}

		}
		
		collider.setLocation(x+10,y-20);
		return res;
	}
	/**
	 * Mueve verticalmente a mario
	 * @param direction direccion en la cual queremos mover a mario, 'u' up o 'd' down
	 * @return true si la accion se completó
	 *
	 */
	public boolean moveVertical(char direction) 
	{
		boolean res=false;
		int [] pos = {0,0};
		if (ubicacion.getTipo()=="Plataforma") {
			Elemento temporal=((Plataforma)ubicacion).nextEscalera(collider);
			if (temporal!=null) {
				res=true;
				ubicacion=temporal;
			}	
		}
		else if (ubicacion.getTipo()=="Escalera") {
			res=true;
		}
		if (res==true) {
			pos = ubicacion.next(direction, x, y, speed);
		}
		
		if (pos[0]==0 && pos[1]==0) {
			res=false;
		}
		else {
			x=pos[0];
			y=pos[1];
			
		}
		
		collider.setLocation(x+10,y-20);
		return res;
	}
	/**
	 * Hace que mario salte verticalmente
	 * @return true si la accion se completó
	 */
	public boolean saltarVertical(){
		boolean res=false;
		if(!inAir) {
			res=true;
			inAir=true;
			Timer timer2 = new Timer();
			TimerTask tarea2 = new TimerTask() {
				@Override
				public void run() {
					if (cont==80) {
						inAir=false;
						timer2.cancel();
					}
					if (cont<40) {
						y--;
					}
					else if (cont>40) {
						y++;
					}
					collider.setLocation(x+10,y-20);
					Plataforma temp=escenario.enPlataforma(x, y);
					if (temp!=null && cont>40) {
						inAir=false;
						ubicacion=temp;
						timer2.cancel();
					}
					cont+=1;
				}
			};

			timer2.schedule(tarea2, 0, 5);
			cont=0;
		}
		
		return res;

	}
	/**
	 * hace que mario salte parabolicamente
	 * @param direction direccion en la cual va a saltar 'R' derecha o 'L' izquierda
	 * @return true si la accion se completó
	 */
	public boolean saltoParabolico(char direction){
		boolean res = false;
		if(!inAir) {
			res=true;
			y-=5;

			int pro=y;
			int pro2=x;
			inAir=true;
			Timer timer2 = new Timer();
			TimerTask tarea2 = new TimerTask() {
				@Override
				public void run() {				
					x++;
					y=-((int)((x-pro2)-(Math.pow(x-pro2, 2)/130)))+pro;
					collider.setLocation(x+10,y-20);
					Plataforma temp = escenario.enPlataforma(x, y);
					if(temp!=null) {
						ubicacion=temp;
						timer2.cancel();
						inAir=false;
					}
				}

			};
			TimerTask tarea3 = new TimerTask() {
				@Override
				public void run() {				
					x--;
					y=-((int)((-(x-pro2))-(Math.pow(-(x-pro2), 2)/130)))+pro;
					collider.setLocation(x+10,y-20);
					Plataforma temp= escenario.enPlataforma(x, y);
					if(temp!=null) {
						ubicacion=temp;
						timer2.cancel();
						inAir=false;
					}
				}

			};

			if (direction=='R') {timer2.schedule(tarea2,0,5);}
			else {timer2.schedule(tarea3,0,5);}
		}

		return res;

	}
	/**
	 * Retorna la posicion actual de mario
	 * @return arreglo con las coordenas 'x' y 'y' de mario
	 */
	public int [] getPosition() {
		int []position = {x,y};
		return position;
	}
	/**
	 * Dice si mario está en el aire o no
	 * @return true si mario está en aire de lo contrario false
	 */
	public boolean enAire() {
		return inAir;
	}
	/**
	 * Dice si mario se encuentra vivo
	 * @return true si mario está vivo o no
	 */
	public boolean getEstado() {
		return vivo;
	}
	public void caer() {
		
		inAir=true;
		Timer timer2 = new Timer();
		TimerTask tarea2 = new TimerTask() {
			@Override
			public void run() {				
				y++;
				collider.setLocation(x+10,y-20);
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

	/**
	 * Dice si mario está sobre una plataforma o no
	 * @return inPlat true si está sobre una plataforma, false si no
	 */
	public boolean getInPlat() {
		return inPlat;
	}
	/**
	 * Permite cambiar el estado de mario para decir si está o no sobre una plataforma
	 * @param x boolean con el cual se actualizará el estado de mario
	 */
	public void setInplat(boolean x) {
		inPlat=x;
	}
	/**
	 * Devuelve el BoxCollider de mario
	 * @return BoxCollider
	 */
	public BoxCollider getBoxCollider() {
		return collider;
	}
	public void setInEscalera(boolean x) {
		inEscalera=x;
	}
	public boolean getInEscalera() {
		return inEscalera;
	}
	public void setUbicacion(Plataforma ubicacion) {
		this.ubicacion=ubicacion;
	}
	public boolean murio(BoxCollider col) {
		boolean res=collider.intersects(col);
		if(res) {
			lives--;
			vivo=false;
		}
		return res;
	}
	public void setEstado(boolean vivo) {
		this.vivo=vivo;
	}
	public static void  setPausa(boolean t) {
		pausa=true;
	}
	public void reiniciarPos() {
		x=ubicacionini.getPuntoInicial()[0]+30;
		y=ubicacionini.getPuntoInicial()[1]-1;
		collider.setLocation(x+10, y-20);
	}

}
