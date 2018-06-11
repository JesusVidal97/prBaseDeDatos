package ControladoresLista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Field;
import estructuraList.Tabla;
import excepciones.NoSuchElementException;
import excepciones.PrimaryKeyException;
import excepciones.RecordErrorException;
import panelAgregarRegistro.FrameAgregarRegistro;
import panelModificarCampo.FrameModificarCampo;
import panelModificarRegistro.FrameModificarRegistro;
import panelModificarTabla.FrameModificarTabla;

/**
 * Listener para modificar
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class ModificarListener implements ActionListener {

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
	public ModificarListener(Panel panel) {
		this.panel = panel;
		lista = panel.getLista();
	}

	/**
	 * Dependiendo de la fuente del evento, modificara una base de datos, una tabla, un campo o un registro
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(panel.getMntmModificarBaseDeDatos())) {
			boolean BDmodificado = false;
			for (int i = 0; i < lista.size() && BDmodificado == false; i++) {

				if (lista.get(i).getNombre().equals(panel.getCbBaseDeDatos().getSelectedItem())) {
					BDmodificado = true;
					if (JOptionPane.showConfirmDialog(null, "¿Estas seguro de modificar la base de datos  "
							+ panel.getCbBaseDeDatos().getSelectedItem() + "?", "Confirmar", 0) == 0) {
						String nombre = JOptionPane.showInputDialog(null,
								"Introduce el nuevo nombre de la Base de Datos", "Modificar Base de Datos", 3);
						if (nombre != null) {
							BuscarBD();
							bd.setNombre(nombre);
							panel.getCbBaseDeDatos().removeItem(panel.getCbBaseDeDatos().getSelectedItem());
							panel.getCbBaseDeDatos().addItem(nombre);
							panel.getCbBaseDeDatos().setSelectedItem(nombre);
						}
					}
				}
			}
		}

		else if (e.getSource().equals(panel.getMntmModificarTabla())) {

			boolean Tmodificado = false;
			BuscarBD();
			tablas = bd.getTablas();

			for (int i = 0; i < tablas.size() && Tmodificado == false; i++) {

				if (tablas.get(i).getName().equals(panel.getCbTabla().getSelectedItem())) {
					Tmodificado = true;
					tabla = tablas.get(i);
					if (JOptionPane.showConfirmDialog(null,
							"¿Estas seguro de Modificar la tabla  " + panel.getCbTabla().getSelectedItem() + "?",
							"Confirmar", 0) == 0) {
						FrameModificarTabla fmt = new FrameModificarTabla(tabla, panel);
					}
				}
			}
		}

		else if (e.getSource().equals(panel.getMntmModificarCampo())) {
			Field f = null;
			boolean Cmodificado = false;
			boolean Pkey = false;
			Boolean Cvacio = true;
			BuscarBD();
			tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());
			List<Field> campos = tabla.getFields();
			List<Object> dato = new LinkedList<Object>();
			String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del campo que quiere modificar",
					"Modificar campo", 3);

			if (nombre != null) {
				if (nombre.equals(tabla.getKeyName())) {
					JOptionPane.showMessageDialog(null, "No puedes modificar el campo primario", "Error", 0);
					Pkey = true;

				} else {
					for (int i = 1; i < campos.size() && Cmodificado == false; i++) {
						if (campos.get(i).getName().equals(nombre)) {
							Cmodificado = true;
							f = campos.get(i);
							if (JOptionPane.showConfirmDialog(null,
									"¿Estas seguro de modificar el campo  " + nombre + "?", "Confirmar", 0) == 0) {
								for (int j = 0; j < tabla.getRecords().size() && Cvacio == true; j++) {
									dato = (List<Object>) tabla.getRecords().get(j);
									if ((dato.get(i).getClass().getSimpleName().equals("String")
											&& !dato.get(i).equals("null"))
											|| (dato.get(i).getClass().getSimpleName().equals("Integer")
													&& !dato.get(i).equals(-9999))) {
										Cvacio = false;
									}
								}
							}
						}
					}
				}
			}

			if (Cvacio == false) {
				JOptionPane.showMessageDialog(null, "No puedes modificar un campo con datos", "Error", 0);
			} 
			if (Cmodificado == true && Pkey ==false && Cvacio == true) {
				FrameModificarCampo fmc = new FrameModificarCampo(panel, f);
			}

			if (Cmodificado == false && nombre != null && Pkey == false)
				JOptionPane.showMessageDialog(null, "No existe ningun campo con ese nombre", "Error", 0);
		}

		else if (e.getSource().equals(panel.getMntmModificarRegistro())) {

			boolean Rmodificado = false;
			boolean Rconfirmado = false;
			boolean numero = true;
			BuscarBD();
			tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());
			List<Object> datos = new LinkedList<Object>();
			String nombre = JOptionPane.showInputDialog(null,
					"Introduce el valor de la clave primaria del registro que quiere modificar", "Modificar registro",
					3);

			if (nombre != null)
				for (int i = 0; i < tabla.getRecords().size() && Rmodificado == false; i++) {

					datos = (List<Object>) tabla.getRecords().get(i);

					if (tabla.getFields().get(0).getType().equals("Integer")) {

						try {

							if (datos.get(0).equals(Integer.parseInt(nombre))) {
								Rmodificado = true;
								if (JOptionPane.showConfirmDialog(null,
										"¿Estas seguro de modificar el registro con clave primaria " + nombre + "?",
										"Confirmar", 0) == 0) {
									Rconfirmado = true;
								}
							}
						} catch (NumberFormatException e1) {
							numero = false;
						}
					}

					else if (tabla.getFields().get(0).getType().equals("String")) {

						if (datos.get(0).equals(nombre)) {
							Rmodificado = true;
							if (JOptionPane.showConfirmDialog(null,
									"¿Estas seguro de modificar el registro con clave primaria " + nombre + "?",
									"Confirmar", 0) == 0) {
								Rconfirmado = true;
							}
						}
					}
				}

			if (numero == false)
				JOptionPane.showMessageDialog(null, "El campo primario es Integer, debe introducir un numero", "Error",
						0);

			if (Rconfirmado == true) {
				FrameModificarRegistro fmr = new FrameModificarRegistro(datos, panel);
			}

			else if (Rmodificado == false && nombre != null && numero == true) {
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
