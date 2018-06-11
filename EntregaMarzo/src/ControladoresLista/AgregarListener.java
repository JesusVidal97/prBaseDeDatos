package ControladoresLista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import excepciones.DuplicateFieldException;
import excepciones.DuplicateTableException;
import panelAgregarCampo.FrameAgregarCampo;
import panelAgregarRegistro.FrameAgregarRegistro;
import panelAgregarTabla.AgregarTabla;
import panelAgregarTabla.FrameAgregarTabla;

/**
 * Listener para agregar
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class AgregarListener implements ActionListener {

	private Panel panel;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;

	/**
	 * Constructor del listener
	 * @param panel panel de la aplicacion principal
	 */
	public AgregarListener(Panel panel) {
		this.panel = panel;
		lista = panel.getLista();
	}
	/**
	 * Dependiendo de la fuente del evento, añadira una base de datos, una tabla, un campo o un registro
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(panel.getMntmAgregarBaseDeDatos())) {
			
			boolean existe = false;
			String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre de la base de datos", "Agregar BD",
					3);
			if (nombre != null) {
				for (int i = 0; i < lista.size() && existe == false; i++) {
					if (lista.get(i).getNombre().equals(nombre)) {
						existe = true;
						JOptionPane.showMessageDialog(null, "La base de datos ya existe", "Error", 0);
					}
				}

				if (nombre.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio", "Error", 0);
					existe = true;
				}

				if (existe == false) {
					lista.add(new BaseDeDatos(nombre));
					panel.getCbBaseDeDatos().addItem(nombre);
				}
			}
		}

		else if (e.getSource().equals(panel.getMntmAgregarTabla())) {
			FrameAgregarTabla at = new FrameAgregarTabla(panel);
		}

		else if (e.getSource().equals(panel.getMntmAgregarCampo())) {
			FrameAgregarCampo ac = new FrameAgregarCampo(panel);
		}

		else if (e.getSource().equals(panel.getMntmAgregarRegistro())) {
			FrameAgregarRegistro ag = new FrameAgregarRegistro(panel);
		}
	}

}
