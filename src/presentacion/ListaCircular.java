package presentacion;

import java.util.ArrayList;
/**
 * Esta clase define una lista circular
 * @author David Coronado, Andres Gonzalez
 * @version 1.0
 *
 */
public class ListaCircular<E> extends ArrayList<E>
{

    int cont;
    public ListaCircular() {
    	cont=0;
    }
    public E next(int index) {
    	if (index == cont) {
    		cont++;
    	}
    	return next();
    }
    public E next()
    {
    	if (size()==cont) {
    		cont=0;
    	}
    	 cont+=1;
    	
        return super.get(cont-1);
       
    }
    public E inicial() {
    	return super.get(0);
    }
}
  