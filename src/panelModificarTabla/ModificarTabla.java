package panelModificarTabla;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VistaLista.Panel;
import estructuraList.Tabla;

/**
 * Panel de modificar una Tabla
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class ModificarTabla extends JPanel {

	private JTextField txtNombre;
	private JTextField txtCampo;
	private JComboBox<String> cbTipo;
	private JLabel lblCampoPrimario;
	private JLabel lbl;
	private JButton btnCrear;
	private FrameModificarTabla frame;
	private Tabla t;
	
	/**
	 * Create the panel.
	 * @param panel Panel de la aplicacion principal
	 * @param frameModificarTabla Frame de ModificarTabla
	 * @param tabla Tabla a modificar 
	 */
	public ModificarTabla(Tabla tabla, FrameModificarTabla frameModificarTabla, Panel panel) {
		frame = frameModificarTabla;
		t = tabla;
		
		setLayout(null);
	
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(59, 58, 72, 16);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText(t.getName());
		txtNombre.setBounds(127, 55, 175, 22);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		lblCampoPrimario = new JLabel("Campo Primario:");
		lblCampoPrimario.setBounds(59, 108, 110, 16);
		add(lblCampoPrimario);
		
		txtCampo = new JTextField();
		txtCampo.setText(t.getKeyName());
		txtCampo.setBounds(186, 105, 116, 22);
		add(txtCampo);
		txtCampo.setColumns(10);
		
		lbl = new JLabel("Tipo:");
		lbl.setBounds(59, 158, 56, 16);
		add(lbl);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setEnabled(false);
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"String", "Integer"}));
		cbTipo.setSelectedItem(t.getKeyType());
		cbTipo.setBounds(127, 154, 175, 25);
		add(cbTipo);
		
		btnCrear = new JButton("Modificar");
		btnCrear.setBounds(205, 207, 97, 25);
		add(btnCrear);
		
		ModificarTablaListener mtl = new ModificarTablaListener(tabla, this, panel);
		btnCrear.addActionListener(mtl);
		
	}

	public FrameModificarTabla getFrame() {
		return frame;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtCampo() {
		return txtCampo;
	}

	public JComboBox<String> getCbTipo() {
		return cbTipo;
	}

	public JLabel getLblCampoPrimario() {
		return lblCampoPrimario;
	}

	public JLabel getLbl() {
		return lbl;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}
	
	
}


