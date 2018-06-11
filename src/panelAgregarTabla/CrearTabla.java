package panelAgregarTabla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import excepciones.DuplicateFieldException;
import excepciones.DuplicateTableException;

/**
 * Listener para Crear una Tabla
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class CrearTabla implements ActionListener{

	private Panel panel;
	private AgregarTabla panelAgr;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;
	
	/**
	 * Constructor del listener
	 * @param panel Panel de la aplicacion principal
	 * @param panelAgr Panel de AgregarTabla
	 */
	public CrearTabla(Panel panel, AgregarTabla panelAgr) {
		this.panelAgr = panelAgr;
		this.panel = panel;
		lista = panel.getLista();
	}

	/**
	 * Crea una tabla
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		BuscarBD();
		boolean crear = true;
		try {
			if(!panelAgr.getTxtNombre().getText().equals("") && !panelAgr.getTxtCampo().getText().equals("")) {
			String nombre = panelAgr.getTxtNombre().getText();
			String campo = panelAgr.getTxtCampo().getText();
			String tipo = panelAgr.getCbTipo().getSelectedItem().toString();
			tabla = new Tabla(nombre, campo, tipo);
			bd.addTable(tabla);
			panel.getCbTabla().addItem(panelAgr.getTxtNombre().getText());
			crear = false;
			}else {
				JOptionPane.showMessageDialog(null, "Los campos son obligatorios", "Error", 0);
			}
				
		} catch (DuplicateFieldException | DuplicateTableException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", 0);
		}
		if(crear == false)
		panelAgr.getFrame().dispose();
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
