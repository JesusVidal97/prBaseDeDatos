package ControladoresLista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import excepciones.NoSuchElementException;
import excepciones.PrimaryKeyException;
import excepciones.RecordErrorException;

/**
 * Listener para eliminar
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class EliminarListener implements ActionListener {

	private Panel panel;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;
	private List<Object> datos;

	/**
	 * Constructor del listener
	 * @param panel panel de la aplicacion principal
	 */
	public EliminarListener(Panel panel) {
		this.panel = panel;
		lista = panel.getLista();
	}

	/**
	 * Dependiendo de la fuente del evento, eliminara una base de datos, una tabla, un campo o un registro
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		boolean BDeliminado = false;
		if (e.getSource().equals(panel.getMntmEliminarBaseDeDatos())) {

			for (int i = 0; i < lista.size() && BDeliminado == false; i++) {

				if (lista.get(i).getNombre().equals(panel.getCbBaseDeDatos().getSelectedItem())) {
					BDeliminado = true;
					if (JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar la base de datos  "
							+ panel.getCbBaseDeDatos().getSelectedItem() + "?", "Confirmar", 0) == 0) {

						lista.remove(i);
						panel.getCbBaseDeDatos().removeItem(panel.getCbBaseDeDatos().getSelectedItem());
						panel.getCbBaseDeDatos().setSelectedItem(null);
						panel.getCbTabla().removeAllItems();
						panel.getCbTabla().setEnabled(false);
						panel.getMntmAgregarTabla().setEnabled(false);
						panel.getMntmModificarBaseDeDatos().setEnabled(false);
						panel.getMntmEliminarBaseDeDatos().setEnabled(false);

					}
				}
			}
		}

		else if (e.getSource().equals(panel.getMntmEliminarTabla())) {

			boolean Teliminado = false;
			BuscarBD();
			tablas = bd.getTablas();

			for (int i = 0; i < tablas.size() && Teliminado == false; i++) {

				if (tablas.get(i).getName().equals(panel.getCbTabla().getSelectedItem())) {

					if (JOptionPane.showConfirmDialog(null,
							"¿Estas seguro de eliminar la tabla  " + panel.getCbTabla().getSelectedItem() + "?",
							"Confirmar", 0) == 0) {
						try {
							bd.removeTable(panel.getCbTabla().getSelectedItem().toString());
						} catch (NoSuchElementException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", 0);
						}
						panel.getCbTabla().removeItem(panel.getCbTabla().getSelectedItem());
						Teliminado = true;
					}
				}
			}
		}

		else if (e.getSource().equals(panel.getMntmEliminarCampo())) {

			boolean Celiminado = false;
			boolean CConfirmado = false;
			BuscarBD();
			tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());
			List<Object> datos = new LinkedList<Object>();
			String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del campo que quiere eliminar",
					"Eliminar campo", 3);

			if (nombre != null)
				if (JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el campo  " + nombre + "?",
						"Confirmar", 0) == 0) {
					try {
						tabla.removeField(nombre);
					} catch (java.util.NoSuchElementException | PrimaryKeyException | RecordErrorException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", 0);
					}

					ModeloFila mf = new ModeloFila(panel, tabla);
					panel.getTabla().setModel(mf);

				}
		}

		else if (e.getSource().equals(panel.getMntmEliminarRegistro())) {

			boolean Reliminado = false;
			boolean Rconfirmado = false;
			boolean numero = true;
			BuscarBD();
			tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());
			List<Object> datos = new LinkedList<Object>();
			String nombre = JOptionPane.showInputDialog(null,
					"Introduce el valor de la clave primaria del registro que quiere eliminar", "Eliminar registro", 3);

			if (nombre != null)
				for (int i = 0; i < tabla.getRecords().size(); i++) {

					datos = (List<Object>) tabla.getRecords().get(i);

					if (tabla.getFields().get(0).getType().equals("Integer")) {

						try {

							if (datos.get(0).equals(Integer.parseInt(nombre))) {
								Reliminado = true;
								if (JOptionPane.showConfirmDialog(null,
										"¿Estas seguro de eliminar el registro con clave primaria " + nombre + "?",
										"Confirmar", 0) == 0) {

									tabla.getRecords().remove(i);
								}
							}
						} catch (NumberFormatException e1) {
							numero = false;
						}
					}

					else if (tabla.getFields().get(0).getType().equals("String")) {

						if (datos.get(0).equals(nombre)) {
							Reliminado = true;
							if (JOptionPane.showConfirmDialog(null,
									"¿Estas seguro de eliminar el registro con clave primaria " + nombre + "?",
									"Confirmar", 0) == 0) {

								tabla.getRecords().remove(i);
							}
						}
					}
				}
		
			if (numero == false)
				JOptionPane.showMessageDialog(null, "El campo primario es Integer, debe introducir un numero", "Error",
						0);

			if (Reliminado == true) {
				ModeloFila mf = new ModeloFila(panel, tabla);
				panel.getTabla().setModel(mf);
			}
			
			else if (Reliminado == false && nombre != null && numero == true) {
				JOptionPane.showMessageDialog(null, "No existe ningun registro con esa clave primaria", "Error", 0);
			}
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
