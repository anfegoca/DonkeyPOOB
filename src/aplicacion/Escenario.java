package aplicacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Define el Escenario del juego, esta clase coordina todos los elementos
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Escenario {

	private int w;
	private int h;
	private ArrayList<Plataforma> plataformas = new ArrayList<Plataforma>(); 
	private int ultimo;

	//private ArrayList<Bono> bonos = new ArrayList<Bono>();
	private ArrayList<Barril> barriles = new ArrayList<Barril>();
	private Mario mario;
	private boolean pausa;
	//private DonkeyKong mono;
	//private Princesa princesa;
	//private Martillo martillo;
	private int ide=0;
	private boolean flag;


	/**
	 * Constructor de la clase
	 */
	public Escenario() {
		w=800;
		h=600;
		EscenarioClasico();

	}
	/** 
	 * Crea un escenario clasico del juego
	 */
	private void EscenarioClasico() {
		int[] lowerEnd = {0,523};
		int[] higherEnd= {784,513};
		addPlataforma(lowerEnd,higherEnd);

		int[] lowerEnd2 = {26,431};
		int[] higherEnd2= {707,451};

		addPlataforma(lowerEnd2,higherEnd2);

		int[] lowerEnd3 = {78,376};
		int[] higherEnd3= {758,353};
		addPlataforma(lowerEnd3,higherEnd3);
		int[] lowerEnd4 = {27,280};
		int[] higherEnd4= {706,299};
		addPlataforma(lowerEnd4,higherEnd4);
		int[] lowerEnd5 = {76,221};
		int[] higherEnd5= {757,202};
		addPlataforma(lowerEnd5,higherEnd5);

		int[] lowerEnd6 = {27,80};
		int[] higherEnd6= {707,145};
		addPlataforma(lowerEnd6,higherEnd6);



		addEscalera(0,1,650);
		addEscalera(1,2,450);
		addEscalera(1,2,200);
		addEscalera(2,3,500);
		addEscalera(2,3,650);
		addEscalera(3,4,340);
		addEscalera(3,4,200);
		addEscalera(4,5,650);

		mario = new Mario(this,plataformas.get(0));	
		pausa=false;

	}
	public void generarBarriles(Escenario escenario) {

		Timer timer2 = new Timer();
		TimerTask tarea2 = new TimerTask() {
			@Override

			public void run() {
				if (pausa) {
					
					timer2.cancel();
				}
				else if (!flag) {
					if  (ide%3==0) {
						Rojo barril = new Rojo(escenario,plataformas.get(5),ide);
						barril.iniciar();
						escenario.addBarril(barril);
					}
					else {
						Barril barril2 = new Barril(escenario,plataformas.get(5),ide);
						barril2.iniciar();
						escenario.addBarril(barril2);
					}
					ide+=1;
				}



			}
		};

		timer2.schedule(tarea2, 0, 5000);
	}
	/**
	 * Permite agregar una nueva escalera que une a la plataforma p1 y la plataforma p2 en el punto x
	 * @param p1 Plataforma 1
	 * @param p2 Plataforma 2
	 * @param x posicion en el eje x en la cual va la escalera
	 */
	public void addEscalera(int p1, int p2, int x){
		Plataforma plat1 = plataformas.get(p1);
		Plataforma plat2 = plataformas.get(p2);
		try {
		Escalera escalera = new Escalera(plat1,plat2,x);
		plat1.addEscalera(plat2,escalera);
		plat2.addEscalera(plat1,escalera);

		}catch(Exception e) {
			
		}

		

	}
	public void addBarril(Barril barril) {
		barriles.add(barril);
	}
	/**
	 * Permite agregar una nueva plataforma teniendo el punto de inicio y fin
	 * @param lowerEnd arreglo con las coordenas del extremo izquierdo de la plataforma
	 * @param higherEnd arreglo con las coordenas del extremo derecho de la plataforma
	 */
	public void addPlataforma(int[] lowerEnd, int[] higherEnd) {

		Plataforma plataforma = new Plataforma(lowerEnd,higherEnd);
		plataformas.add(plataforma);

	}


	/**
	 * Obtiene las posiciones de las plataformas
	 * @return plataf ArrayList de las posiciones de todas la plataformas del escenario
	 */
	public ArrayList getPlataformas() {
		ArrayList plataf = new ArrayList();
		for(Plataforma p: plataformas) {
			int[] pos = p.getPuntoInicial();
			plataf.add(pos[0]);
			plataf.add(p.getX2());
			plataf.add(p.getM());
			plataf.add(p.getB());
		}
		return plataf;
	}
	/**
	 * Obtiene las posiciones de las escaleras
	 * @return res ArrayList con las posiciones de las escaleras
	 */
	public ArrayList getEscaleras() {
		ArrayList res = new ArrayList();
		HashSet<Escalera> escaleras = new HashSet<Escalera>(); 
		for(Plataforma p: plataformas) {
			for(Escalera e: p.getEscaleras()) {
				int [] posiciones = e.getPosiciones();
				res.add(posiciones[0]);
				res.add(posiciones[1]);
				res.add(posiciones[2]);
			}
		}
		for(Escalera e: escaleras) {
			res.add(e.getPosiciones());
		}
		return res;
	}
	public int getNumBarriles() {
		int res= barriles.size();

		return res;
	}
	public void eliminarBarril(Mario b) {
		barriles.remove((Barril)b);
		
	}

	/**
	 * Usando una posicion se le consulta al escenario si la posicion dada
	 * coincide con la de una plataforma
	 * @param x posicion en x del objeto a consultar
	 * @param y posicion en y del objeto a consultar
	 * @return res retorna true si la posicion coincide con la de una plataforma y false si no
	 */
	public Plataforma enPlataforma(int x, int y) {
		Plataforma res= null;
		for(Plataforma p: plataformas ) {
			if(p.encima(x, y) ) {
				res=p;
				break;
			}
		}
		return res;
	}


	/**
	 * Consulta la posicion de mario
	 * @return position arreglo con la posicion 'x' y 'y' de mario
	 */
	public int[] getPositionMario() {
		int[] position = mario.getPosition();
		return position;
	}
	/**
	 * Hace que mario salte verticalmente
	 * @return true si la accion se completó
	 */
	public boolean saltoVerticalMario() {
		return mario.saltarVertical();
	}
	/**
	 * Hace que mario haga un salto parabolico
	 * @param dir direccion en la cual se va a realizar el salto, 'R' derecha 'L' izquierda
	 * @return true si la accion se completó
	 */
	public boolean saltoParabolicoMario(char dir) {
		return mario.saltoParabolico(dir);
	}
	/**
	 * Hace que mario se mueva horizontalmente
	 * @param dir direccion en la cual se va a mover mario '+' derecha '-' izquierda
	 * @return true si la accion se completó
	 */
	public boolean moveHorizontalMario(char dir) {
		return mario.moveHorizontal(dir);
	}
	/**
	 * Hace que mario se mueva Verticalmente
	 * @param dir direccion en la cual se va a mover mario '+' arriba '-' abajo
	 * @return true si la accion se completó
	 */
	public boolean moveVerticalMario(char dir) {
		return mario.moveVertical(dir);
	}
	/**
	 * Consulta el estado de mario
	 * @return vivo true si mario está vivo, false si está muerto
	 */
	public boolean estadoMario() {
		return mario.getEstado();
	}


	public int[] getDimension() {
		int [] dim = {w,h};
		return dim;
	}
	public int[] consultarPosBarril(int identificador) {
		
		int[] pos = {0,0};

		for (Barril b:barriles) {
			if (b.getId()==identificador) {
				pos = b.getPosition();
				break;
			}

		}

		return pos;
	}
	/**
	 * Verifica si mario choca con un barril
	 * @return vivo true si mario está vivo, false si está muerto
	 */
	public void choco() {

		Timer timer2 = new Timer();
		TimerTask tarea2 = new TimerTask() {
			@Override
			public void run() {		
				
				flag=true;
				for(Barril b: barriles) {

					if(mario.murio(b.getBoxCollider())) {
						stop();
						break;
						
					}

				}
				flag=false;
			}
		};
		timer2.schedule(tarea2, 0, 1);

	}
	public void stop() {
		pausa=true;
		Barril.setPausa(true);

	}

	public void setUltimo(int ult) {
		ultimo=ult;
	}
	public int getUltimo() {
		return ultimo;
	}
	public void reiniciar() {
		ide=0;
		pausa=false;
		mario.setEstado(true);
		mario.reiniciarPos();
		barriles.clear();
	}
	public boolean estado() {
		return pausa;
	}
}
