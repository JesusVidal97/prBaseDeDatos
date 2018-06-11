package panelAgregarRegistro;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import panelAgregarCampo.FrameAgregarCampo;

/**
 * Panel de agregar Registro
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class AgregarRegistro extends JPanel {

	private JButton btnCrear;
	private FrameAgregarRegistro frame;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;
	private JLabel lblDato;
	private JTextField txtDato;
	private Panel panel;

	/**
	 * Create the panel.
	 * 
	 * @param panel Panel de la aplicacion principal
	 * @param frameAgregarRegistro Frame de agregar registro
	 */
	public AgregarRegistro(Panel panel, FrameAgregarRegistro frameAgregarRegistro) {
		frame = frameAgregarRegistro;
		lista = panel.getLista();
		this.panel = panel;
		List<JLabel> etiquetas = new LinkedList<JLabel>();
		List<JTextField> campos = new LinkedList<JTextField>();
		BuscarBD();

		tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());


		for (int i = 0; i < tabla.getFields().size(); i++) {
			lblDato = new JLabel(tabla.getFields().get(i).getName() + " (" + tabla.getFields().get(i).getType() + "):");
			lblDato.setBounds(50, 50 + i * 50, 200, 16);
			etiquetas.add(lblDato);

			txtDato = new JTextField();
			txtDato.setBounds(250, 50 + i * 50, 100, 22);
			campos.add(txtDato);

			if (i == tabla.getFields().size() - 1) {
				btnCrear = new JButton("Crear");
				btnCrear.setBounds(198, 100 + i * 50, 97, 25);
				add(btnCrear);
				
				frame.setBounds(100, 100, 450, (200 + i * 50));
			}
		}
		
		for (int i = 0; i < etiquetas.size(); i++) {
			add(etiquetas.get(i));
			add(campos.get(i));
		}

		CrearRegistro cr = new CrearRegistro(campos, panel, this);
		btnCrear.addActionListener(cr);
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

	public FrameAgregarRegistro getFrame() {
		return frame;
	}
}
