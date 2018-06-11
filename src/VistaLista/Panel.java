package VistaLista;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import ControladoresLista.AgregarListener;
import ControladoresLista.EliminarListener;
import ControladoresLista.ModificarListener;
import ControladoresLista.Seleccionar;
import estructuraList.BaseDeDatos;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

/**
 * Panel de la aplicacion principal
 * 
 * @author jesus
 * @see JPanel
 * @version 1.0
 */
public class Panel extends JPanel {

	private JLabel lblBaseDeDatos;
	private JComboBox<String> cbBaseDeDatos;
	private JComboBox<String> cbTabla;
	private JLabel lblTabla;
	private JButton btnVer;
	private JMenuBar menuBar;
	private JMenu mnAgregar;
	private JMenuItem mntmAgregarCampo;
	private JMenuItem mntmAgregarRegistro;
	private JScrollPane scrollPane;
	private JTable tabla;
	private ModeloFila mf;
	private List<BaseDeDatos> lista;
	private JMenuItem mntmAgregarBaseDeDatos;
	private JMenuItem mntmAgregarTabla;
	private JMenuItem mntmEliminarBaseDeDatos;
	private JMenu mnEliminar;
	private JMenuItem mntmModificarRegistro;
	private JMenuItem mntmModificarCampo;
	private JMenuItem mntmModificarTabla;
	private JMenu mnModificar;
	private JMenuItem mntmModificarBaseDeDatos;
	private JMenuItem mntmEliminarTabla;
	private JMenuItem mntmEliminarCampo;
	private JMenuItem mntmEliminarRegistro;

	/**
	 * Create the panel.
	 * 
	 * @param lista
	 *            Lista de las bases de datos
	 */
	public Panel(List<BaseDeDatos> lista) {
		this.lista = lista;

		setLayout(null);

		lblBaseDeDatos = new JLabel("Base de Datos");
		lblBaseDeDatos.setBounds(26, 44, 99, 14);
		add(lblBaseDeDatos);

		cbBaseDeDatos = new JComboBox<String>();
		cbBaseDeDatos.setBounds(26, 64, 99, 20);
		cbBaseDeDatos.setActionCommand("BD");

		for (int i = 0; i < lista.size(); i++)
			cbBaseDeDatos.addItem(lista.get(i).getNombre());
		cbBaseDeDatos.setSelectedItem(null);
		add(cbBaseDeDatos);

		cbTabla = new JComboBox<String>();
		cbTabla.setEnabled(false);
		cbTabla.setActionCommand("TABLA");
		cbTabla.setBounds(135, 64, 99, 20);
		add(cbTabla);

		lblTabla = new JLabel("Tabla");
		lblTabla.setBounds(135, 44, 99, 14);
		add(lblTabla);

		btnVer = new JButton("Ver");
		btnVer.setEnabled(false);
		btnVer.setBounds(244, 63, 89, 22);
		add(btnVer);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 611, 21);
		add(menuBar);

		mnAgregar = new JMenu("Agregar");
		menuBar.add(mnAgregar);

		mntmAgregarBaseDeDatos = new JMenuItem("Agregar Base de Datos");
		mnAgregar.add(mntmAgregarBaseDeDatos);

		mntmAgregarTabla = new JMenuItem("Agregar Tabla");
		mntmAgregarTabla.setEnabled(false);
		mnAgregar.add(mntmAgregarTabla);

		mntmAgregarCampo = new JMenuItem("Agregar Campo");
		mntmAgregarCampo.setEnabled(false);
		mnAgregar.add(mntmAgregarCampo);

		mntmAgregarRegistro = new JMenuItem("Agregar registro");
		mntmAgregarRegistro.setEnabled(false);
		mnAgregar.add(mntmAgregarRegistro);

		mnModificar = new JMenu("Modificar");
		menuBar.add(mnModificar);

		mntmModificarBaseDeDatos = new JMenuItem("Modificar Base De Datos");
		mntmModificarBaseDeDatos.setEnabled(false);
		mnModificar.add(mntmModificarBaseDeDatos);

		mntmModificarTabla = new JMenuItem("Modificar Tabla");
		mntmModificarTabla.setEnabled(false);
		mnModificar.add(mntmModificarTabla);

