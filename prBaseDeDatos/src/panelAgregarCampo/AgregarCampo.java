package panelAgregarCampo;

import javax.swing.JPanel;
import javax.swing.JTextField;

import VistaLista.Panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Panel de agregar campo
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class AgregarCampo extends JPanel {

	private JLabel lblCampo;
	private JTextField txtCampo;
	private JLabel lblTipo;
	private JComboBox<String> cbTipo;
	private JButton btnCrear;
	private FrameAgregarCampo frame;

	/**
	 * Crea el panel
	 * @param panel
	 *            Panel de la aplicacion principal
	 * @param frameAgregarCampo
	 *            Frame de Agregar campo
	 */
	AgregarCampo(Panel panel, FrameAgregarCampo frameAgregarCampo) {
		frame = frameAgregarCampo;

		setLayout(null);

		lblCampo = new JLabel("Campo:");
		lblCampo.setBounds(52, 54, 110, 16);
		add(lblCampo);

		txtCampo = new JTextField();
		txtCampo.setBounds(179, 51, 116, 22);
		add(txtCampo);
		txtCampo.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(52, 104, 56, 16);
		add(lblTipo);

		cbTipo = new JComboBox<String>();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] { "String", "Integer" }));
		cbTipo.setBounds(120, 100, 175, 25);
		add(cbTipo);

		btnCrear = new JButton("Crear");
		btnCrear.setBounds(198, 153, 97, 25);
		add(btnCrear);

		CrearCampo cc = new CrearCampo(panel, this);
		btnCrear.addActionListener(cc);
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
		return btnCrear;
	}

	public FrameAgregarCampo getFrame() {
		return frame;
	}

}
