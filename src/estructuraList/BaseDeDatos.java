package estructuraList;


import java.util.LinkedList;

import java.util.List;

import excepciones.DuplicateTableException;
import excepciones.NoSuchElementException;

/**
 * Clase que contiene el modelo de BaseDeDatos
 * @author jesus
 * @see DataBase
 * @version 1.0
 */

public class BaseDeDatos implements DataBase {

	private String nombre;
	private List<Tabla> tablas;

	/**Contructor de Base de Datos
	 * 
	 * @param nombre Nombre de la base de datos
	 */
	
	public BaseDeDatos(String nombre) {
		this.nombre = nombre;
		tablas = new LinkedList<Tabla>();
	}

	/**
	 * Devuelve el numero de tablas de la base de datos
	 * 
	 * @return El numero de tablas de la base de datos
	 */
	public int size() {
		return tablas.size();
	}

	/**
	 * Devuelve si la base de datos esta vacio o no
	 * 
	 * @return true si la base de datos esta vacia, false en caso contrario
	 */
	public boolean isEmpty() {
		boolean vacio = false;
		if (tablas.size() == 0) {
			vacio = true;
		}
		return vacio;
	}

	/**
	 * Borra todos las tablas de la base de datos
	 */
	public void removeAll() {
		tablas = new LinkedList<Tabla>();
	}

	/**
	 * Setter de nombre
	 * @param nombre Nombre de la base de datos
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Añade una tabla a la base de datos
	 * 
	 * @param t La tabla que se quiere añadir
	 * @throws DuplicateTableException Si ya existia una tabla con el mismo nombre
	 */
	public void addTable(Tabla t) throws DuplicateTableException {
		for (int i = 0; i < tablas.size(); i++) {
			if (tablas.get(i).getName().equals(t.getName())) {
				throw new DuplicateTableException("Existe una tabla con el mismo nombre");
			}
		}
		tablas.add(t);
	}

	/**
	 * Elimina una tabla de la base de datos
	 * 
	 * @param name Nombre de la tabla
	 * @throws NoSuchElementException Si la tabla no existe
	 */
	public void removeTable(String name) throws NoSuchElementException {
		boolean existe = false;
		for (int i = 0; i < tablas.size() && existe == false; i++) {
			if (tablas.get(i).getName().equals(name)) {
				existe = true;
				tablas.remove(i);
			}
		}
		if (existe == false) {
			throw new NoSuchElementException("No existe ninguna tabla con ese nombre");
		}
	}

	/**
	 * Busca en la base de datos una tabla
	 * 
	 * @param name Nombre de la tabla
	 * @return t La tabla con el nombre introducido, null si no existe
	 */
	public Tabla searchTable(String name) {
		boolean existe = false;
		Tabla t = null;
		for (int i = 0; i < tablas.size() && existe == false; i++) {
			if (tablas.get(i).getName().equals(name)) {
				existe = true;
				t = tablas.get(i);
			}
		}
		return t;
	}

	/**
	 * getter de nombre
	 * @return Nombre de la base de datos
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * getter de tablas
	 * @return Lista de tabla de la base de datos
	 */
	public List<Tabla> getTablas() {
		return tablas;
	}

}