		mntmModificarCampo = new JMenuItem("Modificar Campo");
		mntmModificarCampo.setEnabled(false);
		mnModificar.add(mntmModificarCampo);

		mntmModificarRegistro = new JMenuItem("Modificar Registro");
		mntmModificarRegistro.setEnabled(false);
		mnModificar.add(mntmModificarRegistro);

		mnEliminar = new JMenu("Eliminar");
		menuBar.add(mnEliminar);

		mntmEliminarBaseDeDatos = new JMenuItem("Eliminar Base De Datos");
		mntmEliminarBaseDeDatos.setEnabled(false);
		mnEliminar.add(mntmEliminarBaseDeDatos);

		mntmEliminarTabla = new JMenuItem("Eliminar Tabla");
		mntmEliminarTabla.setEnabled(false);
		mnEliminar.add(mntmEliminarTabla);

		mntmEliminarCampo = new JMenuItem("Eliminar Campo");
		mntmEliminarCampo.setEnabled(false);
		mnEliminar.add(mntmEliminarCampo);

		mntmEliminarRegistro = new JMenuItem("Eliminar Registro");
		mntmEliminarRegistro.setEnabled(false);
		mnEliminar.add(mntmEliminarRegistro);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 113, 556, 291);
		add(scrollPane);

		tabla = new JTable();
		scrollPane.setViewportView(tabla);

		Seleccionar s = new Seleccionar(this);
		cbBaseDeDatos.addActionListener(s);
		cbTabla.addActionListener(s);
		btnVer.addActionListener(s);

		AgregarListener al = new AgregarListener(this);
		mntmAgregarBaseDeDatos.addActionListener(al);
		mntmAgregarTabla.addActionListener(al);
		mntmAgregarCampo.addActionListener(al);
		mntmAgregarRegistro.addActionListener(al);

		EliminarListener el = new EliminarListener(this);
		mntmEliminarBaseDeDatos.addActionListener(el);
		mntmEliminarTabla.addActionListener(el);
		mntmEliminarCampo.addActionListener(el);
		mntmEliminarRegistro.addActionListener(el);

		ModificarListener ml = new ModificarListener(this);
		mntmModificarBaseDeDatos.addActionListener(ml);
		mntmModificarTabla.addActionListener(ml);
		mntmModificarCampo.addActionListener(ml);
		mntmModificarRegistro.addActionListener(ml);
	}

	public JComboBox<String> getCbBaseDeDatos() {
		return cbBaseDeDatos;
	}

	public JComboBox<String> getCbTabla() {
		return cbTabla;
	}

	public JButton getBtnVer() {
		return btnVer;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public JMenu getMnAgregar() {
		return mnAgregar;
	}

	public JMenuItem getMntmAgregarCampo() {
		return mntmAgregarCampo;
	}

	public JMenuItem getMntmAgregarRegistro() {
		return mntmAgregarRegistro;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTabla() {
		return tabla;
	}

	public List<BaseDeDatos> getLista() {
		return lista;
	}

	public JMenuItem getMntmAgregarTabla() {
		return mntmAgregarTabla;
	}

	public JMenuItem getMntmEliminarBaseDeDatos() {
		return mntmEliminarBaseDeDatos;
	}

	public JMenu getMnEliminar() {
		return mnEliminar;
	}

	public JMenuItem getMntmModificarRegistro() {
		return mntmModificarRegistro;
	}

	public JMenuItem getMntmModificarCampo() {
		return mntmModificarCampo;
	}

	public JMenuItem getMntmModificarTabla() {
		return mntmModificarTabla;
	}

	public JMenu getMnModificar() {
		return mnModificar;
	}

	public JMenuItem getMntmModificarBaseDeDatos() {
		return mntmModificarBaseDeDatos;
	}

	public JMenuItem getMntmAgregarBaseDeDatos() {
		return mntmAgregarBaseDeDatos;
	}

	public JMenuItem getMntmEliminarTabla() {
		return mntmEliminarTabla;
	}

	public JMenuItem getMntmEliminarCampo() {
		return mntmEliminarCampo;
	}

	public JMenuItem getMntmEliminarRegistro() {
		return mntmEliminarRegistro;
	}

}
