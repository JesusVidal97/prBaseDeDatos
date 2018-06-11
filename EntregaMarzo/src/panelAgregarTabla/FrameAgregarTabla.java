package panelAgregarTabla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import VistaLista.Panel;

/**
 * Frame de agregar tabla
 * @author jesus
 * @see JFrame
 * @version 1.0
 */
public class FrameAgregarTabla extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 * @param panel Panel de la aplicacion principal
	 */
	public FrameAgregarTabla(Panel panel) {
		this.setTitle("Agregar tabla");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new AgregarTabla(panel, this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		setContentPane(contentPane);
	}

}
