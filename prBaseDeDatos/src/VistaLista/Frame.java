package VistaLista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import estructuraList.BaseDeDatos;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Frame de la aplicacion principal
 * @author jesus
 * @see JFrame
 * @version 1.0
 */
public class Frame extends JFrame {

	private Panel contentPane;

	/**
	 * Create the frame.
	 * @param lista Lista de bases de datos
	 */
	public Frame(List<BaseDeDatos> lista) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 458);
		contentPane = new Panel(lista);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setTitle("prBaseDeDatos");
		this.setResizable(false);
	}

}
