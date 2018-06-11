package VistaLista;

import java.util.List;

import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import estructuraList.Tabla;

/**
 * El modelo de la tabla de la aplicacion principal
 * @author jesus
 * @see AbstractTableModel
 * @version 1.0
 */
public class ModeloFila extends AbstractTableModel {

	private Panel panel;
	private int columnas;
	private int filas;
	private Tabla tabla;
	Object[][] datos;
	private List<Object> registro;

	/**
	 * Primer constructor de ModeloFila
	 * @param panel Panel de la aplicacion principal
	 * @param tabla tabla que vaya a ser visualizada
	 */
	public ModeloFila(Panel panel, Tabla tabla) {
		this.tabla = tabla;
		this.columnas = this.tabla.getFields().size();
		this.filas = this.tabla.getRecords().size();
		datos = new Object[columnas][filas];
		getData();
	}

	/**
	 * Segundo constructor de ModeloFila
	 */
	public ModeloFila() {
	}

	/**
	 * Añade al array datos los datos de la tabla a visualizar
	 */
	public void getData() {
		for (int i = 0; i < filas; i++) {
			registro = (List<Object>) tabla.getRecords().get(i);
			for (int j = 0; j < columnas; j++) {
				if(registro.get(j).getClass().getSimpleName().equals("Integer") && registro.get(j).equals(-9999))
					datos[j][i] = "null";
				else
					datos[j][i] = registro.get(j);
			}
		}
	}

	/**
	 * Imprime el nombre de los campos de la tabla, que seran las columnas
	 * @param col El numero de la columna
	 */
	public String getColumnName(int col) {
		return tabla.getFields().get(col).getName();
	}

	/**
	 * Asigna el numero de columnas de la tabla
	 * @return El numero de columnas
	 */
	public int getColumnCount() {
		return columnas;
	}

	/**
	 * Asigna el numero de fila de la tabla
	 * @return El numero de filas
	 */
	public int getRowCount() {
		return filas;
	}

	/**
	 * Imprime los datos de la tabla
	 * @param fil El numero de la fila
	 * @param colum El numero de la columna
	 */
	public Object getValueAt(int fil, int colum) {
		return datos[colum][fil];
	}

}
