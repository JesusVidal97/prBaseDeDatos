package panelModificarCampo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import VistaLista.Panel;
import estructuraList.Field;
import panelAgregarCampo.AgregarCampo;

/**
 * Frame de modificar campo
 * @author jesus
 * @see JFrame
 * @version 1.0
 */
public class FrameModificarCampo extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param panel Panel de la aplicacion principal
	 * @param f Campo a modificar
	 */
	public FrameModificarCampo(Panel panel, Field f) {
		this.setTitle("Modificar Campo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new ModificarCampo(panel, this, f);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		setContentPane(contentPane);
	}
}