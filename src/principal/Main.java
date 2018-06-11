package principal;

import java.util.LinkedList;
import java.util.List;

import VistaLista.*;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import excepciones.DuplicateFieldException;
import excepciones.DuplicateTableException;
import excepciones.PrimaryKeyException;
import excepciones.RecordErrorException;
import excepciones.TypeErrorException;;

/**
 * Clase que inicia la aplicacion
 * @author jesus
 * @version 1.0
 */

public class Main {

	public static void main(String[] args) throws PrimaryKeyException, TypeErrorException, RecordErrorException {
		// TODO Auto-generated method stub

		BaseDeDatos bd = null;
		List<BaseDeDatos> lista = new LinkedList<BaseDeDatos>();
		List<Object> r = new LinkedList<Object>();

		bd = new BaseDeDatos("prueba");

		try {
			Tabla tabla1 = new Tabla("Tabla1", "id", "Integer");
			tabla1.addField("primer campo", "String");
			r.add(1);
			r.add("Prueba");
			tabla1.addRecord(r);
			bd.addTable(tabla1);

			r = new LinkedList<Object>();
			Tabla tabla2 = new Tabla("Tabla2", "id", "Integer");
			
			tabla2.addField("Probando3", "Integer");
			tabla2.addField("hola2", "String");
			r.add(2);
			r.add(3);
			r.add("Hola");
			
			tabla2.addRecord(r);
			
			r = new LinkedList<Object>();
			r.add(1);
			r.add(19);
			r.add("Hola de nuevo");
			tabla2.addRecord(r);

			bd.addTable(tabla2);
			
		/*	Tabla tabla3 = new Tabla("Tabla2", "id", "Integer");
			
			bd.addTable(tabla3);*/
		} catch (DuplicateTableException | DuplicateFieldException ex) {
			ex.printStackTrace();
		}
		lista.add(bd);

		bd = new BaseDeDatos("prueba2");

		try {
			Tabla tabla3 = new Tabla("Tabla3", "id", "Integer");
			tabla3.addField("Probando2", "String");
			tabla3.addField("hola", "String");
			r = new LinkedList<Object>();
			r.add(3);
			r.add("meh");
			r.add("Hola");
			tabla3.addRecord(r);
			bd.addTable(tabla3);
			Tabla tabla4 = new Tabla("Tabla4", "id", "Integer");
			tabla4.addField("Probando4", "int");
			bd.addTable(tabla4);
			Tabla tabla5 = new Tabla("Tabla5", "id", "Integer");
			tabla5.addField("Probando5", "int");
			bd.addTable(tabla5);
			Tabla tabla6 = new Tabla("Tabla6", "id", "Integer");
			tabla6.addField("Probando6", "int");
			bd.addTable(tabla6);
		} catch (DuplicateTableException | DuplicateFieldException ex) {
			ex.printStackTrace();
		}

		lista.add(bd);
		Frame f = new Frame(lista);
		f.setVisible(true);
	}

}
