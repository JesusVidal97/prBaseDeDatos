package panelModificarCampo;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import VistaLista.Panel;
import estructuraList.Field;
import panelAgregarCampo.CrearCampo;
import panelAgregarCampo.FrameAgregarCampo;

/**
 * Panel de modificar campo
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class ModificarCampo extends JPanel {

	private JLabel lblCampo;
	private JTextField txtCampo;
	private JLabel lblTipo;
	private JComboBox<String> cbTipo;
	private JButton btnModificar;
	private FrameModificarCampo frame;
	private Field f;

	/**
	 * Create the panel
	 * @param panel Panel de la aplicacion principal
	 * @param frameModificarCampo Frame de ModificarCampo
	 * @param f Campo a modificar
	 */
	ModificarCampo(Panel panel, FrameModificarCampo frameModificarCampo, Field f) {
		frame = frameModificarCampo;
		this.f = f;

		setLayout(null);

		lblCampo = new JLabel("Campo:");
		lblCampo.setBounds(52, 54, 110, 16);
		add(lblCampo);

		txtCampo = new JTextField();
		txtCampo.setBounds(179, 51, 116, 22);
		txtCampo.setText(f.getName());
		add(txtCampo);
		txtCampo.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(52, 104, 56, 16);
		add(lblTipo);

		cbTipo = new JComboBox<String>();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] { "String", "Integer" }));
		cbTipo.setSelectedItem(f.getType());
		cbTipo.setEnabled(false);
		;
		cbTipo.setBounds(120, 100, 175, 25);
		add(cbTipo);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(198, 153, 97, 25);
		add(btnModificar);

		ModificarCampoListener mcl = new ModificarCampoListener(panel, this, f);
		btnModificar.addActionListener(mcl);
	}

	public JLabel getLblCampo() {
		return lblCampo;
	}

	public JTextField getTxtCampo() {
		return txtCampo;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public JComboBox<String> getCbTipo() {
		return cbTipo;
	}

	public JButton getBtnCrear() {
		return btnModificar;
	}

	public FrameModificarCampo getFrame() {
		return frame;
	}

}
