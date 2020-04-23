package aplicacion;

import java.awt.Point;

/**
 * Esta clase define la escalera
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class Escalera implements Elemento{
	
	private int y1;
	private int y2;
	private int x;
	private BoxCollider collider;
	private String tipo;
	private Plataforma p1,p2;
	
	
	/**
	 * Constructor de la clase
	 * @param y1 punto inicial de la escalera
	 * @param y2 punto final de la escalera
	 * @param x punto en x en el cual se ubicará la escalera
	 */
	public Escalera(Plataforma p1,Plataforma p2,int x) throws ExcepcionEscalera{
		tipo="Escalera";
		int t1=p1.next(x, 0);
		int t2=p2.next(x, 0);
		if(t1==0 || t2 == 0) {
			new ExcepcionEscalera(ExcepcionEscalera.escaleraError);
		}
		this.y2=Math.max(t2, t1);
		this.y1=Math.min(t2, t1);
		this.x=x;
		
		if (t1<t2){
			this.p1=p1;
			this.p2=p2;
		}
		else {
			this.p2=p1;
			this.p1=p2;
			
		}
		collider = new BoxCollider(1,(y2-y1)+20);
		collider.setLocation(x+15,y1+(int)((y2-y1)/2)-30);
	}
	
	/**
	 * Devuelve las posiones de la escalera
	 * @return res arreglo con x,y1 y y2
	 */
	public int[] getPosiciones() {
		
		int[] res = new int[3];
		res[0]=x;
		res[1]=y1;
		res[2]=y2;
		
		return res;
	}

	public boolean enEscalera(BoxCollider col) {
		
		
		return (collider.intersects(col));
	}
	
	public BoxCollider getColaider() {
		
		return collider;
	}
	public int[] next(char dir,int x,int y,int speed) {
		
		
		if(dir=='+') {
			y-=speed;
			
		}
		else {
			y+=speed;
		}
		
		int[] pos = {x,y};
		
		if (y<y1) {
			pos[0]=x;
			pos[1]=y1;
		}else if(y>y2) {
			pos[0]=x;
			pos[1]=y2;
		}
		
		return pos;
	}
	public int  getY2() {
		return y2;
	}
	public int  getY1() {
		return y1;
	}
	@Override

	public String getTipo() {
		// TODO Auto-generated method stub
		return tipo;
	}
	public Plataforma getP1() {
		return p1;
	}
	public Plataforma getP2() {
		return p2;
	}
}
