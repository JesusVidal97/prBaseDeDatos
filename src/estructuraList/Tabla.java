package estructuraList;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import excepciones.*;

/**
 * Clase que contiene el modelo de Tabla
 *
 * @author jesus
 * @see List
 * @see Table
 * @version 1.0
 */
public class Tabla implements Table {

	private String name;
	private String key;
	private String type;
	private List<Field> fields;
	private List<List<Object>> records;
	boolean ref;

	/**
	 * Primer constructor de Tabla
	 *
	 * @param name Nombre de la tabla
	 * @param key Nombre del campo primario de la tabla
	 * @param type Tipo del campo primario de la tabla
	 * @param ref Indica si la tabla es creada por referencia o no
	 * @throws DuplicateFieldException Si el campo primario ya existe
	 */
	public Tabla(String name, String key, String type, boolean ref) throws DuplicateFieldException {
		this.name = name;
		this.key = key;
		this.type = type;
		this.ref = ref;
		fields = new LinkedList<Field>();
		records = new LinkedList<List<Object>>();
		addField(key, type);
	}

	/**
	 * Segundo constructor de Tabla, ref se inicia a true
	 *
	 * @param name Nombre de la tabla
	 * @param key Nombre del campo primario de la tabla
	 * @param type Tipo del campo primario de la tabla
	 * @throws DuplicateFieldException Si el campo primario ya existe
	 */
	public Tabla(String name, String key, String type) throws DuplicateFieldException {
		this.name = name;
		this.key = key;
		this.type = type;
		this.ref = true;
		fields = new LinkedList<Field>();
		records = new LinkedList<List<Object>>();
		addField(key, type);
	}

	/**
	 * Getter de name
	 * @return Nombre de la tabla
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter de name
	 * @param name Nombre de la tabla
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*/**
	 * getter de key
	 * @return El nombre del campo primario de la tabla
	 *
	public String getKey() {
		return key;
	}*/

	/**
	 * setter de key
	 * @param key El nombre del campo primario de la tabla
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/*/**
	 * getter de type
	 * @return El tipo del campo primario de la tabla
	 *
	public String getType() {
		return type;
	}*/
	
	/**
	 * setter de type
	 * @param type El tipo del campo primario de la tabla
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getter de fields
	 * @return La lista de campos de la tabla
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * setter de fields
	 * @param fields La lista de campos de la tabla
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * getter de records
	 * @return La lista de registros de la tabla
	 */
	public List getRecords() {
		return records;
	}

	/**
	 * setter de records
	 * @param records La lista de registros de la tabla
	 */
	public void setRecords(List records) {
		this.records = records;
	}


	/**
	 * setter de ref
	 * @param ref Si la tabla se crea por referencia o no
	 */
	public void setRef(boolean ref) {
		this.ref = ref;
	}

	/**
	 * Devuelve el numero de campos de la tabla
	 *
	 * @return El numero de campos
	 */
	public int size() {
		return fields.size();
	}

	/**
	 * Devuelve si la tabla esta vacio o no
	 *
	 * @return true si la tabla esta vacia, false en caso contrario
	 */
	public boolean isEmpty() {
		boolean vacio = false;
		if (fields.size() == 0) {
			vacio = true;
		}
		return vacio;
	}

	/**
	 * Elimina todos los registros de la tabla
	 */
	public void removeAll() {
		records = new LinkedList<>();
	}

	/**
	 * getter de ref
	 * @return ref Si la tabla se crea por referencia o no
	 */
	public boolean getRef() {
		return ref;
	}

	/**
	 * añade un campo a la tabla
	 *
	 * @param name nombre del campo
	 * @param type tipo del campo
	 * @throws DuplicateFieldException Si ya habia un campo con el mismo nombre
	 */
	public void addField(String name, String type) throws DuplicateFieldException {

		boolean existe = false;
		for (int i = 0; i < fields.size() && existe == false; i++) {
			if (fields.get(i).getName().equals(name)) {
				existe = true;
			}
		}
		if (existe == true) {
			throw new DuplicateFieldException("Ya hay un campo con ese nombre");
		} else {
			Field f = new Field(name, type);
			fields.add(f);

		}
	}

	/**
	 * Elimina un campo de la tabla
	 *
	 * @param name Nombre del campo
	 * @throws PrimaryKeyException Si el campo es el primario
	 * @throws NoSuchElementException Si no existe ningun campo con ese nombre
	 * @throws RecordErrorException Si tiene registros con datos
	 */
	public void removeField(String name) throws PrimaryKeyException, NoSuchElementException, RecordErrorException {
		Field f = null;
		List<Object> dato = new LinkedList<Object>();
		if (key.equals(name)) {
			throw new PrimaryKeyException("El campo es clave primaria");
		} else {
			for (int i = 0; i < fields.size() && f == null; i++) {
				if (fields.get(i).getName().equals(name)) {
					f = fields.get(i);
					for (int j = 0; j < records.size(); j++) {
						dato = records.get(j);
						if ((dato.get(i).getClass().getSimpleName().equals("String") && !dato.get(i).equals("null"))
						 || (dato.get(i).getClass().getSimpleName().equals("Integer") && !dato.get(i).equals(-9999)))
						throw new RecordErrorException("El campo tiene 1 o mas registros con datos");

					}
				}
			}
		}
		if (f == null)
			throw new NoSuchElementException("No existe ningun campo con ese nombre");
		else
			fields.remove(f);
	}

	

	/**
	 * añade un registro a la tabla
	 *
	 * @param r Lista de objetos que contienen los datos del registro
	 * @throws PrimaryKeyException Si ya existe un registro con la clave primaria del registro a introducir
	 * @throws TypeErrorException Si el tipo del campo y del dato del registro no coinciden
	 * @throws RecordErrorException Si no tiene el mismo nomero de datos que campos en la tabla 
	 */
	public void addRecord(List<Object> r) throws PrimaryKeyException, TypeErrorException, RecordErrorException {
		boolean error = false;
		List<Object> registro = new LinkedList<Object>();
		if (records.size() > 0) {
			for (int i = 0; i < records.size(); i++) {
				registro = records.get(i);
				if (registro.get(0).equals(r.get(0))) {
					throw new PrimaryKeyException("Clave primaria duplicada");
				}
			}

		} else if (fields.size() != r.size()) {
			throw new RecordErrorException("Hay mas registros que campos");
		} else {
			for (int i = 0; i < fields.size() && error == false; i++) {
				if (!fields.get(i).getType().equals(r.get(i).getClass().getSimpleName())) {
					throw new TypeErrorException("El tipo del registro y el campo no coinciden");
				}
			}
		}
		records.add(r);
	}

	/**
	 * Elimina un registro de la tabla
	 *
	 * @param o Registro a eliminar
	 */
	public void removeRecord(Object o) {
		if (records.contains(o)) {
			records.remove(o);
		}
	}

	/**
	 * Busca un registro en la tabla
	 *
	 * @param name 
	 * @param func
	 * @param value
	 * @return
	 * @throws RecordErrorException
	 */
	public Table searchRecords(String name, Comparator func, Object value) throws RecordErrorException {
		return null;
	}

	/**
	 * getter de key
	 * @return El nombre del campo primario
	 */
	public String getKeyName() {
		return key;
	}

	/**
	 * getter de type
	 * @return El tipo del campo primario
	 */
	public String getKeyType() {
		return type;
	}
}


