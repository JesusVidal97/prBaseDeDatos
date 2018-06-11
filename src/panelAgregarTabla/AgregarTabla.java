package panelAgregarTabla;

import java.util.List;

import javax.swing.JPanel;

import VistaLista.Panel;
import estructuraList.BaseDeDatos;
import estructuraList.Tabla;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ControladoresLista.AgregarListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

/**
 * Panel de agregar tabla
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class AgregarTabla extends JPanel {

	
	private JTextField txtNombre;
	private JTextField txtCampo;
	private JComboBox<String> cbTipo;
	private JLabel lblCampoPrimario;
	private JLabel lbl;
	private JButton btnCrear;
	private FrameAgregarTabla frame;
	
	/**
	 * Create the panel.
	 * @param frameAgregarTabla Frame de agregar tabla
	 * @param panel Panel de la aplicacion principal
	 */
	public AgregarTabla(Panel panel, FrameAgregarTabla frameAgregarTabla) {
		frame = frameAgregarTabla;
		
		setLayout(null);
	
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(59, 58, 72, 16);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setToolTipText("");
		txtNombre.setBounds(127, 55, 175, 22);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		lblCampoPrimario = new JLabel("Campo Primario:");
		lblCampoPrimario.setBounds(59, 108, 110, 16);
		add(lblCampoPrimario);
		
		txtCampo = new JTextField();
		txtCampo.setBounds(186, 105, 116, 22);
		add(txtCampo);
		txtCampo.setColumns(10);
		
		lbl = new JLabel("Tipo:");
		lbl.setBounds(59, 158, 56, 16);
		add(lbl);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"String", "Integer"}));
		cbTipo.setBounds(127, 154, 175, 25);
		add(cbTipo);
		
		btnCrear = new JButton("Crear");
		btnCrear.setBounds(205, 207, 97, 25);
		add(btnCrear);
		
		CrearTabla ct = new CrearTabla(panel, this);
		btnCrear.addActionListener(ct);
		
	}

	public FrameAgregarTabla getFrame() {
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






