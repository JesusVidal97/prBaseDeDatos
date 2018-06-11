package ControladoresLista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;

/**
 * Listener para seleccionar una base de datos y una tabla y visualizarla
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class Seleccionar implements ActionListener {

	private Panel panel;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;

	/**
	 * Constructor del listener
	 * @param panel panel de la aplicacion principal
	 */
	public Seleccionar(Panel panel) {
		this.panel = panel;
		lista = panel.getLista();
	}

	/**
	 * Dependiendo de la fuente del evento, seleccionara una base de datos, una tabla o la visualizara
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BD")) {
			if (panel.getCbTabla().getItemCount() != 0) {
				panel.getCbTabla().removeAllItems();
			}
			
			BuscarBD();
			tablas = bd.getTablas();
			for (int i = 0; i < tablas.size(); i++) {
				panel.getCbTabla().addItem(tablas.get(i).getName());
			}

			panel.getCbTabla().setEnabled(true);
			panel.getMntmAgregarTabla().setEnabled(true);
			panel.getMntmModificarBaseDeDatos().setEnabled(true);
			panel.getMntmEliminarBaseDeDatos().setEnabled(true);
			
			ModeloFila mf = new ModeloFila();
			panel.getTabla().setModel(mf);
			
		} else if (e.getActionCommand().equals("TABLA")) {
			if (panel.getCbTabla().getItemCount() != 0 && panel.getCbTabla().getSelectedItem()!=null) {
				tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());

				panel.getBtnVer().setEnabled(true);
				panel.getMntmAgregarCampo().setEnabled(true);
				panel.getMntmAgregarRegistro().setEnabled(true);
				panel.getMntmModificarTabla().setEnabled(true);
				panel.getMntmModificarCampo().setEnabled(true);
				panel.getMntmModificarRegistro().setEnabled(true);
				panel.getMntmEliminarTabla().setEnabled(true);
				panel.getMntmEliminarCampo().setEnabled(true);
				panel.getMntmEliminarRegistro().setEnabled(true);
				ModeloFila mf = new ModeloFila();
				panel.getTabla().setModel(mf);
			}else {
				panel.getBtnVer().setEnabled(false);
				panel.getMntmAgregarCampo().setEnabled(false);
				panel.getMntmAgregarRegistro().setEnabled(false);
				panel.getMntmModificarTabla().setEnabled(false);
				panel.getMntmModificarCampo().setEnabled(false);
				panel.getMntmModificarRegistro().setEnabled(false);
				panel.getMntmEliminarTabla().setEnabled(false);
				panel.getMntmEliminarCampo().setEnabled(false);
				panel.getMntmEliminarRegistro().setEnabled(false);
				ModeloFila mf = new ModeloFila();
				panel.getTabla().setModel(mf);
			}
		} else {
			ModeloFila mf = new ModeloFila(panel, tabla);
			panel.getTabla().setModel(mf);
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
