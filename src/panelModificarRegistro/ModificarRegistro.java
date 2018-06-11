package panelModificarRegistro;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;
import panelAgregarRegistro.CrearRegistro;
import panelAgregarRegistro.FrameAgregarRegistro;

/**
 * Panel de modificar registro
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class ModificarRegistro extends JPanel {

	private JButton btnModificar;
	private FrameModificarRegistro frame;
	private List<BaseDeDatos> lista;
	private BaseDeDatos bd;
	private List<Tabla> tablas;
	private Tabla tabla;
	private JLabel lblDato;
	private JTextField txtDato;
	private Panel panel;
	private List<Object> datos;
	private List<JLabel> etiquetas = new LinkedList<JLabel>();
	private List<JTextField> campos = new LinkedList<JTextField>();

	/**
	 * Create the panel.
	 * 
	 * @param panel Panel de la aplicacion principal
	 * @param datos Lista de Objetos que contienen los datos del registro a modificar
	 * @param frameModificarRegistro Frame de ModificarRegistro
	 */
	public ModificarRegistro(List<Object> datos, Panel panel, FrameModificarRegistro frameModificarRegistro) {
		frame = frameModificarRegistro;
		lista = panel.getLista();
		this.panel = panel;
		etiquetas = new LinkedList<JLabel>();
		campos = new LinkedList<JTextField>();
		BuscarBD();
		this.datos = datos;

		tabla = bd.searchTable(panel.getCbTabla().getSelectedItem().toString());

		for (int i = 0; i < tabla.getFields().size(); i++) {
			lblDato = new JLabel(tabla.getFields().get(i).getName() + " (" + tabla.getFields().get(i).getType() + "):");
			lblDato.setBounds(50, 50 + i * 50, 200, 16);
			etiquetas.add(lblDato);

			txtDato = new JTextField();
			txtDato.setBounds(250, 50 + i * 50, 100, 22);
			txtDato.setText(datos.get(i).toString());
			campos.add(txtDato);

			if (i == tabla.getFields().size() - 1) {
				btnModificar = new JButton("Modificar");
				btnModificar.setBounds(198, 100 + i * 50, 97, 25);
				add(btnModificar);

				frame.setBounds(100, 100, 450, (200 + i * 50));
			}
		}
		campos.get(0).setEditable(false);
		
		for (int i = 0; i < etiquetas.size(); i++) {
			add(etiquetas.get(i));
			add(campos.get(i));
		}

		
		  ModificarRegistroListener mrl = new ModificarRegistroListener(datos, panel, this);
		  btnModificar.addActionListener(mrl);
		 
	}

	public List<JTextField> getCampos() {
		return campos;
	}

	public void setCampos(List<JTextField> campos) {
		this.campos = campos;
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

	public FrameModificarRegistro getFrame() {
		return frame;
	}
}
