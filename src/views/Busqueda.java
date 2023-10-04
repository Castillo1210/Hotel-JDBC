package views;

import controller.HuespedesController;
import controller.ReservasController;
import modelo.Huespedes;
import modelo.Reservas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController = new ReservasController();
	private HuespedesController huespedesController = new HuespedesController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		listarReservas();
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		listarHuespedes();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = getjPanel();
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarRegistros();
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarRegistros();
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	private void modificarRegistros() {
		int filasReservas = tbReservas.getSelectedRow();
		int filasHuespedes = tbHuespedes.getSelectedRow();

		if (filasReservas >= 0) {

			int confirm = JOptionPane.showConfirmDialog(null, "Desea modificar el registro?", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				Reservas reservas = null;
				String fechaEntrada = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
				String fechaSalida = modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
				reservas = new Reservas(Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString()), java.sql.Date.valueOf(fechaEntrada), java.sql.Date.valueOf(fechaSalida), Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString()), (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4));
				reservasController.modificarReservas(reservas);
				JOptionPane.showMessageDialog(null, "Registro modificado con exito", "EXITO", JOptionPane.INFORMATION_MESSAGE);
				modelo.setRowCount(0);
				listarReservas();
			}
		} else if (filasHuespedes >= 0) {
			int confirm = JOptionPane.showConfirmDialog(null, "Desea modificar el registro", "ADVERTENCIA", JOptionPane.OK_CANCEL_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				Huespedes huespedes = null;
				String fechaNacimiento = modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
				huespedes = new Huespedes(Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString()), (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1), (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2), java.sql.Date.valueOf(fechaNacimiento), (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4), (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5), Long.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString()));
				huespedesController.modificarHuespedes(huespedes);
				JOptionPane.showMessageDialog(null, "Registro modificado con exito", "EXITO", JOptionPane.INFORMATION_MESSAGE);
				modeloHuesped.setRowCount(0);
				listarHuespedes();
			}
		}
	}

	private JPanel getjPanel() {
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelo.setRowCount(0);
				modeloHuesped.setRowCount(0);
				if (!Objects.equals(txtBuscar.getText(), "")) {
					try {
						Long num = Long.parseLong(txtBuscar.getText());

						var reservas = reservasController.buscarReservas(num);

						if (!reservas.isEmpty()) {
							reservas.forEach(reserva -> modelo.addRow(new Object[] {reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago()}));
						} else {
							JOptionPane.showMessageDialog(null, "No hay registros disponibles", "VACIO", JOptionPane.WARNING_MESSAGE);
							listarReservas();
						}
					} catch (NumberFormatException exception){
						var huespedes = huespedesController.buscarHuespedes(txtBuscar.getText());

						if (!huespedes.isEmpty()) {
							huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReservasId()}));
						} else {
							JOptionPane.showMessageDialog(null, "No hay registros disponibles", "VACIO", JOptionPane.WARNING_MESSAGE);
							listarHuespedes();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese el ID de busqueda", "VACIO", JOptionPane.WARNING_MESSAGE);
					listarReservas();
					listarHuespedes();
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		return btnbuscar;
	}

	private void listarReservas() {
		var reservas = reservasController.listarReservas();
		reservas.forEach(reserva -> modelo.addRow(new Object[] {reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago()}));
	}

	private void listarHuespedes() {
		var huespedes = huespedesController.listarHuespedes();
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReservasId()}));
	}

	private void eliminarRegistros() {
		int filaReservas = tbReservas.getSelectedRow();
		int filaHuespedes = tbHuespedes.getSelectedRow();

		if (filaReservas >= 0) {
			var reservas = tbReservas.getValueAt(filaReservas, 0).toString();
			int confirm = JOptionPane.showConfirmDialog(null, "Desea eliminar el registro seleccionada?");

			if (confirm == JOptionPane.YES_OPTION) {
				String val = tbReservas.getValueAt(filaReservas, 0).toString();
				reservasController.eliminarReservas(Long.valueOf(val));
				JOptionPane.showMessageDialog(null, "Registro eliminado con exito", "EXITO", JOptionPane.INFORMATION_MESSAGE);
				modelo.setRowCount(0);
				listarReservas();
			}
		} else if (filaHuespedes >= 0) {
			var huespedes = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
			int confirm = JOptionPane.showConfirmDialog(null, "Desea eliminar el registro seleccionado?");

			if (confirm == JOptionPane.YES_OPTION) {
				String val = tbHuespedes.getValueAt(filaHuespedes, 0).toString();
				huespedesController.eliminarHuespedes(Long.valueOf(val));
				JOptionPane.showMessageDialog(null, "Registro eliminado con exito", "EXITO", JOptionPane.INFORMATION_MESSAGE);
				modeloHuesped.setRowCount(0);
				listarHuespedes();
			}
		}
	}

	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
