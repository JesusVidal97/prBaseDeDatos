package panelModificarTabla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.Tabla;

/**
 * Listener para modificar una Tabla
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class ModificarTablaListener implements ActionListener {

	private Tabla t;
	private ModificarTabla panel;
	private Panel p;
	/**
	 * Constructor del listener
	 * @param tabla Tabla a modificar
	 * @param modificarTabla Panel de ModificarTabla
	 * @param panel Panel de la aplicacion principal
	 */
	public ModificarTablaListener(Tabla tabla, ModificarTabla modificarTabla, Panel panel) {
		t = tabla;
		this.panel = modificarTabla;
		p = panel;
	}

	/**
	 * Modifica la tabla
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		boolean Modificar = true;
		if (!panel.getTxtNombre().getText().equals("") && !panel.getTxtCampo().getText().equals("")) {
			p.getCbTabla().removeItem(t.getName());
			t.setName(panel.getTxtNombre().getText());
			t.setKey(panel.getTxtCampo().getText());
			t.getFields().get(0).setName(panel.getTxtCampo().getText());
			p.getCbTabla().addItem(t.getName());
			p.getCbTabla().setSelectedItem(t.getName());
			Modificar = false;
			
			ModeloFila mf = new ModeloFila(p, t);
			p.getTabla().setModel(mf);
			
		} else {
			JOptionPane.showMessageDialog(null, "Los campos son obligatorios", "Error", 0);
		}

		if (Modificar == false)
			panel.getFrame().dispose();
	}

}
