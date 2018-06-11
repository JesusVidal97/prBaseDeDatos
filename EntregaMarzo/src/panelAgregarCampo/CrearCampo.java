package panelAgregarCampo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import VistaLista.ModeloFila;
import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import excepciones.DuplicateFieldException;

/**
 * Listener para Crear un campo
 * @author jesus
 * @see ActionListener
 * @version 1.0
 */
public class CrearCampo implements ActionListener {

	private Panel panel;
	private AgregarCampo panelAgr;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;

	/**
	 * Constructor del listener
	 * @param panel Panel de la aplicacion principal
	 * @param agregarCampo Panel de AgregarCampo
	 */
	public CrearCampo(Panel panel, AgregarCampo agregarCampo) {
		this.panel = panel;
		panelAgr = agregarCampo;
		lista = panel.getLista();
	}

	/**
	 * Crea un campo para la tabla
	 * @param e El evento a manejar
	 */
	public void actionPerformed(ActionEvent e) {
		BuscarBD();
		tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());
		if (!panelAgr.getTxtCampo().getText().equals("")) {
			String campo = panelAgr.getTxtCampo().getText();
			String tipo = panelAgr.getCbTipo().getSelectedItem().toString();
			try {
				tabla.addField(campo, tipo);
				for(int i = 0; i<tabla.getRecords().size(); i++) {
					List<Object> l = (List<Object>) tabla.getRecords().get(i);
					if(tipo.equals("String"))
						l.add("null");
					else if (tipo.equals("Integer"))
						l.add(-9999);
				}
				ModeloFila mf = new ModeloFila(panel, tabla);
				panel.getTabla().setModel(mf);
				panelAgr.getFrame().dispose();
			} catch (DuplicateFieldException e1) {
				JOptionPane.showMessageDialog(null, "Ya existe ese campo", "Error", 0);
			}
		}else {
			JOptionPane.showMessageDialog(null, "El campo es obligatorio", "Error", 0);
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
