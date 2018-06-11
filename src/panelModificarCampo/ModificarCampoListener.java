package panelModificarCampo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Field;
import estructuraList.Tabla;
import excepciones.DuplicateFieldException;
import panelAgregarCampo.AgregarCampo;

/**
 * Listener para modificar un campo
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class ModificarCampoListener implements ActionListener {

	private Panel p;
	private ModificarCampo panel;
	private Field f;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private Tabla t;

	/**
	 * Constructor del listener
	 * @param panel Panel de la aplicacion principal
	 * @param modificarCampo Panel de ModificarCampo
	 * @param f Campo a modificar
	 */
	public ModificarCampoListener(Panel panel, ModificarCampo modificarCampo, Field f) {
		p = panel;
		this.panel = modificarCampo;
		this.f = f;
		lista = p.getLista();
		BuscarBD();
		t = bd.searchTable(p.getCbTabla().getSelectedItem().toString());
	}

	/**
	 * Modifica el campo
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		if (!panel.getTxtCampo().getText().equals("")) {
			f.setName(panel.getTxtCampo().getText());
			ModeloFila mf = new ModeloFila(p, t);
			p.getTabla().setModel(mf);
			panel.getFrame().dispose();
		} else {
			JOptionPane.showMessageDialog(null, "El campo es obligatorio", "Error", 0);
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
