package estructuraList;

import estructuraList.Tabla;
import excepciones.*;

public interface DataBase {


	public int size();
	
	public boolean isEmpty();
	
	public void removeAll();
	
	public boolean equals(Object o);
	
	public String toString();
	
	public void addTable (Tabla t) throws DuplicateTableException;

	public void removeTable (String name) throws NoSuchElementException;

	public Tabla searchTable (String name);
	
	
}