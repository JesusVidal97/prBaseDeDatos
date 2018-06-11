package panelModificarRegistro;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import VistaLista.Panel;

/**
 * Frame de modificar registro
 * @author jesus
 * @see JFrame
 * @version 1.0
 */
public class FrameModificarRegistro extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param panel Panel de la aplicacion principal
	 * @param datos Lista de Objetos que contienen los datos del registro a modificar
	 */
	public FrameModificarRegistro(List<Object> datos, Panel panel) {
		this.setTitle("Modificar Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new ModificarRegistro(datos, panel, this);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		setContentPane(contentPane);
	}

}
