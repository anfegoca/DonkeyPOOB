package aplicacion;

public interface Elemento {
	public abstract int[] next(char dir,int x,int y,int speed);
	public abstract String getTipo();
	public abstract int getY2();
	public abstract int getY1();
}
