package panelModificarTabla;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import VistaLista.Panel;
import estructuraList.Tabla;

/**
 * Frame de modificar una Tabla
 * 
 * @author jesus
 * @see JFrame
 * @version 1.0
 */
public class FrameModificarTabla extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the Frame
	 * @param tabla Tabla a modificar
	 * @param panel Panel de la aplicacion principal
	 */
	public FrameModificarTabla(Tabla tabla, Panel panel) {
		this.setTitle("Modificar Tabla");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new ModificarTabla(tabla, this, panel);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.setVisible(true);
		setContentPane(contentPane);
	}

}
