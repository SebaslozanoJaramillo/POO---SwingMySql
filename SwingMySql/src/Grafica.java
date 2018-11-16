import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Grafica {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblresp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafica window = new Grafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Grafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 507, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/Universidad", "root", "");
					
					if(conexion != null) {
						Statement st = conexion.createStatement();
						lblresp.setText("Conexion a bd correcta");
					}else
						System.out.println("Conexion fallida.");
				}
				
				catch (SQLException e) {e.printStackTrace();}
				catch (ClassNotFoundException e) {e.printStackTrace();}
				catch (Exception e) {e.printStackTrace();}
			}
		});
		btnConectar.setBounds(10, 132, 89, 23);
		frame.getContentPane().add(btnConectar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");			
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Universidad", "root", "");
				
					Statement statement = conexion.createStatement();
					
					String nombre = textField.getText();
					((java.sql.Statement)statement).executeUpdate("Insert into alumnos(nombre) values('"+nombre+"')");
					conexion.close();
				}catch (ClassNotFoundException o) {
					o.printStackTrace();
				}catch (SQLException l) {
					l.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(133, 132, 89, 23);
		frame.getContentPane().add(btnAgregar);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(35, 73, 144, 14);
		frame.getContentPane().add(lblMatricula);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(textField_1.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");			
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Universidad", "root", "");
					Statement statement = conexion.createStatement();
					ResultSet resultSet = statement.executeQuery("Select nombre from alumnos where matricula="+id);
					
					if(resultSet.next() == true) {
						lblMatricula.setText(resultSet.getString("nombre"));	
					}
					
					conexion.close();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(255, 132, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(textField_1.getText());
				try {
					Class.forName("com.mysql.jdbc.Driver");			
					java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Universidad", "root", "");
					Statement statement = conexion.createStatement();
					
					String query = "Delete from alumnos where matricula ="+id;
					statement.executeUpdate(query);
					
					conexion.close();
			}catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
		});
		btnBorrar.setBounds(367, 132, 89, 23);
		frame.getContentPane().add(btnBorrar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(35, 23, 144, 14);
		frame.getContentPane().add(lblNombre);
		
		
		
		textField = new JTextField();
		textField.setBounds(206, 20, 250, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(206, 70, 250, 20);
		frame.getContentPane().add(textField_1);
		
		lblresp = new JLabel("");
		lblresp.setBounds(35, 195, 421, 75);
		frame.getContentPane().add(lblresp);
	}
}
