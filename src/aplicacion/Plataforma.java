package aplicacion;

import java.util.ArrayList;
/**
 * Esta clase define la plataforma del juego
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Plataforma implements Elemento{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private double m,b;
	private ArrayList<Plataforma> vecinos = new ArrayList<Plataforma>();
	private ArrayList<Escalera> escaleras = new ArrayList<Escalera>();
	private String tipo;

	/**
	 * Constructor de la clase
	 * @param lowerEnd arreglo con x,y que representan la coordenada mas a la izquierda de la plataforma
	 * @param higherEnd arreglo con x,y que representan la coordenada mas a la derecha de la plataforma
	 */
	
	public Plataforma(int[] lowerEnd, int[] higherEnd) {
		tipo="Plataforma";
		x1=lowerEnd[0];
		y1=lowerEnd[1];
		x2=higherEnd[0];
		y2=higherEnd[1];
		m = ((double)y2-(double)y1)/((double)x2-(double)x1);
		b = -m*x1+y1;
		
		
	}
	/**
	 * Permite agregar una escalera a la plataforma que la unirá con otra
	 * @param vecino plataforma con la cual se va a unir
	 * @param escalera escalera que une ambas plataformas
	 */
	public void addEscalera(Plataforma vecino, Escalera escalera) {
		vecinos.add(vecino);
		escaleras.add(escalera);
	}
	/**
	 * Devuelve la pendiete y el punto b de la ecuacion de la recta correspondiente a la plataforma
	 * @return arreglo con m y b
	 */
	public double[] getEcu(){
		double [] ecuacion = {m,b};
		return ecuacion;
	}
	/**
	 * Devuelve x1, punto en x de la coordenada mas cercano a la izquierda
	 * @return x1
	 */
	public int[] getPuntoInicial() {
		int[] pos = {x1,y1}; 
		return pos;
	}

	/**
	 * Permite cambiar x1, punto en x de la coordenada mas cercano a la izquierda
	 * 
	 */
	public int  getY1() {
		return y1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	/**
	 * Devuelve x2, punto en x de la coordenada mas cercano a la derecha
	 * @return x2
	 */
	public int getY2() {
		return y2;
	}
	public int getX2() {
		return x2;
	}
	/**
	 * Permite cambiar x2, punto en x de la coordenada mas cercano a la derecha
	 * 
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	/**
	 * Permite obtener la pendiente de la recta correspondiente a la plataforma
	 * @return m
	 */
	public double getM() {
		return m;
	}
	/**
	 * Permite cambiar la pendiete de la recta correspondiente a la plataforma
	 * 
	 */
	public void setM(double m) {
		this.m = m;
	}
	/**
	 * Permite obtener el punto b de la recta correspondiente a la plataforma
	 * @return b
	 */
	public double getB() {
		return b;
	}
	/**
	 * Permite cambiar el punto b de la recta correspondiente a la plataforma
	 * 
	 */
	public void setB(double b) {
		this.b = b;
	}
	/**
	 * Dice si la posicion dada se encuentra en la plataforma
	 * @param x poisicion en x dada
	 * @param y posicion en y dada
	 * @return res true si la posicion está en la plataforma, false si no.
	 */
	public boolean encima(int x, int y) {
		if((x1<=x && x2>= x) && (y==(int)(m*x+b)|| y+1==(int)(m*x+b)|| y-1==(int)(m*x+b))) {
			
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Devuelve el punto x evaluado en la ecuacion de la recta asosiada a la plataforma
	 * @param x posicion en x dada
	 * @param y posicion en y dada
	 * @return res coordena x evaluada en la ecuacion de la recta
	 */
	public int next(int x, int y) {
		double res;
		res=m*x+b;

		return (int)res;
	}
	/**
	 * Retorna las escaleras de la plataforma
	 * @return escaleras ArrayList con las escaleras
	 */
	public ArrayList<Escalera> getEscaleras() {
		return escaleras;
	}
	/**
	 * Usando la posicion , el BoxCollider del objeto y la direccion a la cual se quiere mover
	 * se le consulta a la plataforma la siguiente posicion a la
	 * cual el objeto se debe mover
	 * @param x posicion en x del objeto a consultar
	 * @param y posicion en  del objeto a consultar
	 * @param col BoxCollider del objeto a consultar
	 * @param dir direccion en la que se quiere mover '+' arriba '-' abajo
	 * @return siguiente posicion en el eje y que el objeto debe tomar
	 */
	public Escalera nextEscalera(BoxCollider col) {
		Escalera res=null;
		for(Escalera e: escaleras) {
			if(e.enEscalera(col)) {
				res=e;
				break;
			}
		}
		return res;
	}
	
	public int[] next(char dir,int x,int y,int speed) {
		
		if (dir=='-') {
			x-=speed;
			y=(int)(m*x+b);
			
		}
		else {
			x+=speed;
			y=(int)(m*x+b);
		}
		int [] pos = {x,y};
		
		if (x<x1 || x>x2) {
			pos[0]=0;
			pos[1]=0;
		}
		
		
		return pos;
	}
	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}

}

