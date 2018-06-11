package panelAgregarCampo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import VistaLista.Panel;

/**
 * Frame de agregar campo
 * @author jesus
 * @see JFrame
 * @version 1.0
 */
public class FrameAgregarCampo extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param panel Panel de la aplicacion principal
	 */
	public FrameAgregarCampo(Panel panel) {
		this.setTitle("Agregar Campo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new AgregarCampo(panel, this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		setContentPane(contentPane);
	}

}
