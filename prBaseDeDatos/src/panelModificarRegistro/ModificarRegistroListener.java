package panelModificarRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Field;
import estructuraList.Tabla;
import excepciones.PrimaryKeyException;
import excepciones.RecordErrorException;
import excepciones.TypeErrorException;
import panelAgregarRegistro.AgregarRegistro;

/**
 * Listener para Modificar un Registro
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class ModificarRegistroListener implements ActionListener {

	private List<JLabel> etiquetas;
	private List<JTextField> campos;
	private ModificarRegistro panel;
	private Panel p;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;
	private List<Object> datos;

	/**
	 * Constructor del listener
	 * @param panel Panel de la aplicacion principal
	 * @param modificarRegistro Panel de ModificarRegistro
	 * @param datos Lista de Objetos que contienen los datos del registro a modificar
	 */
	public ModificarRegistroListener(List<Object> datos, Panel panel, ModificarRegistro modificarRegistro) {
		this.p = panel;
		this.panel = modificarRegistro;
		lista = panel.getLista();
		this.datos = datos;
		this.campos = this.panel.getCampos();
		BuscarBD();
		tabla = bd.searchTable(p.getCbTabla().getSelectedItem().toString());

	}

	/**
	 * Modifica el registro
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		boolean numero = true;
		List<Field> fields = tabla.getFields();

		for (int i = 0; i < campos.size(); i++) {
			if (fields.get(i).getType().equals("String")) {
				if (campos.get(i).getText().equals(""))
					datos.set(i, "null");
				else
					datos.set(i, campos.get(i).getText());
			} else if (fields.get(i).getType().equals("Integer")) {
				if (campos.get(i).getText().equals(""))
					datos.set(i, -9999);
				else
					try {
						datos.set(i, Integer.parseInt(campos.get(i).getText()));
					} catch (NumberFormatException e1) {
						numero = false;
					}
			}
		}
		if (numero == false)
			JOptionPane.showMessageDialog(null, "Los campos de tipo integer deben ser numeros", "Error", 0);

		else {
			ModeloFila mf = new ModeloFila(p, tabla);
			p.getTabla().setModel(mf);
			panel.getFrame().dispose();
		}
	}

	/**
	 * Busca la base de datos seleccionada
	 */
	private void BuscarBD() {
		boolean encontrado = false;
		for (int i = 0; i < lista.size() && encontrado == false; i++) {
			if (lista.get(i).getNombre().equals(p.getCbBaseDeDatos().getSelectedItem())) {
				bd = lista.get(i);
				encontrado = true;
			}
		}
	}
}
