package panelAgregarRegistro;

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

/**
 * Listener para Crear un registro
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class CrearRegistro implements ActionListener {
	private List<JLabel> etiquetas;
	private List<JTextField> campos;
	private AgregarRegistro panelAgr;
	private Panel panel;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;

	/**
	 * Constructor del listener
	 * @param campos Lista de los JTextField del panel AgregarRegistro
	 * @param panel Panel de la aplicacion principal
	 * @param agregarRegistro Panel de Agregar Registro
	 */
	public CrearRegistro(List<JTextField> campos, Panel panel, AgregarRegistro agregarRegistro) {
		this.campos = campos;
		this.panel = panel;
		panelAgr = agregarRegistro;
		lista = panel.getLista();
	}

	/**
	 * Crea un registro para la tabla
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		boolean numero = true;
		boolean claveprimaria = true;
		BuscarBD();
		tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());
		List<Field> fields = tabla.getFields();
		List<Object> datos = new LinkedList<Object>();
		
		if(campos.get(0).getText().equals(""))
			claveprimaria = false;
		
		for (int i = 0; i < campos.size(); i++) {
			if (fields.get(i).getType().equals("String")) {
				if (campos.get(i).getText().equals(""))
					datos.add("null");
				else
					datos.add(campos.get(i).getText());
			} else if (fields.get(i).getType().equals("Integer")) {
				if (campos.get(i).getText().equals(""))
					datos.add(-9999);
				else
					try {
						datos.add(Integer.parseInt(campos.get(i).getText()));
					} catch (NumberFormatException e1) {
						numero = false;
					}
			}
		}
		if (numero == false)
			JOptionPane.showMessageDialog(null, "Los campos de tipo integer deben ser numeros", "Error", 0);
		else if (claveprimaria == false)
			JOptionPane.showMessageDialog(null, "La clave primaria no puede estar vacia", "Error", 0);
		else
			try {
				tabla.addRecord(datos);
				ModeloFila mf = new ModeloFila(panel, tabla);
				panel.getTabla().setModel(mf);
				panelAgr.getFrame().dispose();
			} catch (PrimaryKeyException | TypeErrorException | RecordErrorException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", 0);
			}
	}
	
	/**
	 * Busca la base de datos seleccionada
	 */
	private void BuscarBD() {
		boolean encontrado = false;
		for (int i = 0; i < lista.size() && encontrado == false; i++) {
			if (lista.get(i).getNombre().equals(panel.getCbBaseDeDatos().getSelectedItem())) {
				bd = lista.get(i);
				encontrado = true;
			}
		}
	}
}
