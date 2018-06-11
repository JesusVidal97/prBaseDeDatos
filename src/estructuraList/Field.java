package estructuraList;

import java.util.List;

/**
 * Clase que contiene el modelo de Field
 * @author jesus
 * @version 1.0
 */
public class Field {
	
	private String name;
	private String type;

	/**
	 * Constructor de la clase Field
	 * @param name Nombre del campo
	 * @param type Tipo del campo
	 */
	public Field(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * @return Nombre
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Nombre del campo
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Tipo
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type Tipo del campo
	 */
	public void setType(String type) {
		this.type = type;
	}
}
